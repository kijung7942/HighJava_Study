package kr.or.ddit.tcp;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpFileServer {

	private ServerSocket server;
	private Socket socket;
	private DataInputStream dis;
	private BufferedOutputStream bos;
	
	private void serverStart() {
		//저장할 폴더를 이용한 File 객체 생성
		File saveDir = new File("e:/daedoek/d_other/연습용");
		if(!saveDir.exists()) { //저장폴더가 있는지 검사
			saveDir.mkdirs(); //저장 폴더가 없으면 새로 생성(경로 모두해야하므로 s를 붙임)
		}
		
		try {	
			server = new ServerSocket(7777);
			System.out.println("서버가 준비되었습니다.");
			
			socket = server.accept(); // 클라이언트의 요청을 기다린다.
			
			System.out.println("파일 다운로드 시작...");
			
			// 소켓을 이용한 수신용 스트림 객체
			dis = new DataInputStream(new BufferedInputStream(socket.getInputStream()));

			
			// 클라이언트에서 첫번째로 보내온 파일이름을 받아서 변수에 저장한다.
			String fileName = dis.readUTF();
			
			// 파일이 저장될 객체 생성
			File saveFile = new File(saveDir,fileName);
			
			// 파일 저장용 스트림 객체 생성
			bos = new BufferedOutputStream(new FileOutputStream(saveFile));
			
			
			//소켓으로 파일 내용을 수신 받아서 파일로 저장한다.
			byte[] temp = new byte[1024];
			
			int len = 0;
			
			while((len = dis.read(temp)) > 0) {
				bos.write(temp, 0, len);
			}
			bos.flush();
			
			System.out.println("파일 다운로드 완료...");
			
			
		} catch (IOException e) {
				System.out.println("다운로드 실패" + e.getMessage());
		} finally {
			if(bos != null) try {bos.close();} catch(IOException e) {}
			if(dis != null) try {dis.close();} catch(IOException e) {}
			if(socket != null) try {socket.close();} catch(IOException e) {}
			if(server != null) try {server.close();} catch(IOException e) {}
		}
		
		
		
		
		
	}
	
	
	
	public static void main(String[] args) throws IOException {

		new TcpFileServer().serverStart();
		
		
		
//	내가한거		
//		ServerSocket server = new ServerSocket(7777);
//		System.out.println("서버가 켜졌습니다.");
//		Socket socket = server.accept();
//		System.out.println("연결되었습니다.");
//		FileReceiver r = new FileReceiver(socket);
//		
//		r.start();
//		System.out.println("파일 받기 및 작성 완료.");
		
		
	
	
	}

}
