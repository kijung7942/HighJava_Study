package kr.or.ddit.basic;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FileIOTest {

	public static void main(String[] args) {
		// 파일 내용 읽기 연습 -> 입력용스트림 -> FileInputStream 객체를 이용한 예제
		
		try { // 읽어올 File이 실제 데이터에 없을때 IOException이 나기 때문에 try - catch문으로 묶어준다.
			//읽어올 파일을 인수로 받는 FileInputStream객체 생성
			// 방법 1
//			FileInputStream fin = new FileInputStream("E:/daedoek/D_Other/test.txt");
			
			// 방법 2
			File file = new File("E:/daedoek/D_Other/test.txt");
			FileInputStream fin = new FileInputStream(file);
			
			int c; // 읽어올 데이터가 저장 될 변수
			
			while((c = fin.read()) != -1) {
				// 읽어온 데이터를 화면에 출력
				System.out.print((char)c);
			}
			
			fin.close(); // 작업 완료 후 스트림 닫기
			
		} catch (IOException e) {
//			e.printStackTrace();
			System.out.println("입출력 오류");
		}
		
		
		
		
		
	}

}
