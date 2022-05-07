CREATE TABLE default.snaptube_test_data_tmp (
  time TIMESTAMP,
  event STRING,
  action STRING,
  position_source STRING,
  title STRING,
  sa_url STRING,
  version_code DOUBLE,
  random_id DOUBLE,
  content_url STRING,
  full_url string,
  device_id string,
  file_type string,
  arg3 string,
  arg4 string,
  elapsed double,
  file_size double,
  config_result string
)
STORED AS PARQUET
LOCATION 'hdfs://nameservice01/user/hive/warehouse/snaptube_test_data_tmp'
;

CREATE TABLE test.snaptube_test_data_tmp (
  time TIMESTAMP,
  event STRING,
  action STRING,
  position_source STRING,
  title STRING,
  sa_url STRING,
  version_code DOUBLE,
  random_id DOUBLE,
  content_url STRING,
  full_url string,
  device_id string,
  file_type string,
  arg3 string,
  arg4 string,
  elapsed double,
  file_size double,
  config_result string
)
PARTITIONED BY (
  `dt` string)
STORED AS PARQUET
LOCATION 's3://sensors-access/liudu/snaptube_test_data_tmp/'