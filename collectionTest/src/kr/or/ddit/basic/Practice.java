package kr.or.ddit.basic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Practice {


//			상품테이블은 prod,    회원테이블은 member.  우편번호테이블은 ziptb
//			sql문의 결과물을 가지고 자바프로그램에서 수행하기 위한 메소드를 만든다
//			public  리턴결과형  메소드이름(매개변수타입  변수명)
			
////			1) 상품 테이블로부터 모든 데이타 검색
//				String sql = "SELECT * FROM PROD";
//			
//				public List<Map<String, Object>> selectPROD(){}
//			
//			
////			   1-1 상품테이블로부터 prod_id = 'P101000001' 인 데이터 검색
//				String sql = "SELECT * FROM PROD WHERE PROD_ID = 'P101000001'";
//			
//				public Map<String, Object> selectByProdId(String prodId){}
//			
////			2) 회원 테이블로부터 모든 데이타 검색
//				String sql = "SELECT * FROM MEMBER";
//			
//				public List<Map<String, Object>> selectMember(){}
				
//			3) 상품 테이블로부터 상품코드와 상품명을 검색
//				String sql = "SELECT PROD_ID, PROD_NAME FROM PROD";
			
//				public List<Map<String, Object>> selectProdIdName(){}
				
////			4) 회원테이블에서 김씨 성을 가진 회원을 검색( Alias는 회원ID, 성명 )
//				String sql = "SELECT MEM_ID \"회원ID\", MEM_NAME \"성명\" FROM MEMBER";
//			
//				public List<Map<String, Object>> selectMemberIdName(){}
				
//			5)회원테이블에서 ID중복검사를 실행하기 위한 검색 
//			String sql = "SELECT MEM_ID FROM MEMBER WHERE MEM_ID = ?";
//			
//			public Map<String,Object> idCheck(String id){};
//			
//			
////			6) 우편번호테이블 에서  동이름조건으로 하여  주소를 찾는 검색 
//			String sql = "SELECT * FROM ZIPTB WHERE DONG = ?";
//			
//			public List<Map<String,Object>> selectByZip(String dong){};
//			
//			
//			
////			7) 회원테이블에 새로운 회원을  저장하기 위한 문
//			String sql = "INSERT INTO MEMBER VALUES(?,?,?...) ";
//			Map<String,Object> param = new HashMap();
//			
//			public int memberJoin(Map<String, Object> param){}; 
//			
////			8)상품테이블에서 상품아이디를 이용해 해당 상품을 상세보기 하기위한 검색 
//			String sql = "SELECT * FROM PROD WHERE PROD_ID = ?";
//						
//			public List<Map<String,Object>> prodInfo(String id){};
//			
//			
////			9) 상품테이블의 거래처코드를 중복되지 않게 검색 
//			String sql = "SELECT DISTINCT BUYER_ID FROM BUYER";
//			public List<Map<String,Object>> buyerDistinct(){};
//			
//			
////			10) 상품중 판매가가 170,000초과 또는 미만인 상품 조회
//			String sql = "SELECT * FROM PROD WHERE PROD_SALE > 170000 OR PROD_SALE < 170000";
//			
//			public List<Map<String,Object>> prodSale(){};
//			
//			
////			11)회원중 76년도 1월 1일 이후에 태어난 회원을 검색 
//			String sql = "SELECT * FROM MEMBER WHERE MEM_REGNO1 > 760101";
//			
//			public List<Map<String,Object>> memRegno(){};
//			
//			
////			12)  상품중 상품분류가 p201(여성캐주얼)이고 판매가가 170,000인 상품 조회
//			Stirng sql = "SELECT * FROM PROD WHERE PROD_LGU = 'p201' AND PROD_SALE = 170000";
//
//			public List<Map<String,Object>> selectProd(){}
//			
//			
////			13) 회원테이블의 전체 총 레코드수 구하기 
//			String sql = "SELECT COUNT(*) count FROM MEMBER";
//			
//			public Map<String,Object> memRowCnt();
//			
//			
////			14) 회원테이블에서 mem_id를 조건으로 비밀번호 , 주소 , 우편번호, 전화번호 를 수정 
//			String sql = "UPDATE MEMBER SET mem_pass = ?, mem_ADD1 = ?, mem_add2 = ?,"
//					+ " mem_zip = ?, mem_hp = ? where mem_id =?";
//			
//			public int updateMemberByMemID(){}
//			
//			
////			15) 상품테이블에서 분류코드(prod_lgu)를 조건으로 해당 상품들을 검색 
//			String sql = "SELECT * FROM PROD WHERE prod_lgu = ?";
//			
//			public List<Map<String,Object>> selectByProdlgu(){}			
//			
////			16) 다음의 내용으로 testboard테이블을 생성 - 자바 메소드 없이 sql문만 기술
////			 1. 컬럼명 :  
////			 num(번호) , subject(제목), writer(작성자), mail(이메일),
////			 password(비밀번호),  content(내용) , wdate(날짜)  ,
////			 hit(조회수) , answer(댓글수)
////			 seq는 기본키 이며 , 자동증가하는 데이타 레코드의 번호 이다
////			 hit 은 조회수를 나타내는 컬럼  , answer는 해당 글의 댓글 갯수를 나타내는 컬럼
//			 
//				 CREATE TABLE testboard(seq number, num number, subject varchar2(200), writer varchar2(200), mail varchar2(200),
//				password varchar2(200), content varchar2(200), wdate date, hit number, answer number);
//				
//				
////			 2. 시퀀스 생성 -  board_num_seq 라는 이름으로 시퀀스를 생성 
//				create sequence board_num_seq start with 1	increment by 1;
//			
////			17) board테이블에 데이타를 저장하기 위한 코드 
//				String sql = "INSERT INTO testboard(seq, num, subject, writer, mail, password, content, wdate, hit, answer)" + 
//							 " VALUES (board_num_seq.nextval,1,null,null,null,null,null,null,null,null)";
//						
//				public int insertBoard(Map<String,Object> param){
//					
//				}
//			
//			
////			18.) board테이블에서  seq를 조건(seq=10)으로 조회수를 1씩 증가하는 코드 
//				String sql = "UPDATE testboard SET hit = nvl(hit,0) + 1 WHERE seq = 10";
//			
//				public int updateHitBySEQ(){}
////			19) board테이블의 seq를 조건으로 answer 값을 1씩 증가하는 코드 
//			    String sql = "UPDATE testboard SET answer = nvl(answer,0) + 1 WHERE seq = ?";
//			
//	    		public int updateAnswerBySEQ(){}
////			20) board테이블에서 seq를 조건으로 댓글(answer) 수를  조회 
//				String sql = "SELECT answer FROM testboard WHERE seq = ?";
//						
//				public Map<String, Object> selectAnswerBySEQ(){}
//
//		 

}
