package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public class SetTest {

	public static void main(String[] args) {
		
		
		/*
		 * List와 Set의 차이점
		 * 
		 * 1. List
		 * 		- 데이터의 순서(index)가 있다.
		 * 		- 중복되는 데이터를 저장할 수 있다.
		 * 2. Set
		 * 		- 데이터의 순서(index)가 없다.
		 * 		- 중복되는 데이터를 저장할 수 없다.
		 *  
		 */
	
	
		HashSet hs1 = new HashSet();
		
		// set에 데이터를 추가할 때도 add()메서드를 사용한다.
		hs1.add("DD");
		hs1.add("aa");
		hs1.add(2);
		hs1.add("CC");
		hs1.add('B');
		hs1.add(1);
		hs1.add(3);
		
		
		System.out.println("Set의 데이터 개수 : " + hs1.size());
		System.out.println("Set의 데이터 : " + hs1);
		
		// set에 중복되는 데이터를 추가하면 false를 반환하고, 데이터는 추가되지 않는다.
		
		boolean isAdd = hs1.add("FF");
		System.out.println("중복되었나? : " + isAdd);
		System.out.println("Set의 데이터 : " + hs1);
		
		isAdd = hs1.add("CC");
		System.out.println("중복되었나? : " + isAdd);
		System.out.println("Set의 데이터 : " + hs1);
		
	
		// set에 들어있는 데이터를 삭제하는 메서드 -> remove() -> 반환값 = boolean
		// 전체 데이터 삭제 -> clear()
		hs1.remove("FF");
		
		
		// Set의 데이터를 수정하려면 수정하는 명령이 따로 없기 때문에 
		// 해당 자료를 삭제한 후 새로운 데이터를 추가하는 방식으로 처리해야 한다.
		hs1.add("FF");
		// "FF"를 "EE"로 수정하는 과정
		hs1.remove("FF");
//		System.out.println("삭제후 set : " + hs1);
		hs1.add("EE");
		System.out.println("수정후 set : " + hs1);
		
//		hs1.clear();
		System.out.println("삭제후 set : " + hs1);
	
		
		
		/*
		 * - Set의 데이터는 순서(index)가 없기 때문에 List처럼 index로 데이터를 하나씩 불러올 수 없다.
		 * 	  그래서 데이터를 하나씩 차례로 얻기 위해서는 Iterator형 객체로 변환해야 한다.
		 * 
		 * 포인터 시작시점-> 		---------------------
		 * 			                                       데이터 1
		 * next() 실행 후 포인터 ->	---------------------
		 * 			                                       데이터 2
		 * 						---------------------
		 * 			                                       데이터 3
		 * 						---------------------
		 * 
		 * 
		 * - Iterator: 데이터를 하나씩 차례차례 순서대로 접근해서 가져오는 메서드를 제공하는 인터페이스
		 *		hasNext() : 가리키는 포인터가 현재 가리키는 곳의 다음번째 데이터가 있으면 true를 반환하고 없으면 false를 반환
		 *		next() : 현재가리키는 포인터의 데이터를 가져 온 다음 포인터를 다음번째로 이동시킨다. 
		 */
		
		Iterator i = hs1.iterator();

		//예전 방식
		while(i.hasNext()){
			System.out.println(i.next());
		}
		
		
		//향상된 포문 이용 : 이터레이터 사용 x
		for(Object o : hs1){
			System.out.println(o);
		}
		
		System.out.println("-------------------------------------------");
		// Set을 이용하는 간단한 예제 : 중복되지 않는 난수 만들기
		// 번호는 1부터 22번 까지 있고, 추첨할 인원은 3명이다.
		
		
		// 최소값 ~ 최대값 사이의 난수 만들기
		// (int)(Math.random() * (최대값 - 최소값 +1) + 최소값)
		HashSet<Integer> testSet = new HashSet<>();
		while(testSet.size() < 3){
			testSet.add((int)(Math.random()*22 -1 +1)+1);
			}

		System.out.println("당첨자 : " + testSet);
		
		
		// Set유형의 자료를 List형으로 변환하기
		
		ArrayList<Integer> testList = new ArrayList<>(testSet);
		for(Integer in : testList){
			System.out.println("List데이터: " + in);
		}
		
		
		
	}

}
