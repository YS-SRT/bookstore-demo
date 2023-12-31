// package com.yfh.bookstore.stateruserage;

// import com.yfh.bookstore.config.Setting;

// import org.quartz.DisallowConcurrentExecution;
// import org.quartz.JobExecutionContext;
// import org.quartz.JobExecutionException;
// import org.springframework.amqp.rabbit.core.RabbitTemplate;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.scheduling.quartz.QuartzJobBean;

// import cn.hutool.core.date.DateUtil;


// @DisallowConcurrentExecution
// public class DelayMsgQuartzJob extends QuartzJobBean {

//     @Autowired
//     private RabbitTemplate rabbitTemplate;

//     @Override
//     protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
//         rabbitTemplate.convertAndSend(Setting.RABBITMQ_DELAY_EXCHANGE, Setting.RABBITMQ_DELAY_ROUTER,
//                 String.format("BuildJob Quartz Delay Msg,  Time: %s", DateUtil.date().toString()),
//                 message -> {
//                     message.getMessageProperties().setDelay(1000);
//                     return message;
//                 });
        
//     }
    
// }
