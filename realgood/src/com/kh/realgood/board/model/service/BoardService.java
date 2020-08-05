package com.kh.realgood.board.model.service;

import java.io.File;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import static com.kh.realgood.common.DBCP.getConnection;

import com.kh.realgood.board.model.dao.BoardDAO;
import com.kh.realgood.board.model.dto.Attachment;
import com.kh.realgood.board.model.dto.Board;
import com.kh.realgood.store.model.dto.StoreInfoMenu;
import com.kh.realgood.store.model.vo.PageInfo;

public class BoardService {

	private BoardDAO dao;
	
	public BoardService() throws Exception{
		dao = new BoardDAO();
	}

	/** 게시글 등록
	 * @param board
	 * @param attachList
	 * @return result
	 * @throws Exception
	 */
	public int insertBoard(Board board, List<Attachment> attachList) throws Exception{

		Connection conn = getConnection();
		
		int result = 0;
		
		int boardNo = dao.selectNextNo(conn);
		
		if(boardNo > 0) {
			board.setBoardNo(boardNo);
			
			board.setBoardContent(replaceParameter(board.getBoardContent())); // 크로스 사이트 스크립팅 방지
			board.setBoardContent(board.getBoardContent().replace("\r\n", "<br>")); // 개행문자 처리
			
			
			result = dao.insertBoard(conn, board);
			
			if(result > 0 && !attachList.isEmpty()) {
				result = 0;
				
				for(Attachment at : attachList) {
					at.setParentBoardNo(boardNo);
					
					result = dao.insertAttachment(conn, at);
					
					if(result == 0) break;
				}
			}
		}
		
		if(result > 0) {
			result = boardNo;
			conn.commit();
		}else {
			for(Attachment at : attachList) {
				String filePath = at.getFilePath();
				String fileName = at.getFileChangeName();
				
				File deleteFile = new File(filePath + fileName);
				deleteFile.delete();
			}
			conn.rollback();
		}
		
		conn.close();
		
		return result;
	}
	
	
	
	
	
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

	/** 썸네일 목록 조회
	 * @param pInfo
	 * @return 
	 * @throws Exception
	 */
	public List<Attachment> selectThumbnailList(PageInfo pInfo) throws Exception{
		
		Connection conn = getConnection();
		
		List<Attachment> thumbnailList = dao.selectThumbnailList(conn, pInfo);
		
		return thumbnailList;
	}

	/** 상세 페이지 페이징 처리 정보 생성
	 * @param currentPage
	 * @param storeNum
	 * @return
	 */
	public PageInfo getPageInfo(String currentPage, int storeNum) throws Exception {

		Connection conn = getConnection();
		
		int cp = currentPage == null ? 1 : Integer.parseInt(currentPage);

		int listCount = dao.getListCount(conn, storeNum);
		
		conn.close();
		
		return new PageInfo(cp, listCount, storeNum);
	}

	/** 리뷰글 목록 조회
	 * @param pInfo
	 * @return boardList
	 * @throws Exception
	 */
	public List<Board> selectReviewList(PageInfo pInfo) throws Exception{

		Connection conn = getConnection();
		
		List<Board> boardList = dao.selectReviewList(conn, pInfo);
		
		conn.close();
		
		return boardList;
	}

	

	public List<Board> getBoardList(int storeNum) throws Exception {

		Connection conn = getConnection();

		List<Board> boardList = dao.getBoardList(conn, storeNum);

		conn.close();
		
		return boardList;
	}

	/** 상세페이지 클릭 시 리뷰 페이지 확인
	 * @param boardNo
	 * @return
	 * @throws Exception
	 */
	public Board reviewInfo2(int boardNo) throws Exception{

		Connection conn = getConnection();

		Board reviewList = dao.reviewInfo2(conn, boardNo);

		conn.close();
		
		return reviewList;
	}

	/** 이미지 조회
	 * @param boardNo
	 * @return fileList
	 * @throws Exception
	 */
	public List<Attachment> selectFiles(int boardNo) throws Exception {

		Connection conn = getConnection();

		List<Attachment> fileList = dao.selectFiles(conn, boardNo);

		conn.close();
		
		return fileList;
	}

	/** 게시글 삭제 
	 * @param boardNo
	 * @return result
	 * @throws Exception
	 */
	public int deleteBoard(int boardNo) throws Exception{
		Connection conn = getConnection();

		int result = dao.deleteBoard(conn, boardNo);
		if(result >0) {
			conn.commit();
		}else {
			conn.rollback();
		}
		conn.close();
		
		return result;
	}

	 
	
}
