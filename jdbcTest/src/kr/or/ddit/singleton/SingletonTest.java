package kr.or.ddit.singleton;

public class SingletonTest {

	public static void main(String[] args) {
//		MySingleton single1 = new MySingleton();  // 외부에서 new 명령으로 생성 불가
		MySingleton single2 = MySingleton.getInstance();
		MySingleton single3 = MySingleton.getInstance();
		
		System.out.println("single2 =>" + single2);
		System.out.println("single3 =>" + single3);
		
		System.out.println(single2 == single3);
		System.out.println(single2.equals(single3));
		
		System.out.println();
		single2.displayTest();
	}

}
