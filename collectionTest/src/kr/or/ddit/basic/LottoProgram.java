package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

public class LottoProgram {

	/*
	 *  로또를 구매하는 프로그램 작성하기 6/45
 
		 사용자는 로또를 구매할 때 구매할 금액을 입력하고 입력한 금액에 맞게 로또번호를 출력한다.
		 (단, 로또 한장의 금액은 1000원이며 최대 100장(10만원)까지만 구입할 수 있고, 거스름돈도 계산하여 출력한다.)

			==========================
		         	Lotto 프로그램
			--------------------------
			 1. Lotto 구입
			 2. 프로그램 종료
			==========================		 
			메뉴선택 : 1  <-- 입력
					
			 Lotto 구입 시작
				 
			(1000원에 로또번호 하나입니다.)
			금액 입력 : 2500  <-- 입력
					
			행운의 로또번호는 아래와 같습니다.
			로또번호1 : 2,3,4,5,6,7
			로또번호2 : 20,21,22,23,24,25
					
			받은 금액은 2500원이고 거스름돈은 500원입니다.
		
			==========================
		         	Lotto 프로그램
			--------------------------
			 1. Lotto 구입
			 2. 프로그램 종료
			==========================		 
			메뉴선택 : 1  <-- 입력
					
			 Lotto 구입 시작
				 
			(1000원에 로또번호 하나입니다.)
			금액 입력 : 900  <-- 입력
			
			입력 금액이 너무 적습니다. 로또번호 구입 실패!!!
		
			==========================
		         	Lotto 프로그램
			--------------------------
			 1. Lotto 구입
			 2. 프로그램 종료
			==========================		 
			메뉴선택 : 1  <-- 입력
					
			 Lotto 구입 시작
				 
			(1000원에 로또번호 하나입니다.)
			금액 입력 : 101000  <-- 입력
			
			입력 금액이 너무 많습니다. 로또번호 구입 실패!!!
					
		   	 ==========================
		         	Lotto 프로그램
			--------------------------
			  1. Lotto 구입
			  2. 프로그램 종료
			==========================		 
			메뉴선택 : 2  <-- 입력
				
			감사합니다
			 */
	public static void main(String[] args) {
	
		new LottoProgram().start();
	}

	private void start() {
	
		boolean flag = true;
		int input = 0, cash = 0, count =0;
		
	while(flag){	
			System.out.println("==========================");
			System.out.println("      Lotto 프로그램");
			System.out.println("--------------------------");
			System.out.println("      1. Lotto 구입");
			System.out.println("      2. 프로그램 종료");
			System.out.println("==========================");		 
			while(flag){
				System.out.println("메뉴선택 >");
				try{
					input = Integer.parseInt(ScanUtil.nextLine());
					flag=false;
				}catch(Exception e){
					System.out.println("숫자로 입력하세요");
				}
			}
			
			switch(input){
			case 1:
				System.out.printf("%n Lotto 구입 시작  %n");
				System.out.println("\n(1000원에 로또번호 하나입니다.)");
				System.out.println(" 금액입력>   ex) 25000");
				flag = true;
				while(flag){
					try{cash = Integer.parseInt(ScanUtil.nextLine());
					}catch(Exception e){
						System.out.println("숫자로 입력하세요");
					}
					if(cash > 100000){
						System.out.println("\n입력 금액이 너무 많습니다. 로또번호 구입 실패!!\n");
						break;
					}else if(cash < 1000){
						System.out.println("\n입력 금액이 너무 적습니다. 로또번호 구입 실패!!\n");
						break;
					}else{
						System.out.println("\n행운의 로또번호는 아래와 같습니다.");
						for(int i = 0; i < cash/1000; i ++){
							HashSet<Integer> random = new HashSet<>();
							while(random.size() < 6){
								random.add((int)(Math.random()*45+1));
							}
							ArrayList<Integer> numberList = new ArrayList<>(random);
							System.out.print("로또번호"+(i+1)+":");
							Collections.sort(numberList);
							for(int num:numberList){
								if(count == 5){
									System.out.print(num);
									count = 0;
								}else{
									System.out.print(num+",");
								count++;}
							}
							System.out.println();
						}
						System.out.println();
						System.out.println("받은 금액은 " + cash + "원이고 거스름돈은 " + cash%1000 + "원 입니다.");
						System.out.println();
						flag = true;
						break;
					}
				}break;
				
			case 2:
				System.out.println("프로그램을 종료합니다");
				System.exit(0);
			}
			
	}
			
		
		
		
	}

}
