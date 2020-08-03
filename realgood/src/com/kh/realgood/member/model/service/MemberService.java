package com.kh.realgood.member.model.service;


import static com.kh.realgood.common.DBCP.getConnection;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import com.kh.realgood.member.model.dao.MemberDAO;
import com.kh.realgood.member.model.dto.Board;
import com.kh.realgood.member.model.dto.BuyList;
import com.kh.realgood.member.model.dto.Member;
import com.kh.realgood.store.model.dto.Store;

public class MemberService {
	private MemberDAO dao;
	
	public MemberService() throws FileNotFoundException, IOException {
		dao = new MemberDAO();
	}
	
	/** 로그인용 Service
	 * @param member
	 * @return loginMember
	 * @throws Exception
	 */
	public Member loginMember(Member member) throws Exception {
		
		// 커넥션 얻어오기
		Connection conn = getConnection();
		
		// dao 호출
		Member loginMember = dao.loginMember(conn, member);
		
		// 커넥션 반환
		conn.close();
		
		// 결과 반환
		return loginMember;
	}
	
	/** 회원 가입용 Service
	 * @param member
	 * @return result
	 * @throws Exception
	 */
	public int signUp(Member member) throws  Exception{
		
		Connection conn = getConnection();
		
		int result = dao.signUp(conn, member);
		
		if(result >0) {
			conn.commit();
		}else {
			conn.rollback();
		}
		
		conn.close();
		
		return result;
	}
	
	/** 아이디 중복 체크용 Service
	 * @param id
	 * @return result
	 */
	public int idDupCheck(String id) throws Exception {
		
		Connection conn = getConnection();
		int result = dao.idDupCheck(conn, id);		
		
		conn.close();
		
		return result;
	}

	/** 회원 내정보 변경창
	 * @param member
	 * @return result
	 * @throws Exception
	 */
	public int updateMember(Member member) throws Exception{
		Connection conn = getConnection();
		int result = dao.updateMember(conn, member);
		
		if(result >0) {
			conn.commit();
		}else {
			conn.rollback();
		}
		
		conn.close();
		
		return result;
	}
	
	// 크로스 사이트 스크립팅 방지
	   private String replaceParameter(String param) {
	      String result = param;
	      if(param != null) {
	    	 result = result.replaceAll("&", "&amp;");
	         result = result.replaceAll("<", "&lt;");
	         result = result.replaceAll(">", "&gt;");
	         result = result.replaceAll("\"", "&quot;");
	      }
	      
	      return result;
	   }

	/** 회원 패스워드 변경
	 * @param id
	 * @param oldPwd
	 * @param newPwd
	 * @return result
	 * @throws Exception
	 */
	public int changePwd(String id, String oldPwd, String newPwd) throws Exception{
		Connection conn = getConnection(); 
		// 현재 패스워드 확인
		int result = dao.checkPwd(conn,id,oldPwd);
		// 패스워드 확인 결과 값이 0이상 일경우
		if (result > 0) {
			// 비밀번호 변경 진행
			result = dao.changePwd(conn,id,newPwd);
			if(result > 0) {
				conn.commit();
			}else {
				conn.rollback();
			}
		}else {
			// 현재 패스워드가 틀린경우 -1을 반환
			result = -1;
		}
		
		return result;
	}

	/** 회원 탈퇴 서비스
	 * @param id
	 * @param gradeName 
	 * @return return
	 * @throws Exception
	 */
	public int removeUser(String id, String gradeName) throws Exception{
		Connection conn = getConnection();
		
		int result = dao.removeUser(conn,id);
		int count = 0;
		
		if (gradeName.equals("사장회원")) {
			count = dao.removeStoreCount(conn, id);
			if (count > 0) {
				result = dao.removeStore(conn, id);
			}
		}
		if (result > 0) {
			conn.commit();
		}else {
			conn.rollback();
		}
		conn.close();
		return result;
	}

	/** 관리자 - 회원 조회 Service
	 * @return list
	 * @throws Exception
	 */
	public List<Member> selectMember() throws Exception{
		Connection conn = getConnection();
		
		List<Member> list = dao.selectMember(conn);
		
		conn.close();
		
		return list;
	}

