package com.kh.realgood.board.model.dao;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.kh.realgood.board.model.dto.Attachment;
import com.kh.realgood.board.model.dto.Board;
import com.kh.realgood.store.model.vo.PageInfo;


public class BoardDAO {
	
	private Properties prop;
	
	public BoardDAO() throws Exception{
		String fileName = BoardDAO.class.getResource("/com/kh/realgood/sql/board/board.properties").getPath();
		
		prop = new Properties();
		
		prop.load(new FileReader(fileName));
	}

	/** 다음 게시글 번호 반환
	 * @param conn
	 * @return
	 * @throws Exception
	 */
	public int selectNextNo(Connection conn) throws Exception{

		Statement stmt = null;
		
		ResultSet rset = null;
		
		int boardNo =0;
		
		String query = prop.getProperty("selectNextNo");
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			
			if(rset.next()) {
				boardNo = rset.getInt(1);
			}
			
		}finally {
			rset.close();
			stmt.close();
		}
		
		return boardNo;
	}

	/** 게시글 삽입
	 * @param conn
	 * @param board
	 * @return result
	 * @throws Exception
	 */
	public int insertBoard(Connection conn, Board board) throws Exception{

		PreparedStatement pstmt = null;
		
		int result = 0;
		
		String query = prop.getProperty("insertBoard");
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, board.getBoardNo());
			pstmt.setString(2, board.getBoardContent());
			pstmt.setString(3, board.getMemberId());
			pstmt.setInt(4, board.getStoreNum());
			
			result = pstmt.executeUpdate();
			
			
		}finally {
			pstmt.close();
			
		}
		
		return result;
	}

	/** 파일 등록
	 * @param conn
	 * @param at
	 * @return
	 * @throws Exception
	 */
	public int insertAttachment(Connection conn, Attachment at) throws Exception {

		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("insertAttachment");
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, at.getParentBoardNo());
			pstmt.setString(2, at.getFileOriginName());
			pstmt.setString(3, at.getFileChangeName());
			pstmt.setString(4, at.getFilePath());
			pstmt.setInt(5, at.getFileLevel());
			
			result = pstmt.executeUpdate();
			
			
		}finally {
			pstmt.close();
		}
		
		return result;
	}

	/** 썸네일 목록 조회 
	 * @param conn
	 * @param pInfo
	 * @return thumbnailList
	 * @throws Exception
	 */
	public List<Attachment> selectThumbnailList(Connection conn, PageInfo pInfo) throws Exception {

		PreparedStatement pstmt =null;
		ResultSet rset = null;
		List<Attachment> thumbnailList = null;
		
		String query = prop.getProperty("selectThumbnailList");

		try {

	
			int startRow = (pInfo.getCurrentPage()-1) * pInfo.getLimit() + 1;
			
			int endRow = startRow + pInfo.getLimit()-1;
			
			pstmt = conn.prepareStatement(query);


			pstmt.setInt(1, pInfo.getStoreNum());
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			
			rset = pstmt.executeQuery();

			thumbnailList = new ArrayList<Attachment>();
			Attachment at = null;
			while(rset.next()) {
				at = new Attachment(rset.getInt("FILE_NO"),
						rset.getInt("PARENT_BOARD_NO"),
						rset.getString("FILE_CHANGE_NAME"),
						rset.getString("FILE_PATH"));
				
				thumbnailList.add(at);
				
			}


		}finally {
			rset.close();
			pstmt.close();
		}
		
		return thumbnailList;
	}

	/** 상세 페이지 페이징 처리 정보 생성
	 * @param conn
	 * @param storeNum
	 * @return
	 */
	public int getListCount(Connection conn, int storeNum) throws Exception {
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		int listCount = 0;
		
		String query = prop.getProperty("getReviewListCount");

		try {
			 pstmt = conn.prepareStatement(query);
			 pstmt.setInt(1, storeNum);
			 rset=pstmt.executeQuery();
			 
			 if(rset.next()) listCount = rset.getInt(1);

			
		}finally {
			rset.close();
			pstmt.close();
		}
		
		return listCount;
	}

	/** 리뷰글 목록 조회
	 * @param conn
	 * @param pInfo
	 * @return
	 * @throws Exception
	 */
	public List<Board> selectReviewList(Connection conn, PageInfo pInfo) throws Exception{

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Board> boardList = null;
		
		String qeury = prop.getProperty("selectReviewList");
		
		try {
			// SQL 조건절에 사용할 값을 가공
			int startRow = (pInfo.getCurrentPage()-1) * pInfo.getLimit() + 1;
			
			int endRow = startRow + pInfo.getLimit()-1;
			
			pstmt = conn.prepareStatement(qeury);
			pstmt.setInt(1, pInfo.getStoreNum());
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			
			rset = pstmt.executeQuery();
			boardList = new ArrayList<Board>();
			Board board= null;

			while(rset.next()){
				board = new Board(rset.getInt("BOARD_NO"),
									rset.getString("BOARD_CONTENT"),
									rset.getString("ID"),
									rset.getInt("READ_COUNT"),
									 rset.getTimestamp("BOARD_CREATE_DT")
									 );  
				boardList.add(board);
				
			}
			
		
			
		}finally {
			rset.close();
			pstmt.close();
		}
		
		return boardList;
	}


	public List<Board> getBoardList(Connection conn, int storeNum) throws Exception {

		PreparedStatement pstmt = null;
		List<Board> boardList = null;
		Board board = null;
		ResultSet rset = null;
		
		
		String query = prop.getProperty("getBoardList");

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, storeNum);
			rset = pstmt.executeQuery();

			boardList = new ArrayList<Board>();
			while(rset.next()) {
				board = new Board(rset.getInt("BOARD_NO"),
						rset.getString("BOARD_CONTENT"),
						rset.getString("BOARD_WRITER"),
						rset.getInt("READ_COUNT"),
						rset.getTimestamp("BOARD_MODIFY_DT"));
				board.setNickName(rset.getString(1));
				
				boardList.add(board);

			}
			
		}finally {
			rset.close();
			pstmt.close();
		}
		
		return boardList;
	}

	public Board reviewInfo2(Connection conn, int boardNo) throws Exception{

		PreparedStatement pstmt = null;
		Board reviewList = null;
		Board board = null;
		ResultSet rset = null;
		
		String query = prop.getProperty("reviewInfo2");

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, boardNo);

			rset = pstmt.executeQuery();

			while(rset.next()) {
				reviewList = new Board(rset.getString("NICKNAME"),
						rset.getString("BOARD_CONTENT"),
						rset.getInt("READ_COUNT"),
						rset.getString("SNAME"),
						rset.getTimestamp("BOARD_MODIFY_DT"),
						rset.getString("ADDR") 
						);

			}

		
		}finally {
			rset.close();
			pstmt.close();
		}
			
		return reviewList;
	}

	public List<Attachment> selectFiles(Connection conn, int boardNo) throws Exception {

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Attachment> fileList = null;
		String query = prop.getProperty("selectFiles");
		
		try {
			
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, boardNo);
			
			rset = pstmt.executeQuery();
			
			fileList = new ArrayList<Attachment>();
			Attachment file = null;
			
			while(rset.next()) {
				file = new Attachment();
				file.setFileNo(rset.getInt("FILE_NO"));
				file.setFileChangeName(rset.getString("FILE_CHANGE_NAME"));
				file.setFilePath(rset.getString("FILE_PATH"));
				file.setFileLevel(rset.getInt("FILE_LEVEL"));
				
				fileList.add(file);
			}
			
		} finally {
			rset.close();
			pstmt.close();
		}
		
		
		return fileList;
	}

	
	/** 평점 Insert DAO
	 * @param conn
	 * @param board
	 * @return
	 */
	public int insertStoreStarScore(Connection conn, Board board) throws Exception {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("insertStoreStarScore");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, board.getStarScore());
			pstmt.setString(2, board.getMemberId());
			pstmt.setInt(3, board.getStoreNum());
			
			result = pstmt.executeUpdate();
		} finally {
			pstmt.close();
		}
		return result;
	}
	
	/** 리뷰 삭제
	 * @param conn
	 * @param boardNo
	 * @return result
	 * @throws Exception
	 */
	public int deleteBoard(Connection conn, int boardNo) throws Exception{
		PreparedStatement pstmt = null;
		int result = 0;
		String query = prop.getProperty("deleteBoard");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, boardNo);
			result = pstmt.executeUpdate();
		} finally {
			pstmt.close();
		}
		return result;
	}

	/** 조회수 증가용(댓글) DAO
	 * @param conn
	 * @param boardNo
	 * @return
	 */
	public int readCountAdd(Connection conn, int boardNo) throws Exception {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("readCountAdd");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, boardNo);
			
			result = pstmt.executeUpdate();
			
		} finally {
			pstmt.close();
		}
		
		return result;
	}

}
