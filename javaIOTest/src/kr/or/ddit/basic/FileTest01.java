package kr.or.ddit.basic;

import java.io.File;
import java.io.IOException;

public class FileTest01 {

	public static void main(String[] args) {
		//File 객체 만들기 연습
		
		// 1. new File(String 파일 또는 경로명)
		//		-> 디렉토리와 디렉토리 사이 또는 디렉토리와 파일명 사이의 구분문자는 '/'를 사용하거나 '\'를 사용할 수 있다.
		File file1 = new File("e:/daedoek/D_Other/test.txt");
								// 윈도우에서는 대소문자 구분 안해도 되지만 유닉스 기반 os에서는 대소문자 구분 확실히 해야한다.
//		File file1 = new File("e:\\daedoek\\D_Other\\test.txt");
								// 구분 문자를 '\'로 이용하기 위해서는 \를 연달아 두번 써줘야 한다.
		
		System.out.println("파일명 : "+ file1.getName());
		System.out.println("디렉토리 인가? : " + file1.isDirectory());
		System.out.println("파일 인가? : " + file1.isFile());
		System.out.println();
		
		File file2 = new File("e:/daedoek/d_other");
		System.out.println("폴더명 : "+ file2.getName());
		System.out.println("디렉토리 인가? : " + file2.isDirectory());
		System.out.println("파일 인가? : " + file2.isFile());
		System.out.println();
		
		// 2. new File(File parent, String child)
		File file3 = new File(file2,"test.txt");
		System.out.println("파일명 : "+ file3.getName());
		System.out.println("디렉토리 인가? : " + file3.isDirectory());
		System.out.println("파일 인가? : " + file3.isFile());
		System.out.println();
		
		// 3. new File(String parent, String child)
		//            -> 'parent'디렉토리 안에 있는 'child' 파일을 갖는다.
		File file4 = new File("e:/d_other","test.txt");
		System.out.println("파일명 : "+ file4.getName());
		System.out.println("디렉토리 인가? : " + file4.isDirectory());
		System.out.println("파일 인가? : " + file4.isFile());
		System.out.println();
		
		// file1, file3, file4는 생성하는 방법만 다르고 서로 같은 파일을 가리키고 있는 객체이다.
		
		/*
		 *  - 디렉토리(폴더) 만들기
		 * 		1. mkdir() -> File객체의 경로 중 마지막 위치에 디렉토리를 만든다.
		 *					-> 반환 값 : 만들기 성공 시 true, 실패시 false -> 보통 if문을 사용
		 *					-> 중간 부분의 경로가 모두 만들어져 있어야 마지막 위치에 디렉토리를 생성할 수 있다.
		 *
		 * 		2. mkdirs() => 중간 부분의 경로가 없으면 중간부분의 경로도 같이 다 만들어주는 메서드. 
		 *					-> 반환 값 : 만들기 성공 시 true, 실패시 false -> 보통 if문을 사용
		 */
		File file5 = new File("e:/daedoek/d_other/연습용"); //디스크에 실제 파일이나 폴더가 존재하지 않을때 -> 경로 설정을 잘못했을 때 
		System.out.println("파일명 : "+ file5.getName());				
		System.out.println("디렉토리 인가? : " + file5.isDirectory());  // false
		System.out.println("파일 인가? : " + file5.isFile());			// false
		System.out.println(file5.getName() + " 파일 존재여부 : " + file5.exists());
		System.out.println();
		if(!file5.exists()) {
		if(file5.mkdir()) {System.out.println(file5.getName() + " 만들기 성공");}
		else {System.out.println(file5.getName() + "만들기 실패");}
		}
		
		
		
		File file6 = new File("e:/daedoek/d_other/test/java/src.txt"); // -> 파일 만드는게 아니고 폴더를 만드는 거임.
		
		if(file6.mkdirs()) {
			System.out.println(file6.getName() + " 만들기 성공");
					}
		else {
			System.out.println(file6.getName() + " 만들기 실패");
		}
		
		
		
	}

}
