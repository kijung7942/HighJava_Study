package kr.or.ddit.basic;

import javax.swing.JOptionPane;

public class ThreadTest06 {

	public static void main(String[] args) {
		
		DataInput d = new DataInput();
		MyCountDown cd = new MyCountDown();				
		d.start();
		cd.start();
		
		
	}

}


//데이터를 입력하는 쓰레드
class DataInput extends Thread{
	public static boolean inputCheck = false;
	@Override
	public void run() {
		String str = JOptionPane.showInputDialog("아무거나 입력하세요");
		System.out.println("입력값 = " + str);
		inputCheck = true;
	}
}

class MyCountDown extends Thread{
	
	@Override
	public void run() {
		for(int i = 10; i > 0; i--) {
			//static inputCheck 변수의 상태를 검사 후 카운트 다운을 출력
			if(DataInput.inputCheck) {return;}
			System.out.println(i);
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
			}
		}
		System.out.println("10초가 지났습니다. 프로그램을 종료합니다.");
		System.exit(0);
	}
}