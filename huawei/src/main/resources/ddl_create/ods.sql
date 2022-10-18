-- prod_ods.ods_mip_author_raw

DROP TABLE prod_ods.ods_mip_author_raw;

CREATE TABLE prod_ods.ods_mip_author_raw
(
    data STRING
)
    PARTITIONED BY (dt STRING,hour STRING)
    STORED AS PARQUET
    LOCATION 'obs://donson-mip-data/prod/ods/author';

MSCK REPAIR TABLE prod_ods.ods_mip_author_raw;

-- prod_ods.ods_mip_comment_raw

DROP TABLE prod_ods.ods_mip_comment_raw;

CREATE TABLE prod_ods.ods_mip_comment_raw
(
    data STRING
)
    PARTITIONED BY (dt STRING,hour STRING)
    STORED AS PARQUET
    LOCATION 'obs://donson-mip-data/prod/ods/comment';

MSCK REPAIR TABLE prod_ods.ods_mip_comment_raw;

-- prod_ods.ods_mip_article_raw

DROP TABLE prod_ods.ods_mip_article_raw;

CREATE TABLE prod_ods.ods_mip_article_raw
(
    data STRING
)
    PARTITIONED BY (dt STRING,hour STRING)
    STORED AS PARQUET
    LOCATION 'obs://donson-mip-data/prod/ods/article';

MSCK REPAIR TABLE prod_ods.ods_mip_article_raw;

-- prod_ods.ods_mip_media_raw

DROP TABLE prod_ods.ods_mip_media_raw;

CREATE TABLE prod_ods.ods_mip_media_raw
(
    data STRING
)
    PARTITIONED BY (dt STRING,hour STRING)
    STORED AS PARQUET
    LOCATION 'obs://donson-mip-data/prod/ods/media';

MSCK REPAIR TABLE prod_ods.ods_mip_media_raw;

-- prod_ods.ods_mip_be_mysql_raw

DROP TABLE prod_ods.ods_mip_be_mysql_raw;

CREATE TABLE prod_ods.ods_mip_be_mysql_raw
(
    db      STRING,
    `table` STRING,
    data    STRING
)
    PARTITIONED BY (dt STRING)
    STORED AS PARQUET
    LOCATION 'obs://donson-mip-data/prod/ods/be_cdc';

MSCK REPAIR TABLE prod_ods.ods_mip_be_mysql_raw;