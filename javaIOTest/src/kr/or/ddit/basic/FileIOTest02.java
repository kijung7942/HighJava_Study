package kr.or.ddit.basic;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileIOTest02 {

	//파일에 출력하기
	
	public static void main(String[] args) {

		try {
			FileOutputStream fou = new FileOutputStream("E:/daedoek/D_Other/out.txt");
														//파일이 없으면 파일 생성, 파일이 있으면 덮어쓰기 함.
			for(char ch ='A'; ch <='Z'; ch++) {
				fou.write(ch); //ch 변수의 값을 파일에 출력
			}
			System.out.println("출력 작업 완료!!");
			
			fou.close(); // 출력용 스트림 닫기
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
