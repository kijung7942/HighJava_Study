package kr.or.ddit.basic;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/*
 * 	문제) 'E:/daedoek/D_Other' 폴더에 있는 '펭귄.jpg' 파일을 'E:/daedoek/D_Other/연습용'
 *  폴더에 '펭귄_복사본.jpg'파일로 복사하는 프로그램 작성
 */
public class FileCopy {

	public static void main(String[] args) {

		File inFile = new File("E:/daedoek/D_Other/펭귄.jpg");
		File outFile = new File("E:/daedoek/D_Other/연습용/펭귄_복사본.jpg");
		
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
