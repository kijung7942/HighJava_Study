package kr.or.ddit.tcp;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class TcpClient01 {

	public static void main(String[] args) throws UnknownHostException, IOException {
		// 현재 자신 컴퓨터를 나타내는 방법
		// 1) 원래의 IP주소를 쓰는 방법 : 예) 192.168.44.2
		// 2) 자신을 나타내는 지정된 IP주소를 쓰는 방법 : 127.0.0.1 
		// 3) 원래의 컴퓨터 이름 : 예) SEM-PC
		// 4) 지정된 컴퓨터 이름 : localhost 
		
		String serverIp = "localhost";
		System.out.println(serverIp + "서버에 연결중입니다...");
		System.out.println();
		
		// 서버의 IP주소와 Port번호를 지정하여 Socket 객체를 생성한다.
		// Socket 객체는 생성이 완료되면 지정된 서버로 요청신호를 자동으로 보낸다.
		Socket socket = new Socket(serverIp, 7777);
		
		// 이 부분은 서버와 연결 된 이후에 처리할 내용을 기술한다.
		System.out.println("서버와 연결 완료.");
		System.out.println("통신 시작");
		// 서버에서 보내온 메시지를 받아서 화면에 출력하기.
		
		// InputStream객체를 생성한다. (Socket 이용)
		InputStream is = socket.getInputStream();
		DataInputStream dis = new DataInputStream(is);
		
		//서버에서 보내온 자료를 읽어온다고 생각하면 된다.
		System.out.println("서버에서 보낸 메시지\n" + dis.readUTF());
		System.out.println();
		
		System.out.println("연결을 종료 합니다.");
		
		
		System.out.println();
		
		// 스트림과 소켓 닫기
		dis.close();
		socket.close();
		
		
	}

}
