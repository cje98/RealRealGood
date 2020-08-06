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

	/** 게시글 수정 화면 구성
	 * @param boardNo
	 * @return
	 * @throws Exception
	 */
	public Board updateView(int boardNo) throws Exception{

		Connection conn = getConnection();
		
		Board board = dao.updateView(conn, boardNo);
		
		conn.close();
		
		
		return board;
	}

	/** 게시글 수정 
	 * @param board
	 * @param fList
	 * @return result
	 * @throws Exception
	 */
	public int updateView(Board board, List<Attachment> fList) throws Exception{

Connection conn = getConnection();
		
		int result = 0;
		
		// 크로스 사이트 스크립트 방지 + 개행문자 처리
	    board.setBoardContent(replaceParameter(board.getBoardContent())); // 크로스 사이트 스크립팅 방지
	    board.setBoardContent(board.getBoardContent().replace("\r\n", "<br>")); // 개행문자 처리
		
	    // 게시글 수정 DAO 호출
	    result = dao.updateBoard(conn, board);
	    
	    // 서버에서 삭제되어야될 파일 정보를 모아두는 List
	    List<Attachment> deleteFiles = new ArrayList<Attachment>();
	    if(result > 0 && !fList.isEmpty()) {
	    	result = 0; // result 재사용을 위해 초기화
	    	
	    	// 기존 해당 게시글에 포함되었던 파일 정보를 DB로 부터 얻어옴
	    	List<Attachment> oldList = dao.selectFiles(conn, board.getBoardNo());
	    	
	    	boolean flag = false;
	    	// flag : 어떤 작업을 수행하고 난 후 결과확인 용도로 사용
	    	// oldList랑 fList와 비교 작업, 같은게 있으면 true로 변경
	    	for(Attachment newFile : fList) {
	    		// 새로운 파일 목록의 요소(newFIle)에 순차적으로 접근
	    		
	    		flag = false; // flag 초기화
	    		
	    		for(Attachment oldFile : oldList) {
	    			// 기존 파일 목록의 요소(oldFile)에 순차적으로 접근
	    			
	    			if(newFile.getFileLevel() == oldFile.getFileLevel()) {
	    				// 새로운 파일의 레벨과 기존 파일 중에 중복되는 레벨이 있을 경우
	    				// 기존에 0 레벨 있고 새로운 것도 0레벨있으면 새로운 것 0 반영해야함
	    				flag = true;
	    				deleteFiles.add(oldFile); // 기존 파일을 삭제 리스트에 추가
	    				newFile.setFileNo(oldFile.getFileNo());
	    				// 새로운 내용이 업데이트 됐을 경우 비교가 종료
	    				
	    			}
	    		}
	    		
	    		newFile.setParentBoardNo(board.getBoardNo());
	    		
	    		// flag 상태에 따라 알맞은 DAO 호출
	    		if(flag) { // update 상황 (겹치는게 있을 때)
	    			result = dao.updateAttachment(conn, newFile);
	    		}else { // insert 상황 (겹치는게 없을 때)
	    			result = dao.insertAttachment(conn, newFile);
	    		}
	    		
	    		if(result == 0	) break;
	    		
	    	}
	    	
	    }
	    	    
	    // 트랜잭션 처리 + 삭제 처리
	    
	    List<Attachment> tempList = null;
	    // service의 모든 동작이 성공적으로 진행된 경우
	    // deleteFiles에 담긴 기존 파일을 삭제해야 되고,
	    // service 동작 중 오류 또는 실패 발생 시
	    // fList에 담긴 새로운 파일을 삭제해야함.
	    
	    if(result > 0 ) {
	    	result = board.getBoardNo();
	    	// 수정 후 내가 수정한 글로 다시 돌아오기 위해서는 번호를 알아야 하기 때문에
	    	// 수정 완료 후 해당 게시글 상세 보기를 위해 result에 글 번호를 저장하여 반환
	    	conn.commit();

	    	tempList = deleteFiles;
	    }else {
	    	conn.rollback();
	    	tempList = fList;
	    }
	    
	    // 서버에 저장된 파일 삭제
	    for(Attachment at : tempList) {
	    	String filePath = at.getFilePath();
	    	String fileName = at.getFileChangeName();
	    	
	    	File deleteFile = new File(filePath + fileName);
	    	deleteFile.delete();
	    }
	    
	    
		return result;
	}

	 
	
}
