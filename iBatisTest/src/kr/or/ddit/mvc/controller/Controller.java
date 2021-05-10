package kr.or.ddit.mvc.controller;

import java.util.List;
import java.util.Scanner;

import kr.or.ddit.mvc.service.BoardService;
import kr.or.ddit.mvc.vo.BoardVO;

public class Controller {

	BoardService service = new BoardService();
	Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		new Controller().start();
	}

	
	public void start(){
		int input = 0;
		
		while(true) {
			System.out.println("-----------------------------------------------");
			System.out.println("NO\t\t제 목\t\t작성자\t조회수");
			System.out.println("-----------------------------------------------");
			readAll();
			System.out.println("-----------------------------------------------");
			System.out.println("메뉴 : 1. 새글작성 2. 게시글보기 3. 검색 0. 작업끝");
			System.out.println("작업선택");
			input = Integer.parseInt(sc.nextLine());
			switch(input) {
			case 1:
				write();
				break;
			case 2:
				read();
				break;
			case 3:
				search();
				break;
			case 0:
				System.out.println("프로그램을 종료합니다.");
				System.exit(0);
			}
		}
	}


	private void search() {
				System.out.println("검색 작업");
		System.out.println("-----------------------------------------------");
		System.out.println("- 검색할 제목 입력 : ");
		String title = sc.nextLine();
		List<BoardVO> list = service.search(title);
		
		System.out.println("-----------------------------------------------");
		System.out.println("NO\t\t제 목\t\t작성자\t조회수");
		System.out.println("-----------------------------------------------");
		for(BoardVO vo : list) {
			System.out.println(vo.getBoard_no()+"\t\t"+vo.getBoard_title()+"\t\t"+vo.getBoard_writer()+"\t"+vo.getBoard_cnt());
		}
		System.out.println("-----------------------------------------------");
		System.out.println("메뉴 : 1. 새글작성 2. 게시글보기 3. 검색 0. 작업끝");
		System.out.println("작업선택");
		int input = Integer.parseInt(sc.nextLine());
		switch(input) {
		case 1:
			write();
			break;
		case 2:
			read();
			break;
		case 3:
			search();
			break;
		case 0:
			System.out.println("프로그램을 종료합니다.");
			System.exit(0);
		}
		
	}


	private void readAll() {
		List<BoardVO> list = service.readAll();
		for(BoardVO vo:list) {
			System.out.println(vo.getBoard_no()+"\t\t"+vo.getBoard_title()+"\t\t"+vo.getBoard_writer()+"\t"+vo.getBoard_cnt());
		}
	}


	private void read() {
		BoardVO vo = null;
		System.out.println("보기를 원하는 게시물 번호 입력>>");
		int input = Integer.parseInt(sc.nextLine());
		vo = service.read(input);
		if(vo == null) {
			System.out.println("해당 번호의 게시물은 없습니다.");
			return;
		}
		System.out.println(input + "번 글 내용");
		System.out.println("-----------------------------------------------");
		System.out.println(" - 제 목 : " + vo.getBoard_title());
		System.out.println(" - 작성자 : " + vo.getBoard_writer());
		System.out.println(" - 내 용 : " + vo.getBoard_content());
		System.out.println(" - 작성일 : " + vo.getBoard_date());
		System.out.println(" - 조회수 : " + (vo.getBoard_cnt()+1));
		vo.setBoard_cnt(vo.getBoard_cnt()+1);
		service.update(vo);
		System.out.println("-----------------------------------------------");
		System.out.println("메뉴 : 1. 수정  2. 삭제  3. 리스트로 가기");
		int input2 = Integer.parseInt(sc.nextLine());
		switch(input2) {
		case 1:
			System.out.println("수정 작업하기");
			System.out.println("-----------------------------------------------");
			System.out.println(" - 제 목 : ");
			String title = sc.nextLine();
			System.out.println(" - 내 용 : ");
			String content = sc.nextLine();
			vo.setBoard_title(title);
			vo.setBoard_content(content);
			int res = service.update(vo);
			if(res>0) {
				System.out.println(input + "번 글이 수정되었습니다.");
			}
			
			break;
		case 2:
			int cnt = service.delete(input);
			if(cnt>0) {
				System.out.println(input + "번 글이 삭제되었습니다.");
			}else {
				System.out.println("삭제 실패");
			}
			break;
		case 3:
			System.out.println("리스트로 돌아갑니다.");
			return;
		}
	}


	private void write() {
		System.out.println("새글 작성하기");
		System.out.println("-----------------------------------------------");
		System.out.println("- 제  목:");
		String title = sc.nextLine();
		System.out.println("- 작성자:");
		String writer = sc.nextLine();
		System.out.println("- 내  용:");
		String content = sc.nextLine();
		
		BoardVO vo = new BoardVO();
		vo.setBoard_title(title);
		vo.setBoard_writer(writer);
		vo.setBoard_content(content);
		
		int cnt = service.write(vo);
		if(cnt > 0) {
			System.out.println("글 작성 완료");
		}else {
			System.out.println("글 작성 실패");
		}
	}
}
