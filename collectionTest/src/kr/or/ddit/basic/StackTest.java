package kr.or.ddit.basic;
import java.util.LinkedList;
import java.util.Scanner;


public class StackTest {

	public static void main(String[] args) {

		Browser b = new Browser();


		
		b.goURL("1.네이버");
		b.goURL("2.야후");
		b.goURL("3.구글");
		b.goURL("4.다음");
		b.history();
		b.goForward();
		System.out.println("앞으로 가기 후");
		b.history();
		b.goBack();
		System.out.println("뒤로 가기 후");
		b.history();
		b.goBack();
		System.out.println("뒤로 가기 후2");
		b.history();
		
		System.out.println("새로운 사이트 방문");
		b.goURL("5.네이트");
		b.history();
		b.goForward();
		System.out.println("앞으로 가기 후");
		b.history();
	}

}


// 웹브라우저의 앞으로 가기, 뒤로 가기 기능 구현하기(스택 이용)
class Browser{
	private LinkedList<String> back;    // 이전 방문 내역이 저장될 스택
	private LinkedList<String> forward; // 다음 방문 내역이 저장될 스택
	private String currentURL; 			// 현재페이지
	
	// 생성자
	public Browser(){
		back = new LinkedList<>();
		forward = new LinkedList<>();
		currentURL = "";
		
	}	

	//사이트 방문하는 메서드 -> 파라미터에는 방문할 URL주소
	public void goURL(String url){
		if(currentURL != null && !"".equals(currentURL)){ // 현재페이지가 있으면
			back.push(currentURL); 						  // 현재 페이지를 back 스택에 추가한다.
		}
		currentURL = url;  // 현재 페이지 변경
	}
	
	//뒤로 가기 메서드
	public void goBack(){
		// Back List가 비었는지 검사 -> isEmpty() : 리스트가 비어있으면 true를 반환.
		if(!back.isEmpty()){          // back 리스트가 비어있지 않으면
			forward.push(currentURL); // 현재 페이지의 URL을 앞으로가기 스택에 넣기
			currentURL = back.pop();  // 현재 페이지를 이전 페이지에서 가져온 URL로 바꾸기
		}   
	}
	

	//앞으로 가기 메서드(백과 반대로 생각하면 됨.)
	public void goForward(){
		if(!forward.isEmpty()){    
			back.push(currentURL); 
			currentURL = forward.pop();
		}   
	}	
	
	// 방문기록 확인하기
	public void history(){
		if(!back.isEmpty()){    
			System.out.println();
			System.out.println("-----------------------------------");
			System.out.println("   방	문	기	록");
			System.out.println("-----------------------------------");
			System.out.println("back ->>" + back);
			System.out.println("current ->>" + currentURL);
			System.out.println("forward ->>" + forward);
			System.out.println("-----------------------------------");
			
			
		}   
	}	
	
	
	
}