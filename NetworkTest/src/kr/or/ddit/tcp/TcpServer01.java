package kr.or.ddit.tcp;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpServer01 {

	public static void main(String[] args) throws IOException {

		// TCP소켓 통신을 하기 위해 ServerSocket객체를 생성
				ServerSocket server = new ServerSocket(7777);
				//0~1024 번까지는 시스템에서 사용하고 있음. -> 그 이후 번호로 6만 몇까지 있으니 그 중에 겹치지 않게 쓰기
				
				System.out.println("서버가 접속을 기다립니다.");
				
				//accept()  => 클라이언트에서 연결 요청이 올때까지 기다리다가 
				//			=> 연결 요청이 오면 새로운 소켓 객체를 만들어서 클라이언트의 소켓 객체와 연결해준다.(서버 소켓 객체가 사라지는 것은 아님) 
				Socket socket = server.accept();
				
				//accept()메서드 이후의 명령은 통신이 연결이 완료된 이후의 작업을 기술하면 된다.
				System.out.println("클라이언트와 연결되었습니다.");
				System.out.println();
		
				System.out.println("연결된 상대방의 정보 출력하기");
				System.out.println("IP 주소 : " + socket.getInetAddress().getHostAddress());
				System.out.println("Port 번호 : " + socket.getPort());
				System.out.println();
				
				System.out.println("연결된 자신의 정보 출력하기");
				System.out.println("IP 주소 : " + socket.getLocalAddress());
				System.out.println("Port 번호 : " + socket.getLocalPort());
				System.out.println();
				
				
				// 클라이언트에게 메시지 보내기
				// Socket의 OutputStream객체를 구해서 전송한다.
				// (Socket의 getOutputStream() 이라는 메서드를 이용)
				OutputStream os = socket.getOutputStream();
				DataOutputStream dos = new DataOutputStream(os);
				
				// 클라이언트에게 메시지 보내기(클라이언트로 출력한다고 생각하면 된다.)
				dos.writeUTF("안녕하세요. 어서오세요.");
				System.out.println("메세지 전송 완료");
				
				
				// 소켓과 스트림 객체 닫기
				
				dos.close();
				socket.close();
				server.close();
	}

}
