package dao;

import java.util.List;

import vo.BoardVO;

public interface IBoardDAO {

	public int write(BoardVO vo);
	public int update(BoardVO vo);
	public int delete(int number);
	public BoardVO read(int number);
	public List<BoardVO> search(String title);
	
}
