package example;

import java.util.Date;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
 
public class TestJob1 implements Job {
 public void execute(JobExecutionContext context) {
  System.out.println("TestJob1.execute() is Executed... : " + new Date());
 }
}