package kr.or.ddit.basic;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;

public class PhoneBookTest {

	public static void main(String[] args) {

		new PhoneBookTest().start();
	/*
	 * 문제> 이름, 주소, 전화번호를 멤버로 갖는 Phone 클래스를 만들고
	 * 		Map을 이용하여 '전화번호 정보'를 관리하는 프로그램을 작성
	 * 		(이때, Map의 구조는 key값으로 '이름' 데이터를 사용하고, value 값으로는 'Phone클래스의 인스턴스'로 한다.)
	 * 
	 * 		- 추가조건)
	 * 		1) 6. 전화번호 저장 메뉴를 추가하고 구현한다.
	 * 			(저장 파일명은 'phoneData.dat'으로 한다.)
	 * 		2) 프로그램이 시작될 때 저장파일명이 있으면 그 데이터를 읽어와 Map에 세팅한다.
	 * 		3) 프로그램이 종료될 때 Map의 데이터가 변경되거나 추가 또는 삭제된 자료가 있으면
	 * 		   저장 후 종료되도록 한다.	
	 * 
	 * 		아래의 메뉴를 처리하는 프로그램을 완성
	 */
	}

	HashMap<String, Phone> phoneMap = new HashMap<>(); 
	boolean autoSave = false;
	private void start() {
		File file = new File("e:/daedoek/d_other/phoneData.dat");
		if(!file.exists()) {System.out.println("파일이 존재하지 않습니다.");}
		else{
			try {
				DataInputStream dis = new DataInputStream(new FileInputStream(file));
				try {
					while(true) {
					String a = (String)dis.readUTF();
					String b = (String)dis.readUTF();
					String c = (String)dis.readUTF();
					Phone p = new Phone(a,b,c);
					phoneMap.put(a, p);
					}	
				}catch (EOFException e) {
					System.out.println("DB입력완료");
				}
			} catch (FileNotFoundException e) {
				System.out.println("파일이 없습니다.");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		while(true){
			System.out.println("=============================");
			System.out.println("          메               뉴");
			System.out.println("        1. 전화번호 등록");
			System.out.println("        2. 전화번호 수정");
			System.out.println("        3. 전화번호 삭제");
			System.out.println("        4. 전화번호 검색");
			System.out.println("        5. 전화번호 전체 출력");
			System.out.println("        6. 전화번호 DB저장");
			System.out.println("        0. 프로그램 종료");
			System.out.println("==============================");
			System.out.print("메뉴를 선택하세요 > ");
			switch(ScanUtil.nextInt()){
			case 1:
				addNum();
				autoSave = true;
				break;
			case 2:
				updateNum();
				
				break;
			case 3:
				deleteNum();
				autoSave = true;
				break;		
			case 4:
				search();
				break;
			case 5:
				searchAll();
				break;
			case 6:
				save();
				autoSave = false;
				break;
			case 0:
				if(autoSave) {
					System.out.println("변경 값이 있으므로 자동 저장됩니다.");
					save();
					System.out.println("자동 저장 완료.");
				}
				System.out.println("프로그램을 종료합니다.");
				System.exit(0);
			}
			
		}
		
	}
	
	private void save() {
		

		try {
			System.out.println("저장 시작");
			DataOutputStream oos = new DataOutputStream(new FileOutputStream("e:/daedoek/d_other/phoneData.dat"));
			Phone p;
			for(String name : phoneMap.keySet()) {
				p = phoneMap.get(name);
				oos.writeUTF(p.getName());
				oos.writeUTF(p.getAddr());
				oos.writeUTF(p.getTelNum());
			}
			
			System.out.println("저장 완료.");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}

	private void searchAll() {
		System.out.println("등록되어 있는 전체 목록 입니다.");
		System.out.println("============================================");
		System.out.println("번호\t이름 \t 주소 \t 전화번호 \t");
		if(phoneMap.size() ==0){
			System.out.println(" 등록된 번호가 없습니다.");
		}else{
			int count = 1;
			for(String str : phoneMap.keySet()){
				System.out.print(count++ +". ");
				System.out.print("\t" + phoneMap.get(str).getName());
				System.out.print("\t" + phoneMap.get(str).getAddr());
				System.out.print("\t" + phoneMap.get(str).getTelNum());
				System.out.println();
			}
			}
		System.out.println("============================================");
	}

	private void search() {
		System.out.println("검색할 이름을 입력 해주세요");
		String name = ScanUtil.nextLine();
		if(!phoneMap.containsKey(name)){
			System.out.println(name+"은(는) 등록되지 않은 번호입니다.");
		}else{
			System.out.println("검색하신 " + name + "의 정보입니다.");
			System.out.println("이름 : " + phoneMap.get(name).getName());
			System.out.println("주소 : " + phoneMap.get(name).getAddr());
			System.out.println("전화번호 : " + phoneMap.get(name).getTelNum());
		}		
	}

	private void deleteNum() {
		System.out.println("삭제할 사람의 이름을 입력하세요");
		String name = ScanUtil.nextLine();
		if(!phoneMap.containsKey(name)){
			System.out.println(name+"은(는) 등록되지 않은 번호입니다.");
		}else{
			phoneMap.remove(name);
			System.out.println("삭제 되었습니다.");
		}
		
		
	}

	private void updateNum() {
		System.out.println("수정할 사람의 이름을 입력하세요");
		String name = ScanUtil.nextLine();
		if(!phoneMap.containsKey(name)){
			System.out.println(name+"은(는) 등록되지 않은 번호입니다.");
		}else{
			s1: while(true){
				System.out.println("수정할 정보를 선택하세요");
				System.out.println("1. 이름, 2. 주소, 3. 전화번호, 4. 홈으로");
				switch(ScanUtil.nextInt()){
				case 1 : 
					System.out.println("수정할 이름을 입력하세요.");
					String name2 = ScanUtil.nextLine();
					if(name.equals(name2)){
						System.out.println("입력한 이름이 수정전과 동일합니다.");
						break;
					}else if(phoneMap.containsKey(name2)){
						System.out.println("이미 동일한 이름이 저장되어 있습니다.");
						break;
					}else{
						phoneMap.get(name).setName(name2);;
						phoneMap.put(name2, phoneMap.get(name));
						phoneMap.remove(name);
						System.out.println(name + "은(는)" + name2 + " (으)로 수정 되었습니다.");
						autoSave = true;
					}
				break;
				case 2 :
					System.out.println("수정할 주소를 입력하세요.");
					String addr = ScanUtil.nextLine();
					phoneMap.get(name).setAddr(addr);
					System.out.println("주소가 변경되었습니다.");
					autoSave = true;
					break;
				case 3 :
					System.out.println("수정할 전화번호를 입력하세요.");
					String telNum = ScanUtil.nextLine();
					phoneMap.get(name).setTelNum(telNum);
					System.out.println("전화번호가 변경되었습니다.");
					autoSave = true;
					break;
				case 4 : 
					System.out.println("홈 화면으로 돌아갑니다.");
					break s1;
				}
				break;
			}
		}
		
		
	}

	private void addNum() {
		System.out.println("새롭게 등록할 전화번호 정보를 입력하세요.");
		System.out.println("이름 >>");
		String name = ScanUtil.nextLine();
		if(phoneMap.containsKey(name)){
			System.out.println("이미 등록된 사용자입니다.");
		}else{
			System.out.println("전화번호 >>");
			String telNum = ScanUtil.nextLine();
			System.out.println("주소 >>");
			String addr = ScanUtil.nextLine();
			phoneMap.put(name,new Phone(name,addr,telNum));
		}		
	}

	
	
	
}








class Phone implements Serializable{
	private String name;
	private String addr;
	private String telNum;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getTelNum() {
		return telNum;
	}
	public void setTelNum(String telNum) {
		this.telNum = telNum;
	}
	public Phone(String name, String addr, String telNum) {
		super();
		this.name = name;
		this.addr = addr;
		this.telNum = telNum;
	}
	@Override
	public String toString() {
		return "Phone [name=" + name + ", addr=" + addr + ", telNum=" + telNum + "]";
	}
	
	
}