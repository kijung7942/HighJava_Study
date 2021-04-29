package kr.or.ddit.basic;

public class ThreadTest08_daemonThread {

	
	//데몬 쓰레드 연습 -> 자동 저장 기능 구현하기
	public static void main(String[] args) {

		AutoSaveThread auto = new AutoSaveThread();
		
		// 데몬 쓰레드로 설정하기 => 반드시 start()메서드 호출 전에 설정해야 한다.
		// 설정하지 않고 스타트를 하면 InteredExruptception이 발생
		
		auto.setDaemon(true); // 데몬 쓰레드로 설정하는 방법
		
		boolean daemonCheck = auto.isDaemon(); // auto라는 이름의 쓰레드가 데몬인지 확인하는 메서드(반환값 : boolean)
		
		auto.start();
		
		try {
			for(int i = 1; i <=20  ; i++) {
				System.out.println(i);
				Thread.sleep(1000);
			}
		} catch (InterruptedException e) {
		}
		
		System.out.println("작업 끝"); 
		// auto메서드와 main메서드 두개의 쓰레드가 동시 진행 되지만 auto쓰레드는 데몬쓰레드이기 때문에
		// 메인메서드가 끝나면 자동으로 auto메서드도 끝난다.
	}

}


// 자동 저장 쓰레드 작성 (3초에 한번씩 저장)
class AutoSaveThread extends Thread{
	// 작업 내용을 저장하는 메서드
	
	public void save() {
		System.out.println("작업 내용을 저장합니다."); // 아직 IO를 배우기 전이라 그냥 콘솔에 내용 띄우는 걸로 대체
		
	}
	
	@Override
	public void run() {
		while(true) {	// 데몬쓰레드의 특성 (무한루프)
			try {
	
				
				Thread.sleep(3000);
			} catch (InterruptedException e) {
			}
			save();
		}
	}
}