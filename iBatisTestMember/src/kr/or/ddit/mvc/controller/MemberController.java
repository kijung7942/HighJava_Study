package kr.or.ddit.mvc.controller;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import kr.or.ddit.mvc.service.IMemberService;
import kr.or.ddit.mvc.service.MemberServiceImpl;
import kr.or.ddit.mvc.vo.MemberVO;
import kr.or.ddit.util.CryptoUtil;

/*
ibatisTest 프로젝트에 작성한 회원 관리 프로그램에 암호화 기능을 적용해 보시오.

- 회원ID는 양방향 암호화를 사용해서 DB에 저장하고
  DB에 저장된 데이터를 가져와 화면에 보여줄 때는 
  원래 데이터로 보이게한다.
- 비밀번호는 단방향 암호화를 적용해서 DB에 저장한다.
  (단, DB 컬럼의 길이도 암호화에 맞게 변경한다.)
*/

public class MemberController {
	private IMemberService service;   // Service객체가 저장될 변수 선언
	private Scanner scan;
	private final String key = "!@#@!%@#%#$!@#$^&*";
	// 생성자
	public MemberController() {
		scan = new Scanner(System.in);
		//service = new MemberServiceImpl();
		service = MemberServiceImpl.getInstance();
	}

	public static void main(String[] args) throws Exception {
		new MemberController().memberStart();
	}
	
	// 작업을 시작하는 메서드
	private void memberStart() throws Exception{
		while(true){
			int choice = displayMenu();
			switch(choice){
				case 1 :	// 추가
					insertMember(); break;
				case 2 :	// 삭제
					deleteMember(); break;
				case 3 :	// 수정
					updateMember(); break;
				case 4 :	// 전체 출력
					displayAllMember(); break;
				case 5 :	// 수정2
					updateMember2(); break;
				case 0 :	// 종료
					System.out.println("작업을 마칩니다.");
					return;
				default :
					System.out.println("번호를 잘못 입력했습니다. 다시 입력하세요.");
			}
		}
	}
	
	// 회원 정보 수정2
	private void updateMember2() throws Exception{
		System.out.println();
		System.out.println("수정할 회원 정보를 입력하세요.");
		System.out.print("회원ID >> ");
		String memId = scan.next();
		
		String encryptMemId = CryptoUtil.encryptAES256(memId, key);
		
		int count = service.getMemberCount(encryptMemId);
		if(count==0){  	// 없는 회원이면...
			System.out.println(memId + "은(는) 없는 회원ID 입니다.");
			System.out.println("수정 작업을 종료합니다.");
			return;
		}
		
		int num;	// 수정항목 선택 메뉴 번호가 저장될 변수
		String updateField = null;	// 수정항목의 컬럼명이 저장될 변수
		String updateTitle = null;	// 수정항목의 제목이름이 저장될 변수
		do{
			System.out.println();
			System.out.println("수정할 항목을 선택하세요.");
			System.out.println(" 1. 비밀번호     2. 회원이름     3. 전화번호     4. 회원주소");
			System.out.println("---------------------------------------------");
			System.out.print(" 수정 항목 선택 >> ");
			num = scan.nextInt();
			
			switch(num){
				case 1 : updateField = "mem_pass"; 
						updateTitle = "비밀번호"; break;
				case 2 : updateField = "mem_name"; 
						updateTitle = "회원이름"; break;
				case 3 : updateField = "mem_tel"; 
						updateTitle = "전화번호"; break;
				case 4 : updateField = "mem_addr"; 
						updateTitle = "회원주소"; break;
				default : System.out.println("수정 항목을 잘못 선택했습니다. 다시 선택하세요.");
			}
			
		}while(num<1 || num>4);
		
		System.out.println();
		scan.nextLine();  // 입력버퍼 비우기
		System.out.print("새로운 " + updateTitle + " >> ");
		String updateData = scan.nextLine();	// 수정할 값 입력 받기
		
		// 수정에 필요한 만들어진 자료들을 Map에 추가한다.
		// key값 정보  ==> 회원ID(memId), 수정할 컬럼명(field), 수정할데이터값(data)
		Map<String, String> paramMap = new HashMap<>();
		paramMap.put("memId", encryptMemId);
		paramMap.put("field", updateField);
		paramMap.put("data", updateData);
		
		int cnt = service.updateMember2(paramMap);
		
		if(cnt>0){
			System.out.println("원하는 항목 수정 완료~~~");
		}else{
			System.out.println("원하는 항목 수정 실패!!!");
		}
		
	}
	
