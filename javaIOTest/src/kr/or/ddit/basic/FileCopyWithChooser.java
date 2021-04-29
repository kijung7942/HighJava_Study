package kr.or.ddit.basic;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JPanel;

public class FileCopyWithChooser {

	public static void main(String[] args) {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File("e:/daedoek/d_other"));
		int result = fileChooser.showOpenDialog(new JPanel());
		File inFile = null;
		File outFile = null;
		
		if(result == JFileChooser.APPROVE_OPTION) {
			inFile = fileChooser.getSelectedFile();
		}
	
		result = fileChooser.showSaveDialog(new JPanel());
		if(result == JFileChooser.APPROVE_OPTION) {
			outFile = fileChooser.getSelectedFile();
		}
		
		
		if(!inFile.exists()) {
			System.out.println("파일이 없습니다.");
			System.out.println("복사를 중단합니다.");
		}else {
			System.out.println("복사 시작");
			try {
				
				FileInputStream fin = new FileInputStream(inFile);
				BufferedInputStream bis = new BufferedInputStream(fin);
				FileOutputStream fout = new FileOutputStream(outFile);
				BufferedOutputStream bos = new BufferedOutputStream(fout);
				
				
				
				int data;
				
				while((data = bis.read()) != -1){
					bos.write(data);
				}
				bos.flush();
				
				System.out.println("복사 완료");
				bis.close();
				bos.close();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		
		}
	}
}