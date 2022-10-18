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
)
    PARTITIONED BY (dt STRING)
    STORED AS ORC
    LOCATION 'obs://donson-mip-data/prod/dim/dim_mysql_mip_activity_scd_daily'
    TBLPROPERTIES ("orc.compression" = "SNAPPY");