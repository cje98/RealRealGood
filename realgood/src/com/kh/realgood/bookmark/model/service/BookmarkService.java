package com.kh.realgood.bookmark.model.service;

import static com.kh.realgood.common.DBCP.getConnection;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;

import com.kh.realgood.bookmark.model.dao.BookmarkDAO;

public class BookmarkService {
	private BookmarkDAO dao;
	
	public BookmarkService() throws FileNotFoundException, IOException {
		dao = new BookmarkDAO();
	}
	
	/** 보고있는 가게번호, 로그인 한 회원번호 저장 Service
	 * @param storeNo
	 * @param memberNo
	 * @return result
	 * @throws Exception
	 */
	public int insertNo(int storeNo, int memberNo) throws Exception{
		Connection conn = getConnection();
		
		int result = dao.insertNo(storeNo, memberNo, conn);
		
		if(result > 0) conn.commit();
		else 		   conn.rollback();
		
		return result;
	}
}
