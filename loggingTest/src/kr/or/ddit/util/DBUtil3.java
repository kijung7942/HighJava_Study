package kr.or.ddit.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

// JDBC드라이버를 로딩하고 Connection 객체를 생성해서 반환하는 메서드로 구성된 class 만들기

// Properties 객체를 이용하여 처리하기
public class DBUtil3 {
	private static final Logger logger = Logger.getLogger(DBUtil3.class);
	
	static ResourceBundle bundle; 
	static { //static 초기화 블럭
		bundle = ResourceBundle.getBundle("dbinfo");
		logger.info("ResourceBundle객체 생성 완료 - dbinfo.properties파일 읽기");
		try {
			Class.forName(bundle.getString("driver"));
			logger.info("Driver로딩 성공.");
		} catch (ClassNotFoundException e) {
//			System.out.println("드라이버 로딩 실패 => 드라이버 파일이 없습니다." + e.getMessage());
			logger.error("Driver로딩 실패."+e.getMessage());
		} 
	}

	public static Connection getConnection() {
		Connection con;
		try {
//			return DriverManager.getConnection(bundle.getString("url"),bundle.getString("user"),bundle.getString("pass"));
			con = DriverManager.getConnection(bundle.getString("url"),bundle.getString("user"),bundle.getString("pass"));
			logger.info("오라클 연결 성공");
		} catch (SQLException e) {
//			System.out.println("오라클 연결 실패");
			logger.info("오라클 연결 실패"+e.getMessage());
			return null;
		}
		return con;
	}
	
}
