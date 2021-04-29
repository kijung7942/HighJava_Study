package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

// 문제) 사용자로부터 LPROD_ID값을 입력 받아 입력한 값보다 LPROD_ID가 큰 자료들을 출력하시오 
public class JDBCTest02 {

	public static void main(String[] args) {
		//DB의 LPROD 테이블의 내용을 가져와 출력해 보자.
		
		//DB 작업에 필요한 변수 선언
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		
		try {
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe" , "pc24", "java");
			
			String sql = "SELECT LPROD_ID, LPROD_GU, LPROD_NM lname FROM LPROD";
			
			st = con.createStatement();
			
			rs = st.executeQuery(sql);
			System.out.println("LPROD_ID를 입력하세요");
			Scanner sc = new Scanner(System.in);
			int input = sc.nextInt();
			System.out.println(" == 쿼리문 처리 결과 == ");
			while(rs.next()) {
				if(rs.getInt("LPROD_ID") > input) {
				System.out.println("LPROD_ID : " + rs.getString("LPROD_ID"));
				System.out.println("LPROD_GU : " + rs.getString(2));
				System.out.println("LPROD_NM : " + rs.getString("lname"));
				System.out.println("-------------------------------------------------------------");}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(rs != null) try {rs.close();}catch(Exception e) {}
			if(st != null) try {st.close();}catch(Exception e) {}
			if(con != null) try {con.close();}catch(Exception e) {}
		}
		
	}

}
