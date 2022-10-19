DROP TABLE prod_dwd.dwd_mip_author_detail_inc_hourly;

CREATE TABLE prod_dwd.dwd_mip_author_detail_inc_hourly
(
    `_id`              STRING,
    source             BIGINT,
    `type`             BIGINT,
    id                 STRING,
    author_id          STRING,
    avatar             STRING,
    account            STRING,
    name               STRING,
    keyword            STRING,
    identify           STRING,
    officially         STRING,
    officially_type    STRING,
    officially_details STRING,
    is_certified       STRING,
    intro              STRING,
    sign               STRING,
    category           STRING,
    home_page          STRING,
    tags               STRING,
    age                STRING,
    birthday           STRING,
    area               STRING,
    gender             STRING,
    industry           STRING,
    brand_cooperation  STRING,
    company            STRING,
    email              STRING,
    school             STRING,
    website            STRING,
    has_shop           STRING,
    has_live           STRING,
    ip_location        STRING,
    mcn                STRING,
    level              STRING,
    follow_cnt         BIGINT,
    fans_cnt           BIGINT,
    works_cnt          BIGINT,
    music_cnt          BIGINT,
    praised_cnt        BIGINT,
    forward_cnt        BIGINT,
    comment_cnt        BIGINT,
    `time`             BIGINT,
    ctime              BIGINT,
    mtime              BIGINT
)
    PARTITIONED BY (dt STRING,hour STRING)
    STORED AS ORC
    LOCATION 'obs://donson-mip-data/prod/dwd/author'
    TBLPROPERTIES ("orc.compression" = "SNAPPY");

DROP TABLE prod_dwd.dwd_mip_article_detail_inc_hourly;

CREATE TABLE prod_dwd.dwd_mip_article_detail_inc_hourly
(
    `_id`            STRING,
    source           BIGINT,
    id               STRING,
    article_id       STRING,
    author_id        STRING,
    title            STRING,
    content          STRING,
    share_url        STRING,
    keyword          STRING,
    `desc`           STRING,
    location         STRING,
    tags             STRING,
    topic            STRING,
    author_name      STRING,
    author_cover     STRING,
    forward_tid      STRING,
    cover            STRING,
    content_img_list STRING,
    post_source      STRING,
    relate_objects   STRING,
    source_url       STRING,
    words_cnt        BIGINT,
    play_cnt         BIGINT,
    video_list       STRING,
    video_cover_list STRING,
    last_update_time STRING,
    adorable_cnt     BIGINT,
    share_cnt        BIGINT,
    forward_cnt      BIGINT,
    collect_cnt      BIGINT,
    read_cnt         BIGINT,
    click_cnt        BIGINT,
    comment_cnt      BIGINT,
    diamond_cnt      BIGINT,
    reward_cnt       BIGINT,
    down_cnt         BIGINT,
    up_cnt           BIGINT,
    watching_cnt     BIGINT,
    `time`           BIGINT,
    ctime            BIGINT,
    mtime            BIGINT
)
    PARTITIONED BY (dt STRING,hour STRING)
    STORED AS ORC
    LOCATION 'obs://donson-mip-data/prod/dwd/article'
    TBLPROPERTIES ("orc.compression" = "SNAPPY");

DROP TABLE prod_dwd.dwd_mip_media_detail_inc_hourly;

CREATE TABLE prod_dwd.dwd_mip_media_detail_inc_hourly
(
    `_id`            STRING,
    source           BIGINT,
    id               STRING,
    author_id        STRING,
    media_id         STRING,
    cover            STRING,
    title            STRING,
    share_url        STRING,
    duration         STRING,
    status           STRING,
    keyword          STRING,
    `desc`           STRING,
    group_name       STRING,
    inner_group_name STRING,
    topic            STRING,
    tags             STRING,
    activity_tags    STRING,
    related_user     STRING,
    bg_music         STRING,
    author_name      STRING,
    author_cover     STRING,
    author_account   STRING,
    media_url        STRING,
    media_image_url  STRING,
    media_audio_url  STRING,
    adorable_cnt     BIGINT,
    comment_cnt      BIGINT,
    share_cnt        BIGINT,
    collect_cnt      BIGINT,
    play_cnt         BIGINT,
    coin_cnt         BIGINT,
    barrage_cnt      BIGINT,
    click_cnt        BIGINT,
    `rank`           BIGINT,
    `time`           BIGINT,
    ctime            BIGINT,
    mtime            BIGINT
)
    PARTITIONED BY (dt STRING,hour STRING)
    STORED AS ORC
    LOCATION 'obs://donson-mip-data/prod/dwd/media'
    TBLPROPERTIES ("orc.compression" = "SNAPPY");

DROP TABLE prod_dwd.dwd_mip_comment_detail_inc_hourly;

CREATE TABLE prod_dwd.dwd_mip_comment_detail_inc_hourly
(
    `_id`            STRING,
    source           BIGINT,
    id               STRING,
    item_id          STRING,
    comment_pid      STRING,
    comment_id       STRING,
    commentor_id     STRING,
    commentor_name   STRING,
    commentor_avatar STRING,
    content          STRING,
    device           STRING,
    adorable_cnt     BIGINT,
    down_cnt         BIGINT,
    comment_cnt      BIGINT,
    `time`           BIGINT,
    ctime            BIGINT,
    mtime            BIGINT
)
    PARTITIONED BY (dt STRING,hour STRING)
    STORED AS ORC
    LOCATION 'obs://donson-mip-data/prod/dwd/comment'
    TBLPROPERTIES ("orc.compression" = "SNAPPY");
