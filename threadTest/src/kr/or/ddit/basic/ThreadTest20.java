package kr.or.ddit.basic;

public class ThreadTest20 {

	public static void main(String[] args) {

		
		DataBox db = new DataBox();
		
		Thread th1 = new InputThread(db);
		Thread th2 = new OutputThread(db);
		
		
		th1.start();
		th2.start();
		
	}

}


// 공통 클래스
class DataBox{
	private String data;

	//데이터를 가져가는 메서드
	public synchronized String getData() {
		if(data == null) {  // => 데이터 변수 값이 null이면 data변수에 문자열이 채워질때까지 기다리고.
							//	  data변수에 값이 있으면 해당 문자열을 반환한다.
							//	  data를 주면 data변수를 초기화(null)한다.
			try {
				wait(); 
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
		//data변수에 문자열이 채워졌을 때 실행 되는 구간
		String returnData = data;
		System.out.println("쓰레드가 읽은 데이터 : " + returnData);
		
		data = null;
		notify();
		return returnData;
	}
	
	//데이터를 채워주는 메서드
	// data변수에 값이 있으면 기다리고 없으면 채우는 방식
	public synchronized void setData(String data) {
		//현재 data 값이 null이되면 '새로운값 data'를 this.data에 넣어준다.
		if(this.data != null) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		//this.data변수 값이 null이 되면 실행되는 구간.
		this.data = data;
		System.out.println("Thred에서 새롭게 저장한 데이터 : " + data);
		notify();
		
		
	}
}

//데이터 넣는 쓰레드
class InputThread extends Thread{
	private DataBox db;

	public InputThread(DataBox db) {
		this.db = db;
	}

	@Override
	public void run() {
		for(int i = 1; i <=3 ; i++) {
			db.setData("공급 데이터 " + i);
		}
	}
	
}

class OutputThread extends Thread{
	private DataBox db;

	public OutputThread(DataBox databox) {
		this.db = databox;
	}

	@Override
	public void run() {
		for (int i = 1; i <=3 ; i++) {
			String data = db.getData();
			//이부분에 가져온 데이터를 이용하는 코드를 기술
//			System.out.println(data);
		}
	
	
	}
	
}
