// package com.yfh.bookstore.starter;

// import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

// import com.yfh.bookstore.stateruserage.DelayMsgQuartzJob;
// import com.yfh.bookstore.stateruserage.QuartzJobService;

// import org.junit.jupiter.api.Disabled;
// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.context.SpringBootTest;

// @SpringBootTest
// public class QuartzStarterTest {

//     @Autowired
//     private QuartzJobService quartzJobService;

//     @Test
//     //@Disabled
//     public void buildJobTaskTest() throws InterruptedException{
//         assertDoesNotThrow(()->quartzJobService.buildJobTask(DelayMsgQuartzJob.class, "0/1 * * * * ? ", "DelayMsgPerSecond", "DelayMsg"));
//         //quartzJobService.runJobNow("DelayMsgPerSecond", "DelayMsg");
//         Thread.sleep(1000 * 10); //等待10秒
        

//     }
    
// }
