package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class JDBCTest05 {

	/*
	 *  LPROD테이블에 새로운 데이터를 추가하는 프로그램 작성
	 *  
	 *  lprod_gu와 lprod_nm은 직접 받아서 처리하고,
	 *  lprod_id는 현재의 lprod_id 값들 중에서 제일 큰 값보다 1 크게 한다.
	 *  
	 *  입력받은 lprod_gu가 이미 등록되어 있으면 다시 입력받아서 처리한다.
	 */
	
	
	
	public static void main(String[] args) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Scanner scan = new Scanner(System.in);
		
		try {
			con = DBUtil.getConnection();
			while(true) {
				System.out.println("=== 새로운데이터 추가하기 ===");
				System.out.println("LPROD_GU : ");
				String lprodGu = scan.next();
				
				String sql = "SELECT COUNT(*) FROM LPROD WHERE LPROD_GU = ?";
				
				ps = con.prepareStatement(sql);
				ps.setString(1, lprodGu);
				
				rs = ps.executeQuery();
				
				rs.next();
				
				String exists = rs.getString("count(*)");
				ps.close();
				if("1".equals(exists)) {
					System.out.println("이미 등록된 GU입니다. 다시 입력해주세요.");
				}
				else {
			
				System.out.println("LPROD_NM : ");
				String lprodNm = scan.next();
				 sql = "INSERT INTO LPROD (LPROD_ID,LPROD_GU,LPROD_NM)"
						+ " VALUES ((SELECT MAX(LPROD_ID) FROM LPROD)+1,?,?)";
				
				// PreparedStatement 객체 생성하기 ==> 이때 처리할 쿼리문을 인수값으로 준다.
				ps = con.prepareStatement(sql);
				
				// 쿼리문의 ?자리에 인수 넣어주기
				// 형식) ps.set자료형(물음표번호,데이터값)
				ps.setString(1, lprodGu);
				ps.setString(2, lprodNm);
				
				int cnt = ps.executeUpdate();
				
				System.out.println("반환값 :" + cnt);
				break;
				}
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
