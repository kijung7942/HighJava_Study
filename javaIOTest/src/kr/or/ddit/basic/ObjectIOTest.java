package kr.or.ddit.basic;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class ObjectIOTest {

	public static void main(String[] args) {

		// Member의 인스턴스 생성 
		Member mem1 = new Member("홍길동", 30, "대전");
		Member mem2 = new Member("홍길서", 40, "서울");
		Member mem3 = new Member("홍길남", 50, "인천");
		Member mem4 = new Member("홍길북", 60, "울산");
		
		try {
			//객체를 파일에 저장하기
			FileOutputStream fos = new FileOutputStream("e:/daedoek/d_other/memObj.bin");
			BufferedOutputStream bos = new BufferedOutputStream(fos);
			ObjectOutputStream oos = new ObjectOutputStream(bos);
			
			//쓰기 작업
			System.out.println("객체 저장");
			oos.writeObject(mem1);
			oos.writeObject(mem2);
			oos.writeObject(mem3);
			oos.writeObject(mem4);
			System.out.println("객체 저장완료");
			
			oos.close();
			
			//---------------------------------------------------------------
			// 저장된 객체 읽어오기
			
//			FileInputStream fis = new FileInputStream("e:/daedoek/d_other/memObj.bin");
//			BufferedInputStream bis = new BufferedInputStream(fis);
//			ObjectInputStream ois = new ObjectInputStream(bis);
			
			ObjectInputStream ois = new ObjectInputStream(
													 new BufferedInputStream( 
															 new FileInputStream("e:/daedoek/d_other/memObj.bin")));
			
			Object obj; // 읽어온 객체를 저장할 변수
			try {
				//while 문으로 출력할 것이라 EOFException을 받아 줄 트라이캐치 블록 생성
				System.out.println("객체 읽기 시작");
				while((obj = ois.readObject()) != null) {
					// 읽어온 자료 => obj -> 원래의 객체형으로 형변환 후에 사용
					Member mem = (Member)obj;
					System.out.println(mem);
					
				}
				
				
				
			} catch (EOFException e) {
				System.out.println("객체 읽기 종료");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} finally {
				ois.close();
			}
						
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}

class Member implements Serializable{
	/**
	 *  transient ->  직렬화가 되지 않을 멤버변수에 지정한다.
	 *  			-> 직렬화가 되지 않을 멤버변수는 기본값으로 초기화되어 들어간다.
	 *  			-> (참조변수 : null, 숫자 유형 변수 : 0, 논리형 변수 : false)
	 */
	private static final long serialVersionUID = -1096438140999733430L; // 객체가 같은 객체인지 빠르게 확인시켜주는 시리얼번호
	private String name;
	private transient int age;
	private String addr;
	public Member(String name, int age, String addr) {
		super();
		this.name = name;
		this.age = age;
		this.addr = addr;
	}
	@Override
	public String toString() {
		return "Member [name=" + name + ", age=" + age + ", addr=" + addr + "]";
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	
}