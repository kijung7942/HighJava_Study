package kr.or.ddit.basic;

import java.util.Scanner;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.util.BuildedSqlMapClient;

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
		Scanner scan = new Scanner(System.in);
		SqlMapClient smc =null;
		try {

		smc = BuildedSqlMapClient.getSqlMapClient();
//		Charset c = Charset.forName("UTF-8");
//		Resources.setCharset(c);
//		Reader r = Resources.getResourceAsReader("sqlMapConfig.xml");
//		SqlMapClient smc = SqlMapClientBuilder.buildSqlMapClient(r);
//		r.close();
		
			while(true) {
				System.out.println("=== 새로운데이터 추가하기 ===");
				System.out.println("LPROD_GU : ");
				String lprodGu = scan.nextLine();
				int cnt = (int) smc.queryForObject("lprod.getCount",lprodGu);
				if(cnt>0) {
					System.out.println("이미 등록된 GU입니다. 다시 입력해주세요.");
					continue;
				}

				System.out.println("LPROD_NM : ");
				String lprodNm = scan.nextLine();
				LprodVO lvo = new LprodVO();
				lvo.setLprod_gu(lprodGu);
				lvo.setLprod_nm(lprodNm);
				Object ojb = smc.insert("lprod.insertLprodTest",lvo);
				if(ojb == null) {
					System.out.println("업데이트 실패!");
				}else {
					System.out.println("업데이트 성공");
				}
				
				System.out.println("추가로 입력?");
				System.out.println("1.예 2.아니오");
				int input = Integer.parseInt(scan.nextLine());
				if(input != 1)break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		
	}

}
