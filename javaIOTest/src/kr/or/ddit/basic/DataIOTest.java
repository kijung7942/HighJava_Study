package kr.or.ddit.basic;

import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class DataIOTest {

	public static void main(String[] args) {
		try {
			FileOutputStream fos = new FileOutputStream("e:/daedoek/d_other/test.dat");
			
			//자료형 단위로 출력하는 보조스트림 객체 생성 (DataOutputStream객체)
			DataOutputStream dos = new DataOutputStream(fos);
			
			dos.writeInt(200); 			// Int형 데이터 출력
			dos.writeFloat(253.45f); 	// float형 데이터 출력
			dos.writeBoolean(true);	// boolean형 데이터 출력
			dos.writeBoolean(false);	// boolean형 데이터 출력
			dos.writeUTF("abdcsf안녕123");		// String형 데이터 출력
			
			System.out.println("출력 완료.");
			
			dos.close(); // 스트림 닫기
			
			
			// ------------------------------------------------------
			
			// 파일에 출력한 자료 읽어오기
			FileInputStream fis = new FileInputStream("e:/daedoek/d_other/test.dat");
			
			DataInputStream dis = new DataInputStream(fis);
			
			// DataInputStream으로 자료를 읽어올 때는 출력할 때의 순서와 같은 순서로 읽어와야 한다.
			
			System.out.println(dis.readInt()); 		
			System.out.println(dis.readFloat()); 	
			System.out.println(dis.readBoolean());	
			System.out.println(dis.readBoolean());	
			System.out.println(dis.readUTF());		
			
			System.out.println("읽기 작업 완료");
			dis.close();
			
		} catch (IOException e) {
		}

	}

}
