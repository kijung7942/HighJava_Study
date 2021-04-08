package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ListSortTest02 {

	public static void main(String[] args) {

		/*
		 * 리스트에 추가하면서 내부정렬기준을 만들어서 추가할 때마다 정렬에 맞추어 추가되게 하기.
		 * 
		 * 회원 정보를 처리하는 Member클래스를 작성.
		 * 이 Member클래스는 '회원 이름'을 기준으로 오름차순으로 되도록 내부 정렬 기준을 설정한다. 
		 *  
		 */
	
		ArrayList<Member> memList = new ArrayList<>();
		
		memList.add(new Member(1, "홍길동", "010-1111-1111"));
		memList.add(new Member(5, "이순신", "010-2222-1111"));
		memList.add(new Member(9, "성춘향", "010-3333-1111"));
		memList.add(new Member(3, "강감찬", "010-4444-1111"));
		memList.add(new Member(6, "일지매", "010-5555-1111"));
		memList.add(new Member(2, "변학도", "010-6666-1111"));
		
		System.out.println("정렬전");
		for(Member mem : memList){
			System.out.println(mem);
		}
		System.out.println("--------------------------------------");
		
//		Collections.sort(memList); // 정렬기준이 없기 때문에 소트 불가함.

		// 클래스에 내부정렬기준 만들기
		Collections.sort(memList); // 정렬기준이 없기 때문에 소트 불가함.
		System.out.println("정렬후");
		for(Member mem : memList){
			System.out.println(mem);
		}
		System.out.println("--------------------------------------");

		// 내부 정렬기준(Member클래스가 가진 정렬기준)을 오름차순으로 만들었으니 내림차순으로 정렬할 기준을 외부에 클래스로 만든다.
		Collections.sort(memList, new SortNumDesc());
		System.out.println("정렬후");
		for(Member mem : memList){
			System.out.println(mem);
		}
		System.out.println("--------------------------------------");
		
		
	}
}



class Member implements Comparable<Member>{
	private int num;
	private String name;
	private String tel;
	
	//alt+shift+s 단축키를 누르면 생성자를 자동으로 생성할 수 있는 창으로 이동 된다.
	// 제네레이트 컨스트럭터
	public Member(int num, String name, String tel) {
		super();
		this.num = num;
		this.name = name;
		this.tel = tel;
	}

	//alt+shift+s 단축키를 누르면 생성자를 자동으로 생성할 수 있는 창으로 이동 된다.
	// 제네레이트 게터세터
	
	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	//alt+shift+s 단축키를 누르면 생성자를 자동으로 생성할 수 있는 창으로 이동 된다.
	// 제네레이트 투 스트링
	@Override
	public String toString() {
		return "Member [num=" + num + ", name=" + name + ", tel=" + tel + "]";
	}

	
	// compareTo()메서드를 이용해서 정렬하고자 하는 기준을 정해 준다. (멤버 이름의 오름차순으로 정렬)
		/* compare(Member mem)메서드의 반환값 (이 메서드의 매개변수는 뒤쪽 데이터)
		 * 반환값이 0    ->  두 값이 같다.
		 * 반환 값이 양수   ->  자신의 데이터가 크다 -> 순서를 바꾼다.
		 * 반환 값이 음수   ->  뒤쪽 데이터가 크다 -> 순서를 바꾸지 않는다.
		 * 
		 * 예) 오름차순일 경우 -> 앞의 값이 크면 양수를, 같으면 0, 앞의 값이 작으면 음수를 반환하도록 구현.
		 * 예) 내림차순일 경우 -> 앞의 값이 크면 음수를, 같으면 0, 앞의 값이 작으면 양수를 반환하도록 구현.
		 * 
		 */
	@Override
	public int compareTo(Member mem) {
		
//		// 방법1
//		if(name.compareTo(mem.getName()) > 0){
//			return 1;
//		}else if(name.compareTo(mem.getName()) < 0){
//			return -1;
//		}else{
//			return 0;
//		}
		
		// 방법2
		return name.compareTo(mem.getName()); // 스트링의 내부정렬 기준이 오름차순이기 때문에 그대로 리턴해주면 된다.
	}
}
	
class SortNumDesc implements Comparator<Member>{

	@Override
	public int compare(Member mem1, Member mem2) {
		
		
//		방법1 - if문으로 직접 비교하기		
//		if(mem1.getNum() > mem2.getNum()){
//			return -1;
//		}
//		else if(mem1.getNum() < mem2.getNum()){
//			return 1;
//		}else{
//		return 0;
//		}
	
		
		// Wrapper클래스 이용하는 방법1
		//return (new Integer(mem1.getNum()).compareTo(mem2.getNum())) * -1;
	
		// Wrapper클래스 이용하는 방법2
		return Integer.compare(mem1.getNum(), mem2.getNum()) * -1; // 내림차순 하려고 -1을 곱해줌.
		
	}
	
}
	
	
	
