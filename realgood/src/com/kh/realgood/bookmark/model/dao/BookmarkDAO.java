package com.kh.realgood.bookmark.model.dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

import com.kh.realgood.member.model.dao.MemberDAO;

public class BookmarkDAO {
	private Properties prop = null;
	
	public BookmarkDAO() throws FileNotFoundException, IOException {
		String fileName = MemberDAO.class.getResource("/com/kh/realgood/sql/bookmark/bookmark.properties").getPath();
		
		prop = new Properties();
		
		prop.load(new FileReader(fileName));
	}
	
	/** 보고있는 가게번호, 로그인 한 회원번호 저장 DAO
	 * @param storeNo
	 * @param memberNo
	 * @return result
	 * @throws Exception
	 */
	public int insertNo(int storeNo, int memberNo, Connection conn) throws Exception{
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("insertFavorite");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, memberNo);
			pstmt.setInt(2, storeNo);
			
			result = pstmt.executeUpdate();
			
			
		}finally {
			pstmt.close();
		}
		return result;
	}
	

	/** 즐겨찾기 삭제를 위한 체크 DAO
	 * @param conn
	 * @param storeNo
	 * @param memberNo
	 * @return result
	 * @throws Exception
	 */
	public int checkBookmark(Connection conn, int storeNo, int memberNo) throws Exception{
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		int result = 0;
		
		String qeury = prop.getProperty("checkBookmark");
		
		try {
			pstmt = conn.prepareStatement(qeury);
			pstmt.setInt(1, storeNo);
			pstmt.setInt(2, memberNo);
			
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

	/** 즐겨찾기 삭제 DAO
	 * @param conn
	 * @param storeNo
	 * @param memberNo
	 * @return result
	 * @throws Exception
	 */
	public int deleteBookmark(Connection conn, int storeNo, int memberNo) throws Exception{
		PreparedStatement pstmt = null;
		int result = 0;
		
		String qeury = prop.getProperty("deleteBookmark");
		
		try {
			pstmt = conn.prepareStatement(qeury);
			pstmt.setInt(1, storeNo);
			pstmt.setInt(2, memberNo);
			
			result = pstmt.executeUpdate();
		}finally {
			pstmt.close();
		}
		return result;
	}
}
