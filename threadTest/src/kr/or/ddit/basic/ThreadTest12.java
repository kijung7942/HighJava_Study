package kr.or.ddit.basic;

public class ThreadTest12 {

	// 3개의 쓰레드가 각각 알파벳을 A~Z까지 출력하는데, 출력을 끝낸 순서대로 결과를 나타내는 프로그램 작성하기
	
	public static void main(String[] args) {

//		AlphaThread th1 = new AlphaThread("첫번째 쓰레드");
//		AlphaThread th2 = new AlphaThread("두번째 쓰레드");
//		AlphaThread th3 = new AlphaThread("세번째 쓰레드");
//		
//		th1.start();
//		th2.start();
//		th3.start();
//		
		// 배열을 만들고 반복문을 사용하는 것이 더 좋은 코드임.
		
		AlphaThread[] threads = new AlphaThread[] {
			new AlphaThread("첫번째 쓰레드"),
			new AlphaThread("두번째 쓰레드"),			
			new AlphaThread("세번째 쓰레드")			
		};
		
		for(AlphaThread th : threads) {
			th.start();
		}
		
		for(AlphaThread th : threads) {
			try {
				th.join();	
			} catch (InterruptedException e) {
			}
		}
		System.out.println("경기 끝");
		System.out.println(AlphaThread.rank);
	}

}


class AlphaThread extends Thread{
	public static String rank = "순위 :  "; // 출력을 마친 순서대로 저장할 변수
	private String name; // 쓰레드의 이름.
	
	
	public AlphaThread(String name) {
		this.name = name;
	}
	
	
	@Override
	public void run() {
		for(char c = 'A'; c <= 'Z'; c++) {
			System.out.println(name + "의 출력 문자: " + c);
			try {
				Thread.sleep((int)(Math.random()*300+201));
			} catch (InterruptedException e) {
			}
		}
			System.out.println(name + "출력 완료");
			
			AlphaThread.rank += name + "   ";
	}
}