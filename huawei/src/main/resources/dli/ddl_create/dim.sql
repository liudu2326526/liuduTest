DROP TABLE prod_dim.dim_mysql_mip_activity_scd_daily;

CREATE TABLE prod_dim.dim_mysql_mip_activity_scd_daily
(
    `id`               STRING COMMENT 'id',
    `name`             STRING COMMENT '活动名称',
    `user_id`          STRING COMMENT '用户id',
    `begin_date`       STRING COMMENT '开始日期',
    `end_date`         STRING COMMENT '结束日期',
    `type`             BIGINT COMMENT '类型 1-本品  2-竞品',
    `status`           BIGINT COMMENT '状态 1-未开始 2-进行中 3-已完成 4-已停止',
    `edit_cnt`         BIGINT COMMENT '进行中状态的编辑次数',
    `is_deleted`       BIGINT COMMENT '是否删除  1-未删除 2-已删除',
    `describe`         STRING COMMENT '任务描述',
    `object`           STRING COMMENT '活动监测:活动目标值',
    `stage`            STRING COMMENT '活动监测:活动监测阶段',
    `activity_type`    BIGINT COMMENT '监测类型 1 活动监测 2 热点监测 3 单帖监测 4  账号监测',
    `single_type`      BIGINT COMMENT '单贴: 1:立即监测 2预约监测',
    `single_cycle`     BIGINT COMMENT '单贴:监测周期 单位 秒',
    `single_frequency` BIGINT COMMENT '单贴:监测频率 单位:秒',
    `insight_source`   BIGINT COMMENT '监控个个平台 位或',
    `is_comment`       BIGINT COMMENT '单贴:是否需要评论 1:不需要 2:需要',
    `ext`              STRING COMMENT '扩展：json数据',
    `mtime`            BIGINT COMMENT '更新时间戳',
    `ctime`            BIGINT COMMENT '创建时间戳',
    `is_notice`        INT COMMENT '1 不提示 2 提示',
    `href`             STRING COMMENT '单贴监测，账号主页链接 or 内容链接'
) PARTITIONED BY (dt STRING)
    STORED AS ORC
    LOCATION 'obs://donson-mip-data/prod/dim/dim_mysql_mip_activity_scd_daily'
    TBLPROPERTIES ("orc.compression" = "SNAPPY");

DROP TABLE prod_dim.dim_mysql_mip_activity_account_config_scd_daily;

CREATE TABLE prod_dim.dim_mysql_mip_activity_account_config_scd_daily
(
    `id`             BIGINT,
    `activity_id`    STRING COMMENT '活动id',
    `source`         BIGINT COMMENT '媒体',
    `author_id`      STRING COMMENT '爬虫作者id',
    `dwd_author_id`  STRING COMMENT '数据组作者id',
    `name`           STRING COMMENT '名称',
    `budget`         BIGINT COMMENT '预算，单位分',
    `avatar`         STRING COMMENT '头像url',
    `fans_cnt`       BIGINT COMMENT '粉比数',
    `ctime`          BIGINT COMMENT '添加时间戳',
    `mtime`          BIGINT COMMENT '修改时间戳',
    `is_cooperation` BIGINT COMMENT '1:合作 2:不合作',
    `is_deleted`     BIGINT COMMENT '是否删除 1:否 2:是'
) PARTITIONED BY (dt STRING)
    STORED AS ORC
    LOCATION 'obs://donson-mip-data/prod/dim/dim_mysql_mip_activity_account_config_scd_daily'
    TBLPROPERTIES ("orc.compression" = "SNAPPY");

CREATE TABLE prod_dim.dim_mysql_mip_activity_content_config_scd_daily
(
    `id`             BIGINT,
    `activity_id`    STRING COMMENT '活动id',
    `source`         BIGINT COMMENT '媒体',
    `author_id`      STRING COMMENT '作者id',
    `content`        STRING COMMENT '内容',
    `avatar`         STRING COMMENT '头像url',
    `ctime`          BIGINT COMMENT '添加时间戳',
    `mtime`          BIGINT COMMENT '修改时间戳',
    `budget`         BIGINT COMMENT '该内容关联的作者预算 -1表示没设置预算',
    `is_deleted`     BIGINT COMMENT '是否删除 1:否 2:是',
    `author_name`    STRING COMMENT '作者名字',
    `content_type`   BIGINT COMMENT '作品类型 1视频 2 图文',
    `cover`          STRING COMMENT '封面',
    `kol_level`      BIGINT COMMENT '作者等级',
    `ori_content_id` STRING COMMENT '内容源id',
    `dwd_content_id` STRING COMMENT '内容id',
    `type`           BIGINT COMMENT '1 来自采集 2 来自数据组',
    `title`          STRING COMMENT '内容标题'
) PARTITIONED BY (dt STRING)
    STORED AS ORC
    LOCATION 'obs://donson-mip-data/prod/dim/dim_mysql_mip_activity_content_config_scd_daily'
    TBLPROPERTIES ("orc.compression" = "SNAPPY");


CREATE TABLE prod_dim.dim_mysql_mip_activity_platform_config_scd_daily
(
    `id`          BIGINT,
    `activity_id` STRING COMMENT '活动id',
    `budget`      BIGINT COMMENT '预算 -1表示没设置预算',
    `source`      BIGINT COMMENT '媒体',
    `include_kws` STRING COMMENT '源关键词',
    `exclude_kws` STRING COMMENT '非关键词',
    `ext`         STRING COMMENT '扩展：json数据',
    `update_at`   STRING COMMENT '修改时间',
    `create_at`   STRING COMMENT '创建时间',
    `is_deleted`  BIGINT COMMENT '是否删除 1:否 2:是',
    `sort`        BIGINT COMMENT '分组使用'
) PARTITIONED BY (dt STRING)
    STORED AS ORC
    LOCATION 'obs://donson-mip-data/prod/dim/dim_mysql_mip_activity_platform_config_scd_daily'
    TBLPROPERTIES ("orc.compression" = "SNAPPY");


CREATE EXTERNAL TABLE `prod_dim`.`dim_china_area`
(
    `id`   BIGINT,
    `country` STRING,
    `province` STRING,
    `city` STRING,
    `region` STRING
)
    ROW FORMAT DELIMITED FIELDS TERMINATED BY ','
    STORED AS TEXTFILE
    LOCATION 'obs://donson-mip-data/prod/dim/china_area'