package kr.or.ddit.basic;

public class FileTEst04 {

	/*
	 * 스트림 : 데이터 통신에 필요한 통로
	 * 	- 스트림은 단방향임(일방통행)
	 *  - 입출력을 동시에 하고 싶으면 스트림이 두개 필요함.
	 *  - 기본적으로 데이터는 바이트단위로 보내지고 받아짐.(바이트 단위 스트림이 최고 조상임)
	 *  - 바이트기반 스트림은 바이트 단위.
	 *  	* 클래스이름이 ~~~InputStream, ~~~OutputStream.
	 *  	
	 * 	- 문자기반 스트림은 문자단위(char)로 함.
	 * 		* 클래스이름이 ~~~Reader, ~~~Writer.
	 * 
	 *  - 읽기 명령어 -> read() : 파라미터 값이 비어있으면 첫번째 데이터만 읽음. -> 읽어올 데이터가 더 이상 없으면 -1을 반환 함.
	 *  			-> 파라미터 값이 배열이면 배열의 값만큼 읽어와서 파라미터로 준 배열에 저장을 해서 반환 해 줌. -> 실제 읽어 간 데이터 갯수를 반환 값으로 줌.
	 *  
	 *  - 쓰기 명령어 -> write() : 파라미터 값의 마지막 데이터만 반환함.
	 *  			-> write(byte[] b, int off, int length) -> byte[] b 에서 off번째부터 length 갯수를 써넣어라.
	 */		
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
