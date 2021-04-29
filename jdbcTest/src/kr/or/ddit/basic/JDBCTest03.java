package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/*
 *  문제) LPROD_ID 값을 2개를 차례로 입력 받아서 두 값 중 작은 값부터 큰 값까지의 자료들을 출력
 *  
 *  실행 예시)
 *  	첫번째 LPROD_ID값 : 4
 *  	두번째 LPROD_ID값 : 7
 */

public class JDBCTest03 {

	public static void main(String[] args) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "pc24";
		String pass = "java";
		
		try {
			con = DriverManager.getConnection(url, user, pass);

			String sql = "SELECT * FROM LPROD WHERE LPROD_ID >= ? AND LPROD_ID <= ?";
			
			ps = con.prepareStatement(sql);
			
			Scanner sc = new Scanner(System.in);
			System.out.println("숫자입력1");
			int a = sc.nextInt();
			System.out.println("숫자입력2");
			int b = sc.nextInt();
			
			if(a>b) {
				ps.setObject(1, b);
				ps.setObject(2, a);
			}else {
				ps.setObject(2, b);
				ps.setObject(1, a);
			}

			rs = ps.executeQuery();
			System.out.println(" == 출력 결과 ==");
			while(rs.next()) {
				System.out.print(rs.getString(1) + "\t");
				System.out.print(rs.getString(2) + "\t");
				System.out.print(rs.getString(3) + "\t");
				System.out.println();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs != null) try {rs.close();} catch (Exception e2) {}
			if(ps != null) try {ps.close();} catch (Exception e2) {}
			if(con != null) try {con.close();} catch (Exception e2) {}
		}
	}

}
