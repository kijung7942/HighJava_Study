package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.DBUtil3;
import vo.BoardVO;

public class BoardDao implements IBoardDAO {
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	@Override
	public int write(BoardVO vo) {
		con = DBUtil3.getConnection();
		int cnt = 0;
		String sql = "INSERT INTO JDBC_BOARD VALUES (BOARD_SEQ.nextVal, ? , ? , SYSDATE , 0 , ?)";
		
		try {
			ps = con.prepareStatement(sql);
			ps.setObject(1, vo.getBoard_title());
			ps.setObject(2, vo.getBoard_writer());
			ps.setObject(3, vo.getBoard_content());
		
			cnt = ps.executeUpdate();
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		} finally {
			if(rs!=null)try {rs.close();} catch (Exception e) {}
			if(ps!=null)try {ps.close();} catch (Exception e) {}
			if(con!=null)try {con.close();} catch (Exception e) {}
		}
		return cnt;
	}

	@Override
	public BoardVO read(int number) {
		con = DBUtil3.getConnection();
		int cnt = 0;
		String sql = "SELECT * FROM JDBC_BOARD WHERE BOARD_NO = ? ORDER BY 1 DESC";
		BoardVO vo = new BoardVO();
		try {
			ps = con.prepareStatement(sql);
			ps.setObject(1, number);
			rs = ps.executeQuery();
			
			if(rs.next())vo = new BoardVO(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getString(6));
				
		} catch (SQLException e) {
			vo = null;
			e.printStackTrace();
		} finally {
			if(rs!=null)try {rs.close();} catch (Exception e) {}
			if(ps!=null)try {ps.close();} catch (Exception e) {}
			if(con!=null)try {con.close();} catch (Exception e) {}
		}
		return vo;
	}

	@Override
	public int update(BoardVO vo) {
		con = DBUtil3.getConnection();
		int cnt = 0;
		String sql = "UPDATE JDBC_BOARD SET BOARD_TITLE = ?, BOARD_WRITER = ?, BOARD_CNT = ?, BOARD_CONTENT =? "
				+ " WHERE BOARD_NO = ?";
		
		try {
			ps = con.prepareStatement(sql);
			ps.setObject(1, vo.getBoard_title());
			ps.setObject(2, vo.getBoard_writer());
			ps.setObject(3, vo.getBoard_cnt());
			ps.setObject(4, vo.getBoard_content());
			ps.setObject(5, vo.getBoard_no());
			
			cnt = ps.executeUpdate();
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		} finally {
			if(rs!=null)try {rs.close();} catch (Exception e) {}
			if(ps!=null)try {ps.close();} catch (Exception e) {}
			if(con!=null)try {con.close();} catch (Exception e) {}
		}
		return cnt;
	}

	@Override
	public int delete(int number) {
		con = DBUtil3.getConnection();
		int cnt = 0;
		String sql = "DELETE FROM JDBC_BOARD WHERE BOARD_NO = ?";
		
		try {
			ps = con.prepareStatement(sql);
			ps.setObject(1, number);
			
			cnt = ps.executeUpdate();
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		} finally {
			if(rs!=null)try {rs.close();} catch (Exception e) {}
			if(ps!=null)try {ps.close();} catch (Exception e) {}
			if(con!=null)try {con.close();} catch (Exception e) {}
		}
		return cnt;
	}

	public List<BoardVO> readAll() {
		con = DBUtil3.getConnection();
		int cnt = 0;
		String sql = "SELECT * FROM JDBC_BOARD ORDER BY 1 DESC";
		List<BoardVO> list = new ArrayList<>();
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				BoardVO vo = new BoardVO(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getString(6));
				list.add(vo);
			}
			
		} catch (SQLException e) {
			list = null;
			e.printStackTrace();
		} finally {
			if(rs!=null)try {rs.close();} catch (Exception e) {}
			if(ps!=null)try {ps.close();} catch (Exception e) {}
			if(con!=null)try {con.close();} catch (Exception e) {}
		}
		return list;
	}

	@Override
	public List<BoardVO> search(String title) {
		con = DBUtil3.getConnection();
		String sql = "SELECT * FROM JDBC_BOARD WHERE BOARD_TITLE LIKE ? ORDER BY 1 DESC";
		List<BoardVO> list = new ArrayList<>();
		try {
			ps = con.prepareStatement(sql);
			ps.setObject(1, "%"+title+"%");
			rs = ps.executeQuery();
			
			while(rs.next()) {
				BoardVO vo = new BoardVO(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getString(6));
				list.add(vo);
			}
		} catch (SQLException e) {
			list = null;
			e.printStackTrace();
		} finally {
			if(rs!=null)try {rs.close();} catch (Exception e) {}
			if(ps!=null)try {ps.close();} catch (Exception e) {}
			if(con!=null)try {con.close();} catch (Exception e) {}
		}
		return list;
	}

}
