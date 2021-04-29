package kr.or.ddit.udp;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UdpFileClient {

	public static void main(String[] args) {

		String fileName = "e:/daedoek/d_other/펭귄.jpg";
		String severIp = "localhost";
		int port = 8888;
		
		File file = new File(fileName);
		if(!file.exists()) {
			System.out.println("전송할 파일이 없습니다. 프로그램 종료");
			return;
		}
		
		long fileSize = file.length(); // 전송할 파일의 크기 구하기
		long totalReadSize = 0;
		
		DatagramSocket socket = null;
		FileInputStream fis = null;
		try {
			socket = new DatagramSocket();
			InetAddress serverAdd = InetAddress.getByName(severIp);
			
			String str = String.valueOf(fileSize); // fileSize를 문자열로 반환. -> 서버에 보내줄 것이기 때문에
			DatagramPacket dpacket = new DatagramPacket(str.getBytes(), str.getBytes().length, serverAdd, port);
			
			socket.send(dpacket);
			
			// ------------------------------  파일 크기 보내기 완료 ------------------------------------------
			// 파일 내용을 읽어와 전송하기
			fis = new FileInputStream(file); // 파일을 읽어올 스트림 객체 생성
			byte[] buffer = new byte[1024]; // 파일을 한번에 읽어올 크기 설정
			int len = 0;
			// 파일 내용을 읽어와 전송하기
			while((len = fis.read(buffer)) > 0) {
				dpacket = new DatagramPacket(buffer, len, serverAdd, port);
				socket.send(dpacket);
			
				totalReadSize += len;
				System.out.println("진행 상태 : " + totalReadSize + " / " + fileSize + " Bytes");
				Thread.sleep(10); // 이정도만 간격을줘도 신뢰성(파일 빠짐 없음)을 올릴 수 있다.(다만 성능은 하향 됨.) 
			}
			System.out.println("파일 전송 완료...");
			
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if(fis != null) {try{fis.close();}catch(Exception e) {}}
			if(socket != null) {try{socket.close();}catch(Exception e) {}}
		}
		
		System.out.println("프로그램 종료");
		
		
	}

}
