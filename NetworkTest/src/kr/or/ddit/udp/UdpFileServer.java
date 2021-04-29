package kr.or.ddit.udp;
//클라이언트는 서버에 접속하면 'e:/daedoek/d_other/펭귄.jpg'파일을 서버로 전송
//클라이언트가 보낸 파일 데이터를 받아서 'e:/daedoek/d_other/펭귄_복사본.jpg' 파일로 저장.

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UdpFileServer {


	public static void main(String[] args) {
		new UdpFileServer().serverStart();
	}
	
	private void serverStart() {
		String fileName = "e:/daedoek/d_other/펭귄_복사본.jpg";
		long fileSize;			 // 전송받을 파일의 전체 길이를 저장할 변수 선언
		long totalReadSize = 0;  // 전송받은 데이터 길이를 누적할 변수 선언
		
		byte[] buffer = new byte[1024];
		
		FileOutputStream fos = null;
		DatagramSocket socket = null;
		try {
			System.out.println("서버 준비 중...");
			socket = new DatagramSocket(8888);		 // UDP 소켓 객체 생성
			fos = new FileOutputStream(fileName);	 // 파일 출력용 스트림 객체 생성
			
			// 첫번째 데이터 받기(전송될 파일의 전체 길이)
			DatagramPacket dpacket = new DatagramPacket(buffer, buffer.length);
			socket.receive(dpacket);
			
			String str = new String(buffer).trim();  // 문자열로 전송된 파일 길이를 문자열로 변환한다.			
			fileSize = Long.parseLong(str); // 문자열 -> 숫자형으로 변환하기
			
			System.out.println("다운로드 시작...");
			int len = 0; // 수신 받은 데이터 길이가 저장될 변수
			while(true) {
				socket.receive(dpacket);   // 파일 데이터 받기
				len = dpacket.getLength(); // 수신받은 데이터의 길이(읽어온 길이)
				fos.write(buffer, 0 , len); // 파일로 저장
				
				totalReadSize += len;
				
				System.out.println("진행 사항 : " + totalReadSize + " / " + fileSize + " Bytes");
				
				if(totalReadSize >= fileSize) break;
			}
			
			System.out.println("다운로드 완료...");
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if(fos!=null) {try {fos.close();}catch(Exception e) {}}
			if(socket!=null) {try {socket.close();}catch(Exception e) {}}
		}
		
		System.out.println("프로그램 종료");
	}
}
