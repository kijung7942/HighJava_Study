package kr.or.ddit.tcp;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpServer02 {
	// 서버 소켓을 만들고 대기중에 클라이언트가 접속해 오면 소켓을 만들어서
	// 새메시지를 받는 쓰레드와 메시지를 보내는 쓰레드에 넘겨준다.
	public static void main(String[] args) throws IOException {

		ServerSocket server = new ServerSocket(7777);
		System.out.println("서버가 준비중입니다.");
		Socket socket = server.accept(); // 반복문으로 만들면 여러명이 해당 서버에 접속 할 수 있다.(리스트 등으로 만들어서 관리)
		System.out.println("서버가 준비되었습니다... 기다리는 중...");
		
		Sender sender = new Sender(socket);
		Receiver receiver = new Receiver(socket);
	// 각 쓰레드 구동	
		sender.start();
		receiver.start();

	
	
	
	
	}
}
