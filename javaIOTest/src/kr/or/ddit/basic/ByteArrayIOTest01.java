package kr.or.ddit.basic;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class ByteArrayIOTest01 {
	
	/*
	 * 파일 입출력의 표준 틀
	 * 1. 인풋 & 아웃풋 스트림 객체 각각 생성
	 * 2. 데이터를 받을 변수 선언
	 * 3. while문을 열어서 data=input스트림.read()로 -1이 나올때까지 읽기(데이터 하나씩 읽어다가 처리하는 방법)
	 * 				    input스트림.available()로 0보다 클때까지 읽기(배열로 읽어다가 처리하는 방법)
	 * 4. while문 안에서 input스트림에서 사용할 코드 작성
	 * 5. close()로 스트림 닫기
	 */

	public static void main(String[] args) {

		byte[] inSrc = {0,1,2,3,4,5,6,7,8,9};
		byte[] outSrc = null;
		
		// 입력용 스트림과 출력용 스트림 객체 생성
		
		ByteArrayInputStream input = new ByteArrayInputStream(inSrc); 		// 입력용 스트림
		ByteArrayOutputStream output = new ByteArrayOutputStream(); 		// 출력용 스트림
				
		int data; 															// 읽어온 자료가 저장될 변수 data
		
		while((data = input.read()) != -1) {								// read()메서드 -> 1바이트씩 데이터를 읽어오는데
																			// 더이상 읽어올 데이터가 없으면 -1을 반환 함.
			// 읽어올 데이터를 사용 할 코드를 작성
			output.write(data); // 여기서는 예시로 읽어 온 데이터를 output에 바로 작성하는 코드를 작성함.
			
		}
		
		// 출력된 데이터는 스트림에 저장되어 있는데 이 데이터를 꺼내와 배열에 저장하기
		outSrc = output.toByteArray();
		
		
		try {
			// 사용했던 스트림을 닫아준다.
			input.close();
			output.close();
		}catch(IOException e) {
			
		}
		
		System.out.println("inSrc = > " + Arrays.toString(inSrc));
		System.out.println("outSrc = > " + Arrays.toString(outSrc));
		
		
	}

}
