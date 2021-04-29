package example;

import java.util.Timer;
import java.util.TimerTask;

public class TimerTest {

	public static void main(String[] args) {
		Timer t1 = new Timer(); 						// Timer 객체
		TimerTask task = new TimerTask() {				// TimerTask 추상화 객체 -> run부분을 완성해줘야 객체생성 가능
			
			@Override
			public void run() {							// 실제로 수행할 작업을 기재
				System.out.println("첫번째 타이머 테스크");	// 여기서는 해당 문장을 출력해주는 작업을 하라고 기재한 것임.
				
			}
		};
														// run 작성완료
		t1.schedule(task, 0, 3000);						// Timer객체 t1의 schedule() 메서드를 이용해서 타이머 실행
			 											// schedule(TimerTask객체, Delay, Period)
		
		
		Timer t2 = new Timer();
		
		TimerTask task2 = new TimerTask() {
			
			@Override
			public void run() {
				System.out.println("두번째 타이머 테스크");
				throw new RuntimeException();			// 위에랑 똑같은데 테스트를 위해서 RuntimeException을 발생시킴.
			}
		};
		t1.schedule(task2, 1000, 1000);
		
		try {
			Thread.sleep(3000);							//이것도 테스트를 위해 넣어놓은거라 신경 안써도 됩니다.
		} catch (InterruptedException e) {
		}
	
		
		
		
	}
}
