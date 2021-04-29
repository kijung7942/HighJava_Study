package example;
import java.text.ParseException;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
 
public class TestCronTrigger {
 private SchedulerFactory schedFact;
 private Scheduler sched;
 
 public TestCronTrigger() {
  try {
   // 스케쥴 생성후 시작
   schedFact = new StdSchedulerFactory();
   sched = schedFact.getScheduler();
   sched.start();
    
   // Job1 생성 
   JobDetail job1 = JobBuilder.newJob(TestJob1.class).build(); 
   //Trigger1 생성 - 크론 트리거
   Trigger trigger1 = TriggerBuilder.newTrigger().withSchedule(CronScheduleBuilder.cronSchedule("30 15 8 * * ?")).build(); 
   sched.scheduleJob(job1, trigger1);
    
   JobDetail job2 = JobBuilder.newJob(TestJob2.class).build();
   //Trigger2 생성 - 심플 트리거   
   Trigger trigger2 = TriggerBuilder.newTrigger().withSchedule(SimpleScheduleBuilder.repeatSecondlyForTotalCount(5, 1)).build();
   sched.scheduleJob(job2, trigger2);
   
  } catch (SchedulerException e) {
   e.printStackTrace();
  }
 }
 
 public static void main(String[] args) {
  new TestCronTrigger();
 }
}