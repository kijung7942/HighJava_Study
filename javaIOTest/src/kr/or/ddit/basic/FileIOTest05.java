package kr.or.ddit.basic;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileIOTest05 {

	public static void main(String[] args) {
		//한글이 저장된 파일 읽어오기
		// (한글의 인코딩 방식을 지정해서 읽어온다.)
		try {
//			FileReader fr = new FileReader("E:/daedoek/d_other/test_utf8.txt");
//			FileReader fr = new FileReader("E:/daedoek/d_other/test_ansi.txt");
//			int c;
//			while((c = fr.read()) != -1) {
//				System.out.print((char)c);
//			}
//			fr.close();

			
			
			//java 프로그램이 utf8로 작성되었기 때문에 읽어오는 파일도 utf8방식이라고 생각하고 읽어온다
			// -> utf8방식이 아닌 파일은 한글이 깨져서 나옴. -> 인코딩 방식을 지정해서 읽어와야 함.
			
			// 입력용 byte기반의 입력스트림을 문자기반의 스트림으로 변환해서 사용할 때 인코딩 방식을 지정할 수 있다.
			FileInputStream fis = new FileInputStream("E:/daedoek/d_other/test_ansi.txt");
			
			// 바이트기반 입력 스트림을 문자기반 스트림으로 변환
//			InputStreamReader isr = new InputStreamReader(fis); // 기본 인코딩 방식으로 읽어온다.
			
			// 인코딩 방식을 지정하기
			// 인코딩 방식 예시
			// - MS949 		-> 윈도우의 기본 한글 인코딩 방식(ANSI와 같다)
			// - UTF-8 		-> 유니코드 UTF-8 인코딩 방식
			// - US-ASCII	-> 영문 전용 인코딩 방식
			InputStreamReader isr = new InputStreamReader(fis,"MS949"); // 인코딩 방식을 지정해서 읽어온다.(파일의 인코딩 방식과 똑같이 맞춰줘야 한다.)
			//InputStramReader는 보조스트림 -> 파라미터 값으로 기반이 되는 스트림이 들어가야 한다.
			
			int c;
			
			while((c = isr.read()) != -1) {
				System.out.print((char)c);
			}
			isr.close(); // 보조스트림을 사용할 때는 보조스트림만 닫으면 이것과 연결된 기반 스트림도 자동으로 닫힌다.
		} catch (IOException e) {
		}
			
		
	}

}
