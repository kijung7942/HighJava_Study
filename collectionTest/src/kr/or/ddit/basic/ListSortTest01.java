package kr.or.ddit.basic;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class ListSortTest01 {

	public static void main(String[] args) {

		ArrayList<String> list = new ArrayList<>();
		
		list.add("일지매");
		list.add("홍길동");
		list.add("성춘향");
		list.add("변학도");
		list.add("이순신");
		
		System.out.println("정렬 전 : " + list);
		
		/* 정렬(sort)은 Collections.sort()메서드를 이용
		 * Collections.sort()메서드는 기본적으로 내부 정렬 기준으로 정렬한다.
		 * 문자열은 내부 정렬 기준이 오름차순을 기준으로 설정되어 있다.
		 */
		
		Collections.sort(list);
		System.out.println("정렬 후 : " + list);

		Collections.shuffle(list);
		System.out.println("셔플 후 : " + list);

	
		
		// 내림차순 정렬 -> 기존 내부적 정렬기준이 오름차 순이기 때문에 Desc 클래스 만들기
		Collections.sort(list, new Desc());
		System.out.println("내림차순 정렬 후 : " + list);
		
	}
}
	/*
	 * 정렬과 관련된 interface는 Comparable, Comparator 이렇게 두가지가 있다.
	 * 
	 *  - Comparable은 Collection에 추가되는 데이터 자체에 정렬 기준을 넣고 싶을 때	(내부 정렬 기준)
	 *  - Comparator는 외부에 별도로 정렬 기준을 구현							(외부 정렬 기준)
	 *  
	 *  - Comparable에서는 compareTo()메서드를 재정의(오버라이딩)하고,
	 *  - Comparator에서는 compare()메서드를 재정의(오버라이딩) 해야 한다.
	 *  
	 *  - String클래스, Wrapper 클래스, Date클래스, File클래스에는 내부 정렬 기준이 구현되어 있다.
	 *    (내부 정렬 기준은 기본적으로 오름차순으로 처리되도록 구현되어 있다.)
	 */
	
	// 내림차순으로 정렬하고 싶어서 정렬 방식을 정해주는 class를 새로 작성한다.(외부 정렬 기준 클래스)
	// -> Comparator인터페이스를 구현해서 작성.

class Desc implements Comparator<String>{

		// compare()메서드를 이용해서 정렬하고자 하는 기준을 정해 준다.
		/* compare(String str1, String str2)메서드의 반환값 (이 메서드의 매개변수는 서로 인접한 데이터)
		 * 반환값이 0    ->  두 값이 같다.
		 * 반환 값이 양수   ->  앞에 들어온 str1이 크다 -> 순서를 바꾼다.
		 * 반환 값이 음수   ->  뒤에 들어온 str2이 크다 -> 순서를 바꾸지 않는다.
		 * 
		 * 예) 오름차순일 경우 -> 앞의 값이 크면 양수를, 같으면 0, 앞의 값이 작으면 음수를 반환하도록 구현.
		 * 예) 내림차순일 경우 -> 앞의 값이 크면 음수를, 같으면 0, 앞의 값이 작으면 양수를 반환하도록 구현.
		 * 
		 */
		
		@Override
		public int compare(String str1, String str2) {
			int result = 0;
			if(str1.equals(str2)){}
			else if(str1.compareTo(str2) > 0){
				result = -1;}
			else if(str1.compareTo(str2) < 0){
				result = 1;}
			
			return result;
		}
	
	
	
}
	
	
	
	

