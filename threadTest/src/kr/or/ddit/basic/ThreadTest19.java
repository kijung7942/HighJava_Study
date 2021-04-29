package kr.or.ddit.basic;

/*
 * wait()메서드와 notify()메서드를 이용해서 두 쓰레드가 번갈아 한번씩 실행되는 예제
 * 
 * 	wait(), notify(), notifyAll()메서드는 동기화 영역에서만 사용 가능하다.
 */

public class ThreadTest19 {

	public static void main(String[] args) {
		WorkObject workObj = new WorkObject();
		Thread th1 = new ThreadA(workObj);
		Thread th2 = new ThreadB(workObj);
		
		th1.start();
		th2.start();
		
	}

}


// 공통으로 사용할 객체

class WorkObject{
	public synchronized void testmethod1() {
		System.out.println("테스트메서드 1번 실행 중...");
		
		notify();
		
		try {
			wait();
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
	}

	public synchronized void testmethod2() {
		System.out.println("테스트메서드 2번 실행 중...");
		
		notify();
		
		try {
			wait();
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
	}
	
}

// workObject의 testMethod1()메서드만 호출하는 쓰레드
class ThreadA extends Thread{
	private WorkObject workObj;

	public ThreadA(WorkObject workObj) {
		this.workObj = workObj;
	}
	@Override
	public void run() {
		for(int i = 1 ; i <=10 ; i++) {
			workObj.testmethod1();
		}
		synchronized (workObj) {
			workObj.notify();
		}
	}	
}


// workObject의 testMethod2()메서드만 호출하는 쓰레드
class ThreadB extends Thread{
	private WorkObject workObj;
	
	public ThreadB(WorkObject workObj) {
		this.workObj = workObj;
	}
	@Override
	public void run() {
		for(int i = 1 ; i <=10 ; i++) {
			workObj.testmethod2();
		}
		synchronized (workObj) {
			workObj.notify();
		}
	}
}