package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class HotelTest {

	HashMap<Integer,Room> roomMap = new HashMap<>();
	
	public static void main(String[] args) {

		new HotelTest().start();
	}

	private void start() {
		for(int i = 2 ; i <5; i++){
			for(int j = 1; j <10; j++){
				roomMap.put(i*100+j,new Room(i*100+j));
			}
		}
		
			System.out.println("*******************************");
			System.out.println("   호텔문을 열었습니다. 어서오십시요.");
			System.out.println("*******************************");
		
			
		while(true){
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println("---------------------------------------");
			System.out.println("어떤 업무를 하시겠습니까?");
			System.out.println("1. 체크인   2. 체크아웃   3. 객실상태   4. 업무종료");
			System.out.println("---------------------------------------");
			System.out.println("선택 >");
			
			switch(ScanUtil.nextInt()){
			case 1: // 체크인
				checkIn();
				break;
			case 2: // 체크아웃
				checkOut();
				break;
			case 3: // 객실상태
				System.out.println("----------------------------------");
				System.out.println("방 번호 \t 방 종류 \t 투숙객 이름");
				System.out.println("----------------------------------");
				ArrayList<Integer> roomNumList = new ArrayList<>(roomMap.keySet());
				Collections.sort(roomNumList);
				for(Integer r : roomNumList){
					 System.out.print(roomMap.get(r).getRoomNum()+"\t"); 
					 System.out.print(roomMap.get(r).getRoomKind()+"\t"); 
					 if(roomMap.get(r).getcName() == null){System.out.print("-");
					 }else{System.out.print(roomMap.get(r).getcName());}
					 System.out.println();
				}
				break;
			case 4: 
				System.out.println("*******************************");
				System.out.println("         호텔문을 닫았습니다. ");
				System.out.println("*******************************");
				System.exit(0);
			}
		}
		
	}

	private void checkOut() {
		System.out.println("체크아웃 할 방 번호를 입력하세요");
		System.out.println("방 번호 입력>>");
		int roomNum = ScanUtil.nextInt();
				if(!roomMap.containsKey(roomNum)){
				System.out.println(roomNum + "호 객실은 존재하지 않습니다.");
				}else if(roomMap.get(roomNum).getcName() != null){
					String checkOutName = roomMap.get(roomNum).getcName(); 
					roomMap.get(roomNum).setcName(null);
					System.out.println(roomNum + "호 객실의 " + checkOutName + "님 체크아웃을 완료하였습니다.");
				}else{System.out.println(roomNum+"호 객실에는 체크인 한 사람이 없습니다.");
				}
		}

	private void checkIn() { // 체크인
			System.out.println("-------------------------------------");
			System.out.println("    체크인 작업");
			System.out.println("-------------------------------------");
			System.out.println("* 201 ~ 209 : 싱글룸");
			System.out.println("* 301 ~ 309 : 더블룸");
			System.out.println("* 401 ~ 409 : 스위트룸");
			System.out.println("-------------------------------------");
			System.out.println("방 번호 입력 >>");
			int roomNum = ScanUtil.nextInt();
			if(!roomMap.containsKey(roomNum)){
				System.out.println(roomNum + "호 객실은 존재하지 않습니다.");
			}else if(roomMap.get(roomNum).getcName() != null){
				System.out.println(roomNum + "호 객실은 이미 손님이 있습니다.");
			}else{
				System.out.println("누구를 체크인 하겠습니까?");
				System.out.println("이름 입력 >> ");
				String cName = ScanUtil.nextLine();
				Room r = roomMap.get(roomNum);
				r.setcName(cName);
				roomMap.put(roomNum, r);
				System.out.println("체크인이 완료되었습니다.");
			}
			
			
			
	}

}


class Room{
	private int roomNum;
	private String roomKind;
	private String cName;
	
	Room(int roomNum){
		this.roomNum = roomNum;
		if(roomNum > 200 && roomNum < 210 ){
			roomKind = "싱글룸";
		}else if(roomNum > 300 && roomNum < 310 ){
			roomKind = "더블룸";
		}else if(roomNum > 400 && roomNum < 410 ){
			roomKind = "스위트룸";
		}
	}

	public int getRoomNum() {
		return roomNum;
	}

	public void setRoomNum(int roomNum) {
		this.roomNum = roomNum;
	}

	public String getRoomKind() {
		return roomKind;
	}

	public void setRoomKind(String roomKind) {
		this.roomKind = roomKind;
	}

	public String getcName() {
		return cName;
	}

	public void setcName(String cName) {
		this.cName = cName;
	}

	@Override
	public String toString() {
		return "Room [roomNum=" + roomNum + ", roomKind=" + roomKind
				+ ", cName=" + cName + "]";
	}
	
}