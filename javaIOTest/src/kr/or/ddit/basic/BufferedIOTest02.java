package kr.or.ddit.basic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class BufferedIOTest02 {

	public static void main(String[] args) {
		// 문자 기반의 Buffered스트림 사용 예제
		try {
			// 작성했던 자바프로그램 FileTest01을 읽어올 것임.
			// 이클립스에서의 자바 프로그램이 실행되는 현재 위치는 해당 프로젝트 폴더가 현재 위치가 된다.
			FileReader fr = new FileReader("./src/kr/or/ddit/basic/FileTest01.java");
			BufferedReader br = new BufferedReader(fr); // 크기 지정 x -> 기본크기 : 8kb
			
			String temp = "";

			//temp=br.readLine() => 줄 단위로 읽어오기 -> 읽어올 값이 없으면 null
			for(int i = 1; (temp=br.readLine()) != null; i++){
				System.out.printf("%4d : %s\n", i , temp);
			}
			
			br.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
		
		
	}

}
