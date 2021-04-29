package kr.or.ddit.tcp;

import java.io.IOException;
import java.net.Socket;

public class TcpClient02 {

	public static void main(String[] args) {
		// 소켓객체를 생성해서 서버에 요청을 보내고, 서버와 연결이 완료되면
		// 해당 소켓을 송신용 쓰레드와 수신용 쓰레드에 넣어 준다.
		
		try {
			Socket socket = new Socket("localhost", 7777);
			System.out.println("서버에 연결되었습니다.");
			System.out.println("------------------------------------------------------------------");
			Sender sender = new Sender(socket);
			Receiver receiver = new Receiver(socket);
			
			sender.start();
			receiver.start();
			
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
