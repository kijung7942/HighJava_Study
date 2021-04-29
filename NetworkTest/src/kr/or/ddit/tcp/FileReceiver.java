package kr.or.ddit.tcp;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class FileReceiver extends Thread{
	Socket socket;
	FileOutputStream fos;
	InputStream fis;
	File file = new File("E:/daedoek/D_Other/연습용/펭귄복사본.jpg");
	int data;
	FileReceiver(Socket socket){
		this.socket = socket;
		try {
			this.fis = socket.getInputStream();
			fos = new FileOutputStream(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	@Override
	public void run() {
		try {
		while((data = fis.read()) != -1) {
				fos.write(data);
				}
			} catch (IOException e) {
				e.printStackTrace();
			} 
//		finally {
//				try {
//					if(fos != null) fos.close();
//					if(fis != null) fis.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//				
//			}
		}
	}

