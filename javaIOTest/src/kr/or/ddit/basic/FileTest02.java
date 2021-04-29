package kr.or.ddit.basic;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileTest02 {

	public static void main(String[] args) {

		File f1 = new File("e:/daedoek/d_other/test.txt");
		
		System.out.println(f1.getName() + "의 크기는 : " + f1.length() + " Bytes");
		System.out.println("path : " + f1.getPath());
		System.out.println("absolutePath : " + f1.getAbsolutePath());
		
		File f2 = new File(".");
		System.out.println("path : " + f2.getPath());
		System.out.println("absolutePath : " + f2.getAbsolutePath());
		System.out.println("----------------------------------------");
		// Java프로그램의 실행 위치 구하기
		// 방법 1 . File객체 이용하기
		File f = new File("");
		System.out.println("현재 실행중인 위치의 절대경로 : " + f.getAbsolutePath());
		
		// 방법 2. System.getProperty("user.dir") 명령 이용하기
		System.out.println("현재 실행중인 위치의 절대경로 : " + System.getProperty("user.dir"));
		
		// 방법 3. Path 객체 사용하기 (상대 경로에서 절대 경로로 변환하기)
		Path rePath = Paths.get("");
		String path = "";
		
		
	}

}
