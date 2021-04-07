package kr.or.ddit.basic;

import java.util.LinkedList;
import java.util.Stack;

public class StackQueueTest {

	public static void main(String[] args) {

		/*
		 *  Stack : LIFO(Last-In-First-Out:후입선출)의 자료구조
		 *  Queue : FIFO(First-In-First-Out:선입선출)의 자료구조
		 *  
		 *  Stack과 Queue는 LinkedList를 이용하여 사용할 수 있다.
		 */
		
		/* Stack 명령
		 * 1. 자료입력 : push(입력값)
		 * 2. 자료 출력  : pop()  -> 마지막 넣은 자료를 꺼내온 후 꺼내온 자료를 Stack에서 삭제한다. (주로 사용 됨: 데이터를 사용할 때)
		 * 			 : peek() -> 삭제없이 자료를 꺼내온다.(확인할 때 사용)
		 */
		
		
	 LinkedList<String> list = new LinkedList<>();
		
	 list.push("첫번째");
	 list.push("두번째");
	 list.push("세번째");
	 list.push("네번째");
	 
	 list.add("애드로 넣은거");          // add로 넣을 수도 있지만 add로 넣으면 뒤로 들어감.
	 
	 System.out.println(list);       // [네번째, 세번째, 두번째, 첫번째, 애드로 넣은거]
	 
	 System.out.println(list.pop()); // 네번째
	 System.out.println(list);       // [세번째, 두번째, 첫번째, 애드로 넣은거]
	 
	 String data2 = list.peek();     //삭제 없이 꺼내온 데이터
	 System.out.println(data2);      // 세번째
	 System.out.println(list);       // [세번째, 두번째, 첫번째, 애드로 넣은거]
	 
	 list.pop();
	 list.pop();
	 list.pop();
	 System.out.println(list.pop()); // 애드로 넣은거
	 System.out.println(list);       // [] 
	 								 // pop으로 애드로 추가한거도 가져올수는 있음. 다만 Stack처럼 쓰려는 의도에 맞지 않는다.
	}

}
