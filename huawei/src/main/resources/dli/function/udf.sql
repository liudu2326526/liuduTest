DROP FUNCTION json_array_get_last

CREATE FUNCTION prod_dwd.json_array_get_last AS 'com.donson.udf.JsonArrayGetLast'
using jar 'obs://donson-mip-data/jar/udf/huawei-udf-1.0-SNAPSHOT.jar';

