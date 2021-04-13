package kr.or.ddit.basic;

import java.util.Scanner;

public class ScannerTest {

	public static void main(String[] args) {
		new ScannerTest().start();
	}

	private void start() {

		Scanner sc = new Scanner(System.in);
		//next() 메서드 test
		
		String name = sc.next();
		String add = sc.next();
		String tel = sc.next();
		
		// 입력창에서 "이기정 대전 010-2724-4110" 한번에 입력
		
		System.out.println("name = " +name+ ", add = " + add + ", tel = " + tel);

		// 출력 결과
//		name = 이기정, add = 대전, tel = 010-2724-4110

		//next() 메소드는 띄어쓰기 단위로 입력 버퍼에서 따로따로 데이터를 가져와 저장하기 때문에 한번에 입력하더라도
		// 띄어쓰기, Tab키, Enter키를 단위로 데이터를 따로 가져와서 각각의 변수에 저장시켜줄 수 있다.
		// nextInt(), nextFloat() 도 동일함.
		
		
		// nextLine() -> 한 줄 단위의 문자열을 입력한다.
		//            -> 즉, 자료를 입력하고 Enter키를 누르면 Enter키 까지 읽어가서 Enter키를 뺀 값을 가져온다.
		
		 name = sc.nextLine();
		 add = sc.nextLine();
		 tel = sc.nextLine();
		
		 System.out.println("name = " +name+ ", add = " + add + ", tel = " + tel);
		// 출력 결과 name = , add = 이기정 대전 010-2724-4110, tel = 
		// name과 tel 값이 비워져나오는데 이유는 위에 String tel = sc.next()를 이용해서 입력을 받으면서
		// Enter의 데이터값이 입력버퍼에 남아있기 때문이다.
		// 이를 해결하기 위해서 입력버퍼를 비워줘야하는데, 그 방법은 sc.nextLine()을 한번 더 작성해주어서 Enter값을
		// 빼주는 형식으로 해결이 가능하다.
		 
		
	}

}
