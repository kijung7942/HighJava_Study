package kr.or.ddit.basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

public class URLTest02 {

	public static void main(String[] args) throws IOException {
		// URLConnection 클래스 	=> 	애플리케이션과 URL간의 통신 연결을 위한 클래스
		//						=> 	해당 URL에 접속해서 자료를 가져오거나 보낼 수 있다.
		
		// 예) 특정 서버의 정보와 파일 내용을 가져와 출력하는 예제
		URL url = new URL("https://www.naver.com/index.html");
		
		
		// URLConnection 객체 구하기
		URLConnection urlCon = url.openConnection();
		
		//Header 정보 가져오기
		Map<String, List<String>> headerMap = urlCon.getHeaderFields();
		
		// headerMap의 key값과 value값 출력하기
		for(String headerKey : headerMap.keySet()) {
			System.out.println(headerKey + headerMap.get(headerKey));
		}
		System.out.println("------------------------------------------------");
	
		// URL에 지정한 문서의 내용을 가져오기 (여기서는 index,html문서의 내용(소스) 가져오기)
		
//		// 방법 1. -> URLConnection객체 이용
//		
//		// 파일을 읽어오기 위한 스트림 객체 생성
//		InputStream is = urlCon.getInputStream();
//		InputStreamReader isr = new InputStreamReader(is, "utf-8");
//		BufferedReader br = new BufferedReader(isr);
//		
//		
//		// 자료를 읽어와 출력하기
//		while(true) {
//			String str = br.readLine();
//			if(str==null) {
//				break;
//			}else {
//				System.out.println(str);
//			}
//		}
//		
//		br.close();
		
		
		// 방법 2. => URL 객체의 openStream() 메서드 활용하기
		InputStream is2 = url.openStream();
		InputStreamReader isr2 = new InputStreamReader(is2, "utf-8");
		BufferedReader br2 = new BufferedReader(isr2);
		
		
		// 자료를 읽어와 출력하기
		while(true) {
			String str = br2.readLine();
			if(str==null) {
				break;
			}else {
				System.out.println(str);
			}
		}
		
		br2.close();
		
		
		
		
		
		
		
	}
	
	
}
