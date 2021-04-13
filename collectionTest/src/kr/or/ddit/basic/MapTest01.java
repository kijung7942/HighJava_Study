package kr.or.ddit.basic;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class MapTest01 {

	public static void main(String[] args) {

		/*
		 * Map -> key값, value 값을 한 쌍으로 관리하는 객체
		 * 			- key값은 중복을 허용하지 않고, 순서가 없다 (Set의 특징)
		 * 			- value 값은 중복을 허용.
		 */
		
		
		HashMap<String, String> map = new HashMap();
		
		// 자료 추가 -> put(key값, value값)
		map.put("name", "홍길동");
		map.put("addr", "대전");
		map.put("tel", "010-1234-5678");
		System.out.println("map :" + map);
		
		
		// 자료 수정 ->	데이터를 추가 할 때 key 값이 같으면, 나중에 추가한 값으로 덮어쓰여진다.
		//						반환 값 -> 기존 자료의 value 값.
		
		String arr = map.put("addr","서울");
		System.out.println("map :" + map);
		System.out.println("기존 값 :" + arr);
		
		// 자료 삭제 -> remove(key값) -> key값이 같은 자료를 찾아서 삭제.
		//						반환 값 -> 삭제된 자료의 value 값.
		
		String tel = map.remove("tel");
		System.out.println("map :" + map);
		System.out.println("반환 값" + tel);
		
		map.put("tel", "010-1234-5678");
		
		// 자료 읽기 -> get(key값) -> key값과 일치하는 곳의 value값을 반환한다.
		System.out.println("이름 : " + map.get("name"));
		System.out.println("나이 : " + map.get("age"));
		System.out.println();
		
		
		// key값이 Map에 존재하는지 여부를 나타내는 메서드 : containsKey(key값)
		// 있으면 true, 없으면 false
		
		System.out.println("name 키 값의 존재 여부 : " + map.containsKey("name"));
		System.out.println("age 키 값의 존재 여부 : " + map.containsKey("age"));
		
		System.out.println("------------------------------------------------");
		System.out.println();
		
		
		
		Set<String> keySet = map.keySet();
		
		// Map에 저장된 모든 데이터를 차례로 가져와 처리하는 방법
		
		// 방법 1: Map에 저장된 모든 Key값을 읽어와 처리하기
		//			keySet()메서드 이용하기 -> Map의 모든 Key값들을 읽어와 Set형으로 만든다.
		
		// 방법 1 - 1 -> Iterator 이용하기.
		Iterator<String> keyIt = keySet.iterator();
		while(keyIt.hasNext()){
			String key = keyIt.next();
			String value = map.get(key);
			System.out.println(key + " : " + value);
		}
		System.out.println("================================================");
		
		// 방법 1 - 2 ->  향상된 for문
		
		for(String str : map.keySet()){
			System.out.println(str + " : " + map.get(str));
		}
		System.out.println("================================================");
		

		// 방법 2  -> value 값만 읽어와 처리하기 -> values() 메서드 활용
		for(String val : map.values()){
			System.out.println(val);
		}
		System.out.println("================================================");
		
		
		/* 방법 3 -> Map에는 Entry라는 내부 class가 만들어져 있다.
		*			이 Entry 클래스는 key 라는 변수와 value 변수로 구성되어 있다.
		*			Map에서는 이 Entry 클래스의 인스턴스에 데이터를 저장하고
		*			이 Entry인스턴스를 Set형식으로 저장(EntrySet 클래스로 저장)하여 관리한다.
		*/			
		
		//Entry 라는 내부 객체 전체 가져오기
		Set<Map.Entry<String,String>> mapSet = map.entrySet();
		
		Iterator<Map.Entry<String, String>> entryIt = mapSet.iterator();
		
		while(entryIt.hasNext()){
			Map.Entry<String, String> entry = entryIt.next();
			
			System.out.println("key값 : " + entry.getKey());
			System.out.println("value값 : " + entry.getValue());
			
		}
		System.out.println("================================================");
	
		
		
	}
	
	

}
