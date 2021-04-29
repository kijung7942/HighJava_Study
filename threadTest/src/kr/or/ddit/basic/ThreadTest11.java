package kr.or.ddit.basic;

public class ThreadTest11 {

	/*
	 * Thread의 stop() 메서드를 호출하면 Thread가 바로 멈춘다.
	 * 이 때 사용하던 자원을 정리하지 못하고 프로그램이 종료되어 나중에 실행되는 프로그램에 영향을 줄 수 있다.
	 * 그래서 stop() 메서드는 비추천으로 되어 있다.
	 * 
	 */
	
	public static void main(String[] args) {

		
//		ThreadStopTest1 th1 = new ThreadStopTest1();
//		th1.start(); // setStop()을 이용한 방법
//		
//		try {
//			Thread.sleep(1000);
//		} catch (InterruptedException e) {
//			// TODO: handle exception
//		}
//		th1.setStop(true);
////		th.stop(); // 쓸 수는 있지만 없어질수도 있는 메서드다(비추천) 
		
		ThreadStopTest2 th2 = new ThreadStopTest2();
		th2.start();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
		th2.interrupt();
		
	
	}

}


//쓰레드를 멈추게하는 연습용 쓰레드
class ThreadStopTest1 extends Thread{
	private boolean stop; // 초기화 안하면 false
	
	public void setStop(boolean stop) {
		this.stop = stop;
	}
	
	
	@Override
	public void run() {
		while(!stop) {
			System.out.println("쓰레드 실행 중....");
		}
		System.out.println("자원 정리...");
		System.out.println("쓰레드 종료...");
	}
}

// interrupt() 메서드를 이용하여 쓰레드를 안전하게 멈추는 방법
class ThreadStopTest2 extends Thread{
	@Override
	public void run() {
//		// 방법 1 -> interrupt()메서드와 sleep()메서드를 이용
//		try {
//			while(true) {
//				System.out.println("쓰레드 실행 중...");
//				Thread.sleep(1); // 슬립이 인터럽트 익셉션을 발생시키기 때문에 예외가 발생했을 때 catch로 감
//			}
//		} catch (InterruptedException e) {
//		}
//	
//		System.out.println("자원 정리 작업");
//		System.out.println("쓰레드 종료");
		
		//방법 2
		while(true) {
			System.out.println("쓰레드 실행 중...");
			
			//interrupt() 메서드가 호출되었는지를 검사한다.
			
//			// 검사 방법 1 -> 쓰레드의 인스턴스 메서드인 isInterrupted()메서드 이용하기
//			//				isInterrupted()메서드는 interrupt()메서드가 호출되면 true를 반환함.
//			
//			if(this.isInterrupted()) {
//				break;
//			}
			
			
			// 검사 방법 2 -> 쓰레드의 정적(static) 메서드인 interrupted()메서드 이용하기
			//				이 메서드도 interrupt()메서드가 호출되면 true를 반환한다.
			if(Thread.interrupted()) {
				break;
			}
			
			//결국 1번 2번 똑같은 방법인데 Thread 클래스의 메서드를 사용할거냐 내가 만든 클래스의 메서드를 사용할거냐 차이
			
			
		}
		System.out.println("자원 정리 작업");
		System.out.println("쓰레드 종료");
		
	}
}