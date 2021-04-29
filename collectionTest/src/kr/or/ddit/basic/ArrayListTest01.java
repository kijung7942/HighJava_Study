package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Arrays;

public class ArrayListTest01 {

	public static void main(String[] args) {

		// ArrayList는 기본적인 사용법이 Vector와 같다.
		
		// Vector는 쓰레드에서 기본적으로 동기화처리를 하기 때문에 속도가 느리다. <-> 
		// ArrayList는 쓰레드에서 기본적으로 동기화처리가 안되어 있기 때문에 속도가 빠르다.
		
		
		ArrayList list = new ArrayList(); 
		
		list.add("aaa");
		list.add(123);
		list.add('k');
		list.add(false);
		list.add(123.45);
		list.add("bbb");
		
		System.out.println("list ->" +  list);
		System.out.println("size ->" +  list.size());
		
		//get 메서드로 데이터를 꺼내온다.
		System.out.println("1번째 자료 : " + list.get(1));
		
		//add 메서드로 데이터 끼워 넣기
		list.add(3,"zzz");
		System.out.println("list ->" +  list);

		//데이터 변경
		String temp = (String)list.set(3,"YYY");
		System.out.println("temp : " + temp);
		System.out.println("list ->" +  list);
		
		// 데이터 삭제
		list.remove(3);
		System.out.println("list ->" +  list);
		list.remove("bbb");
		System.out.println("list ->" +  list);
		
		// 제네릭 사용도 같음.
		ArrayList<String> list2 = new ArrayList<>();
		list2.add("aaaa");
		list2.add("bbbb");
		list2.add("cccc");
		list2.add("dddd");
		list2.add("eeee");

	
		// 반복문
		for(int i = 0 ; i < list2.size(); i ++){
			System.out.println(i + " --->" + list2.get(i));
		}
		
		for(String str : list2){
			System.out.println(str);
		}
	
		
		// contains(비교데이터) -> List에 '비교데이터'가 있으면 true, 없으면 false 반환
		System.out.println("dddd값 : " + list2.contains("dddd"));
		System.out.println("dddd값 : " + list2.contains("ffff"));
		
		
		//indexOf(비교데이터) -> 비교데이터의 인덱스값을 반환
		int index = list2.indexOf("aaaa");
		System.out.println(index); // 비교데이터가 없으면 결과값은 -1이다.
		
		// toArray() -> 리스트안에 있는 데이터를 배열로 바꾸어줌.
		Object[] strArr = list2.toArray();
		System.out.println(list2);
		System.out.println(Arrays.toString(strArr));
		
System.out.println("--------------------------------------------");
		String[] strArr2 = list2.toArray(new String[0]);
		for(String str : strArr2){
			System.out.println(str);
		}
		System.out.println(Arrays.toString(strArr2));
	
	
	}

}
