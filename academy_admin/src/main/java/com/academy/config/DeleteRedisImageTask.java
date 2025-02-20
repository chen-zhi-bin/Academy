package com.academy.config;

import com.academy.constant.Constant;
import com.academy.utils.OssUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Set;

@Configuration
@Slf4j
public class DeleteRedisImageTask {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private OssUtils ossUtils;

    /**
     * 每天上午两点清除无效图片
     */
    @Scheduled(cron = "0 0 2 * * ?")
    public  void clearImage(){
        log.debug("=====清除无效图片定时任务开始=====");
        // 1. 得到两个redis集合的差值（redis_all_upload_image， redis_db_upload_image）
        Set set = redisTemplate.opsForSet().difference(Constant.REDIS_ALL_UPLOAD_IMAGE, Constant.REDIS_DB_UPLOAD_IMAGE);
        for (Object filename : set) {
            log.debug("=====删除无效图片: " + filename + "=====");
            // 3. 调用oss的工具类删除图片
            ossUtils.deleteFile(filename.toString());
            // 4. 从redis_all_upload_image删除filename
            redisTemplate.opsForSet().remove("redis_all_upload_image", filename.toString());
        }
        log.debug("=====清除无效图片定时任务结束=====");
    }

}
