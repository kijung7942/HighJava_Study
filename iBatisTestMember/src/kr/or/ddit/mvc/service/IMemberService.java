package kr.or.ddit.mvc.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.mvc.vo.MemberVO;
/**
 * Service객체는 DAO에 작성된 메서드를 원하는 작업에 맞게 호출하여
 * 결과를 받아오고, 받아온 결과를 Controller에게 보내주는 역할을 한다.
 * 
 * 보통 DAO의 interface와 구조가 같게 만든다.
 * 
 * @author 404-SEM
 *
 */

public interface IMemberService {
	/**
	 * MemberVO에 담겨진 자료를 DB에 insert하는 메서드
	 * @param memVo DB에 insert할 자료가 저장된 MemberVO객체
	 * @return insert작업 성공 : 1이상의 정수, 작업실패 : 0
	 */
	public int insertMember(MemberVO memVo);
	
	/**
	 * 회원ID를 인수로 받아서 해당 회원 정보를 삭제하는 메서드
	 * @param memId 삭제할 회원ID
	 * @return 작업성공 : 1, 작업실패 : 0
	 */
	public int deleteMember(String memId);
	
	/**
	 * MemberVO자료를 이용하여 DB에 update하는 메서드
	 * @param memVo update할 회원 정보가 들어 있는 MemberVO객체
	 * @return 작업성공 : 1, 작업실패 : 0
	 */
	public int updateMember(MemberVO memVo);
	
	/**
	 * DB에 있는 전체 회원 정보를 가져와 List에 담아서 반환하는 메서드
	 * 
	 * @return MemberVO객체가 저장된 List객체
	 */
	public List<MemberVO> getAllMember();
	
	/**
	 * 회원ID를 인수값으로 받아사 해당 회원 정보의 개수를 반환하는 메서드
	 * @param memId 검색할 회원ID
	 * @return 검색된 회원ID 개수
	 */
	public int getMemberCount(String memId);
	
	/**
	 * Map의 정보를 이용해서 회원 정보 중 원하는 컬럼을 수정하는 메서드
	 * 	key값 정보  ==> 회원ID(memId), 수정할 컬럼명(field), 수정할데이터값(data)
	 * 
	 * @param paramMap 회원ID, 수정할컬럼명, 수정할데이터값이 저장된 Map객체
	 * @return 작업성공 : 1, 작업실패 : 0
	 */
	public int updateMember2(Map<String, String> paramMap);
}
