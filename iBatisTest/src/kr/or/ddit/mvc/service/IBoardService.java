package kr.or.ddit.mvc.service;

import java.util.List;

import kr.or.ddit.mvc.vo.BoardVO;

public interface IBoardService {
	
	
	public int write(BoardVO vo);
	public List<BoardVO> search(String title);
	public int update(BoardVO vo);
	public int delete(int number);
	public BoardVO read(int number);
}
