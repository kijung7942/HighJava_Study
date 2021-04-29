package kr.or.ddit.basic;

public class ThreadTest03 {

	public static void main(String[] args) {
		// 쓰레드가 수행되는 시간 체크해 보기
		
		Thread th1 = new Thread(new MyRunner());
		
		long startTime = System.currentTimeMillis(); // 1970년 1월 1일 0시 0분 0초(표준시간) 부터 현재까지 경과한 시간을 밀리세컨드 단위로 반환.
		th1.start(); // 쓰레드의 실행 속도를 알아보기 위해서는 쓰레드가 종료될 때까지 기다려야 함. -> try-catch문 안에서 쓰레드의 join()메서드를 호출
		
		try {
			th1.join(); // 현재 실행중인 쓰레드(main쓰레드)에서 대상이되는 쓰레드(변수 th1 쓰레드)가 종료될 때 까지 기다리는 메서드.
		} catch (InterruptedException e) {
			
		}
		long endTime = System.currentTimeMillis(); 
		
		System.out.println("경과시간 : " + (endTime - startTime));
		
	}

}


class MyRunner implements Runnable{
	@Override
	public void run() {
		long sum = 0L;
		for(int i = 1; i <= 1000000000L; i++) {
			sum += i;
		}
		System.out.println("합계 : " + sum);
	}
}