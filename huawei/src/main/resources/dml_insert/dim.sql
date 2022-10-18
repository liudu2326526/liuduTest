-- spark.sql.shuffle.partitions = 1
-- prod_ods.ods_mip_be_mysql_raw to prod_dim.dim_mysql_mip_activity_scd_daily

INSERT OVERWRITE TABLE prod_dim.dim_mysql_mip_activity_scd_daily PARTITION (dt) distribute by rand()
SELECT id,
       name,
       user_id,
       begin_date,
       end_date,
       type,
       status,
       edit_cnt,
       is_deleted,
       `describe`,
       object,
       stage,
       activity_type,
       single_type,
       single_cycle,
       single_frequency,
       insight_source,
       is_comment,
       ext,
       mtime,
       ctime,
       is_notice,
       href,
       '2022-10-18' dt
FROM (
         SELECT *,
                row_number() over (partition by id ORDER BY log_time desc) rn
         FROM (
                  SELECT get_json_object(data, '$.id')               id,
                         get_json_object(data, '$.name')             name,
                         get_json_object(data, '$.user_id')          user_id,
                         from_unixtime(get_json_object(data, '$.begin_date') * 60 * 60 * 24,
                                       'yyyy-MM-dd')                 begin_date,
                         from_unixtime(get_json_object(data, '$.end_date') * 60 * 60 * 24,
                                       'yyyy-MM-dd')                 end_date,
                         get_json_object(data, '$.type')             type,
                         get_json_object(data, '$.status')           status,
                         get_json_object(data, '$.edit_cnt')         edit_cnt,
                         get_json_object(data, '$.is_deleted')       is_deleted,
                         get_json_object(data, '$.describe')         `describe`,
                         get_json_object(data, '$.object')           object,
                         get_json_object(data, '$.stage')            stage,
                         get_json_object(data, '$.activity_type')    activity_type,
                         get_json_object(data, '$.single_type')      single_type,
                         get_json_object(data, '$.single_cycle')     single_cycle,
                         get_json_object(data, '$.single_frequency') single_frequency,
                         get_json_object(data, '$.insight_source')   insight_source,
                         get_json_object(data, '$.is_comment')       is_comment,
                         get_json_object(data, '$.ext')              ext,
                         get_json_object(data, '$.mtime')            mtime,
                         get_json_object(data, '$.ctime')            ctime,
                         get_json_object(data, '$.is_notice')        is_notice,
                         get_json_object(data, '$.href')             href,
                         get_json_object(data, '$.log_time')         log_time
                  FROM prod_ods.ods_mip_be_mysql_raw
                  WHERE dt = '2022-10-18'
                    AND `table` = 'activity')
     )
WHERE rn = 1;


-- prod_ods.ods_mip_be_mysql_raw to prod_dim.dim_mysql_mip_activity_account_config_scd_daily

INSERT OVERWRITE TABLE prod_dim.dim_mysql_mip_activity_account_config_scd_daily PARTITION (dt)
SELECT id,
       activity_id,
       source,
       author_id,
       dwd_author_id,
       name,
       budget,
       avatar,
       fans_cnt,
       ctime,
       mtime,
       is_cooperation,
       is_deleted,
       '2022-10-18' dt
FROM (
         SELECT *,
                row_number() over (partition by id ORDER BY log_time desc) rn
         FROM (
                  SELECT get_json_object(data, '$.id')             id,
                         get_json_object(data, '$.name')           activity_id,
                         get_json_object(data, '$.source')         source,
                         get_json_object(data, '$.author_id')      author_id,
                         get_json_object(data, '$.dwd_author_id')  dwd_author_id,
                         get_json_object(data, '$.name')           name,
                         get_json_object(data, '$.budget')         budget,
                         get_json_object(data, '$.avatar')         avatar,
                         get_json_object(data, '$.fans_cnt')       fans_cnt,
                         get_json_object(data, '$.ctime')          ctime,
                         get_json_object(data, '$.mtime')          mtime,
                         get_json_object(data, '$.is_cooperation') is_cooperation,
                         get_json_object(data, '$.is_deleted')     is_deleted,
                         get_json_object(data, '$.log_time')       log_time
                  FROM prod_ods.ods_mip_be_mysql_raw
                  WHERE dt = '2022-10-18'
                    AND `table` = 'activity_account_config')
     )
WHERE rn = 1 ;

-- prod_ods.ods_mip_be_mysql_raw to prod_dim.dim_mysql_mip_activity_content_config_scd_daily

INSERT OVERWRITE TABLE prod_dim.dim_mysql_mip_activity_content_config_scd_daily PARTITION (dt)
SELECT id,
       activity_id,
       source,
       author_id,
       content,
       avatar,
       ctime,
       mtime,
       budget,
       is_deleted,
       author_name,
       content_type,
       cover,
       kol_level,
       ori_content_id,
       dwd_content_id,
       type,
       title,
       '2022-10-18' dt
FROM (
         SELECT *,
                row_number() over (partition by id ORDER BY log_time desc) rn
         FROM (
                  SELECT get_json_object(data, '$.id')             id,
                         get_json_object(data, '$.name')           activity_id,
                         get_json_object(data, '$.source')         source,
                         get_json_object(data, '$.author_id')      author_id,
                         get_json_object(data, '$.content')        content,
                         get_json_object(data, '$.avatar')         avatar,
                         get_json_object(data, '$.ctime')          ctime,
                         get_json_object(data, '$.mtime')          mtime,
                         get_json_object(data, '$.budget')         budget,
                         get_json_object(data, '$.is_deleted')     is_deleted,
                         get_json_object(data, '$.author_name')    author_name,
                         get_json_object(data, '$.content_type')   content_type,
                         get_json_object(data, '$.cover')          cover,
                         get_json_object(data, '$.kol_level')      kol_level,
                         get_json_object(data, '$.ori_content_id') ori_content_id,
                         get_json_object(data, '$.dwd_content_id') dwd_content_id,
                         get_json_object(data, '$.type')           type,
                         get_json_object(data, '$.title')          title,
                         get_json_object(data, '$.log_time')       log_time
                  FROM prod_ods.ods_mip_be_mysql_raw
                  WHERE dt = '2022-10-18'
                    AND `table` = 'activity_account_config')
     )
WHERE rn = 1