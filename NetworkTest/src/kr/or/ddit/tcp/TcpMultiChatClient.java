package kr.or.ddit.tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class TcpMultiChatClient {

	public static void main(String[] args) {

		
		new TcpMultiChatClient().clientStart();
	}

	
	private void clientStart() {
		Socket socket = null;
		try {
			String serverIP = "localhost";
			socket = new Socket(serverIP,7777);
			System.out.println("서버에 연결되었습니다");
			
			// =======================================================
			
			ClientSender cs = new ClientSender(socket);
			ClientReceiver cr = new ClientReceiver(socket);
			
			cs.start();
			cr.start();
			
			
		} catch (Exception e) {
		}
	}
	
	// 메시지를 전송하는 내부 쓰레드
	class ClientSender extends Thread{
		private Socket socket;
		private DataInputStream dis;
		private DataOutputStream dos;
		
		private String name;
		private Scanner scan;
		
		//생성자
		public ClientSender(Socket socket) {
			this.socket = socket;
			scan = new Scanner(System.in);
			try {
				dis = new DataInputStream(socket.getInputStream());  // 수신용
				dos = new DataOutputStream(socket.getOutputStream());// 송신용
				
				if(dos!=null) {
					// 클라이언트용 프로그램은 처음 실행하면 서버에 접속하고 접속에 성공하면 첫번째로
					// 이름을 입력받아 전송하고 이름의 중복여부를 feedback으로 받아서 확인한다.
					System.out.println("대화명 : ");
					String name = scan.nextLine();
					while(true) {
						dos.writeUTF(name); // 대화명 전송
						//대화명 중복 여부의 feedback을 응답으로 받는다.
						String feedBack = dis.readUTF(); 
						if("이름중복".equals(feedBack)){ // 대화명이 중복되지 않을 때
							System.out.println(name+"은 대화명이 중복됩니다.");
							System.out.println("다른 대화명을 입력하세요");
							System.out.println("대화명 : ");
							name = scan.nextLine();
						}else {
							this.name = name;
							System.out.println(name + " 대화명으로 대화방에 입장했습니다.");
							break; // 반복문 탈출
						}
					}// 반복문 끝
				}
			} catch (Exception e) {
			}
		} // 생성자 끝...
		
		@Override
		public void run() {
			try {
				while(dos != null) {
					// 키보드로 입력한 메시지를 서버로 전송한다.
					dos.writeUTF("[" + name + "]" + scan.nextLine());
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		} // 전송용 쓰레드 끝
		
		
		
		
	}
	
	// 메시지 수신용 쓰레드
	class ClientReceiver extends Thread{
		private Socket socket;
		private DataInputStream dis;
		
		//생성자
		public ClientReceiver(Socket socket) {
			this.socket = socket;
			try {
				dis = new DataInputStream(socket.getInputStream());
			} catch (Exception e) {
				// TODO: handle exception
			}
		} // 생성자 끝

		@Override
		public void run() {

			try {
				while(dis!=null) {
						System.out.println(dis.readUTF());
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
}

