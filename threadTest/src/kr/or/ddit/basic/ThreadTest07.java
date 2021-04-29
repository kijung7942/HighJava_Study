package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

/*
	 *	컴퓨터와 가위바위보를 진행하는 프로그램을 작성하시오.
	 *	
	 *	컴퓨터의 가위 바위 보는 난수를 이용하여 구하고,
	 *	사용자의 입력은 showInputDialog() 메서드를 이용해서 입력 받는다.
	 *
	 *  입력시간은 5초로 제한하고 카운트 다운을 진행한다.
	 *  5초동안 입력이 없으면 진것으로 처리
	 *  
	 *  5초안에 입력이 완료되면 승패를 구해서 결과 출력
	 *  
	 *  결과 예시) - 5초 안에 입력을 못한 경우
	 *  --- 결 과 ---
	 *  시간초과로 당신이 졌습니다.
	 *  
	 */


public class ThreadTest07 {

	public static void main(String[] args) {

		RSP th = new RSP();
		MyTimer th2 = new MyTimer();
		th.start();
		th2.start();
		
	}

}


class RSP extends Thread{
	public static boolean flag = false;
	@Override
	public void run() {
		String myRSP = null;
		do{myRSP = JOptionPane.showInputDialog("가위 바위 보 중 입력");
		}while(myRSP == null || !(myRSP.equals("보")|| myRSP.equals("가위") || myRSP.equals("바위")));
			
//		String[] rsp = {"가위","바위","보"};
//		String comRSP = rsp[((int)(Math.random()*3)];
		List<String> list = new ArrayList<>();
						//   나-컴    	이김			짐		컴-나		이김		짐
		list.add("가위");  // 0			-2			-1   			2
		list.add("바위");  // 1			1			-1
		list.add("보");   //  2			1			2
		String comRSP = list.get((int)(Math.random()*3));
		String result = "";
		switch(myRSP) {
		case "가위":
			if(comRSP.equals("가위")) {
				result = "비겼습니다.";
			}else if(comRSP.equals("바위")) {                                          
				result = "당신이 졌습니다.";
			}else if(comRSP.equals("보")) {                                            
				result = "당신이 이겼습니다.";
			}
			break;                                                                    
		case "바위":                                                                   
			if(comRSP.equals("바위")) {                                                
				result = "비겼습니다.";
			}else if(comRSP.equals("보")) {                                            
				result = "당신이 졌습니다.";
			}else if(comRSP.equals("가위")) {                                          
				result = "당신이 이겼습니다.";
			}
			break;                                                                    
		case "보":                                                                     
			if(comRSP.equals("보")) {                                                  
				result = "비겼습니다.";
			}else if(comRSP.equals("가위")) {                                          
				result = "당신이 졌습니다.";
			}else if(comRSP.equals("바위")) {                                          
				result = "당신이 이겼습니다.";
			}
			break;
		default:
			System.out.println("시스템에러");
		}
		System.out.println("당신이 입력한 값은 " + myRSP + ",\n컴퓨터가 낸 값은 " + comRSP + "로 " + result);
		flag = true;
	}
}

class MyTimer extends Thread{
	@Override
	public void run() {
		System.out.println("카운트다운 시작");
		for(int i = 5 ; i > 0 ; i --) {
			if(RSP.flag){return;}
			System.out.println(i);
		try {
				Thread.sleep(1000);
			} catch (Exception e) {	}
		}
		System.out.println("----------------------");
		System.out.println("시간 초과로 당신이 졌습니다. ");
		System.exit(0);
	}
	
}