package kr.or.ddit.mvc.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import kr.or.ddit.mvc.service.MemberServiceImpl;
import kr.or.ddit.mvc.vo.MemberVO;
import kr.or.ddit.util.ScanUtil;

public class MemberController {
	private MemberServiceImpl service; // Service 객체가 저장될 변수 선언
	private Scanner scan;

	//생성자 
	public MemberController() {
		scan = new Scanner(System.in);
		service = MemberServiceImpl.getInstance();
	}
	
	public static void main(String[] args) {
		new MemberController().memberStart();
	}



	private void memberStart() {

			while(true) {
			System.out.println("-- 작업 선택 --");
			System.out.println("1. 자료 추가");
			System.out.println("2. 자료 삭제");
			System.out.println("3. 자료 수정");
			System.out.println("4. 전체 자료 출력");
			System.out.println("5. 자료수정2");
			System.out.println("0. 작업 끝");
			int input = ScanUtil.nextInt();
			switch(input) {
			case 1:
				add();
				break;
			case 2:
				delete();
				break;
			case 3:
				modifyAll();
				break;
			case 4:
				printAll();
				break;
			case 5:
				modify();
				break;
			case 0:
				System.out.println("프로그램을 종료합니다.");
				return;
			default:
				System.out.println("잘못입력함");
			}
	}

}



	private void modify() {
		System.out.println("수정할 ID를 입력하세요");
		String memberID = ScanUtil.nextLine();
		Map<String,String> paramMap = new HashMap<>();
		while(true) {	
			int count = service.getMemberCount(memberID);
			if(count == 0) {
				System.out.println("등록된 ID가 없습니다.");
				return;
			}else {
				paramMap.put("memId", memberID);
				break;
			}
		}
		System.out.println("수정 할 정보를 선택하세요");
		System.out.println("1. 비밀번호 2. 이름 3. 전화번호 4. 주소");
		int input = Integer.parseInt(scan.nextLine());
		switch(input) {
		case 1: paramMap.put("field", "MEM_PASS"); break;
		case 2: paramMap.put("field", "MEM_NAME"); break;
		case 3: paramMap.put("field", "MEM_TEL"); break;
		case 4: paramMap.put("field", "MEM_ADDR"); break;
		default: System.out.println("잘못 입력 하셨습니다.");
		}
		
		System.out.println("수정 할 정보를 입력하세요");
		
	}

	private void printAll() {
		System.out.println("== 자료 전체 출력 ==");
		List<MemberVO> list = service.getAllMember();
		System.out.println("ID \t Pass \t Name \t H.P \t Addr");
		if(list == null || list.size() == 0) {
			System.out.println("등록된 회원정보가 없습니다.");
		}else {
			for(MemberVO vo:list) {
				System.out.println(vo.getMem_id()+"\t"+vo.getMem_pass()+"\t"+vo.getMem_name()+"\t"+vo.getMem_tel()+"\t"+vo.getMem_addr()+"\t");
			}
		}
	}

	private void modifyAll() {
		System.out.println("수정할 ID를 입력하세요");
		String memberID = ScanUtil.nextLine();
		while(true) {	
			int count = service.getMemberCount(memberID);
			if(count == 0) {
				System.out.println("등록된 ID가 없습니다.");
				return;
			}else {
				break;
			}
		}
		MemberVO vo = new MemberVO();
		
		System.out.println("수정 할 비밀번호을 입력하세요");
		String afterPass = ScanUtil.nextLine();
		System.out.println("수정 할 이름을 입력하세요");
		String afterName = ScanUtil.nextLine();
		System.out.println("수정 할 전화번호을 입력하세요");
		String afterTel = ScanUtil.nextLine();
		System.out.println("수정 할 주소을 입력하세요");
		String afterAddr = ScanUtil.nextLine();
		
		vo.setMem_id(memberID);
		vo.setMem_pass(afterPass);
		vo.setMem_name(afterName);
		vo.setMem_tel(afterTel);
		vo.setMem_addr(afterAddr);
		
		int cnt = service.updateMember(vo);
		if(cnt > 0) {
			System.out.println("수정완료");
		}else {
			System.out.println("수정실패");
		}
	}
	

	private void delete() {
		 System.out.println("== 자료 삭제 ==");
		while(true) {
			System.out.println("삭제할 ID를 입력하세요");
			String memberID = ScanUtil.nextLine();
			int result = service.deleteMember(memberID);
			if(result > 0) System.out.println("삭제 성공");
			else System.out.println("삭제 실패");
			return;
		}
	}

	private void add() {
		System.out.println("== 자료 입력 ==");
		int count = 0;
		System.out.println("ID 입력");
		String memberId = ScanUtil.nextLine();
		count = service.getMemberCount(memberId);
		while(count == 0) {
			System.out.println("비밀번호를 입력해주세요");
			String memberPass = ScanUtil.nextLine();
			System.out.println("이름을 입력해주세요");
			String memberName = ScanUtil.nextLine();
			System.out.println("전화번호를 입력해주세요");
			String memberTel = ScanUtil.nextLine();
			System.out.println("주소를 입력해주세요");
			String memberAddr = ScanUtil.nextLine();
		
			// 입력받은 데이터들을 MemberVO객체에 담아준다.
			MemberVO memVo = new MemberVO();
			memVo.setMem_id(memberId);
			memVo.setMem_pass(memberPass);
			memVo.setMem_name(memberName);
			memVo.setMem_tel(memberTel);
			memVo.setMem_addr(memberAddr);
		
			// Service객체의 insert하는 메서드를 호출한다
			
			int cnt = service.insertMember(memVo);
			
			if(cnt > 0) {
				System.out.println("저장이 완료되었습니다.");
			}else {
				System.out.println("실패했습니다.");
			}
			return;
		}
		System.out.println("중복된 이름이 있습니다.");
	}
}
