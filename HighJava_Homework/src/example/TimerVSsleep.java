package example;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class TimerVSsleep {

	public static void main(String[] args) {
		Timer t = new Timer();
		TimerTask tt1 = new TimerTask() {
			
			@Override
			public void run() {
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//수행할 동작 명시(코딩)
				System.out.println("Hello Timer!");
				
				Date d = new Date();
				System.out.println(d);
			}
		};
		//(Timertask, 딜레이, 반복주기)
		System.out.println(new Date());
		t.schedule(tt1, 0, 4000);
	}
}


