package kr.or.ddit.tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
public class TcpMultiChatServer {
	
	// 접속할 클라이언트의 정보를 저장할 Map객체 변수 선언
	// key 값 -> 접속한 사람의 이름, value값 -> 접속한 Socket객체
	
	private Map<String,Socket> clientMap;
	
	// 생성자
	public TcpMultiChatServer() {
		//clientMap을 동기화 처리가 되도록 생성한다.
		clientMap = Collections.synchronizedMap(new HashMap<String,Socket>());
	}
	
	public static void main(String[] args) {
		new TcpMultiChatServer().serverStart();
	}
	
	
	//서버 시작 메서드
	private void serverStart() {
		ServerSocket server = null;
		Socket socket = null;
		
		try {
			server = new ServerSocket(7777);
			System.out.println("서버가 시작되었습니다.");
			while(true) {
				socket = server.accept(); // 클라이언트의 접속을 기다린다.
				System.out.println("["+socket.getInetAddress() + " : " + socket.getPort() + "] 에서 접속했습니다.");
				System.out.println();
				ServerReceiver serverThread = new ServerReceiver(socket);
				serverThread.start();
			}
		} catch (Exception e) {
		}
		
		
	} //serverStart()메서드 끝
	
	//----------------------------------------------------------------------------------------------------------------
	
	//clientMap에 저장된 전체 사용자에게 메시지를 전송하는 메서드
	private void sendToAll(String msg) {
		// clientMap의 데이터 개수만큼 반복 (key값(즉 이름) 개수만큼 반복)
		for(String name : clientMap.keySet()) {
			try {
				// key값을 이용해서 그에 대응하는 Socket객체를 구한 다음 getOutputStream으로 출력용 스트림객체를 사용한다.
				DataOutputStream dos = new DataOutputStream(clientMap.get(name).getOutputStream());
				dos.writeUTF(msg);
			} catch (Exception e) {
			}
		}
	} // senToAll()메서드 끝
	
	//----------------------------------------------------------------------------------------------------------------
	
	//innerClass로 서버에서 클라이언트로 메시지를 전송하는 쓰레드를 작성한다.
	// innerClass로 만든 이유 = TcpMultiCharServer(outerClass)의 멤버변수를 자유롭게 사용하기 위해서
	class ServerReceiver extends Thread{
		private Socket socket;
		private DataInputStream dis;
		private DataOutputStream dos;

		public ServerReceiver(Socket socket) {
			this.socket = socket;
			try {
				//수신용 인풋스트림
				dis = new DataInputStream(socket.getInputStream());
				//송신용 아웃풋스트림
				dos = new DataOutputStream(socket.getOutputStream());
			} catch (Exception e) {
			}
		} // 생성자 끝
		
		@Override
		public void run() {
			String name = "";
			try {
				while(true) {
					// 클라이언트가 연결이 성공되면 첫번째로 '사용자의 이름'을 보낸다.
					// 서버에서는 이 '사용자의 이름'이 중복되는지의 여부를 응답(feedBack)으로 클라이언트에게 보낸다.
					name = dis.readUTF();
					System.out.println(name);
					if(clientMap.containsKey(name)) { 	// 이름이 중복되면
						dos.writeUTF("이름중복");
					}else {								// 이름이 중복되지 않을 때
						dos.writeUTF("OK");
						break; // 반복문 탈출
					}
				} // while문 끝
				
				// 접속한 사람의 이름을 다른 전체 클라이언트에게 보낸다.
				sendToAll("[ " + name + " ]님이 대화방에 입장했습니다.");
				
				// clientMap에 '사용자 이름'과 접속한 Socket을 저장.
				clientMap.put(name, socket);
				
				System.out.println("현재 접속자 수 : " + clientMap.size() + "명");
				
				while(dis!=null) {
					sendToAll(dis.readUTF());
				}
				
			} catch (Exception e) {
			} finally {
				// finally영역이 실행되는 것은 클라이언트가 접속을 종료했다는 의미이다.
				sendToAll("[" + name + "] 님께서 대화방에서 나가셨습니다.");
				clientMap.remove(name);
				System.out.println("["+socket.getInetAddress() +" : " + socket.getPort() + "]에서 접속을 종료했습니다.");
				System.out.println("현재 접속자 수 : " + clientMap.size() + "명");
				System.out.println();
			}
		
		}
	}
	
	
	
}

