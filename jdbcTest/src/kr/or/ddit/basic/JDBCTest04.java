package kr.or.ddit.basic;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class JDBCTest04 {

	public static void main(String[] args) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Statement stmt = null;
		Scanner scan = new Scanner(System.in);
		
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "pc24";
		String pass = "java";
		
		try {
			con = DriverManager.getConnection(url, user, pass);

			System.out.println("===계좌번호 정보 추가하기===");
			System.out.println("계좌번호 : ");
			String bankNo = scan.next();
			System.out.println("은행명 : ");
			String bankName = scan.next();
			System.out.println("예금주명 : ");
			String bankUser = scan.next();

//			// Statement객체를 이용해서 데이터 추가하기
//			String sql = "INSERT INTO BANKINFO (BANK_NO, BANK_NAME, BANK_USER_NAME, BANK_DATE)"
//					+ " VALUES ('" + bankNo + "','" + bankName + "','" + bankUser + "',SYSDATE)";
//			
//			System.out.println(sql);
//			
//			stmt = con.createStatement();
//			
//			//SQL문을 실행할 때 쿼리문이 'select'문이면 executeQuery() 메서드를 사용하고  => 반환값 : ResultSet
//			// 실행할 쿼리문이 'select'가 아닌 경우에는 executeUpdate() 메서드를 사용한다. => 반환값 : int(실제 처리된 레코드 갯수)
//			
//			int cnt = stmt.executeUpdate(sql);
//			System.out.println("반환값 :" + cnt);

			
			
			
			// PreparedStatement 객체를 이용해서 데이터 추가하기
			// 데이터 들어갈 곳을 ?로 표시 작성
			String sql = "INSERT INTO BANKINFO (BANK_NO, BANK_NAME, BANK_USER_NAME, BANK_DATE)"
					+ " VALUES (?,?,?,SYSDATE)";
			
			// PreparedStatement 객체 생성하기 ==> 이때 처리할 쿼리문을 인수값으로 준다.
			ps = con.prepareStatement(sql);
			
			// 쿼리문의 ?자리에 인수 넣어주기
			// 형식) ps.set자료형(물음표번호,데이터값)
			ps.setString(1, bankNo);
			ps.setString(2, bankName);
			ps.setString(3, bankUser);
			
			int cnt = ps.executeUpdate();
			
			System.out.println("반환값 :" + cnt);
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs != null) try {rs.close();} catch (Exception e2) {}
			if(ps != null) try {ps.close();} catch (Exception e2) {}
			if(con != null) try {con.close();} catch (Exception e2) {}
		}
	}

}
