package kr.or.ddit.basic.args;

public class ArgsTest {

	// 배열을 이용한 메서드 : 매개변수로 받은 정수들의 합계를 구해서 반환하는 메서드
	public int sumArr(int[] x){
		int sum = 0;
		for(int i = 0 ; i < x.length; i++){
			sum += x[i];
		}
		return sum;
	}
	
	public static void main(String[] args) {

		ArgsTest at = new ArgsTest();
		int[] x = {100,80,60};
		int sum = at.sumArr(x);
		System.out.println(sum);
		System.out.println(at.sumArr(new int[]{1,2,3,4,5}));
		
		System.out.println(at.sumArg(100,80,60));
		System.out.println(at.sumArg(1,2,3,4,5));

		System.out.println(at.sumArg2("홍길동", 10,20,30,40,50,20));
		
		
		
	}

	/*가변형 인수를 지원하는 메서드 파라미터 안에  (타입...변수명)으로 정의
	*  - 가변형 인수 -> 메서드의 인수의 개수가 실행될 때마다 다를 때 사용.
	*  - 가변형 인수는 메서드 안에서는 배열로 처리된다.
	*  - 가변형 인수는 한기지 자료형만 사용할 수 있다.
	*/
	
	public int sumArg(int...x){
	//public int sumArg(int[] x){}와 똑같음.
		int sum = 0;
		for(int i = 0 ; i < x.length; i++){
			sum += x[i];
		}
		return sum;
	}
	
	// 가변형 변수와 일반 변수를 같이 사용할 경우에는 가변형 변수를 제일 뒤에  배치해야 한다.
	public String sumArg2(String name,int...x){
		int sum=0;
		for(int i = 0 ; i < x.length; i++){
			sum += x[i];
		}
		return name + "님의 점수 합계 :" +sum;
	}
	
	
	

}
