package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

// JDBC(Java DataBase Connectivity)


public class JdbcTest01 {

	/*
	 *  JDBC를 이용한 데이터베이스 처리 순서
	 *  1. 드라이버로딩 => 라이브러리를 사용할 수 있게 메모리로 읽어들이는 작업.
	 *  	Class.forName("oracle.jdbc.driver.OracleDriver");
	 *  
	 *  2. DB에 접속하기 => DriverManager.gerConnection() 메서드를 이용.
	 *    => 접속이 완료되면 Connection 객체가 생성되어 반환된다.
	 *    
	 *  3. 질의 -> SQL 문장을 DB서버로 보내서 결과를 얻어온다.
	 *    -> Statement 객체 또는 PreparedStatement 객체를 이용하여 작업한다.
	 *    
	 *  4. 결과 처리 -> 질의 결과를 받아서 원하는 작업을 수행한다.
	 *		1) SQL문이 select문일 때 -> select문을 처리한 결과가 ResultSet 객체로 반환 됨.
	 *		2) SQL문이 select문이 아닐 때(인,업,딜) -> SQL문을 처리한 결과가 int 값으로 반환 됨.
	 *  
	 *  5. 사용한 자원 반납하기 ==> close()메서드를 이용한다.
	 */
	
	public static void main(String[] args) {
		//DB의 LPROD 테이블의 내용을 가져와 출력해 보자.
		
		//DB 작업에 필요한 변수 선언
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		
		try {
			// 1. 드라이버 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// 2. DB연결 -> connection 객체 생성
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe" , "pc24", "java");
			
			// 3. SQL문 작성
			String sql = "SELECT LPROD_ID, LPROD_GU, LPROD_NM lname FROM LPROD";
			
			// 4.Statement 객체 또는 PreparedStatement 객체 생성 -> 질의 작업을 진행해주는 객체
			st = con.createStatement();
			
			// 5. SQL문장을 DB서버로 보내서 결과를 얻어온다.
			// 	  (실행할 SQL문장이 SELECT문이기 때문에 결과가 ResultSet객체에 저장되어 반환된다.)
			rs = st.executeQuery(sql);

			// 6. 결과를 처리 -> ResultSet에 저장된 데이터를 한 레코드씩 화면에 출력하기
			//					ResultSet에 저장된 데이터를 차례로 꺼내오려면 반복문과 next()메서드를 이용한다.
			System.out.println(" == 쿼리문 처리 결과 == ");
			while(rs.next()) { // rs.next() => ResultSet객체의 데이터를 가리키는 포인터를 다음번째 레코드로 이동시키고 그 곳에 데이터가 있으면 true값을 반환
				// 현재 포인터가 가리키는 곳의 자료를 가져오는 방법
				// 형식1) rs.get자료형이름("컬럼명")
				System.out.println("LPROD_ID : " + rs.getString("LPROD_ID"));
				// 형식2) rs.get(컬럼번호)
				System.out.println("LPROD_GU : " + rs.getString(2));
				// 형식3) rs.get자료형이름("컬럼의 alias명")
				System.out.println("LPROD_NM : " + rs.getString("lname"));
				
				System.out.println("-------------------------------------------------------------");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			if(rs != null) try {rs.close();}catch(Exception e) {}
			if(st != null) try {st.close();}catch(Exception e) {}
			if(con != null) try {con.close();}catch(Exception e) {}
		}
		
		
		
		
		
		
		
	}

}
