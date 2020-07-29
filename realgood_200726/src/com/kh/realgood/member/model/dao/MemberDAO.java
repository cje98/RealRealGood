package com.kh.realgood.member.model.dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.kh.realgood.member.model.dto.Member;

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
				loginMember = new Member(rset.getString("ID"), rset.getString("NAME"),
						rset.getString("GENDER").charAt(0),
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
			pstmt.setString(5, member.getTel());
			pstmt.setString(6, member.getNickName());
			pstmt.setString(7, member.getEmailReceive());
			pstmt.setString(8, member.getSmsReceive());

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

	public int deleteMember(String id2, Connection conn) throws Exception {
		
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("deleteMember");
		
		try {
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id2);
			
			result = pstmt.executeUpdate();
			
			
		} finally {
			pstmt.close();
		
		}
		
		return result;
	}
}

