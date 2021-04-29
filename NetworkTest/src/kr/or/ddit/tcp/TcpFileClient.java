package kr.or.ddit.tcp;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;

public class TcpFileClient {

	private Socket socket;
	private BufferedInputStream bis;
	private	DataOutputStream dos;
	
	private void clientStart() {
		//전송할 파일을 이용한 File객체 생성
		File file = new File("E:/daedoek/D_Other/펭귄.jpg");
		String fileName =file.getName();
		
		if(!file.exists()) {
			System.out.println("전송할 파일이 없습니다.");
			return;
		}
		
		try {
			socket = new Socket("localhost",7777);
			System.out.println("파일 전송 시작");
			// 파일 읽기용 스트림 객체
			bis = new BufferedInputStream(new FileInputStream(file));
			
			// 서버로 전송할 스트림 객체
			dos = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
			
			//서버로 연결되면 첫번째로 파일 이름을 전송한다.
			dos.writeUTF(fileName);
			dos.flush();
			
			// 파일 내용을 읽어와 소켓을 통해서 전송한다.
			byte[] temp = new byte[1024];
			int len = 0;
			
			while((len = bis.read(temp)) > 0) {		// 파일 내용 읽기
				dos.write(temp, 0, len);
				System.out.println(temp);// 소켓으로 출력
			}
			dos.flush();
			
			System.out.println("파일 전송 끝...");
			
			
			
		} catch (IOException e) {
			// TODO: handle exception
			System.out.println("파일 전송 실패" + e.getMessage());
		} finally {
			if(dos != null) try {dos.close();} catch(IOException e) {}
			if(bis != null) try {bis.close();} catch(IOException e) {}
			if(socket != null) try {socket.close();} catch(IOException e) {}
		}
		
	}
	
	
	
	
	public static void main(String[] args) {

		new TcpFileClient().clientStart();
		
		
		
		
//		내가한거	
//		try {
//			Socket socket = new Socket("localhost",7777);
//			System.out.println("서버에 연결되었습니다.");
//			System.out.println("---------------------------------------");
//						
//			FileSender s = new FileSender(socket);
//			s.start();
//			System.out.println("파일보내기 완료");
//			
//			
//		}catch(IOException e) {
//			
//		}
//
//	
//	
	}
	
}
