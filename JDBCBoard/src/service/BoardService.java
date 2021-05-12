package service;

import java.util.List;

import dao.BoardDao;
import vo.BoardVO;

public class BoardService implements IBoardService {

	BoardDao dao = new BoardDao();
	
	@Override
	public int write(BoardVO vo) {
		return dao.write(vo);
	}

	@Override
	public BoardVO read(int number) {
		return dao.read(number);
	}

	@Override
	public int update(BoardVO vo) {
		return dao.update(vo);
	}

	@Override
	public int delete(int number) {
		return dao.delete(number);
	}

	public List<BoardVO> readAll() {
		return dao.readAll();
	}

	@Override
	public List<BoardVO> search(String title) {
		return dao.search(title);
	}

}
