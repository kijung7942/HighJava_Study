package kr.or.ddit.basic;


// yield()메서드 연습

public class ThreadTest10 {

	public static void main(String[] args) {
		YieldThread th1 = new YieldThread("1번 쓰레드");
		YieldThread th2 = new YieldThread("2번 쓰레드");
		
		th1.start();
		th2.start();
		
		try {
			Thread.sleep(10);
		}catch (InterruptedException e) {
		}
		System.out.println("-----------------------------------------11111111111111");
		th1.work = false;
		
		try {
			Thread.sleep(10);
		}catch (InterruptedException e) {
		}
		
		System.out.println("-----------------------------------------222222222222222");
		th1.work = true;
		
		try {
			Thread.sleep(10);
		}catch (InterruptedException e) {
		}
		
		System.out.println("-----------------------------------------3333333333333333");
		th1.stop = true;
		th2.stop = true;
		
	}

}


// ()yield 쓰레드 생성

class YieldThread extends Thread{
	
	
	public boolean stop = false; // 쓰레드의 종료 여부를 나타내는 변수
	public boolean work = true;	 // 작업의 처리 여부를 나타내는 변수
	
	
	public YieldThread(String name) {
		super(name); //쓰레드의 이름을 설정한다.
	}
	
	
	@Override
	public void run() {
		while(!stop) { // stop이 true가 되면 반복문 종료
			if(work) {
				// getName() --> 위의 생성자에서 설정한 쓰레드의 이름을 반환한다.
				System.out.println(getName() + "작업 중...");
			}else {
				System.out.println(getName() + "양보...");
				Thread.yield();
				System.out.println(getName() + "양보: 작업..."); // yield한 다음 차례에서는 여기부터 시작
			}
		}
		System.out.println(getName() + "쓰레드 종료...");
	}	
	
	
}