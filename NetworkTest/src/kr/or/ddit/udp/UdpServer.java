package kr.or.ddit.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/*
 *  UDP방식 : 비연결 지향, 신뢰성 없다. 데이터가 순서대로 도착한다는 보장이 없다. 그렇지만 TCP방식보다 속도가 빠르다.
 *  
 *  
 *  DatagramSocket 객체와 DatagramPacket 객체를 이용해서 통신한다.
 *   - DatagramSocket : 데이터의 송신과 수신에 관련된 작업을 수행한다. (우체부의 역할)
 *   - DatagramPacket : 실제 주고 받는 데이터와 관련된 작업을 수행한다. (소포나 편지)
 *   		==> 수신을 위한 생성자와 송신을 위한 생성자를 따로 제공한다. (매개변수의 개수가 다름)
 *   
 *   
 *   TCP의 경우에는 스트림을 이용해서 송수신을 했지만 UDP의 경우에는 데이터그램을 이용해서 송수신한다.
 */

public class UdpServer {
	
	
	
	public static void main(String[] args) {
		try {
			// 통신할 포트번호를 지정하여 소켓을 생성한다.
			DatagramSocket socket = new DatagramSocket(8888);
			
			// 송신용 패킷과 수신용 패킷 객체 변수 선언
			DatagramPacket outPacket;
			DatagramPacket inPacket;
			
			System.out.println("서버 실행 중...");
			
			while(true) {
				// 데이터가 저장 될 byte형 배열 선언
				byte[] bMsg = new byte[1024]; // 보통 512이상으로 준다.
				
				// 수신용 패킷 객체 생성
				//  => 수신된 데이터가 저장될 byte형 배열, 배열의 길이를지정해서 생성한다.
				inPacket = new DatagramPacket(bMsg, bMsg.length);
				
				// 소켓을 통해 데이터를 수신한다 => receive()메서드 이용  => 이 메서드는 데이터가 올때까지 기다린다.
				// 수신된 데이터의 패킷정보는 지정한 패킷변수에 저장된다.
				socket.receive(inPacket);
				
				// 수신받은 패킷에서 상대방의 주소, 포트번호 등을 알 수 있다.
				InetAddress address = inPacket.getAddress();
				int port = inPacket.getPort();
				
				System.out.println("상대방의 IP 정보 : " + address);
				System.out.println("상대방의 Port 정보 : " + port);
				System.out.println();
				
				//상대방이 보낸 메시지 출력
				// 수신된 데이터는 수신용 패킷을 생성할 때 지정한 byte형 배열에 저장되고, inpacket.getData() 메서드로 가져올 수 있다.
				// - 실제 수신된 데이터의 길이는 inpacket.getLength() 메서드로 가져올 수 있다.
				
				// 방법1. byte배열 이용하는 방법 => 수신받은 byte형 배열의 값들을 문자열로 변환하기.
//				String msg = new String(bMsg, 0, inPacket.getLength()); 
				
				// 방법2. getData()메서드를 이용하는 방법.
				String msg = new String(inPacket.getData(), 0, inPacket.getLength());
				
				System.out.println("상대방이 보낸 메시지 : " + msg);
				System.out.println();

				// 여기까지 수신 받는 법 끝 
				//----------------------------------------------------------------------------------------------------
				
				// 상대방에게 메시지 보내기 (이 예제에서는 수신받은 메시지를 그대로 송신하기)
				
				// 송신한 메시지를 byte형 배열로 변환한다.
				byte[] sendMsg = msg.getBytes(); // String 을 byte[]로 바꾸어주는 메서드
				
				//송신용 패킷객체 생성
				//        => 송신할 데이터가 저장된 byte배열, 송신할 데이터의 길이(배열의 길이)
				// 			 상대방 주소정보, 상대방 포트 번호 필요
				outPacket = new DatagramPacket(sendMsg,sendMsg.length,address,port);
		
				// 송신하기
				socket.send(outPacket);
			
			}// while문 끝
		} catch (Exception e) {
			
		}
		
	}

}
