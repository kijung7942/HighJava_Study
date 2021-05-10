package kr.or.mvc.dao;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.mvc.vo.BoardVO;
import kr.or.ddit.util.BuildedSqlMapClient;

public class BoardDao implements IBoardDAO {
	SqlMapClient smc = BuildedSqlMapClient.getSqlMapClient();
	@Override
	public BoardVO read(int number) {
		BoardVO vo = null;
		try {
			vo = (BoardVO) smc.queryForObject("jdbc_board.selectjdbc_board",number);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vo;
	}
	
	@Override
	public int write(BoardVO vo) {
		int cnt = 0;
		try {
			 if(smc.insert("jdbc_board.insertjdbc_board",vo)==null) {cnt=1;};
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}
	
	@Override
	public int update(BoardVO vo) {
		int cnt = 0;
		try {
			cnt = (int) smc.update("jdbc_board.updatecnt",vo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}
	@Override
	public int delete(int number) {
		int cnt = 0;
		try {
			cnt = (int) smc.delete("jdbc_board.deletejdbc_board",number);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}
	@Override
	public List<BoardVO> search(String number) {
		List<BoardVO> list = null;
		try {
			list = smc.queryForList("jdbc_board.getjdbc_board",number);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<BoardVO> readAll() {
		List<BoardVO> list = null;
		try {
			list = smc.queryForList("jdbc_board.getAlljdbc_board");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
}
