package kr.or.ddit.basic;

public class ThreadTest15 {

	/* 
	 *  동기화를 했을 때와 하지 않았을 때의 차이
	 *  동기화(synchronized)를 하지 않았을 때는 합계가 200이 아닌 값이 나오기도 하고 
	 *  n의 값을 가져가는 시기가 다르기 때문에 출력하는 sum의 값이 들쑥날쑥 하다.
	 *  
	 *  처리 방법 -> 메서드에 동기화 설정
	 */
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ShareObject sObj = new ShareObject();
		
		TestThread th1 = new TestThread("1번 쓰레드", sObj);
		TestThread th2 = new TestThread("2번 쓰레드", sObj);
		
		th1.start();
		th2.start();
		
			
	}

}


class ShareObject{
	private int sum = 0;
	
	public /*synchronized*/ void add() {   // ---> 메서드에 직접 동기화를 걸어준다.
		
		synchronized (this) {    //  ---> 동기화 블럭: 동기화 시키고 싶은 부분만 동기화 한다.
			
			int n = sum;
			
			n += 10;    // 10만큼 증가 -> 비동기 쓰레드의 문제점을 알아보기 위해 일부러 식을 분리 해놓음.
			// sum += 10;   --->> sum, n, n+10 , sum = n
			sum = n;
			
			System.out.println(Thread.currentThread().getName() + "합계 = " + sum);
		}
		
	}
}


class TestThread extends Thread{
	private ShareObject sObj;
	
	public TestThread(String name, ShareObject sObj) {
		super(name);
		this.sObj = sObj;
	}
	
	@Override
	public void run() {
		for(int i = 0 ; i <10 ; i++) {
			sObj.add();
		}
	}
}