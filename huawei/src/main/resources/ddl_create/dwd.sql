CREATE TABLE prod_dwd.dwd_mip_author_detail_inc_hourly
(
    `_id`                 STRING,
    source                BIGINT,
    `type`                BIGINT,
    id                    STRING,
    author_id             STRING,
    avatar                STRING,
    account               STRING,
    name                  STRING,
    keyword               STRING,
    ip_location           STRING,
    level                 STRING,
    intro                 STRING,
    home_page             STRING,
    birthday              STRING,
    area                  STRING,
    gender                STRING,
    praised_cnt           BIGINT,
    forward_cnt           BIGINT,
    comment_cnt           BIGINT,
    follow_cnt            BIGINT,
    fans_cnt              BIGINT,
    works_cnt             BIGINT,
    report_data_author_id STRING,
    `time`                BIGINT,
    ctime                 BIGINT,
    mtime                 BIGINT,
    insert_time           BIGINT,
    page_source           STRING,
    is_done               BIGINT,
    storestatus           BIGINT
)
    PARTITIONED BY (dt STRING,hour STRING)
    STORED AS ORC
    LOCATION 'obs://donson-mip-data/prod/dwd/author'
    TBLPROPERTIES ("orc.compression" = "SNAPPY");