	/** 관리자 - 회원 삭제 Service
	 * @param idArr
	 * @param gradeNameArr 
	 * @param gradeNameArr 
	 * @return result
	 * @throws Exception
	 */
	public int deleteMember(String[] idArr, String[] gradeNameArr) throws Exception{
		Connection conn = getConnection();
		
		int result = 0;
		
		for(int i=0; i<idArr.length; i++) {
			String id = idArr[i];
			String gradeName = gradeNameArr[i];
			int count = 0;
			result = dao.deleteMember(id, conn);
			if (gradeName.equals("사장회원")) {
				count = dao.removeStoreCount(conn, id);
				if (count > 0) {
					result = dao.removeStore(conn, id);
				}
			}
			if(result == 0) break;
		}
		
		
		if(result > 0) conn.commit();
		else conn.rollback();
		
		conn.close();
	
		return result;
	}

	/** 구매목록 조회용 DAO
	 * @param no
	 * @return list
	 * @throws Exception
	 */
	public List<BuyList> PurchaseList(int memberNo) throws Exception{
		Connection conn = getConnection();
		
		List<BuyList> list = dao.PurchaseList(conn, memberNo);
		
		conn.close();
		
		return list;
	}

	/** 아이디 찾기 
	 * @param name
	 * @param tel
	 * @return id
	 * @throws Exception
	 */
	public List<String> findId(String name, String tel) throws Exception{
	
		Connection conn = getConnection();
		
		List<String> list = dao.findId(conn,name,tel);
		
		if(!list.isEmpty()) {
			for(int i=0; i<list.size(); i++) {
				String prefix = list.get(i).substring(0, list.get(i).indexOf("@") - 4 );
				String suffix = list.get(i).substring(list.get(i).indexOf("@"), list.get(i).length());
				list.set(i,  prefix + "****" + suffix);
			}
		}
		
		conn.close();
		
		return list;
	}
	/** 비밀번호 찾기 - 새로운 비밀번호 설정
	 * @param id
	 * @param newPwd
	 * @return result
	 * @throws Exception
	 */
	public int updatePwd(String id, String newPwd) throws Exception{
		Connection conn = getConnection();
		
		int result = dao.updatePwd(id, newPwd, conn);
		
		if(result > 0) conn.commit();
		else conn.rollback();
		
		conn.close();
		return result;
	}
	
	/** 마이페이지에서 qr코드 값을 눌렀을 때의 로그인 한 사람의 구매 키값을 가져오기 위한 작업 Service
	 * @param qrNum
	 * @param loginMemberNum
	 * @return buyNum
	 */
	public int selectMenuNum(String qrNum, int loginMemberNum) throws Exception {
		Connection conn = getConnection();
		
		int buyNum = dao.selectMenuNum(conn, qrNum, loginMemberNum);
		
		conn.close();
		
		return buyNum;
	}
	
	/** 메뉴 결제 용도 서비스
	 * @param buyList
	 * @param storeNo
	 * @param memberId
	 * @return result
	 * @throws Exception
	 */
	public int menuPay(List<String> buyList, int storeNo, String memberId) throws Exception {
		Connection conn = getConnection();
		
		int result = 0;
		
		for (int i = 0; i < buyList.size(); i++) {
			result = dao.menuPay(buyList.get(i).toString(), storeNo, memberId, conn);
		}
		
		
		if(result > 0) conn.commit();
		else           conn.rollback();
		
		return result;
	}

	/** 로그인한 멤버의 가게정보 확인용 service
	 * @param id
	 * @return store
	 * @throws Exception
	 */
	public Store loginMemberStore(String id) throws Exception{
		Connection conn = getConnection();
		int result = dao.loginCheck(id, conn);
		Store store = null;
		
		if(result > 0) { // 가게 정보가 있다면
			store = dao.loginMemberStore(id, conn); // 가게정보를 가져와서 store에 저장
		}else { // 가게정보없으면 null 가리킴
			store = null;
		}
		
		conn.close();
		return store;
	}

	/** 내가 작성한 게시글 확인
	 * @param memberNo
	 * @return 
	 * @throws Exception
	 */
	public List<Board> myBoardList(int memberNo) throws Exception{
Connection conn = getConnection();
		
		List<Board> list = dao.myBoardList(conn, memberNo);
		
		conn.close();
		
		return list;
	}
}
