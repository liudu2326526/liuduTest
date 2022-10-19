-- 创建 obs 连接

CREATE
SERVER obs_server FOREIGN DATA WRAPPER dfs_fdw
    OPTIONS (
    address 'obs.cn-south-1.myhuaweicloud.com' ,
    ACCESS_KEY 'D4X34IUUCG7PRDHPZEN5',
    SECRET_ACCESS_KEY 'm4Wb41ZYQvJ5AVon7XnQ4T1MpsGIusXeZOzoYk1e',
    encrypt 'on',
    type 'obs'
    );

-- 创建 obs 连接

CREATE FOREIGN TABLE dwd.dwd_mip_author_detail_inc_hourly
(
    _id                TEXT,
    source             BIGINT,
    type               BIGINT,
    id                 TEXT,
    author_id          TEXT,
    avatar             TEXT,
    account            TEXT,
    name               TEXT,
    keyword            TEXT,
    identify           TEXT,
    officially         TEXT,
    officially_type    TEXT,
    officially_details TEXT,
    is_certified       TEXT,
    intro              TEXT,
    sign               TEXT,
    category           TEXT,
    home_page          TEXT,
    tags               TEXT,
    age                TEXT,
    birthday           TEXT,
    area               TEXT,
    gender             TEXT,
    industry           TEXT,
    brand_cooperation  TEXT,
    company            TEXT,
    email              TEXT,
    school             TEXT,
    website            TEXT,
    has_shop           TEXT,
    has_live           TEXT,
    ip_location        TEXT,
    mcn                TEXT,
    level              TEXT,
    follow_cnt         BIGINT,
    fans_cnt           BIGINT,
    works_cnt          BIGINT,
    music_cnt          BIGINT,
    praised_cnt        BIGINT,
    forward_cnt        BIGINT,
    comment_cnt        BIGINT,
    time               BIGINT,
    ctime              BIGINT,
    mtime              BIGINT,
    dt                 TEXT,
    hour               TEXT
) SERVER obs_server
OPTIONS (
format 'orc',
foldername '/donson-mip-data/prod/dwd/author',
encoding 'utf8',
totalrows '100000'
)
DISTRIBUTE BY ROUNDROBIN
PARTITION BY (dt,hour) AUTOMAPPED;
