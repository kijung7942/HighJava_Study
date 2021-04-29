package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class ThreadTest13 {

	/*
	 * 10마리의 말들이 경주하는 프로그램 작성
	 * 
	 * 말은 Horse라는 이름의 쓰레드 클래스로 작성
	 * 이 클래스는 말의 이름(String), 현재위치(int), 등수(int)를 멤버변수로 갖는다.
	 * 그리고, 이 클래스에는 등수를 오름차순으로 처리할 수 있는 내부 정렬 기준이 있다.(Comparable 인터페이스 구현)
	 * 
	 * 경기 구간(위치)은 1~50까지로 설정
	 * 
	 * 경기 중간 중간 각 말들의 위치를 아래와 같이 나타낸다.
	 * 예)
	 * 01번말 : -->--------------------------------------
	 * 02번말 : --------->-------------------------------
	 * 03번말 : --------------------->-------------------
	 * ...
	 * 10번말 : ------------>----------------------------
	 * 
	 * 경기가 끝나면 등수 순으로 출력
	 * 
	 */
	
	public static void main(String[] args) {

		ArrayList<Horse> horses = new ArrayList<>();
		
		String[] setName = {"일번마","이번마","삼번마","사번마","오번마","육번마","칠번마","팔번마","구번마","십번마"};	
		for(int i = 0 ; i < 10; i ++) {
			Horse hTH = new Horse(setName[i]);
			horses.add(hTH);
		}
			
		Display th = new Display(horses);
		th.setDaemon(true);
			
		
		for(Thread h:horses) {
			h.start();
		}
		System.out.println("경주 시작");
		
		
		th.start();
		
		
		for(Thread h:horses) {
			try {
				h.join();
			} catch (InterruptedException e) {
			}
		}
		
		Collections.sort(horses);
		System.out.println();
		System.out.println("경기 끝");
		System.out.println();
		System.out.println("================== 경기 결과 =================");
		for(int i = 0 ; i < horses.size(); i ++) {
//			if(i == 9) {
//				System.out.println("꼴등 : " + horses.get(i).getHname());}
//			else{System.out.println((i+1) + "등 : " + horses.get(i).getHname());
//			}
			System.out.println(horses.get(i));
		}
	}

}


class Horse extends Thread implements Comparable<Horse>{
	static int rank = 0; // 전체 공유 등수

	private String hName; //말이름
	private int privateRank = 1; // 등수
	int position; // 현재 위치

	
	
	public String getHname() {
		return hName;
	}

	public int getLine() {
		return position;
	}

	public void setLine(int line) {
		this.position = line;
	}

	public Horse(String name) {
		this.hName = name;
	}

	@Override
	public int compareTo(Horse o) {
		int result = 0;
		if(this.privateRank > o.privateRank) {result = 1;}
		else if(this.privateRank < o.privateRank) {result = -1;}
		return result;
	}
	
	@Override
	public String toString() {
		return "경주마 " + hName + "는(은) " + privateRank +" 등 입니다.";
	}
	
	@Override
	public void run() {
		for(position = 0; position < 50; position++) {
			try {
				Thread.sleep((int)(Math.random()*300+201));
			} catch (InterruptedException e) {
			}
		}
			rank++;
			privateRank = rank;
	}
}



class Display extends Thread{
	ArrayList<Horse> horses;
	
	
	public Display(ArrayList<Horse> horses) {
		super();
		this.horses = horses;
	}

//모든 말들의 경기가 종료되었는지 여부 검사
	@Override
	public void run() {
		while(true) {
			if(Horse.rank == this.horses.size()) break;
			
			for(Horse h:horses) {
				System.out.print(h.getHname() + " : ");
				for(int i = 0; i < 50; i++) {
					if(i == h.getLine()) {
						System.out.print(">");
					}else {
					System.out.print("-");}
				}
				System.out.println();
			}
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
			}
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
	
		}
	
	}
	
//	@Override
//	public void run() {
//		while(true) {
//			for(Horse h:horses) {
//				System.out.print(h.getHname());
//				for(int i = 0; i < 50; i++) {
//					System.out.print("-");
//					if(i == h.getLine()) {
//						System.out.print(">");
//					}
//				}
//				System.out.println();
//			}
//			try {
//				Thread.sleep(500);
//			} catch (InterruptedException e) {
//			}
//			System.out.println();
//			System.out.println();
//			System.out.println();
//			System.out.println();
//			
//		}
//		
//	}
}