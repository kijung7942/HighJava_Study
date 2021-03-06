package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

/*
 *  Vector, Hashtable 등의 예전부터 존재하던 Collection객체들은 내부에 동기화 처리가 되어 있다.
 *  
 *  그런데, 새로 구성된 다른 Collection객체들은 동기화 처리가 되어 있지 않다.
 *  그래서, 동기화가 필요한 프로그램에서는 이런 Collection 객체들을 사용하려면 동기화 처리를 한 후에 사용해야 한다.
 */

// 어레이리스트 -> 일단 배열을 10개(예를들어) 만들어 놓은 배열을 사용하다가 10개가 넘어가는 순간 새로운 배열을 만들고 기존의 데이터를 새로운 리스트에 넣는 과정이 있음.
//			-> 이 과정중에 동기화 처리가 안되어 있다면 쓰레드의 권한이 넘어갈 수 있기 때문에 동기화 처리를 해둬야한다. 

public class ThreadTest18 {
	private static Vector<Integer> vec = new Vector<>();
	//동기화 처리를 하지 않은 경우
	private static List<Integer> list1 = new ArrayList<>();
	//동기화 처리를 한 경우
	private static List<Integer> list2 = Collections.synchronizedList(new ArrayList<Integer>());
	
	public static void main(String[] args) {
		// 익명 구현체로 쓰레드 구현
		
		Runnable r = new Runnable() {
			
			@Override
			public void run() {
				for(int i = 0 ; i < 10000; i++) {
//					vec.add(i);
//					list1.add(i);
					list2.add(i);
				}
			}
		};
		Thread[] ths = new Thread[] {
				new Thread(r),new Thread(r),new Thread(r),new Thread(r),new Thread(r)
		};
		
		for(Thread th : ths) {
			th.start();
		}

		for(Thread th : ths) {
			try {
				th.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
//		System.out.println("vec의 개수 : " + vec.size());
//		System.out.println("list의 개수 : " + list1.size());
		System.out.println("list의 개수 : " + list2.size());
		
		
	}

}
