package com.yfh.bookstore.config;

import java.util.concurrent.atomic.AtomicInteger;

import com.yfh.bookstore.utils.RedisUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.TimeInterval;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@EnableScheduling
@Configuration
public class ScheduleConfig {

    private AtomicInteger incrInteger = new AtomicInteger();

    @Autowired
    private RedisUtil redisUtil;

    @Scheduled(initialDelay = 500, fixedDelay = 10000)
    public void publishChannelMessage() {
        redisUtil.sendChannelMsg(Setting.CHANNEL_TEST, String.format("Message No. %d", incrInteger.incrementAndGet()));
    }

    // @Scheduled(cron = "0/1 * * * * ? ")
    // public void do1() throws InterruptedException {
    //     TimeInterval timer = DateUtil.timer();
    //     Thread.sleep(1000 * 5);//模拟长时间执行，比如IO操作，http请求
    //     log.info("do1 success, 耗时= {}ms" + timer.interval());
    // }

    // @Scheduled(cron = "0/1 * * * * ? ")
    // public void do2() {
    //     log.info("do2 success, time: {}" , DateUtil.date().toString());
    // }
    
}
