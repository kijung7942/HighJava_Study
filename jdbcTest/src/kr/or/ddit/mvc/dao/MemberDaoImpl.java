package kr.or.ddit.mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import kr.or.ddit.mvc.vo.MemberVO;
import kr.or.ddit.util.DBUtil3;

public class MemberDaoImpl implements IMemberDao {

	private static MemberDaoImpl instance;
	
	private MemberDaoImpl() {	}
	
	public static MemberDaoImpl getInstance() {
		if(instance ==null) instance = new MemberDaoImpl();
		return instance;
	}
	
	
	@Override
	public int insertMember(MemberVO memVo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = 0;
		
		try {
			conn = DBUtil3.getConnection();
			String sql = "INSERT INTO MYMEMBER (MEM_ID, MEM_PASS, MEM_NAME, MEM_TEL, MEM_ADDR) "
					+ " VALUES (?, ?, ?, ?, ?) ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memVo.getMem_id());
			pstmt.setString(2, memVo.getMem_pass());
			pstmt.setString(3, memVo.getMem_name());
			pstmt.setString(4, memVo.getMem_tel());
			pstmt.setString(5, memVo.getMem_addr());
			
			cnt = pstmt.executeUpdate();
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		} finally {
			if(pstmt != null)try {pstmt.close();} catch (Exception e) {}
			if(conn != null)try {conn.close();} catch (Exception e) {}
		}
		
		return cnt;
	}

	@Override
	public int deleteMember(String memId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = 0;
		
		try {
			conn = DBUtil3.getConnection();
			String sql = "DELETE FROM MYMEMBER WHERE MEM_ID = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			
			cnt = pstmt.executeUpdate();
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		} finally {
			if(pstmt != null)try {pstmt.close();} catch (Exception e) {}
			if(conn != null)try {conn.close();} catch (Exception e) {}
		}
		
		return cnt;
	}

	@Override
	public int updateMember(MemberVO memVo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = 0;
		
		try {
			conn = DBUtil3.getConnection();
			String sql = "UPDATE MYMEMBER SET MEM_PASS = ?, MEM_NAME = ?, MEM_TEL = ?, MEM_ADDR = ? WHERE MEM_ID = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memVo.getMem_pass());
			pstmt.setString(2, memVo.getMem_name());
			pstmt.setString(3, memVo.getMem_tel()); 
			pstmt.setString(4, memVo.getMem_addr());
			pstmt.setString(5, memVo.getMem_id());  
			
			cnt = pstmt.executeUpdate();
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		} finally {
			if(pstmt != null)try {pstmt.close();} catch (Exception e) {}
			if(conn != null)try {conn.close();} catch (Exception e) {}
		}
		return cnt;
	}
	
	@Override
	public List<MemberVO> getAllMember() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<MemberVO> list = new ArrayList<>();;
		
		try {
			conn = DBUtil3.getConnection();
			String sql = "SELECT * FROM MYMEMBER";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				MemberVO vo = new MemberVO(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5));
				list.add(vo);
			}
		} catch (SQLException e) {
			list = null;
			e.printStackTrace();
		} finally {
			if(rs != null)try {rs.close();} catch (Exception e) {}
			if(pstmt != null)try {pstmt.close();} catch (Exception e) {}
			if(conn != null)try {conn.close();} catch (Exception e) {}
		}
		return list;
	}

	@Override
	public int getMemberCount(String memId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		try {
			conn = DBUtil3.getConnection();
			String sql = "SELECT COUNT(*) cnt FROM MYMEMBER WHERE MEM_ID = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				count = rs.getInt("cnt");
			}
		} catch (Exception e) {
			count = 0;
			e.printStackTrace();
		} finally {
			if(rs != null)try {rs.close();} catch (Exception e) {}
			if(pstmt != null)try {pstmt.close();} catch (Exception e) {}
			if(conn != null)try {conn.close();} catch (Exception e) {}
		}
		
		return count;
	}

	@Override
	public int updateMember2(Map<String, String> paramMap) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = 0;
		
		try {
			conn = DBUtil3.getConnection();
			String sql = "UPDATE MYMEMBER SET ? = ? WHERE MEM_ID = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setObject(1, paramMap.get("field"));
			pstmt.setString(2, paramMap.get("data"));
			pstmt.setString(3, paramMap.get("memId")); 
			
			cnt = pstmt.executeUpdate();
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		} finally {
			if(pstmt != null)try {pstmt.close();} catch (Exception e) {}
			if(conn != null)try {conn.close();} catch (Exception e) {}
		}
		return cnt;
	}

}
