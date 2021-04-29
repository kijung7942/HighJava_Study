package kr.or.ddit.basic;

import javax.swing.JOptionPane;

public class ThreadTest05 {

	public static void main(String[] args) {
		// 사용자로부터 데이터 입력 받기
		
		String str = JOptionPane.showInputDialog("아무거나 입력하세요."); 
		// 새창 띄워서 콘솔창에 입력 받는 것. -> 무조건 문자열로 입력 받음./ 취소버튼은 null값
		System.out.println("입력한 값 : " + str);
		
		
		// 싱글 쓰레드이기 때문에 동시 실행이 안됨.
		for(int i = 10 ; i >= 0 ; i--) {
			System.out.println("카운트다운 : " + i);
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
			}
		}
	
	}

}
