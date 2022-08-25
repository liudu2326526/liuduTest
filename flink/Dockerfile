FROM public.ecr.aws/docker/library/flink:1.13.2-scala_2.11

# Install dependencies
RUN set -ex; \
  apt-get update && \
  apt-get -y install \
    vim \
    awscli \
    procps \
    sudo \
    wget \
    curl; \
  rm -rf /var/lib/apt/lists/*

# Configure User Container
COPY ./cicd.sh $FLINK_HOME/
RUN chmod 777 $FLINK_HOME/cicd.sh
RUN mkdir -p $FLINK_HOME/usrlib

# Install Kube Env
RUN apt-get update && \
  apt-get install -y \
    apt-transport-https \
    ca-certificates;

RUN curl -fsSLo /usr/share/keyrings/kubernetes-archive-keyring.gpg https://packages.cloud.google.com/apt/doc/apt-key.gpg; \
  echo "deb [signed-by=/usr/share/keyrings/kubernetes-archive-keyring.gpg] https://apt.kubernetes.io/ kubernetes-xenial main" | sudo tee /etc/apt/sources.list.d/kubernetes.list;

RUN apt-get update && apt-get install -y kubectl

RUN curl -o aws-iam-authenticator https://amazon-eks.s3.us-west-2.amazonaws.com/1.19.6/2021-01-05/bin/linux/amd64/aws-iam-authenticator; \
  chmod a+x aws-iam-authenticator && \
  cp ./aws-iam-authenticator $FLINK_HOME/bin/aws-iam-authenticator && export PATH=$PATH:$FLINK_HOME/bin

# Add flink to sudo
RUN echo 'flink    ALL=(ALL)  NOPASSWD:ALL' >> /etc/sudoers

COPY ./bin/job/bootstrap_action.sh $FLINK_HOME/
RUN chmod 777 $FLINK_HOME/bootstrap_action.sh
RUN cat /docker-entrypoint.sh > $FLINK_HOME/tmp.sh && \
 cat $FLINK_HOME/bootstrap_action.sh > /docker-entrypoint.sh && \
 echo "\n" >> /docker-entrypoint.sh && \
 cat $FLINK_HOME/tmp.sh >> /docker-entrypoint.sh


RUN aws s3 cp s3://dywx-data/app/a-orc-core-1.5.6.jar $FLINK_HOME/lib/; \
  aws s3 cp s3://dywx-data/app/hive-exec-2.3.7-amzn-1.jar $FLINK_HOME/lib/; \
  aws s3 cp s3://dywx-data/app/hive-storage-api-2.6.0.jar $FLINK_HOME/lib/; \
  aws s3 cp s3://dywx-data/app/flink-shaded-hadoop-3-uber-3.1.1.7.1.1.0-565-9.0.jar $FLINK_HOME/lib/; \
  aws s3 cp s3://dywx-data/app/hadoop-hdfs-2.2.0.jar $FLINK_HOME/lib/; \
  aws s3 cp s3://dywx-data/app/flink-orc_2.11-1.13.2.jar $FLINK_HOME/lib/; \
  aws s3 cp s3://dywx-data/app/kubernetes/bigdata-flow-1.0.0.jar $FLINK_HOME/usrlib/bigdata-flow-1.0.0.jar; \
  aws s3 cp s3://dywx-data/app/kubernetes/log4j.properties $FLINK_HOME/conf/log4j-console.properties;

COPY ./src/main/resources/log4j.properties $FLINK_HOME/conf/log4j.properties