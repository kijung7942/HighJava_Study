package kr.or.ddit.basic;

import java.util.HashMap;

public class MapTest02 {

	public static void main(String[] args) {
		HashMap<Integer,Member> memberMap = new HashMap<>();
		
		memberMap.put(1, new Member(1, "일지매", "010-111-1111"));
		memberMap.put(2, new Member(2, "이순신", "010-222-2222"));
		
		System.out.println(memberMap);
		
	}

}
