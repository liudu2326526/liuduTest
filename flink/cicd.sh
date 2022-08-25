#!/bin/bash

set -e
echo "start cicd script"
# 1. Get the parameters first
# 2. There is no parameter and then get it from the environment
temp=$1
ENV=${temp:-${ENV}}
if [[ "${ENV}" != "staging" ]] && [[ "${ENV}" != "prod" ]]; then
    echo "Parameter error, set it as the staging default parameter"
    ENV="staging"
fi

# set docker image
temp=$2
DOCKER_IMAGE=${temp:-${DOCKER_IMAGE}}

# set job name
temp=$3
NAME=${temp:-${NAME}}

# set parallelism
temp=$4
PARALLELISM=${temp:-${PARALLELISM}}

# set run main class
temp=$5
CLASS=${temp:-${CLASS}}

# set taskmanager slot number
temp=$6
TASK_SLOT=${temp:-${TASK_SLOT}}

# set taskmanager memory
temp=$7
TASKMANAGER_MEMORY=${temp:-${TASKMANAGER_MEMORY}}

# set taskmanager core
temp=$8
TASKMANAGER_CPU=${temp:-${TASKMANAGER_CPU}}

# set jobmanager memory
temp=$9
JOBMANAGER_MEMORY=${temp:-${JOBMANAGER_MEMORY}}

# set jobmanager core
temp=${10}
JOBMANAGER_CPU=${temp:-${JOBMANAGER_CPU}}

# Flink Global Params
FLINK_VERSION=1.13.2
SCALA_VERSION=2.11
CLUSTER_ID="bigdata-flow-${NAME}-${ENV}"
HA_STORAGE="s3a://dywx-data/prod/flink-ha/${CLUSTER_ID}/"
FLINK_HOME="/opt/flink"
SERVICE_ACCOUNT=mobiuspace-eks-flink

NAMESPACE="flink"
# Flink Taskmanager Core Params
TASKMANAGER_MEMORY_PROCESS_SIZE=${TASKMANAGER_MEMORY}gb
TASKMANAGER_MEMORY_JVM_METASPACE_SIZE=1gb
TASKMANAGER_MEMORY_JVM_OVERHEAD_MAX=2gb
TASKMANAGER_MEMORY_MANAGED_FRACTION=0.04
TASKMANAGER_MEMORY_NETWORK_FRACTION=0.3
TASKMANAGER_MEMORY_NETWORK_MAX=10gb
TASKMANAGER_NUMBER_OF_TASK_SLOTS=${TASK_SLOT}

set +e
kubectl get deployment ${CLUSTER_ID} -n ${NAMESPACE}
while [[ $? -eq 0 ]]
do
    #    kubectl port-forward service/${CLUSTER_ID}-rest 8081 8081
    #    JOB_ID=`${FLINK_HOME}/bin/flink list --target kubernetes-application -Dkubernetes.cluster-id=my-first-application-cluster -r | grep -r RUNNING | head -1 | awk -F " : " '{print $2}'`
    #    CMD="docker run -i -v /home/ec2-user/.aws/:/opt/flink/.aws ${DOCKER_IMAGE} bash -c 'aws eks update-kubeconfig --region us-east-1 --name emr-mobiuspace &&  \
    #      flink cancel --target kubernetes-application -Dkubernetes.namespace=${NAMESPACE} -Dkubernetes.cluster-id=${CLUSTER_ID} 00000000000000000000000000000000'"
    # double delete
    kubectl delete -n ${NAMESPACE} deployment ${CLUSTER_ID}
    kubectl delete -n ${NAMESPACE} cm flink-config-${CLUSTER_ID}
    kubectl get cm -n flink | grep ${CLUSTER_ID} | xargs kubectl -n flink cm delete
    sleep 10
    kubectl get cm flink-config-${CLUSTER_ID} -n ${NAMESPACE} || kubectl get deployment ${CLUSTER_ID} -n ${NAMESPACE}
done
sleep 10
set -e

