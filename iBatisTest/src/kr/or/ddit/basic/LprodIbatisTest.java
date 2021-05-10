package kr.or.ddit.basic;

import java.io.Reader;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Scanner;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

public class LprodIbatisTest {

	//iBatis를 사용해서 DB자료를 처리하는 순서 및 방법
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		try {
			// 1. iBatis의 환경설정파일을 읽어와 실행한다.
			// 1-1. 문자 인코딩 캐릭터셋 설정
			Charset charset = Charset.forName("UTF-8");
			Resources.setCharset(charset);
			
			// 1-2. 환경 설정 파일 읽어오기
			Reader r = Resources.getResourceAsReader("sqlMapConfig.xml");
			// 1-3. 위에서 읽어온 Reader객체를 이용하여 실제 환경설정을 완성한 후
			//      SQL문을 호출해서 실행할 객체(SqlMapClient)를 생성한다.
			SqlMapClient smc = SqlMapClientBuilder.buildSqlMapClient(r);
			
			r.close(); // Reader 닫기
			
			// ---------------------------------------------- 1. 환경설정 끝
			
			
			// 2. 실행하고 싶은 SQL문이 설정된 태그를 호출하고 실행해서 그 결과를 받아온다.
			
			// 2-1. insert연습
			System.out.println("insert 연습 시작...");
			
			System.out.println("Lprod_id 입력>> ");
			int lprodId = Integer.parseInt(scan.nextLine());

			System.out.println("Lprod_gu 입력>> ");
			String lprodGu = scan.nextLine();
			
			System.out.println("Lprod_nm 입력>> ");
			String lprodNm = scan.nextLine();
			
			// 입력한 값을 VO객체에 담기
			LprodVO lvo = new LprodVO();
			lvo.setLprod_id(lprodId);
			lvo.setLprod_gu(lprodGu);
			lvo.setLprod_nm(lprodNm);
			
			// SqlMapClient객체 변수를 이용하여 처리할 쿼리문을 호출하여 실행한다.
			// 형식 ) SqlMapClient객체변수.insert("namespace값.id값", 파라미터클래스);
			//            반환 : insert 성공 -> null값 반환,
			//					insert 실패 -> 오류 객체 반환.
			
			
			Object obj = smc.insert("lprod.insertLprod",lvo);
			if(obj == null) {
				System.out.println("insert 작업 성공~~~");
			}else {
				System.out.println("insert 작업 실패!!!");
			}
			
			
			// 2-2. update 연습
			System.out.println("update 시작...");
			System.out.print("수정할 Lprod_gu입력>>");
			String lprodGu2 = scan.nextLine();
			
			System.out.print("새로운 Lprod_id입력>>");
			int lprodId2 = Integer.parseInt(scan.nextLine());
			
			System.out.print("새로운 Lprod_nm입력>>");
			String lprodNm2 = scan.nextLine();
			
			// 입력한 값을 VO에 담기
			LprodVO lvo2 = new LprodVO();
			lvo2.setLprod_gu(lprodGu2);
			lvo2.setLprod_id(lprodId2);
			lvo2.setLprod_nm(lprodNm2);
			
			// update명령 형식) sqlMapClient객체변수.update("namespace값.id값",VO)
			int cnt = smc.update("lprod.updateLprod",lvo2);
			
			if(cnt > 0) {
				System.out.println("update 성공");
			}else {
				System.out.println("update 실패");
			}
			
			// 2-3. delete 연습
			System.out.println("delete 작업 시작...");
			System.out.print("삭제할 Lprod_gu 입력>> ");
			String lprodGu3 = scan.nextLine();
			
			// delete명령 형식 ) sqlMapClient객체변수.delete("namespace값.id값", 파라미터클래스)
			//		반환값: 작업에 성공한 레코드 수
			int cnt2 = smc.delete("lprod.deleteLprod", lprodGu3);
			
			if(cnt2>0) {
				System.out.println("delete 작업 성공!!");
			}else {
				System.out.println("delete 작업 실패..");
			}

			
			
			// 2-4. select연습
			System.out.println("select 연습 -- 결과가 여러개인 경우");
			// 응답 결과가 여러 개일 경우에는 queryForList()메서드를 사용하는데 이 메서드는
			// 여러개의 레코드 각각을 VO에 담은 후 이 VO데이터를 List에 추가하는 작업을 자동으로 수행한다.
			// 형식 ) sqlMapClient객체변수.queryForList("namespace값.id값",파라미터클래스)
			List<LprodVO> lprodList = smc.queryForList("lprod.getAllLprod");

			for(LprodVO lvo3:lprodList) {
				System.out.println("ID : " + lvo3.getLprod_id());
				System.out.println("GU : " + lvo3.getLprod_gu());
				System.out.println("NM : " + lvo3.getLprod_nm());
				System.out.println("----------------------------");
			}
			System.out.println("출력 끝...");
			System.out.println("-----------------------------------");
			
			// 2-5. select연습2 => 결과가 하나인 경우
			System.out.println("select연습 - 결과가 1개인 경우");
			//응답 결과가 1개가 확실할 경우에는 queryForObject()메서드를 사용한다.
			// 형식) sqlMapClient객체변수.queryForObject("namespace값.id값", 파라미터클래스)
			//		==> 결과가 여러개인 경우 -->  Exception발생
			//		==> 결과가 1개인 경우   -->  해당 객체 리턴(정상) 
			//		==> 결과가 0개인 경우   -->  null값 리턴
			
			System.out.print("출력할 LPROD_GU 입력>");
			String input = scan.nextLine();

			LprodVO lvo4 = (LprodVO) smc.queryForObject("lprod.getLprod",input);
			if(lvo4 == null) {
				System.out.println("검색결과가 없습니다.");
			}else {
				System.out.println("ID : " + lvo4.getLprod_id());
				System.out.println("GU : " + lvo4.getLprod_gu());
				System.out.println("NM : " + lvo4.getLprod_nm());
				System.out.println("----------------------------");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

}
