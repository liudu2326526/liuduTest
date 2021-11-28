#!/usr/bin/env python
# -*- coding: utf-8 -*-

# ------------------------------------------------------------------------
# 数仓规范  https://docs.google.com/document/d/1c2l1pU3A4yaeohj8dMu59dshlvysBitWIfyWoZbGgS4
# FileName    __init__.py
# createTime  2021-11-26 22:49
# author      刘度
# 功能         
# 使用说明    python3 -m .__init__.py
# 参数说明
#             <DATE> 运行结束日期
#             <FROM> 开始数据日期的偏移量
#             <TO>   结束数据日期的偏移量
# date       author    version      desc
# ------------------------------------------------------------------------
# 2021-11-26     刘度      V01.00      init file
# 2021-11-26     刘度      V01.01      添加程序主体
# ------------------------------------------------------------------------
# 血缘关系  示例: [Athena]    [prod_raw.event]
# ------------------------------------------------------------------------
# source	[Athena]  [# TODO]
# sink    [S3]  []
# ------------------------------------------------------------------------
# 建表语句
# ------------------------------------------------------------------------
#
#
# ------------------------------------------------------------------------
import boto3

from common.env import Env
from util.log import log
from util.schedule import Schedule
from util.emr_containers import EMRContainers

schedule = Schedule()
emr_containers = EMRContainers()
s3 = boto3.client('s3')


@schedule.loop_call_method
def process(target_date, final_stage=False):
  env = Env.ENV
  emr_containers.attach(Env.EKS_CLUSTER_ID)
  log.info('processing date {}'.format(target_date))
  s3_output = ''

  query = """ 
    """.format(target_date)

  log.info(query)
  bucket = 'dywx-data'
  key = 'tmp/eks-sql/env={}/date={}/__init__.py.sql'.format(
      env, target_date)
  s3.put_object(Body=query, Bucket=bucket, Key=key)

  step_args = ['-o', s3_output,
               '-p', '40',
               '-f', 'sqlPath==s3://{bucket}/{key}'.format(bucket=bucket,
                                                           key=key)]

  step_name = ''
  spark_args = ['--conf', 'spark.executor.instances=10',
                '--conf', 'spark.executor.memory=8g',
                '--conf', 'spark.executor.cores=4',
                '--conf', 'spark.driver.cores=1']
  schedule.execute_query(type=6, query=query, cluster=emr_containers,
                         step_name=step_name, step_args=step_args, env=env,
                         spark_args=spark_args,
                         step_class='com.dayuwuxian.em.recommend.etl.processor.QueryProcessor',
                         application_project='_')

  if final_stage:
    log.info('update glue partition stage...')
    schedule.execute_query(
        type=12,
        table_name='_._',
        restrictions={'dt': target_date})


process(emr_containers, target_date=Env.DATE, FROM=Env.FROM, TO=Env.TO,
        POOL_SIZE=Env.POOL_SIZE)
