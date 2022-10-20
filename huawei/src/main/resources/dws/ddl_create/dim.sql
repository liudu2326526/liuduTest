-- dim.dim_author_scd_hourly

DROP TABLE dim.dim_author_scd_hourly;

CREATE TABLE IF NOT EXISTS dim.dim_author_scd_hourly
(
    md5_author_id      TEXT PRIMARY KEY COMMENT 'md5(concat(cast(source as varchar),author_id))',
    author_id          TEXT COMMENT '平台作者 id',
    source             INTEGER COMMENT '来源',
    avatar             TEXT COMMENT '作者头像',
    account            TEXT COMMENT '作者账号',
    name               TEXT COMMENT '作者名字',
    is_certified       INTEGER COMMENT '是否已认证,1-已认证，0-未认证',
    home_page          TEXT COMMENT '作者平台个人主页',
    identify           TEXT COMMENT '作者身份',
    officially         TEXT COMMENT '官方认证',
    officially_type    INTEGER COMMENT '认证类型; 0-未认证；1-网红达人（黄v）；2-企业机构（蓝v）',
    officially_details TEXT COMMENT '作者认证分类细节',
    intro              TEXT COMMENT '简介',
    sign               VARCHAR(255) COMMENT '签名',
    age                INTEGER COMMENT '年龄',
    birthday           VARCHAR(24) COMMENT '生日',
    area               varchar(64) COMMENT '地区',
    gender             INTEGER COMMENT '0=未知 1=男 2=女',
    industry           TEXT COMMENT '行业',
    brand_cooperation  TEXT COMMENT '合作品牌',
    company            VARCHAR(64) COMMENT '公司',
    email              VARCHAR(32) COMMENT '电子邮件',
    school             VARCHAR(64) COMMENT '学校',
    website            VARCHAR(255) COMMENT '网站',
    kol_level          INTEGER COMMENT 'kol 等级',
    gc_type            VARCHAR(6) COMMENT '内容类型',
    category           TEXT COMMENT '作者平台分类,如有多级分类，大在前',
    tags               TEXT COMMENT '作者个性化标签',
    has_shop           INTEGER COMMENT '0：没有店铺(橱窗), 1:有店铺(橱窗)',
    has_live           INTEGER COMMENT '0：没有直播, 1:有直播',
    ip_location        VARCHAR(2048) COMMENT 'ip属地',
    mcn                VARCHAR(2048) COMMENT '所属MCN',
    level              VARCHAR(2048) COMMENT '等级',
    country            VARCHAR(255) COMMENT '国家',
    province           VARCHAR(255) COMMENT '省份',
    city               VARCHAR(255) COMMENT '城市',
    ctime              BIGINT COMMENT '创建时间',
    mtime              BIGINT COMMENT '采集时间'
)
WITH (ORIENTATION = COLUMN, COLVERSION=2.0, ENABLE_DELTA = ON, COMPRESSION = LOW);

-- dim.dim_kol_level

DROP TABLE dim.dim_kol_level;

CREATE TABLE IF NOT EXISTS dim.dim_kol_level
(
    kol_level INTEGER NOT NULL COMMENT 'KOL等级值,1-头部KOL,2-肩部KOL;3-腰部KOL;4-尾部KOL;5-普通用户',
    source    INTEGER NOT NULL COMMENT '来源',
    name      TEXT COMMENT '等级名称',
    intro     TEXT COMMENT '等级描述',
    gc_type   VARCHAR(6) COMMENT '内容类型',
    min_num   INTEGER COMMENT '最小值',
    max_num   INTEGER COMMENT '最大值',
    CONSTRAINT pk PRIMARY KEY (kol_level, source)
)
WITH (ORIENTATION = COLUMN, COLVERSION=2.0, ENABLE_DELTA = ON);

-- dim.china_area

DROP FOREIGN TABLE dim.china_area;

CREATE FOREIGN TABLE dim.china_area
(
    id       INTEGER,
    country  char(50),
    province char(50),
    city     char(50),
    region   char(50)
) SERVER gsmpp_server
OPTIONS(
    LOCATION 'obs://donson-mip-data/prod/dim/china_area',
    FORMAT 'CSV' ,
    DELIMITER ',',
    encoding 'utf8',
    header 'false',
    ACCESS_KEY 'D4X34IUUCG7PRDHPZEN5',
    SECRET_ACCESS_KEY 'm4Wb41ZYQvJ5AVon7XnQ4T1MpsGIusXeZOzoYk1e',
    fill_missing_fields 'true',
    ignore_extra_data 'true'
)
READ ONLY