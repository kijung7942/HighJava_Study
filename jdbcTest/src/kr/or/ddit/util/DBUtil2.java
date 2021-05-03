package kr.or.ddit.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

// JDBC드라이버를 로딩하고 Connection 객체를 생성해서 반환하는 메서드로 구성된 class 만들기

// Properties 객체를 이용하여 처리하기

public class DBUtil2 {
	static Properties prop; // Properties객체 변수 선언
	
	static { //static 초기화 블럭
		prop = new Properties(); // Properties 객체 생성
		File file = new File("res/dbinfo.properties"); // File 객체 생성
		FileInputStream fin = null; 
		
		try {
			fin = new FileInputStream(file);
			prop.load(fin);
			
			Class.forName(prop.getProperty("driver"));
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패 => 드라이버 파일이 없습니다." + e.getMessage());
		} catch (FileNotFoundException e) {
			System.out.println("드라이버 로딩 실패 => properties 설정 파일이 없습니다." + e.getMessage());
		} catch (IOException e) {
			System.out.println("드라이버 로딩 실패 => 파일 입출력 오류" + e.getMessage());
		} finally {
			if(fin!=null)try {fin.close();}catch(Exception e) {}
		}
	}

	public static Connection getConnection() {
		try {
			return DriverManager.getConnection(
												prop.getProperty("url"),
												prop.getProperty("user"),
												prop.getProperty("pass"));
		} catch (SQLException e) {
			System.out.println("오라클 연결 실패");
			return null;
		}
	}
	
}
