package kr.or.ddit.basic;


import java.util.ArrayList;
import java.util.Scanner;

public class ArrayListTest02 {

	public static void main(String[] args) {
		/*
		 * 문제) 5명의 사람 이름을 입력 받아 ArrayList에 저장한 후에
		 *      이들 중 '김'씨 성의 이름을 모두 출력하시오.
		 */
		Scanner sc = new Scanner(System.in);
		ArrayList<String> nameList = new ArrayList();
		int count = 0;
		
		while(count < 5){
		System.out.println("이름 입력");
		nameList.add(sc.nextLine());
		count++;
		}
		
		
		
		for(String str:nameList){
//			if(str.substring(0,1).equals("김")){
//				System.out.println(str);
//			}
			
//			if(str.charAt(0) == '김'){
//				System.out.println(str);
//			}
			
//			if(str.indexOf('김') == 0){
//				System.out.println(str);
//			}
			
			if(str.startsWith("김")){ // '김'으로 시작하면 true, 아니면 false
				System.out.println(str);
			}
		
		
			
		}
		
		
	
	
	}
}
