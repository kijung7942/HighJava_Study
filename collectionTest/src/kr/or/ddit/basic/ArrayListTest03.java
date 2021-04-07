package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class ArrayListTest03 {

	public static void main(String[] args) {

		/*
		 *  문제 1) 5명의 별명을 입력 받아 ArrayList에 저장하고 이들 중 
		 *        별명의 길이가 제일 긴 별명을 출력하시오.
		 *        (단, 각 별명의 길이는 모두 다르게 입력한다.)
		 */
		
		List<String> list = new ArrayList<String>();
		Scanner sc = new Scanner(System.in);
		
		
		for(int i = 1 ; i <= 5 ; i++){
			System.out.println("별명입력");
			list.add(sc.next());
		}
		
		int max = 0;
		
		for(int i = max; i < list.size(); i++){
				if(list.get(max).length() < list.get(i).length()){
					max = i;
			}
		}
		System.out.println(list.get(max));
		
		/*
		 * 문제 2) 문제1에서 별명의 길이가 같은 것이 있을 경우를 처리(여러개 다 출력).
		 */
		System.out.println("--------------------------------------------");
		for(int i = max; i < list.size(); i++){
		 if(list.get(i).length() == list.get(max).length()){
			 System.out.println(list.get(i));
		 }
		}
		
		
		
	}

}