	// 전체 자료 출력하는 메서드
	private void displayAllMember() throws InvalidKeyException, UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException{
		
		List<MemberVO> memList = service.getAllMember();
		
		System.out.println();
		System.out.println("---------------------------------------------");
		System.out.println(" ID\t회원이름\t전화번호\t주 소");
		System.out.println("---------------------------------------------");
		
		if(memList==null || memList.size()==0){
			System.out.println(" 등록된 회원 정보가 하나도 없습니다..");
		}else{
			for(MemberVO memVo : memList){
				String memId = CryptoUtil.decryptAES256(memVo.getMem_id(),key);
//				String memPass = memVo.getMem_pass();
				String memName = memVo.getMem_name();
				String memTel = memVo.getMem_tel();
				String memAddr = memVo.getMem_addr();
//				System.out.println(" " + memId + "\t" + memPass + "\t" + memName 
				System.out.println(" " + memId + "\t" + memName	+ "\t" + memTel + "\t" + memAddr );
			}
		}
		
		System.out.println("-----------------------------------------------------------");
		System.out.println("출력 끝...");
		
	}
	
	
	// 자료 수정 메서드
	private void updateMember() throws InvalidKeyException, UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException{
		System.out.println();
		System.out.println("수정할 회원 정보를 입력하세요.");
		System.out.print("회원ID >> ");
		String memId = scan.next();
		String cryptoMemId = CryptoUtil.encryptAES256(memId, key);
		
		int count = service.getMemberCount(cryptoMemId);
		if(count==0){  	// 없는 회원이면...
			System.out.println(memId + "은(는) 없는 회원ID 입니다.");
			System.out.println("수정 작업을 종료합니다.");
			return;
		}
		
		System.out.println();
		System.out.println("수정할 내용을 입력하세요.");
		System.out.print("새로운 비밀번호 >> ");
		String memPass = scan.next();
		String shaMemPass = CryptoUtil.sha512(memPass);
		
		System.out.print("새로운 회원이름 >> ");
		String memName = scan.next();
		
		System.out.print("새로운 전화번호 >> ");
		String memTel = scan.next();
		scan.nextLine(); // 버퍼 비우기
		System.out.print("새로운 회원주소 >> ");
		String memAddr = scan.nextLine();
		
		MemberVO memVo = new MemberVO();
		
		memVo.setMem_id(cryptoMemId);
		memVo.setMem_pass(shaMemPass);
		memVo.setMem_name(memName);
		memVo.setMem_tel(memTel);
		memVo.setMem_addr(memAddr);
		
		int cnt = service.updateMember(memVo);
		
		if(cnt>0){
			System.out.println("update 성공~~");
		}else{
			System.out.println("update 실패!!!");
		}		
		
		
	}
	
	
	// 자료 삭제 메서드
	private void deleteMember() throws InvalidKeyException, UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException{
		System.out.println();
		System.out.println("삭제할 회원 정보를 입력하세요.");
		System.out.print("회원ID >> ");
		String memId = scan.next();
		String cryptoMemId = CryptoUtil.encryptAES256(memId, key);
		int cnt = service.deleteMember(cryptoMemId);
		
		if(cnt>0){
			System.out.println("delete 성공~~");
		}else{
			System.out.println("delete 실패!!!");
		}
		
	}
	
	
	// 추가 메서드
	private void insertMember() throws InvalidKeyException, UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException{
		System.out.println();
		System.out.println("추가할 회원 정보를 입력하세요.");
		
		int count = 0;  // 회원 수가 저장될 변수
		String cryptoMemId;
		String memId = null;	// 회원ID가 저장될 변수
		do{
			System.out.print("회원ID >> ");
			memId = scan.next();
			cryptoMemId = CryptoUtil.encryptAES256(memId, key);
			count = service.getMemberCount(cryptoMemId);  // 회원 수를 구하는 메서드 호출
			if(count>0){
				System.out.println(memId + "은(는) 이미 등록된 회원ID 입니다.");
				System.out.println("다른 회원ID를 입력하세요.");
			}
		}while(count>0);
		
		System.out.print("비밀번호 >> ");
		String memPass = scan.next();
		String cryptoMemPass = CryptoUtil.sha512(memPass);
		System.out.print("회원이름 >> ");
		String memName = scan.next();
		System.out.print("전화번호 >> ");
		String memTel = scan.next();
		scan.nextLine();  // 입력 버퍼 비우기...
		System.out.print("회원주소 >> ");
		String memAddr = scan.nextLine();
		
		// 입력 받은 데이터들을 MemberVO객체에 담아준다.
		MemberVO memVo = new MemberVO();
		memVo.setMem_id(cryptoMemId);
		memVo.setMem_pass(cryptoMemPass);
		memVo.setMem_name(memName);
		memVo.setMem_tel(memTel);
		memVo.setMem_addr(memAddr);
		
		// Service객체의 insert하는 메서드를 호출한다.
		int cnt = service.insertMember(memVo);
		
		if(cnt>0){
			System.out.println("추가 작업 성공~~~");
		}else{
			System.out.println("추가 작업 실패!!!");
		}
		
		
	}
	
	
	
	// 메뉴를 출력하고 선택한 작업 번호를 반환하는 메서드
	private int displayMenu(){
		System.out.println();
		System.out.println("  -- 작 업 선 택 --");
		System.out.println("  1. 자료 추가");
		System.out.println("  2. 자료 삭제");
		System.out.println("  3. 자료 수정");
		System.out.println("  4. 전체 자료 출력");
		System.out.println("  5. 자료 수정2");
		System.out.println("  0. 작업 끝.");
		System.out.println("-------------------");
		System.out.print("원하는 작업 선택 >> ");
		int num = scan.nextInt();
		return num;
	}
	

}
