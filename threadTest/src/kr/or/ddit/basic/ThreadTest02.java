package kr.or.ddit.basic;

public class ThreadTest02 {

	public static void main(String[] args) {
			//멀티 쓰레드 프로그램 -> 모든 쓰레드가 끝나야 프로그램이 종료된다.
			//ex 지금 클래스는 main 메서드는 일찍 끝남 -> th1, th2, th3 의 start()메서드 들만 실행해주면 끝나기 때문
		
			//Thread를 작성해서 사용하는 방법
		
		// 방법 1
		// Thread 클래스를 상속한 class 작성한 후 이 class의 인스턴스를 생성한 후
		// 이 인스턴스의 start()메서드를 호출해서 실행한다.
		
		MyThread1 th1 = new MyThread1();
		th1.start(); // start() 메서드의 역할 -> Call Stack을 하나 더 만든다. -> 새로 만든 Call Stack에 run 메서드를 실행시킨다. 
		// th1.run(); run을 호출하면 싱글스레드와 똑같이 실행 된다.
		
		// 방법 2
		// Runnanle 인터페이스를 구현한 class를 작성한 후 이 class의 인스턴스를 생성하여
		// Thread의 인스턴스를 생성 할 때 생성자의 인수값으로 넣어준다.
		// 이 때 생성된 Thread의 인스턴스의 start() 메서드를 호출해서 실행한다. 
		
		
		MyThread2 r2 = new MyThread2();
		Thread th2 = new Thread(r2);
		
		th2.start();
		
		// 여기까지 메인, 쓰레드1, 쓰레드2 까지 3개의 쓰레드를 사용한 것임. -> call stack 3개가 돌아감.
		
		// 방법 3  -> 익명구현체를 이용하는 방법 -> 클래스 없이 즉석에서 인터페이스 바로 구현 -> Thread 객체 생성시 파라미터 값으로 주기 
		Runnable r3 = new Runnable() { // 인터페이스 초기화
			
			@Override
			public void run() {
				for(int i = 1; i <= 200; i++) {
					System.out.print("@");
					try {
						//Thread.sleep(시간); -> 주어진 '시간'동안 작업을 잠시 멈춘다.
						// 시간은 밀리세컨드 단위를 사용한다. (즉, 1000은 1초를 의미)
						Thread.sleep(100);
					} catch (InterruptedException e) {
					}
				}
			}
		};
		
		Thread th3 = new Thread(r3);
		th3.start();
		
		System.out.println("메인 메서드 끝");
	}

}


//방법 1 -> Thread를 상속한 class 작성
class MyThread1 extends Thread{
	
	
	@Override // 이 run()안에 쓰레드에서 처리할 내용을 기술한다.
	public void run() {
		super.run();
		
		for(int i = 1; i <= 200; i++) {
			System.out.print("*");
			try {
				//Thread.sleep(시간); -> 주어진 '시간'동안 작업을 잠시 멈춘다.
				// 시간은 밀리세컨드 단위를 사용한다. (즉, 1000은 1초를 의미)
				Thread.sleep(100);
			} catch (InterruptedException e) {
			}
		}
		
	}
}

//방법 2 -> Runnable인터페이스를 구현한 class 작성
class MyThread2 implements Runnable{

	@Override // 이 run()안에 쓰레드에서 처리할 내용을 기술한다.
	public void run() {
		for(int i = 1; i <= 200; i++) {
			System.out.print("$");
			try {
				//Thread.sleep(시간); -> 주어진 '시간'동안 작업을 잠시 멈춘다.
				// 시간은 밀리세컨드 단위를 사용한다. (즉, 1000은 1초를 의미)
				Thread.sleep(100);
			} catch (InterruptedException e) {
			}
		}
	}
	
}