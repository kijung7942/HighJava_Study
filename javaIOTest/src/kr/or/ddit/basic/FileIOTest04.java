package kr.or.ddit.basic;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileIOTest04 {

	public static void main(String[] args) {
			// 사용자가 입력한 내용을 그대로 파일로 저장하기
		try {
			
			//System.in => 콘솔(표준) 입력 스트림: 바이트 기반
		
			// 입력용 byte기반 스트림을 문자기반 스트림으로 변환해준다.
			InputStreamReader  isr = new InputStreamReader(System.in);
					
			// 출력용 문자기반 스트림 객체 생성
			FileWriter fw = new FileWriter("E:/daedoek/d_other/testChar.txt");
			
			System.out.println("아무 내용이나 입력하세요.(입력의 끝은 Ctrl + Z 입니다.)");
			
			int c;
			
			while((c=isr.read()) != -1) {
				fw.write(c); // 콘솔로 입력받은 데이터를 파일에 출력한다.
			}
			
			fw.close();
			isr.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
