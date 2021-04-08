package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class StudentTest {

	public static void main(String[] args) {
		/*
		 *  문제 ) 1.	학번(int), 이름(String), 국어점수, 영어점수, 수학점수, 총점, 등수(모두int)를 멤버로 갖는 
		 *  		Student클래스를 만든다.
		 *  	 2. 이 클래스의 생성자에서는 학번, 이름, 국어점수, 영어점수, 수학점수만 매개변수로 받아서 초기화 한다.
		 *  		(이 때 총점은 세 과목의 점수를 이용해서 초기화 한다.)
		 *  
		 *  	 3.	이 Student 객체는 List에 저장하여 관리한다.
		 *  
		 *  	 4.	List에 저장된 데이터들을 학번의 오름차순으로 정렬할 수 있는 내부 정렬기준을 구현하고
		 *  		총점의 역순으로 정렬하는데 총점이 같으면 이름의 내림차순으로 정렬되는 외부 정렬 기준 클래스를 작성하여
		 *  		정렬된 결과를 출력
		 *  
		 *  		(단, 등수는 List에 전체 데이터가 추가 된 후에 저장되도록 한다.)
		 */
		
		ArrayList<Student> list = new ArrayList();
		
		for(int i = 0; i < 10 ; i++){
			list.add(new Student(i+1,"학생"+(i+1)));
			if(i > 3&&i < 7){
				list.get(i).setKor(50);
				list.get(i).setEng(50);
				list.get(i).setMath(50);
			}
			list.get(i).setSum();
		}
		Collections.shuffle(list);
//		for(int i = 0 ; i < list.size(); i++){
//			for(int j = 0; j < list.size(); j++){
//				if(list.get(i).getSum() < list.get(j).getSum()){
//					list.get(i).setRank(list.get(i).getRank()+1);
//				}
//			}
//		}
			//향상된 포문 사용
		for(Student s1:list){
			for(Student s2:list){
				if(s1.getSum() < s2.getSum()){
					s1.setRank(s1.getRank()+1);
				}
			}
		}

		
		System.out.println("------------------정렬없이----------------------");
		for(Student student:list){
		System.out.println(student);
		}
		System.out.println("--------------번호로 오름차순 정렬후-----------------");
		Collections.sort(list);
		for(Student student:list){
			System.out.println(student);
		}
		System.out.println("---------총점이 같으면 이름으로 내림차순 정렬후------------");
		Collections.sort(list, new SortDesc());
		for(Student student:list){
			System.out.println(student);
		}
		
		
	}

}

class Student implements Comparable<Student>{
	
	private int num;
	private String name;
	private int kor;
	private int eng;
	private int sum;
	private int rank = 1;
	
	public Student(int num, String name) {
		super();
		this.num = num;
		this.name = name;
		this.kor = (int)(Math.random()*101);
		this.eng = (int)(Math.random()*101);
		this.math = (int)(Math.random()*101);
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	private int math;
	
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

	public int getKor() {
		return kor;
	}

	public void setKor(int kor) {
		this.kor = kor;
	}

	public int getEng() {
		return eng;
	}

	public void setEng(int eng) {
		this.eng = eng;
	}

	public int getMath() {
		return math;
	}

	public void setMath(int math) {
		this.math = math;
	}

	public int getSum() {
		return sum;
	}

	public void setSum() {
		this.sum = kor +eng + math;
	}


	@Override
	public String toString() {
		return "Student [num=" + num + ", name=" + name + ", kor=" + kor
				+ ", eng=" + eng + ", math=" + math + ", sum=" + sum + ", rank=" + rank + "]";
	}

	@Override // 내부정렬기준
	public int compareTo(Student student) {
		return Integer.compare(num, student.getNum());
	}
}


class SortDesc implements Comparator<Student>{

	@Override // 총점 정렬 후 총점이 같으면 이름의 내림차순으로 정렬
	public int compare(Student student1, Student student2) {
		 if(student1.getSum() == student2.getSum()){
		 return student1.getName().compareTo(student2.getName()) * -1;
		 }else return Integer.compare(student1.getSum(),student2.getSum()) * -1;
		 
	}
	
}