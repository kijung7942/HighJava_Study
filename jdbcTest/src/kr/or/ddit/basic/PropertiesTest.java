package kr.or.ddit.basic;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesTest {
// 외부의 properties파일의 내용을 읽어와 Properties객체로 처리하기
	public static void main(String[] args) {
		// 읽어온 정보를 저장할 Properties객체 생성
		Properties prop = new Properties();
		
		// 읽어올 파일명을 이용한 File객체 생성
		File file = new File("res/dbinfo.properties");
		
		FileInputStream fin = null;
		try {
			//파일 내용을 읽어올 스트림 객체 생성
			fin = new FileInputStream(file);
			
			// 입력용 스트림 객체를 이용해서 파일 내용을 읽어와 Properties객체에 셋팅한다
			prop.load(fin); // 파일 내용을 읽어와 key값과 value값을 분류한 후 Properties 객체에 추가해준다.
			
			// -----------------------------------------------------------------------
			// 읽어온 파일 정보를 출력해보기
			
			System.out.println("driver => " + prop.getProperty("driver"));
			System.out.println("url    => " + prop.getProperty("url"));
			System.out.println("user   => " + prop.getProperty("user"));
			System.out.println("pass   => " + prop.getProperty("pass"));
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(fin!=null) try{fin.close();}catch(IOException e) {}
		}
		
	}

}
