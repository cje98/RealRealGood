package com.kh.realgood.member.model.dto;

import java.sql.Date;

public class Board {
	private int boardNo; // 게시글 번호
	private String boardContent; // 게시글 내용
	private int readCount; // 조회수
	private Date boardCreateDt; // 게시글 생성 날짜
	private Date boardModifyDt; // 게시글 수정 날짜(마이페이지 표기)
	private char boardStatus; // 게시글 삭제 유무
	private int boardWriter; // 게시글 작성자 번호
	private int storeNum; // 가게 번호
	private String stroeName; // 가게 이름
	
	
	public Board() {
	}


	public Board(int boardNo, String boardContent, int readCount, Date boardCreateDt, Date boardModifyDt,
			char boardStatus, int boardWriter, int storeNum, String stroeName) {
		super();
		this.boardNo = boardNo;
		this.boardContent = boardContent;
		this.readCount = readCount;
		this.boardCreateDt = boardCreateDt;
		this.boardModifyDt = boardModifyDt;
		this.boardStatus = boardStatus;
		this.boardWriter = boardWriter;
		this.storeNum = storeNum;
		this.stroeName = stroeName;
	}


	public int getBoardNo() {
		return boardNo;
	}


	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}


	public String getBoardContent() {
		return boardContent;
	}


	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}


	public int getReadCount() {
		return readCount;
	}


	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}


	public Date getBoardCreateDt() {
		return boardCreateDt;
	}


	public void setBoardCreateDt(Date boardCreateDt) {
		this.boardCreateDt = boardCreateDt;
	}


	public Date getBoardModifyDt() {
		return boardModifyDt;
	}


	public void setBoardModifyDt(Date boardModifyDt) {
		this.boardModifyDt = boardModifyDt;
	}


	public char getBoardStatus() {
		return boardStatus;
	}


	public void setBoardStatus(char boardStatus) {
		this.boardStatus = boardStatus;
	}


	public int getBoardWriter() {
		return boardWriter;
	}


	public void setBoardWriter(int boardWriter) {
		this.boardWriter = boardWriter;
	}


	public int getStoreNum() {
		return storeNum;
	}


	public void setStoreNum(int storeNum) {
		this.storeNum = storeNum;
	}


	public String getStroeName() {
		return stroeName;
	}


	public void setStroeName(String stroeName) {
		this.stroeName = stroeName;
	}


	@Override
	public String toString() {
		return "Board [boardNo=" + boardNo + ", boardContent=" + boardContent + ", readCount=" + readCount
				+ ", boardCreateDt=" + boardCreateDt + ", boardModifyDt=" + boardModifyDt + ", boardStatus="
				+ boardStatus + ", boardWriter=" + boardWriter + ", storeNum=" + storeNum + ", stroeName=" + stroeName
				+ "]";
	}


	
	
	
	
}