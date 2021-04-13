package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class PropertiesTest {

	public static void main(String[] args) {

		
		/*
		 * Properties는  Map보다 축소된 기능의 객체라고 할 수 있다.
		 * 
		 *  - Map은 key값과 value 값에 모든 형태의 객체를 사용할 수 있지만
		 * 	  Properties는 key값과 value값에 String만 사용 가능.
		 *
		 *
		 *  - Map은 put(), get()메서드를 이용하여 데이터를 입출력하지만,
		 * 	  Properties는 setProperty(), getProperty()메서드를 통해서 데이터를 입출력한다.
		 * 
		 * 	- Properties는 데이터를 파일로 입출력 할 수있다.
		 */
		
		Properties prop = new Properties(); //Properties 객체 생성
		
		prop.setProperty("name", "홍길동");
		prop.setProperty("age", "20"); // 숫자도 문자열로 바꿔서 넣어야 함.
		prop.setProperty("tel", "010-2123-3544");
		prop.setProperty("addr", "대전시 중구");
		
		System.out.println(prop);
		
		String name = prop.getProperty("name");
		int age = Integer.parseInt(prop.getProperty("age"));
		
		System.out.println(name + "의 나이는 " + age);
				
		
		
	}

}
