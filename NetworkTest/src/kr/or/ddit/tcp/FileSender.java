package kr.or.ddit.tcp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class FileSender extends Thread{
	Socket socket;
	OutputStream fos;
	File file = new File("E:/daedoek/D_Other/펭귄.jpg");
	FileInputStream fis;
	int data;
	FileSender(Socket socket){
		this.socket = socket;
		try {
			this.fos = socket.getOutputStream();
			fis = new FileInputStream(file);
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
			} finally {
				try {
					if(fos != null) fos.close();
					if(fis != null) fis.close();
				} catch (IOException e2) {
					// TODO: handle exception
				}
			}
		}
	}
