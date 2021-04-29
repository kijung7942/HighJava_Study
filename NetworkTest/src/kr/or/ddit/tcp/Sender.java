package kr.or.ddit.tcp;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

// 이 클래스는 소벳을 통해서 메시지를 보내는 역할만 하는 쓰레드
public class Sender extends Thread{
	Socket socket;
	//파일만 주고 받을거면 데이터 스트림 아니어도 됨
	DataOutputStream dos; 
	
	private String name;
	private Scanner scan;
	
	
	Sender(Socket socket){
		this.socket = socket;
		scan = new Scanner(System.in);
		
		System.out.println("이름 입력");
		name = scan.nextLine();
		
		try {
			this.dos = new DataOutputStream(socket.getOutputStream());
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		
	
	}
	
	
	@Override
	public void run() {
		while(dos!=null) {
			try {
				dos.writeUTF(name +" : " + scan.nextLine());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	
	}
}


