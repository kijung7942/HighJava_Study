package kr.or.ddit.basic;

public class ThreadTest16_은행예시 {

	/*
	 *  은행의 입출금을 쓰레드로 처리하는 예제
	 *  (synchronized 를 이용한 동기화 처리 예제)
	 */
	private int balance;
	
	
	// 동기화 영역 안에서 호출하는 메서드도 동기화 처리를 해야한다.
	public synchronized int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	//입금하는 메서드 -> 여기서는 간단히 썼지만 실제 처리는 복잡하므로 synchronized 써줘야 함.
	public void deposit(int money) {
		this.balance += money;
	}

	//출금하는 메서드 (출금 성공하면 true, 실패하면 false)
	public /*synchronized*/ boolean withdraw(int money) {
		synchronized (this) {
			if(balance >= money) {
				for(int i = 0 ; i < 1000000000; i++) {/*시간 벌이용 -> 문제점을 확인하기위해 처리속도 늦추려고 고의로 넣은거*/}
				balance -= money;
				System.out.println("메서드 안에서 balance = " + getBalance()); // 동기화 영역 안에서 호출하는 메서드도 동기화 처리를 해야한다.
				return true;
			}else {
				System.out.println("잔액이 부족합니다 balance = " + getBalance());
				return false;
			}
		}
		
		
		
	}


	public static void main(String[] args) {
		final ThreadTest16_은행예시 account = new ThreadTest16_은행예시();  //자바 8부터는 final 생략가능 -> 익명구현체에서 지역변수로 받을 때
		account.setBalance(10000); // 잔액 10000원으로 설정
		
		//익명 구현체로 쓰레드 구현
		Runnable test = new Runnable() {
			
			@Override
			public void run() {
				boolean result = account.withdraw(6000); // 6천원 출금
				System.out.println("쓰레드에서 출금여부(result) = " + result + " 잔액 : "+ account.getBalance());
				
			}
		};
		// -------------------------
		
		Thread th1 = new Thread(test);
		Thread th2 = new Thread(test);
		
		th1.start();
		th2.start();
		
	}

}

