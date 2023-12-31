// package com.yfh.bookstore.stateruserage;

// import java.util.ArrayList;
// import java.util.HashMap;
// import java.util.List;
// import java.util.Map;
// import java.util.Set;

// import org.quartz.CronScheduleBuilder;
// import org.quartz.CronTrigger;
// import org.quartz.JobBuilder;
// import org.quartz.JobDetail;
// import org.quartz.JobExecutionContext;
// import org.quartz.JobKey;
// import org.quartz.Scheduler;
// import org.quartz.SchedulerException;
// import org.quartz.Trigger;
// import org.quartz.TriggerBuilder;
// import org.quartz.TriggerKey;
// import org.quartz.impl.matchers.GroupMatcher;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.scheduling.quartz.QuartzJobBean;
// import org.springframework.stereotype.Service;

// @Service
// //@Transactional(rollbackFor = Exception.class)
// public class QuartzJobService {

//     @Autowired
//     private Scheduler scheduler;

//     public void buildJobTask(Class<? extends QuartzJobBean> jobClass, String cronStr, String jobName,
//             String jobGroupName) throws Exception {
     
//         CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronStr);
//         // 创建任务
//         JobDetail jobDetail = JobBuilder.newJob(jobClass).withIdentity(jobName, jobGroupName).storeDurably(true).build();
//         // 创建任务触发器
//         Trigger trigger = TriggerBuilder.newTrigger().withIdentity(jobName, jobGroupName).withSchedule(scheduleBuilder)
//                 .build();
//         // 将触发器与任务绑定到调度器内
//         scheduler.scheduleJob(jobDetail, trigger);

//         // 启动任务调度程序（内部机制是线程的启动） 
//         //scheduler.start();  //这里只构建任务，在外部启动任务 
//     }

//     /**
//      * 修改 一个job的 时间表达式
//      *
//      * @param jobName
//      * @param jobGroupName
//      * @param cronStr
//      */
//     public void updateJob(String jobName, String jobGroupName, String cronStr) {
//         try {
//             TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroupName);
//             CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
//             trigger = trigger.getTriggerBuilder().withIdentity(triggerKey)
//                     .withSchedule(CronScheduleBuilder.cronSchedule(cronStr)).build();
//             // 重启触发器
//             scheduler.rescheduleJob(triggerKey, trigger);
//         } catch (SchedulerException e) {
//             e.printStackTrace();
//         }
//     }

//     /**
//      * 删除任务一个job
//      *
//      * @param jobName 任务名称
//      * @param jobGroupName 任务组名
//      */
//     public void deleteJob(String jobName, String jobGroupName) {
//         try {
//             scheduler.deleteJob(new JobKey(jobName, jobGroupName));
//         } catch (Exception e) {
//             e.printStackTrace();
//         }
//     }

//     /**
//      * 暂停一个job
//      *
//      * @param jobName
//      * @param jobGroupName
//      */
//     public void pauseJob(String jobName, String jobGroupName) {
//         try {
//             JobKey jobKey = JobKey.jobKey(jobName, jobGroupName);
//             scheduler.pauseJob(jobKey);
//         } catch (SchedulerException e) {
//             e.printStackTrace();
//         }
//     }

//     /**
//      * 恢复一个job
//      *
//      * @param jobName
//      * @param jobGroupName
//      */
//     public void resumeJob(String jobName, String jobGroupName) {
//         try {
//             JobKey jobKey = JobKey.jobKey(jobName, jobGroupName);
//             scheduler.resumeJob(jobKey);
//         } catch (SchedulerException e) {
//             e.printStackTrace();
//         }
//     }

//     /**
//      * 立即执行一个job
//      *
//      * @param jobName
//      * @param jobGroupName
//      */
//     public void runJobNow(String jobName, String jobGroupName) {
//         try {
//             JobKey jobKey = JobKey.jobKey(jobName, jobGroupName);
//             scheduler.triggerJob(jobKey);
//         } catch (SchedulerException e) {
//             e.printStackTrace();
//         }
//     }

//     /**
//      * 获取所有计划中的任务列表
//      *
//      * @return
//      */
//     public List<Map<String, Object>> queryAllJob() {
//         List<Map<String, Object>> jobList = null;
//         try {
//             GroupMatcher<JobKey> matcher = GroupMatcher.anyJobGroup();
//             Set<JobKey> jobKeys = scheduler.getJobKeys(matcher);
//             jobList = new ArrayList<Map<String, Object>>();
//             for (JobKey jobKey : jobKeys) {
//                 List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);
//                 for (Trigger trigger : triggers) {
//                     Map<String, Object> map = new HashMap<>();
//                     map.put("jobName", jobKey.getName());
//                     map.put("jobGroupName", jobKey.getGroup());
//                     map.put("description", "触发器:" + trigger.getKey());
//                     Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
//                     map.put("jobStatus", triggerState.name());
//                     if (trigger instanceof CronTrigger) {
//                         CronTrigger cronTrigger = (CronTrigger) trigger;
//                         String cronExpression = cronTrigger.getCronExpression();
//                         map.put("jobTime", cronExpression);
//                     }
//                     jobList.add(map);
//                 }
//             }
//         } catch (SchedulerException e) {
//             e.printStackTrace();
//         }
//         return jobList;
//     }

//     /**
//      * 获取所有正在运行的job
//      *
//      * @return
//      */
//     public List<Map<String, Object>> queryRunJob() {
//         List<Map<String, Object>> jobList = null;
//         try {
//             List<JobExecutionContext> executingJobs = scheduler.getCurrentlyExecutingJobs();
//             jobList = new ArrayList<Map<String, Object>>(executingJobs.size());
//             for (JobExecutionContext executingJob : executingJobs) {
//                 Map<String, Object> map = new HashMap<String, Object>();
//                 JobDetail jobDetail = executingJob.getJobDetail();
//                 JobKey jobKey = jobDetail.getKey();
//                 Trigger trigger = executingJob.getTrigger();
//                 map.put("jobName", jobKey.getName());
//                 map.put("jobGroupName", jobKey.getGroup());
//                 map.put("description", "触发器:" + trigger.getKey());
//                 Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
//                 map.put("jobStatus", triggerState.name());
//                 if (trigger instanceof CronTrigger) {
//                     CronTrigger cronTrigger = (CronTrigger) trigger;
//                     String cronExpression = cronTrigger.getCronExpression();
//                     map.put("jobTime", cronExpression);
//                 }
//                 jobList.add(map);
//             }
//         } catch (SchedulerException e) {
//             e.printStackTrace();
//         }
//         return jobList;
//     }



// }