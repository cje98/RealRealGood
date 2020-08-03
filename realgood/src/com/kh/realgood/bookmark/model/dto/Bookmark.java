package com.kh.realgood.bookmark.model.dto;

import java.sql.Date;

public class Bookmark {
	private int memberNo; 
	private int storeNo;
	private Date enrollDate;
	private int favoNo;
	
	public Bookmark() {}

	public Bookmark(int memberNo, int storeNo, Date enrollDate, int favoNo) {
		super();
		this.memberNo = memberNo;
		this.storeNo = storeNo;
		this.enrollDate = enrollDate;
		this.favoNo = favoNo;
	}

	public int getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}

	public int getStoreNo() {
		return storeNo;
	}

	public void setStoreNo(int storeNo) {
		this.storeNo = storeNo;
	}

	public Date getEnrollDate() {
		return enrollDate;
	}

	public void setEnrollDate(Date enrollDate) {
		this.enrollDate = enrollDate;
	}

	public int getFavoNo() {
		return favoNo;
	}

	public void setFavoNo(int favoNo) {
		this.favoNo = favoNo;
	}

	@Override
	public String toString() {
		return "Bookmark [memberNo=" + memberNo + ", storeNo=" + storeNo + ", enrollDate=" + enrollDate + ", favoNo="
				+ favoNo + "]";
	}
	
	
	
	
}
