package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/* 
 *  회원관리하는 프로그램 작성
 *  (mymember 테이블 이용)
 *  
 *  아래 메뉴의 기능을 모두 구현
 *  
 *  메뉴 예시 )
 *  	-- 작업 선택 --
 *  	1. 자료 추가
 *  	2. 자료 삭제
 *  	3. 자료 수정
 *  	4. 전체 자료 출력
 *  	0. 작업 끝.
 *  
 *  
 *  	처리조건)
 *  	1) 자료 추가에서 '회원 ID'는 중복되지 않도록 처리한다.(중복되는 값을 입력하면 다시 입력받기)
 *  	2) 자료 수정에서 '회원 ID'는 변경할 수 없다.
 *  	3) 자료 삭제는 '회원ID'를 입력 받아서 처리한다.
 */
public class JDBCTest06 {

	
	static Connection con = DBUtil.getConnection();
	static PreparedStatement ps = null;
	static ResultSet rs = null;
	
	public static void main(String[] args) {
		while(true) {
		System.out.println("-- 작업 선택 --");
		System.out.println("1. 자료 추가");
		System.out.println("2. 자료 삭제");
		System.out.println("3. 자료 수정");
		System.out.println("4. 전체 자료 출력");
		System.out.println("0. 작업 끝");
		int input = ScanUtil.nextInt();
		switch(input) {
		case 1:
			add();
			break;
		case 2:
			delete();
			break;
		case 3:
			modify();
			break;
		case 4:
			printAll();
			break;
		case 0:
			if(rs!=null)try {rs.close();}catch(Exception e) {}
			if(ps!=null)try {ps.close();}catch(Exception e) {}
			if(con!=null)try {con.close();}catch(Exception e) {}
			System.out.println("프로그램을 종료합니다.");
			System.exit(0);
		}
		
		
		
		}
		
	}

	private static void printAll() {
		System.out.println("== 자료 전체 출력 ==");
		String sql = "SELECT * FROM MYMEMBER";
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			System.out.println("ID \t Pass \t Name \t H.P \t Addr");
			System.out.println("========================================");
			while(rs.next()) {
				for(int i = 1 ; i < 6; i++) {
				System.out.print(rs.getString(i)+"\t");
				}
				System.out.println();
			}
			System.out.println("========================================");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}

	private static void modify() {
		System.out.println("== 자료 수정 ==");
		while(true) {
			try {
			System.out.println("수정할 ID를 입력하세요");
			String memberID = ScanUtil.nextLine();
			String sql = "SELECT * FROM MYMEMBER";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {	
				if(!memberID.equals(rs.getString(1))) {
					System.out.println("등록된 ID가 없습니다.");
					return;
				}else {
					break;
				}
			}
			
			
			String before = "";
			System.out.println("수정 할 정보를 선택하세요");
			System.out.println("1. 비밀번호 2. 이름 3. 전화번호 4. 주소");
			int input = ScanUtil.nextInt();
			switch(input) {
			case 1: before = "MEM_PASS";
				break;
			case 2: before = "MEM_NAME";
				break;
			case 3: before = "MEM_TEL";
				break;
			case 4: before = "MEM_ADDR";
				break;
			}
			System.out.println("수정 할 내용을 입력하세요");
			String after = ScanUtil.nextLine();
				sql = "UPDATE MYMEMBER SET " + before + " = ? WHERE MEM_ID = ?";
				ps = con.prepareStatement(sql);
				ps.setString(1, after);
				ps.setString(2, memberID);

				int result = ps.executeUpdate();
				System.out.println(result);
				if(result > 0) {System.out.println("수정 성공");}
				else {System.out.println("수정 실패");}
				return;
			}catch(Exception e){
				e.printStackTrace();
			} 
			
		}
		
	}

	private static void delete() {
		System.out.println("== 자료 삭제 ==");
		while(true) {
			System.out.println("삭제할 ID를 입력하세요");
			String sql = "DELETE FROM MYMEMBER WHERE MEM_ID = ?";
			String memberID = ScanUtil.nextLine();
			try {
				ps = con.prepareStatement(sql);
				ps.setString(1, memberID);
				int result = ps.executeUpdate();
				if(result > 0) System.out.println("삭제 성공");
				else System.out.println("삭제 실패");
				return;
			}catch(Exception e){
				System.out.println("시스템 오류 발생" + e.getMessage());				
			} 
			
		}
	
	}
	private static void add() {
		System.out.println("== 자료 입력 ==");
		while(true) {
			System.out.println("ID 입력");
			
			String sql = "SELECT MEM_ID FROM MYMEMBER WHERE MEM_ID = ?";
			String memberID = ScanUtil.nextLine();
			try {
				ps = con.prepareStatement(sql);
			ps.setString(1, memberID);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				if(memberID.equals(rs.getString(1))) {
					System.out.println("중복된 ID가 존재합니다. 다시 입력해주세요");
					memberID = ScanUtil.nextLine();
				}else {
					break;
				}
			}
			System.out.println("비밀번호를 입력해주세요");
			String memberPass = ScanUtil.nextLine();
			System.out.println("이름을 입력해주세요");
			String memberName = ScanUtil.nextLine();
			System.out.println("전화번호를 입력해주세요");
			String memberTel = ScanUtil.nextLine();
			System.out.println("주소를 입력해주세요");
			String memberAddr = ScanUtil.nextLine();

			sql = "INSERT INTO MYMEMBER (MEM_ID, MEM_PASS, MEM_NAME, MEM_TEL, MEM_ADDR) "
					+ " VALUES (?, ?, ?, ?, ?) ";
			
			ps = con.prepareStatement(sql);
			ps.setString(1, memberID);
			ps.setString(2, memberPass);
			ps.setString(3, memberName);
			ps.setString(4, memberTel);
			ps.setString(5, memberAddr);
			
			int result = ps.executeUpdate();
			if(result > 0) {
				System.out.println("입력 성공");
			}else {
				System.out.println("입력 실패");
			}
			return;
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
	}

}
