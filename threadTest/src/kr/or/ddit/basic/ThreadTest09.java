package kr.or.ddit.basic;

public class ThreadTest09 {

	
	 // 쓰레드의 상태를 출력하는 예제
	 
	
	public static void main(String[] args) {
		StatePrintThread th = new StatePrintThread(new TargetThread());
		
		th.start();
		
		
		
	}

}

// 쓰레드 상태의 검사 대상이 되는 쓰레드

class TargetThread extends Thread{
	
	@Override
	public void run() {
		for(long i = 1 ; i <= 20_000_000_000L; i ++) {};// 시간 지연용(검사해보기 위해 넉넉히)
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
		for(long i = 1 ; i <= 20_000_000_000L; i ++) {};// 시간 지연용(검사해보기 위해 넉넉히)

		
	}
}


// 검사 대상의 상태를 출력하는 쓰레드

class StatePrintThread extends Thread{
	private TargetThread target;

	//생성자
	public StatePrintThread(TargetThread target) {
		this.target = target;
	}
	@Override
	public void run() {
		while(true) {
			// 쓰레드의 현재 상태값 구하기
			Thread.State state = target.getState();
			System.out.println("TargetThread의 현재 상태 값 : " + state);
			
			if(state == Thread.State.NEW) {  // 쓰레드가 새로 만들어지고 Runnable쪽에 가지 않은 상태
				target.start();
			}
			
			if(state == Thread.State.TERMINATED) { // 쓰레드가 종료되어 소멸되었을 때
				break;
			}
				
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
			
		}
	}
		
}