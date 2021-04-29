package kr.or.ddit.basic.quartzTest;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class MyObject implements Job{
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		
		//수행할 작업 명시(코딩)
		System.out.println("배치프로그램을 구동합니다.");
		
		
		throw new JobExecutionException();
	}
}


