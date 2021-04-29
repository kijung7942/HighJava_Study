package kr.or.ddit.basic;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
 *  은행의 입출금을 쓰레드로 처리하는 예제
 *  (Lock객체 를 이용한 동기화 처리 예제)
 */

public class ThreadTest17 {

	private int balance; //잔액
	
	
	// Lock 객체 생성 ==> 되도록이면 private final로 만든다.
	private final Lock lock = new ReentrantLock();
	
	
	
	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	
	
	/*
	 * Lock 객체는 lock()메서드로 락을 설정하고
	 * 반드시 unlock()이라는 메서드로 락을 해제해주어야 한다.
	 */
	public void deposit(int money) {
		lock.lock();   // Lock 객체의 lock() 메서드로 lock 설정
		this.balance += money;
		lock.unlock(); // Lock 객체의 unlock() 메서드로 lock 해제
	}

	
	public  boolean withdraw(int money) {
		lock.lock();
		boolean check = false;
	
		try {
		
			if(balance >= money) {
				for(int i = 0 ; i < 1000000000; i++) {}
				balance -= money;
				System.out.println("***메서드 안에서 balance = " + getBalance());
				check = true;
			}
			else {
				System.out.println("***잔액이 부족합니다 balance = " + getBalance());
				check = false;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			lock.unlock();
		}	
	
		return check;
	}
	
	
	
	

	public static void main(String[] args) {
		final ThreadTest17 account = new ThreadTest17();  //자바 8부터는 final 생략가능 -> 익명구현체에서 지역변수로 받을 때
		account.setBalance(10000); // 잔액 10000원으로 설정
		
		//익명 구현체로 쓰레드 구현
		Runnable test = new Runnable() {
			
			@Override
			public void run() {
				boolean result = account.withdraw(6000); // 6천원 출금
				System.out.println("***쓰레드에서 출금여부(result) = " + result + " 잔액 : "+ account.getBalance());
				
			}
		};
		// -------------------------
		
		Thread th1 = new Thread(test);
		Thread th2 = new Thread(test);
		
		th1.start();
		th2.start();
		
	}

}

