package kr.or.ddit.basic;

import java.util.HashMap;

public class MyPractice {

	public static void main(String[] args) {
	String name = new MyPractice().start();
	System.out.println(name);
	}

	public String start() {
//	String[] participant = {"leo", "kiki", "eden"}; 
//	String[] completion = {"eden", "kiki"};
//	String[] participant = {"marina", "josipa", "nikola", "vinko", "filipa"}; 
//	String[] completion = {"josipa", "filipa", "marina", "nikola"};
	String[] participant = {"mislav", "stanko", "mislav", "ana"}; 
	String[] completion = {"stanko", "ana", "mislav"};
	String answer = "처음값"; 
	int count = 1;
	HashMap<String,Integer> checkMap = new HashMap<>();
	for(int i = 0; i < participant.length; i++) {
		answer = participant[i];
		count = 1;
		for(int j = 1 ; j<participant.length-1; j++) {
			if(answer.equals(participant[j])) {
				count++;
			}
		}
		checkMap.put(answer,count);
	}
	
	for(int i = 0 ; i < completion.length; i++) {
		for(int j = 0; j < participant.length; j++) {
			
		}
	}
	
	return answer;
}
}