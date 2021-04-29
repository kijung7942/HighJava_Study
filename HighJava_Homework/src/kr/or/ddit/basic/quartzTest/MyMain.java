package kr.or.ddit.basic.quartzTest;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class MyMain {
	public static void main(String[] args){
		
		JobDetail j = JobBuilder.newJob(MyObject.class).build();    			// 실제 수행할 작업을 컴퓨터에게 알려주기 위해
																				// Job인터페이스를 임플먼트 한 MyObject 객체를 가지고 와서
																				// JobDetail 객체를 만드는 코드(JobBuilder 이용) 
		
		
		
																				// Trigger는 작업을 수행하는 시간, 주기 등을 명시한 객체
		Trigger t = TriggerBuilder.newTrigger()									// TriggerBuilder를 이용해서 쉽게 쓸수 있다(원래는 더 복잡함)						
					.withIdentity("SimpleTrigger")								// withIdentity -> 트리거를 구분하는 이름을 지어준다.
					.withSchedule(SimpleScheduleBuilder.simpleSchedule()		// withSchedule 부분이 실제적인 
									.withIntervalInSeconds(2).repeatForever())  // withIntervalInSeconds 반복주기(초)를 설정해준다.
					.build();
		
		Scheduler s;
		try {
			s = StdSchedulerFactory.getDefaultScheduler();
			s.start();																// 스케쥴러 쓰레드를 실행
			s.scheduleJob(j,t);														// 스케쥴러에 잡디테일과 트리거를 줘서 작업을 수행하도록 명령

		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}				// 기본값을 가지고 스케쥴러를 만들어준다.

	}
}
