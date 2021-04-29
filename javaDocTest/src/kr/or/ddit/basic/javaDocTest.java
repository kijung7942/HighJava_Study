package kr.or.ddit.basic;

// JavaDoc 파일 만들기 예제

/**  '/**' 하고 엔터치면 기본적인 틀 나옴. : 일반적인 html 태그 사용가능
 * 
 * @author 작성자이름
 * @version 1.0
 *
 * <p>
 * 파일명 : JavaDocTest.java<br>
 * 설 명 : JavaDoc문서 작성을 위한 연습용 Interface<br><br>
 * 
 *  수정 이력<br>
 *  ---------------------------------------------------<br>
 *  수정일자 : 2021-04-27<br>
 *  작성자: 홍길동<br>
 *  수정내용 : 최초 생성 <br>
 *  ---------------------------------------------------<br>
 * </p>
 *
 */

public interface javaDocTest {
	/**
	 * 메서드명 : methodTest
	 * 설 명 : 반환값이 없는 메서드
	 * @param a 첫번째 매개변수 (정수형)
	 * @param b 두번째 매개변수 (실수형)
	 */
	public void methodTest(int a, double b);
	
	/**
	 * 
	 * @param a 인트값
	 * @param b 인트값
	 * @return 더하기
	 */
	public int methodAdd(int a, int b);
	}


		


