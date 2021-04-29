package kr.or.ddit.basic;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;

public class BufferedIOTest01 {

	public static void main(String[] args) {
		// 입출력의 성능 향상을 위해서 Buffered 스트림을 사용한다.
		
		try {

			FileOutputStream fout = new FileOutputStream("E:/daedoek/d_other/bufferTest.txt");
			
			BufferedOutputStream bout = new BufferedOutputStream(fout,5);
																//  버퍼의 크기를 5로 제한
			for(int i = '1'; i <= '9'; i++) {
				bout.write(i);
			}

			// 여기서 bufferedOutputStream을 가지고 뭔가 다른 작업을 하려면 flush
			// 보통 출력작업이 끝나면 flush로 버퍼에 남아 있는 데이터를 강제로 출력해주는 것이 좋다.
//			bout.flush();
			
			// 다른작업 없이 출력만 할거면 close -> close에 flush 기능이 들어있다.
			bout.close(); // close나 flush를 안해주면 버퍼에 데이터가 남은 상태로 프로그램이 종료된다. 
//			fout.close(); // 보조스트림을 닫으면 기반스트림도 닫히기 때문에 안적어줘도 됨.
			
			System.out.println("작업완료"); // close 없이 출력 결과 -> 1,2,3,4,5
										  // close 써주고 출력 결과 -> 1,2,3,4,5,6,7,8,9 
			
		} catch (Exception e) {
		}
		
	}

}
