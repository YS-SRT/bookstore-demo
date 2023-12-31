// package com.yfh.bookstore.config;


// import org.quartz.CronScheduleBuilder;
// import org.quartz.DisallowConcurrentExecution;
// import org.quartz.JobBuilder;
// import org.quartz.JobDetail;
// import org.quartz.JobExecutionContext;
// import org.quartz.JobExecutionException;
// import org.quartz.Trigger;
// import org.quartz.TriggerBuilder;
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
// import org.springframework.amqp.rabbit.core.RabbitTemplate;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.scheduling.quartz.QuartzJobBean;

// import cn.hutool.core.date.DateUtil;
// import lombok.extern.slf4j.Slf4j;

// @Slf4j
// @Configuration
// public class QuartzConfig {


//     /**
//      * 测试定时任务构建
//      *
//      * @return
//      */
//     @Bean
//     public JobDetail testTaskJobDetail() {
//         return JobBuilder.newJob(TestTask.class)
//                 .withIdentity(TestTask.class.getName())
//                 .storeDurably(true)
//                 .build();
//     }

//     /**
//      * 测试定时任务配置
//      *
//      * @return
//      */
//     @Bean
//     public Trigger testTaskTrigger() {
//         CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("0/1 * * * * ?");
//         return TriggerBuilder.newTrigger()
//                 .forJob(testTaskJobDetail())
//                 .withIdentity(TestTask.class.getName())
//                 .withSchedule(scheduleBuilder)
//                 .build();
//     }

//     @DisallowConcurrentExecution
//     private class TestTask extends QuartzJobBean {

//         @Autowired
//         private RabbitTemplate rabbitTemplate;
        
//         @Override
//         protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
//             log.debug("执行测试发送5s延时信息的定时任务");
//             rabbitTemplate.convertAndSend(Setting.RABBITMQ_DELAY_EXCHANGE, Setting.RABBITMQ_DELAY_ROUTER,
//                 String.format("ConfigJob Quartz Delay Msg,  CurrentTime: %s", DateUtil.date().toString()),
//                 message -> {
//                     message.getMessageProperties().setDelay(5 * 60 * 1000); //5 second
//                     return message;
//                 });
//         }

//     }
// }
