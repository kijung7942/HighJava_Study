package example;

import java.util.Date;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
 
public class TestJob2 implements Job {
 public void execute(JobExecutionContext context) {
  System.out.println("TestJob2.execute() is Executed... : " + new Date());
 }
}