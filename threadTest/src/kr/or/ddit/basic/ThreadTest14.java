package kr.or.ddit.basic;


// 쓰레드에서 객체를 공통으로 사용하는 예제

/*
 * 원주율을 계산하는 쓰레드와
 * 계산된 원주율을 출력하는 쓰레드가 있다.
 * 
 * 원주율을 저장하는 객체가 필요
 * 이 객체를 두 쓰레드에서 공통으로 사용해서 처리한다.
 */
class ThreadTest14 {

	public static void main(String[] args) {
		// 공통으로 사용할 객체 생성 
		ShareData sd = new ShareData();
		
		
		//쓰레드 객체를 생성하고 공통으로 사용할 객체를 쓰레드에 주입.
		
		CalcPIThread cpi = new CalcPIThread();
		cpi.setSd(sd);
		
		PrintPIThread ppi = new PrintPIThread(sd);
		
		cpi.start();
		ppi.start();
		
	}

}


// 원주율을 관리하는 클래스(공통으로 사용할 클래스)

class ShareData{
	public double result;  //계산된 원주율이 저장 될 변수
	public volatile boolean isOk;   //계산이 완료되면 true
	// volatile -> 효율은 떨어지더라도 cache가 아닌 메모리에서 직접 가져오라는 명령어 
	//			-> 이 키워드가 붙은 변수는 컴파일러의 최적화 대상에서 제외한다.
	//			-> 즉, 이 변수의 값이 변경되면 즉시 변수에 적용.
}


// 원주율을 계산하는 쓰레드
class CalcPIThread extends Thread{
	private ShareData sd;

	// 초기화 : setter를 이용
	public void setSd(ShareData sd) {
		this.sd = sd;
	}
	
	@Override
	public void run() {
		// 원주율 구하는 공식 (1/1 - 1/3 + 1/5 - 1/7 + 1/9 - ...) * 4
		
		// 분모				1	  -3     5    -7     9
		
		double sum = 0.0;
		for(long i = 1; i < 2_000_000_000; i+=2) {
				if( (i/2) % 2 == 0 ) {
					sum += 1.0/i;
				}else {
					sum-= 1.0/i;
				}
		}
		sd.result = sum * 4;
		sd.isOk = true;
		
	}
}



// 원주율을 출력하는 쓰레드
class PrintPIThread extends Thread{
	private ShareData sd;
	
	// 초기화 : 생성자를 이용
	public PrintPIThread(ShareData sd) {
		this.sd = sd;
	}
	@Override
	public void run() {
		while(true) {
			
			if(sd.isOk == true) {
				break;
			}
			
		}
		System.out.println();
		System.out.println("result = " + sd.result);
		System.out.println("   PI  = " + Math.PI);
	}
}
