package kr.or.ddit.basic;

import java.util.Vector;

public class VectorTest {

	public static void main(String[] args) {
		// List와 사용 방법은 비슷하지만 Vector 개체는 다소 무거운 편이다. -> 보안관련된 부분들이 들어가 있기 때문.
		// 컬렉션에 추가 가능한 데이터는 객체의 형태여야 한다.--=\]

		//객체 생성
		Vector v1 = new Vector();
		System.out.println("처음 크기 : " + v1.size());
		
		// 데이터 추가 -> add(추가할 데이터)
		// 반환값 : 성공 true, 실패 false
		boolean add = v1.add("aaaaa");
		System.out.println("추가 여부 : " + add);
		System.out.println("현재 크기 : " + v1.size());
		
		add = v1.add(new Integer(111)); // Integer 와 같이 객체화 시켜주는 클래스를 wrapper 클래스라고 한다.
		System.out.println("추가 여부 : " + add);
		System.out.println("현재 크기 : " + v1.size());
		
		add = v1.add(123); // autoBoxing : 일반 데이터를 자동으로 객체화 시켜준다. (<-> autoUnBoxing: 데이터를 꺼내올 때 자동으로 데이터로 꺼내옴.)
		System.out.println("추가 여부 : " + add); 
		System.out.println("현재 크기 : " + v1.size());
		v1.add('a');
		v1.add(true);
		v1.add(3.14); // 모두 다 오토박싱 됨.
		System.out.println("현재 크기 : " + v1.size());
		
		// 데이터 추가: addElement(추가할 데이터) -> 예전 버전에서 사용하던 메서드 지금은 사용 안해서 생각 안해도 됨.
			v1.addElement("CCC");
			System.out.println("v1 = " + v1);
		
		// 데이터 추가 : add(index, 데이터) -> index번째에 '데이터'를 끼워 넣는다.(index는 0부터 시작) -> 반환값 없음.
			v1.add(0,"Data");
			System.out.println("v1 = " + v1); // v1 = [Data, aaaaa, 111, 123, a, true, 3.14, CCC]
		
		//데이터 수정: set(index,새로운데이터) -> 반환값은 원래 있던 데이터
			String str = (String)v1.set(0,"AfterData"); 
			System.out.println("반환값= " + str); // 반환값= Data
			System.out.println("v1 = " + v1); // v1 = [AfterData, aaaaa, 111, 123, a, true, 3.14, CCC]
		
		// 데이터 삭제 : remove(index) -> index번째의 데이터를 삭제한다. -> 반환값 : 삭제된 데이터.
		
			str = (String)v1.remove(0);
			System.out.println("삭제 된 값= " + str); // 반환값= Data
			System.out.println("삭제 후 v1 = " + v1); // v1 = [aaaaa, 111, 123, a, true, 3.14, CCC]

		
		// 데이터 삭제: remove(삭제할 데이터) -> '삭제할 데이터'를 찾아서 삭제. -> 삭제할 값이 여러개이면 데이터 검사를 하여 앞에서부터 삭제한다.
			 v1.remove("CCC");
			 System.out.println("삭제 후 v1 = " + v1); // v1 = [aaaaa, 111, 123, a, true, 3.14]
			
//			 v1.remove(123); // java.lang.ArrayIndexOutOfBoundsException: Array index out of range: 123
			 v1.remove(new Integer(123)); // 데이터가 int형이나 char형이면 데이터로 인식하지 않고 인덱스 번호로 인식하기 때문에 객체화 해서 넣어주어야 한다.
			 System.out.println("삭제 후 v1 = " + v1); // v1 = [aaaaa, 111, a, true, 3.14]
//			 v1.remove('a'); // java.lang.ArrayIndexOutOfBoundsException: Array index out of range: 97 
			 v1.remove(new Character('a')); // char형도 마찬가지로 데이터로 인식하지 않고 인덱스 번호로 인식하기 때문에 객체화 해서 넣어주어야 한다.
			 System.out.println("삭제 후 v1 = " + v1); // v1 = [aaaaa, 111, true, 3.14]
		 
		 //데이터 꺼내오기 get(index) => int번째의 데이터를 꺼내온다.
			 int data = (int)v1.get(1);
			 System.out.println("1번째 데이터 = " + data);
		
		
			 System.out.println("----------------------------------------------");
		/*
		 * 제네릭타입(Generic Type) -> 객체를 선언할 때 < > 안에 그 객체가 사용할 데이터의 타입을 정해주는 것을 말한다.
		 *  - 제네릭타입을 선언하여 생성된 객체에는 제네릭 타입으로 지정한 데이터 이외의 데이터를 저장 할 수 없다.
		 *  - 제네릭으로 선언될 수 있는 데이터 타입은 클래스형이어야 한다.
		 *  	ex) int는 Integer, boolean은 Boolean, char는 Character 등으로 사용가능.
		 *  - 제네릭 타입으로 선언하게 되면 데이터를 꺼내올 때 별도의 형변환이 필요 없다.
		 */
			 Vector<String> strVec = new Vector<>();  //String만 저장할 수 있는 Vector	 
			 Vector<Integer> intVec = new Vector<>(); //String만 저장할 수 있는 Vector
			 
//			 strVec.add(123); // 오류 -> 제네릭타입과 다른 종류의 데이터를 저장 할 수 없다.
			 strVec.add("123");
			 
			 System.out.println("strVec = " + strVec); // strVec = [123]
			 
			 str = strVec.get(0); // 형변환을 하지 않아도 됨.
			 System.out.println("str = " + str);
			 
			 Vector<Vector> vv = new Vector<>(); // 벡터안에 벡터를 넣는 방식(2차원 배열의 형식)
			 Vector<Vector<Vector>> vvv = new Vector<>(); // 벡터안에 벡터안에 벡터를 넣는 방식(3차원 배열의 형식)
			 
			 // 전체 데이터 삭제 : clear() -> 가지고 있는 데이터를 모두 삭제
			 strVec.clear();
			 System.out.println("strVec의 size = " + strVec.size());
			 
			 // 데이터 삭제 " removeAll() -> 파라미터 값으로 Collection객체를 넣을 수 있음. 
			 // -> Collection객체가 가진 데이터를 모두 지움. -> 반환값은 boolean
			 strVec.add("AAAA");
			 strVec.add("BBBB");
			 strVec.add("CCCC");
			 strVec.add("DDDD");
			 strVec.add("EEEE");
			 
			 Vector<String> strVec2 = new Vector<>();
			 strVec2.add("BBBB");
			 strVec2.add("EEEE");
			 
			 System.out.println("strVec =" +strVec); // [AAAA, BBBB, CCCC, DDDD, EEEE]
			 System.out.println("strVec2 =" +strVec2); // [BBBB, EEEE]
			 strVec.removeAll(strVec2);
			 System.out.println("strVec =" +strVec); // strVec =[AAAA, CCCC, DDDD]
			 System.out.println("----------------------------------------------");
			 
			 //Vector의 데이터를 순서대로 모두 가져와 사용하고 싶으면 반복문을 사용하면 된다.(주로 for문 사용)
			 for(int i = 0 ; i < strVec.size(); i ++){
				 System.out.println(i+"번째 strVec값 :" + strVec.get(i));
			 }
			 
			 //향상된 for문 -> for(저장받을 변수:컬렉션){수행 할 행동}
			 for(String str1:strVec){
				 System.out.println(str1);
			 }
			 
			 
		
	}

}
