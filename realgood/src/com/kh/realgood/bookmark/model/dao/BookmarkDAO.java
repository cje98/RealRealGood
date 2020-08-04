package com.kh.realgood.bookmark.model.dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
}
