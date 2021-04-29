package kr.or.ddit.basic;
/*
 *  1부터 20억까지의 합계를 구하는 프로그램을 하나의 쓰레드가 단독으로 처리할 때와
 *  여러개의 쓰레드가 협력해서 처리할 때의 경과 시간을 비교해보기. 
 */


public class ThreadTest04 {

	public static void main(String[] args) {
		//단독으로 처리하는 쓰레드
		SumThread sm = new SumThread(1L, 2_000_000_000L);
		//여럿이 처리하는 쓰레드
		SumThread[] smThArr = new SumThread[]{
		new SumThread(1L, 500_000_000L),
		new SumThread(500_000_000L, 1_000_000_000L),
		new SumThread(1_000_000_000L, 1_500_000_000L),
		new SumThread(1_500_000_000L, 2_000_000_000L)
		};
		
		long startTime = System.currentTimeMillis();
		sm.start();
		try {
			sm.join();
		} catch (Exception e) {
		}
		long endTime = System.currentTimeMillis();
		System.out.println("단독쓰레드 = " +(endTime - startTime));
		
		
		// 여러개의 쓰레드 협력 처리
		startTime = System.currentTimeMillis();
		for(int i = 0 ; i < smThArr.length; i++) {
			smThArr[i].start();  //쓰레드 실행
		}
		for(SumThread sth : smThArr) {
			try {
				sth.join();
			} catch (Exception e) {
			}
		}
		endTime = System.currentTimeMillis();
		System.out.println("협력쓰레드 = " +(endTime - startTime));
	}

}



class SumThread extends Thread{
	//합계를 구할 영역의 시작값과 종료값이 저장될 변수
	private long start, end, sum;
	
	// 생성자에서 시작값과 종료값을 초기화
	
	public SumThread(long start, long end) {
		super();
		this.start = start;
		this.end = end;
	}


	@Override
	public void run() {
		for(long i = start; i <= end; i++) {
			sum += i;
		}
		System.out.println(start+ "~" + end + "까지의 합계 = " + sum);
	}
}