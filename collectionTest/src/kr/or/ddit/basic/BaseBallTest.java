package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;

public class BaseBallTest {

	public static void main(String[] args) {

		/*
		 * 문제) Set과 List를 이용하여 숫자 야구 게임 프로그램을 작성하시오
		 * 		컴퓨터의 숫자는 난수를 이용하여 구한다.
		 * 		(스트라이크는 S, 볼은 B, 아웃은 O)
		 */
		Scanner sc = new Scanner(System.in);
		HashSet<Integer> random = new HashSet<>();
		int s = 0, b = 0, o = 0, count = 1;
		while (random.size()<3){
			random.add((int)(Math.random()*9)+1);
		}
		
		
		ArrayList<Integer> comNum = new ArrayList<>(random);
		Collections.shuffle(comNum);
		System.out.println(comNum);
		ArrayList<Integer> UserNum = new ArrayList<>(random);
		boolean flag = true;
		while(flag){
				UserNum.clear();
				s = 0; b = 0; o = 0;
//			for(int i = 0 ; i < 3 ; i++){
//				System.out.println("1~9 숫자를 하나 입력해주세요>");
//				UserNum.add(sc.nextInt());}
				System.out.println("=========================");
				System.out.println(count+"번째 시도 중...");
			for(int i = 0 ; i < 3 ; i++){
				System.out.println("1~9 숫자를 하나 입력해주세요>");
				UserNum.add(sc.nextInt());}
				
				
			if(comNum.get(0) == UserNum.get(0))
				s++;
			if(comNum.get(0) != UserNum.get(0)&&comNum.get(0) == UserNum.get(1)||comNum.get(0) == UserNum.get(2))
				b++;
			if(comNum.get(1) == UserNum.get(1))
				s++;
			if(comNum.get(1) != UserNum.get(1)&&comNum.get(1) == UserNum.get(0)||comNum.get(1) == UserNum.get(2))
				b++;
			if(comNum.get(2) == UserNum.get(2))
				s++;
			if(comNum.get(2) != UserNum.get(2)&&comNum.get(2) == UserNum.get(0)||comNum.get(2) == UserNum.get(1))
				b++;
			o = 3-s-b;
			System.out.println("S = " + s + "B = " + b + " O = " + o);
			if(s==3){System.out.println(count+"번째에 맞췄습니다");
			flag = false;
			}else{count++;
			}
		}
		
	}

}
