package util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

// JDBC드라이버를 로딩하고 Connection 객체를 생성해서 반환하는 메서드로 구성된 class 만들기

// Properties 객체를 이용하여 처리하기

public class DBUtil3 {
	static ResourceBundle bundle; 
	static { //static 초기화 블럭
		try {
			bundle = ResourceBundle.getBundle("dbinfo");
			Class.forName(bundle.getString("driver"));
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패 => 드라이버 파일이 없습니다." + e.getMessage());
		} 
	}

	public static Connection getConnection() {
		try {
			return DriverManager.getConnection(bundle.getString("url"),bundle.getString("user"),bundle.getString("pass"));
		} catch (SQLException e) {
			System.out.println("오라클 연결 실패");
			return null;
		}
	}
	
}
