#!/bin/bash

start_date=20220412
end_date=20220426
start_sec=`date -d "$start_date" "+%s"`
end_sec=`date -d "$end_date" "+%s"`
for((i=start_sec;i<=end_sec;i+=86400))
 do
 day=$(date -d "@$i" "+%Y-%m-%d")
    echo $(date "+%Y%m%d-%H:%M:%S")  "start inserting ……"
    impala-shell -q "truncate table default.snaptube_test_data_tmp;
                  INSERT OVERWRITE TABLE default.snaptube_test_data_tmp /*SA_BEGIN(default)*/ SELECT time,event,action,position_source,title,\$url,version_code,random_id,content_url,full_url,device_id,file_type,arg3,arg4,elapsed,file_size,config_result FROM events WHERE date='$day' AND ((event='Upgrade') OR (event='\$AppViewScreen' AND \$url='/compulsory_upgrade') OR (event='Click' AND action IN ('faq_title','faq_up','faq_down')) OR (event='Task' AND action IN ('start','ok') AND file_type='PATCH'))/*SA_END*/" &&
    echo $(date "+%Y%m%d-%H:%M:%S")  "start fetching to local ……"
    hadoop fs -get  hdfs://nameservice01/user/hive/warehouse/snaptube_test_data_tmp  /meta_nginx/liudu/snaptube_test_data_tmp &&
    echo $(date "+%Y%m%d-%H:%M:%S")  "done sycning……"
    aws s3 sync /meta_nginx/liudu/snaptube_test_data_tmp s3://sensors-access/liudu/snaptube_test_data_tmp/dt=$day &&
    echo $(date "+%Y%m%d-%H:%M:%S")  "start removing ……"
    sudo rm -rf  /meta_nginx/liudu/snaptube_test_data_tmp &&
    echo "$day start removing ……"
 done

 echo $(date "+%Y%m%d-%H:%M:%S")  "all done ……"
 break;
# nohup  ~/date_test.sh  > ~/date_test.log 2>&1 &
start_date=20201102
end_date=20201201
start_sec=`date -d "$start_date" "+%s"`
end_sec=`date -d "$end_date" "+%s"`
for((i=start_sec;i<=end_sec;i+=86400))
 do
 day=$(date -d "@$i" "+%Y-%m-%d")
    echo $(date "+%Y%m%d-%H:%M:%S")  "start inserting ……"
    impala-shell -q "truncate table default.zapee_sensor_event;
                  INSERT OVERWRITE TABLE default.zapee_sensor_event PARTITION(dt ='$day')/*SA_BEGIN(VidFun)*/ \
                  SELECT  * FROM events  \
                  WHERE  date ='$day'
                        AND ( (event='Follow' AND action IN('follow','unfollow','dislike'))  \
                       OR (event='Publish' AND action='upload.meta') \
                       OR (event='Publish' AND action='Publish.success')  \
                       OR (event='Click' AND action='ugc.entrance')  \
                       OR (event='Click' AND action='like.video')  \
                       OR (event='Comment')  \
                       OR (event='Click' AND action IN ('add.succeed','reply.succeed'))  \
                       OR (event='Share' AND action='success')  \
                       OR (event='Task' AND action IN ('ok','start'))  \
                       OR (event='Account' AND action IN ('login.third_part_login.succeed','login.succeed','login.failed'))  \
                       OR (event='Exposure' AND server_tag IS NOT NULL AND content_id IS NOT NULL)  \
                       OR (event='VideoPlay'AND action IN ('online_playback.play_stop','online_playback.video_start','online_playback.play_video'))  \
                     ) \
                  /*SA_END*/" &&
    echo $(date "+%Y%m%d-%H:%M:%S")  "start fetching to local ……"
    hadoop fs -get  hdfs://nameservice01/user/hive/warehouse/zapee_sensor_event/dt=$day  /meta_nginx/liudu/zapee_sensor_event &&
    echo $(date "+%Y%m%d-%H:%M:%S")  "start sycning……"
    aws s3 sync /meta_nginx/liudu/zapee_sensor_event/dt=$day s3://sensors-access/zhongweixiong/zapee_sensor_event/dt=$day &&
    echo $(date "+%Y%m%d-%H:%M:%S")  "start removing ……"
    sudo rm -rf  /meta_nginx/liudu/zapee_sensor_event/dt=$day &&
    echo "$day start removing ……"

 done

 echo $(date "+%Y%m%d-%H:%M:%S")  "all done ……"

 break;