echo "`date +'%Y-%m-%d %H:%M:%S'` Starting Flink Application: CLUSTER_ID=${CLUSTER_ID} NAMESPACE=${NAMESPACE}"

# submit the application
CMD="docker run -i -v /home/ec2-user/.aws/:/opt/flink/.aws ${DOCKER_IMAGE} bash -c 'aws eks update-kubeconfig --region us-east-1 --name emr-mobiuspace &&  \
flink run-application -d -p ${PARALLELISM} -t kubernetes-application \
-Dkubernetes.cluster-id=${CLUSTER_ID} \
-Dkubernetes.namespace=${NAMESPACE} \
-Dkubernetes.service-account=${SERVICE_ACCOUNT} \
-Dkubernetes.container.image=${DOCKER_IMAGE} \
-Dhigh-availability=org.apache.flink.kubernetes.highavailability.KubernetesHaServicesFactory \
-Dhigh-availability.storageDir=${HA_STORAGE} \
-Drest.flamegraph.enabled=true \
-Drestart-strategy=fixed-delay \
-Drestart-strategy.fixed-delay.attempts=20000 \
-Drestart-strategy.fixed-delay.delay=5s \
-Dcontainerized.master.env.ENABLE_BUILT_IN_PLUGINS=\"flink-s3-fs-hadoop-${FLINK_VERSION}.jar;flink-s3-fs-presto-${FLINK_VERSION}.jar\" \
-Dcontainerized.taskmanager.env.ENABLE_BUILT_IN_PLUGINS=\"flink-s3-fs-hadoop-${FLINK_VERSION}.jar;flink-s3-fs-presto-${FLINK_VERSION}.jar\" \
-Dmetrics.reporter.prom.class=org.apache.flink.metrics.prometheus.PrometheusReporter \
-Dmetrics.reporter.prom.port=9249 \
-Dmetrics.reporters=prom \
-Dweb.timeout=1200000 \
-Dkubernetes.jobmanager.cpu=${JOBMANAGER_CPU} \
-Djobmanager.memory.process.size=${JOBMANAGER_MEMORY}gb \
-Djobmanager.memory.off-heap.size=1gb \
-Djobmanager.memory.jvm-overhead.min=512m \
-Dkubernetes.transactional-operation.max-retrie=20 \
-Dkubernetes.taskmanager.cpu=${TASKMANAGER_CPU} \
-Dkubernetes.rest-service.exposed.type=NodePort \
-Dkubernetes.jobmanager.node-selector=eks.amazonaws.com/nodegroup:worker-x64-od \
-Dkubernetes.taskmanager.node-selector=eks.amazonaws.com/nodegroup:worker-x64 \
-Dtaskmanager.memory.process.size=${TASKMANAGER_MEMORY_PROCESS_SIZE} \
-Dtaskmanager.memory.jvm-metaspace.size=${TASKMANAGER_MEMORY_JVM_METASPACE_SIZE} \
-Dtaskmanager.memory.jvm-overhead.max=${TASKMANAGER_MEMORY_JVM_OVERHEAD_MAX} \
-Dtaskmanager.memory.managed.fraction=${TASKMANAGER_MEMORY_MANAGED_FRACTION} \
-Dtaskmanager.memory.network.fraction=${TASKMANAGER_MEMORY_NETWORK_FRACTION} \
-Dtaskmanager.memory.network.max=${TASKMANAGER_MEMORY_NETWORK_MAX} \
-Dtaskmanager.numberOfTaskSlots=${TASKMANAGER_NUMBER_OF_TASK_SLOTS} \
-Dtaskmanager.slot.timeout=60s \
-Dakka.ask.timeout=120s
-Denv.java.opts=\"-server -XX:+UseG1GC\" \
-c com.dayuwuxian.data.flow.Bootstrap \
local:///opt/flink/usrlib/bigdata-flow-1.0.0.jar --env ${ENV} --class ${CLASS}'"

eval ${CMD}

i=1
while [[ ${i} -le 10 ]]
do
    kubectl get all -o wide -n ${NAMESPACE}
    sleep 10
    i=`expr ${i} + 1`
done
