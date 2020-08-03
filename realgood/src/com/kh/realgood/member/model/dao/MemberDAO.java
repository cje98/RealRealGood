package com.kh.realgood.member.model.dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import com.kh.realgood.member.model.dto.Board;
import com.kh.realgood.member.model.dto.BuyList;
import com.kh.realgood.member.model.dto.Member;
import com.kh.realgood.store.model.dto.Store;

/**
 * @author home
 *
 */
public class MemberDAO {

	private Properties prop = null;

	public MemberDAO() throws FileNotFoundException, IOException {
		String fileName = MemberDAO.class.getResource("/com/kh/realgood/sql/member/member.properties").getPath();

		prop = new Properties();

		prop.load(new FileReader(fileName));
	}
	
	/**
	 * 로그인용 DAO
	 * 
	 * @param conn
	 * @param member
	 * @return loginMember
	 * @throws Exception
	 */
	public Member loginMember(Connection conn, Member member) throws Exception {

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Member loginMember = null;
		String query = prop.getProperty("loginMember");

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getPwd());
			
			rset = pstmt.executeQuery();
			if (rset.next()) {
				loginMember = new Member(rset.getInt("MEMBER_NUM"),rset.getString("ID"), rset.getString("NAME"), rset.getString("GENDER").charAt(0),
						rset.getString("GRADE_NAME"), rset.getString("TEL"), rset.getDate("ENROLL_DATE"), rset.getString("NICKNAME"), rset.getString("EMAIL_RECEIVE"), rset.getString("SMS_RECEIVE"));
			}
		} finally {
			rset.close();
			pstmt.close();
		}

		return loginMember;
	}
	
	/** 회원가입용 DAO
	 * @param conn
	 * @param member
	 * @return
	 */
	public int signUp(Connection conn, Member member) throws Exception {

		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("signUp");
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getPwd());
			pstmt.setString(3, member.getMame());
			pstmt.setString(4, member.getGender()+"");
			pstmt.setString(5, member.getGradeCode());
			pstmt.setString(6, member.getTel());
			pstmt.setString(7, member.getNickName());
			pstmt.setString(8, member.getEmailReceive());
			pstmt.setString(9, member.getSmsReceive());

			result = pstmt.executeUpdate();

			
			
		}finally {
			close(pstmt);
		}
		
		
		return result;
	}

	private void close(PreparedStatement pstmt) {
		
	}
	
	

	/** 아이디 중복 체크용 DAO
	 * @param conn
	 * @param id
	 * @return result
	 * @throws Exception
	 */
	public int idDupCheck(Connection conn, String id) throws Exception {


		PreparedStatement pstmt = null;
		int result = 0;
		
		ResultSet rset = null;
		
		String query = prop.getProperty("idCheck");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				result = rset.getInt(1);
			}
			
		}finally {
			rset.close();
			pstmt.close();
		}
		
		return result;
	}

	/** 회원 정보 변경
	 * @param conn
	 * @param member
	 * @return result
	 * @throws Exception
	 */
	public int updateMember(Connection conn, Member member) throws Exception{
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("updateMebmer");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, member.getTel());
			pstmt.setString(2, member.getEmailReceive());
			pstmt.setString(3, member.getSmsReceive());
			pstmt.setString(4, member.getId());
			result = pstmt.executeUpdate();
		} finally {
			pstmt.close();
		}
		
		return result;
	}

	/** 패스워드 체크
	 * @param conn
	 * @param id
	 * @param oldPwd
	 * @return
	 * @throws Exception
	 */
	public int checkPwd(Connection conn, String id, String oldPwd) throws Exception{
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String query = prop.getProperty("checkPwd");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
			pstmt.setString(2, oldPwd);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				result = rset.getInt(1);
			}
			
		} finally {
			rset.close();
			pstmt.close();
		}
		

		return result;
	}

	/** 패스워드 변경용 dao
	 * @param conn
	 * @param id
	 * @param newPwd
	 * @return 패스워드 변경
	 * @throws Exception
	 */
	public int changePwd(Connection conn, String id, String newPwd) throws Exception{
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("changePwd");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, newPwd);
			pstmt.setString(2, id);
			result = pstmt.executeUpdate();
			
		} finally {
			pstmt.close();
		}
		return result;
	}

	/** 회원 탈퇴 DAO
	 * @param conn
	 * @param id
	 * @return result
	 * @throws Exception
	 */
	public int removeUser(Connection conn, String id) throws Exception{
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("removeUser");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
			result = pstmt.executeUpdate();
		} finally {
			pstmt.close();
		}
		return result;
	}

	/** 상점 삭제 DAO
	 * @param conn
	 * @param id
	 * @return result
	 * @throws Exception
	 */
	public int removeStore(Connection conn, String id) throws Exception{
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("removeStore");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
			result = pstmt.executeUpdate();
		} finally {
			pstmt.close();
		}
		return result;
	}

	/** 관리자-회원 관리 DAO
	 * @param conn
	 * @return list
	 * @throws Exception
	 */
	public List<Member> selectMember(Connection conn) throws Exception{
		Statement stmt = null;
		List<Member> list = null;
		ResultSet rset = null;
		Member member = null;
		String query = prop.getProperty("selectMember");
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			list = new ArrayList<Member>();
			while(rset.next()) {
				member = new Member(
									rset.getInt("MEMBER_NUM"), 
									rset.getString("ID"), 
									rset.getString("NAME"), 
									rset.getString("GENDER").charAt(0), 
									rset.getString("GRADE_NAME"),
									rset.getString("TEL"), 
									rset.getDate("ENROLL_DATE"),
									rset.getString("NICKNAME"), 
									rset.getString("OUT_YN").charAt(0)
										);
				list.add(member);
			}
			
			
		} finally {
			rset.close();
			stmt.close();
		}
		
		
		return list;
	}

	/** 회원 삭제용 dao
	 * @param id
	 * @param conn
	 * @return result
	 * @throws Exception
	 */
	public int deleteMember(String id, Connection conn) throws Exception {
		
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("deleteMember");
		
		try {
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
			
			result = pstmt.executeUpdate();
			
			
		} finally {
			pstmt.close();
		
		}
		
		return result;
	}

	/** 상점 갯수 체크 용(사장 삭제시 가게 삭제용)
	 * @param conn
	 * @param id
	 * @return count
	 * @throws Exception
	 */
	public int removeStoreCount(Connection conn, String id) throws Exception{
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int count = 0;
		
		String query = prop.getProperty("storeCount");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				count = rset.getInt(1);
			}
			
			
		} finally {
			rset.close();
			pstmt.close();
		}
		
		return count;
	}

	/** 구매내역 표시 리스트 DAO
	 * @param conn
	 * @param no
	 * @return list
	 * @throws Exception
	 */
	public List<BuyList> PurchaseList(Connection conn, int no) throws Exception{
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<BuyList> list = null;
		BuyList buyList = null;
		String query = prop.getProperty("purchaseList");
		
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, no);
			list = new ArrayList<BuyList>();
			
			rset = pstmt.executeQuery();
			System.out.println(rset);
			while(rset.next()) {
					buyList = new BuyList(rset.getInt("BUY_NUM"), rset.getInt("BUY_MEMBER_NO"), rset.getInt("BUY_STORE_NUM"),rset.getString("NAME"), 
										  rset.getString("MENU_NAME"), rset.getDate("BUY_DATE"), rset.getString("BUY_QR_CODE_NUM"),
										  rset.getDate("BUY_USED"));
					list.add(buyList);
			}
			
		} finally {
			rset.close();
			pstmt.close();
		}

		return list;
	}

	/** 아이디 찾기 DAO
	 * @param conn
	 * @param name
	 * @param tel
	 * @return iist
	 * @throws Exception
	 */
	public List<String> findId(Connection conn, String name, String tel) throws Exception{
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String id = "";
		// 같은 이름 같은 번호 아이디가 한개가 아닐 수 있기 때문에 list로 받아옴
		List<String> list = null;
		String query = prop.getProperty("findId");
		
		try {
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, name);
			pstmt.setString(2, tel);
			
			rset = pstmt.executeQuery();
			list = new ArrayList<String>();
			
			
			while(rset.next()) {
				list.add(rset.getString("ID"));
			}
			
		} finally {
			rset.close();
			pstmt.close();
		}
		
		
		return list;
}
/** 비밀번호 찾기 - 새로운 비밀번호 설정
	 * @param id
	 * @param newPwd
	 * @param conn
	 * @return result
	 * @throws Exception
	 */
	public int updatePwd(String id, String newPwd, Connection conn) throws Exception{
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("updatePwd");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, newPwd);
			pstmt.setString(2, id);
			
			result = pstmt.executeUpdate();
			
		} finally {
			pstmt.close();
		}
		
		
		return result;
	}
	
	/** 로그인한 회원의 가게정보 확인용 dao (가게정보 count(*)로 받아서 0보다 크면 등록되어있는것)
	 * @param id
	 * @param conn
	 * @return result
	 * @throws Exception
	 */
	public int loginCheck(String id, Connection conn) throws Exception{
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		
		String query = prop.getProperty("loginCheck");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				result = rset.getInt(1);
			}
			
			
		}finally {
			rset.close();
			pstmt.close();
		}
				
				
		return result;
	}

	/** 가게정보 불러와 저장하기 dao
	 * @param id
	 * @param conn
	 * @return store
	 * @throws Exception
	 */
	public Store loginMemberStore(String id, Connection conn) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Store store = null;
		
		String query = prop.getProperty("loginMemberStore");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				store = new Store(rset.getInt("STORE_NUM"), 
						rset.getString("COR_NUM"),
						rset.getString("NAME"),
						rset.getString("STORE_CONTENT"),
						rset.getString("STORE_TEL"), 
						rset.getString("STORE_CODE"),
						rset.getString("STORE_ADDR"),
						rset.getString("STORE_ZIP"), 
						rset.getDate("ENROLL_DATE"));
						
			}
		}finally {
			rset.close();
			pstmt.close();
		}
		return store;
	}
	
	/** qr코드아 아이디 값을 통해 buyNum값을 가져오는 DAO
	 * @param conn
	 * @param qrNum
	 * @param loginMemberNum
	 * @return buyNum
	 */
	public int selectMenuNum(Connection conn, String qrNum, int loginMemberNum) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int buyNum = 0;
		
		String query = prop.getProperty("selectMenuNum");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, loginMemberNum);
			pstmt.setString(2, qrNum);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				buyNum = rset.getInt(1);
			}
			
		} finally {
			rset.close();
			pstmt.close();
		}
		return buyNum;
	}
	
	/** 메뉴결제용 DAO
	 * @param buyList
	 * @param storeNo
	 * @param memberId
	 * @param conn
	 * @return
	 * @throws Exception
	 */
	public int menuPay(String buyList, int storeNo, String memberId, Connection conn) throws Exception {
		PreparedStatement pstmt = null;
		int result = 0;
		String[] buyListTmp = buyList.split("\\|\\|");
		SimpleDateFormat format1 = new SimpleDateFormat ("yyyy.MM.dd.HH.mm.ss");
		Date time = new Date();
		String time1 = format1.format(time);
		
		String query = prop.getProperty("memberMenuPay");
		
		for (int i = 0; i < Integer.parseInt(buyListTmp[2]); i++) {
			try {
				pstmt = conn.prepareStatement(query);
				pstmt.setInt(1, storeNo);
				pstmt.setString(2, memberId);
				pstmt.setInt(3, storeNo);
				pstmt.setString(4, buyListTmp[0]);
				pstmt.setString(5, storeNo+"_"+buyListTmp[0]+"_"+buyListTmp[1]+"_"+time1+"_"+memberId);
				
				result = pstmt.executeUpdate();
				
			} finally {
				pstmt.close();
			}
		}
		
		return result;
	}

	/** 내가 작성한 게시글 확인 DAO
	 * @param conn
	 * @param memberNo
	 * @return list
	 * @throws Exception
	 */
	public List<Board> myBoardList(Connection conn, int memberNo) throws Exception{
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Board> list = null;
		Board Boardlist = null;
		String query = prop.getProperty("myBoardList");
		
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, memberNo);
			list = new ArrayList<Board>();
			
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Boardlist = new Board(rset.getInt("BOARD_NO"), rset.getString("BOARD_CONTENT"), rset.getInt("READ_COUNT"),rset.getDate("BOARD_CREATE_DT"), 
										  rset.getDate("BOARD_MODIFY_DT"), rset.getString("BOARD_STATUS").charAt(0), rset.getInt("BOARD_WRITER"),
										  rset.getInt("STORE_NUM"),rset.getString("NAME"));
					list.add(Boardlist);
			}
			
		} finally {
			rset.close();
			pstmt.close();
		}

		return list;
	}
	
}

