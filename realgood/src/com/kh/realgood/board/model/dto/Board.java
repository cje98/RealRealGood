package com.kh.realgood.board.model.dto;

import java.sql.Timestamp;

import org.json.simple.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Board {
	
    
		private int boardNo;
		private String boardTitle;
		private String boardContent;
		private String memberId;
	   private int readCount;
	   private Timestamp boardCreateDate;
	   private Timestamp boardModifyDate;
	   private String boardOutYn;
	   private int no;
	   private int storeNum;
	   private String name;
	   private String store_addr;
	   private String nickName;
	   
	   
	public Board() {
		super();
	}


	public Board(int boardNo, String boardTitle, String boardContent, String memberId, int readCount,
			Timestamp boardCreateDate, Timestamp boardModifyDate, String boardOutYn, int no, int storeNum) {
		super();
		this.boardNo = boardNo;
		this.boardTitle = boardTitle;
		this.boardContent = boardContent;
		this.memberId = memberId;
		this.readCount = readCount;
		this.boardCreateDate = boardCreateDate;
		this.boardModifyDate = boardModifyDate;
		this.boardOutYn = boardOutYn;
		this.no = no;
		this.storeNum = storeNum;
	}


	
	
	



	public Board(int boardNo, String boardContent) {
		super();
		this.boardNo = boardNo;
		this.boardContent = boardContent;
	}


	public Board(int boardNo, String boardContent, String memberId, int readCount, Timestamp boardModifyDate) {
		super();
		this.boardNo = boardNo;
		this.boardContent = boardContent;
		this.memberId = memberId;
		this.readCount = readCount;
		this.boardModifyDate = boardModifyDate;
		
	}


	public Board(String boardContent, String memberId,  int storeNum) {
		super();
		this.boardContent = boardContent;
		this.memberId = memberId;
		this.storeNum = storeNum;
	}


	
	
	
	public Board( String nickName ,String boardContent, int readCount, String name, Timestamp boardCreateDate,  String store_addr, int boardNo
			) {
		super();
		this.nickName = nickName;
		this.boardContent = boardContent;
		this.readCount = readCount;
		this.name = name;
		this.boardCreateDate = boardCreateDate;
		this.store_addr = store_addr;
		this.boardNo=boardNo;
	}
	
	public Board( String nickName ,String boardContent, int readCount,  String name, Timestamp boardModifyDate, String store_addr
			) {
		super();
		this.nickName = nickName;
		this.boardContent = boardContent;
		this.readCount = readCount;
		this.name = name;
		this.boardModifyDate = boardModifyDate;
		this.store_addr = store_addr;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getStore_addr() {
		return store_addr;
	}


	public void setStore_addr(String store_addr) {
		this.store_addr = store_addr;
	}


	public String getNickName() {
		return nickName;
	}


	public void setNickName(String nickName) {
		this.nickName = nickName;
	}


	public int getBoardNo() {
		return boardNo;
	}


	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}


	public String getBoardTitle() {
		return boardTitle;
	}


	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}


	public String getBoardContent() {
		return boardContent;
	}


	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}


	public String getMemberId() {
		return memberId;
	}


	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}


	public int getReadCount() {
		return readCount;
	}


	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}


	public Timestamp getBoardCreateDate() {
		return boardCreateDate;
	}


	public void setBoardCreateDate(Timestamp boardCreateDate) {
		this.boardCreateDate = boardCreateDate;
	}


	public Timestamp getBoardModifyDate() {
		return boardModifyDate;
	}


	public void setBoardModifyDate(Timestamp boardModifyDate) {
		this.boardModifyDate = boardModifyDate;
	}


	public String getBoardOutYn() {
		return boardOutYn;
	}


	public void setBoardOutYn(String boardOutYn) {
		this.boardOutYn = boardOutYn;
	}


	public int getno() {
		return no;
	}


	public void setno(int no) {
		this.no = no;
	}


	public int getStoreNum() {
		return storeNum;
	}


	public void setStoreNum(int storeNum) {
		this.storeNum = storeNum;
	}


	@Override
	public String toString() {
		/*
		private int boardNo;
		private String boardContent;
		private String memberId;
	   private int readCount;
	   private Timestamp boardModifyDate;
	   private int storeNum;
	   private String name;
	   private String store_addr;
	   private String nickName;
	   
		 * */
		JSONObject obj = new JSONObject();
		obj.put("boardNo", boardNo);
		obj.put("boardContent", boardContent);
		obj.put("memberId", memberId);
		obj.put("readCount", readCount);
		obj.put("boardModifyDate", boardModifyDate);
		obj.put("storeNum", storeNum);
		obj.put("name", name);
		obj.put("store_addr", store_addr);
		obj.put("nickName", nickName);
		
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss").create();
		return gson.toJson(obj);
	}



	
	   
	   
	   
 
}