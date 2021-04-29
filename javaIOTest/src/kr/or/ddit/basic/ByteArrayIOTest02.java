package kr.or.ddit.basic;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class ByteArrayIOTest02 {

	public static void main(String[] args) {

		byte[] inSrc = {0,1,2,3,4,5,6,7,8,9};
		byte[] outSrc = null;
		
		byte[] temp = new byte[4]; // 4개짜리 배열 선언(입력용)
		
		
		ByteArrayInputStream input = new ByteArrayInputStream(inSrc);
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		
		try {
			//읽어올 데이터가 있는지 확인해서 처리되는 반복문
			while(input.available() > 0) {
				input.read(temp);
				output.write(temp);
				
				System.out.println("반복문 안에서 temp -> " + Arrays.toString(temp) );
		
			outSrc = output.toByteArray();
			
			input.close();
			output.close();
			
			System.out.println(" inSrc(원본) -> " + Arrays.toString(inSrc));
			System.out.println(" outSrc(사본) -> " + Arrays.toString(outSrc));
			
			}
		} catch (IOException e) {
			// TODO: handle exception
		}
		
		
		
	}

}
