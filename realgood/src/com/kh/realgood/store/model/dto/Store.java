package com.kh.realgood.store.model.dto;

import java.sql.Date;

public class Store {
	private int StoreNum; // 가게 번호
	private String corNum; // 사업자 번호
	private String storeName; // 가게 이름
	private String storeContent; // 가게 설명
	private String storeTel; // 가게 전화 번호
	private String storeGroupName; // 가게 업종분류 명
	private String storeAddress; // 가게 주소
	private String storeZip; // 가게 우편번호
	private Date enrollDate; // 가게 등록 날짜
	private String id; // 회원 아이디
	private char outYN;	// 탈퇴 여부
	private char useYN; // 승인 여부
	private String storeTitleImg; // 가게 대표 이미지
	
	
	
	
	public Store(int storeNum, String storeName, String storeGroupName) {
		super();
		StoreNum = storeNum;
		this.storeName = storeName;
		this.storeGroupName = storeGroupName;
	}
	
	
	

	public Store(String storeContent, String storeTel, String storeGroupName, String storeAddress, String storeZip) {
		super();
		this.storeContent = storeContent;
		this.storeTel = storeTel;
		this.storeGroupName = storeGroupName;
		this.storeAddress = storeAddress;
		this.storeZip = storeZip;
	}




	public Store(String corNum, String storeName, String storeContent, String storeTel, String storeGroupName,
			String storeAddress, String storeZip) {
		super();
		this.corNum = corNum;
		this.storeName = storeName;
		this.storeContent = storeContent;
		this.storeTel = storeTel;
		this.storeGroupName = storeGroupName;
		this.storeAddress = storeAddress;
		this.storeZip = storeZip;
	}



	public Store(int storeNum, String corNum, String storeName, String storeContent, String storeTel,
			String storeGroupName, String storeAddress, String storeZip, Date enrollDate, String storeTitleImg) {
		super();
		StoreNum = storeNum;
		this.corNum = corNum;
		this.storeName = storeName;
		this.storeContent = storeContent;
		this.storeTel = storeTel;
		this.storeGroupName = storeGroupName;
		this.storeAddress = storeAddress;
		this.storeZip = storeZip;
		this.enrollDate = enrollDate;
		this.storeTitleImg = storeTitleImg;
	}

	public Store(int storeNum, String corNum, String storeName, String storeContent, String storeTel,
			String storeGroupName, String storeAddress, String storeZip, Date enrollDate) {
		super();
		StoreNum = storeNum;
		this.corNum = corNum;
		this.storeName = storeName;
		this.storeContent = storeContent;
		this.storeTel = storeTel;
		this.storeGroupName = storeGroupName;
		this.storeAddress = storeAddress;
		this.storeZip = storeZip;
		this.enrollDate = enrollDate;
	}

	public Store(String corNum, String storeName, String storeContent, String storeTel, String storeGroupName,
			String storeAddress, String storeZip, Date enrollDate) {
		super();
		this.corNum = corNum;
		this.storeName = storeName;
		this.storeContent = storeContent;
		this.storeTel = storeTel;
		this.storeGroupName = storeGroupName;
		this.storeAddress = storeAddress;
		this.storeZip = storeZip;
		this.enrollDate = enrollDate;
	}
	
	public Store(String storeAddress) {
		super();
		this.storeAddress = storeAddress;
	}
	
	

	public Store(int storeNum, String storeName, String id, char useYN) {
		super();
		StoreNum = storeNum;
		this.storeName = storeName;
		this.id = id;
		this.useYN = useYN;
	}

	public char getUseYN() {
		return useYN;
	}

	public void setUseYN(char useYN) {
		this.useYN = useYN;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public char getOutYN() {
		return outYN;
	}

	public void setOutYN(char outYN) {
		this.outYN = outYN;
	}

	public int getStoreNum() {
		return StoreNum;
	}

	public void setStoreNum(int storeNum) {
		StoreNum = storeNum;
	}

	public String getCorNum() {
		return corNum;
	}
	public void setCorNum(String corNum) {
		this.corNum = corNum;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public String getStoreContent() {
		return storeContent;
	}
	public void setStoreContent(String storeContent) {
		this.storeContent = storeContent;
	}
	public String getStoreTel() {
		return storeTel;
	}
	public void setStoreTel(String storeTel) {
		this.storeTel = storeTel;
	}
	public String getStoreGroupName() {
		return storeGroupName;
	}
	public void setStoreGroupName(String storeGroupName) {
		this.storeGroupName = storeGroupName;
	}
	public String getStoreAddress() {
		return storeAddress;
	}
	public void setStoreAddress(String storeAddress) {
		this.storeAddress = storeAddress;
	}
	public String getStoreZip() {
		return storeZip;
	}
	public void setStoreZip(String storeZip) {
		this.storeZip = storeZip;
	}
	public Date getEnrollDate() {
		return enrollDate;
	}
	public void setEnrollDate(Date enrollDate) {
		this.enrollDate = enrollDate;
	}

	
	public String getStoreTitleImg() {
		return storeTitleImg;
	}

	public void setStoreTitleImg(String storeTitleImg) {
		this.storeTitleImg = storeTitleImg;
	}

	@Override
	public String toString() {
		return "Store [StoreNum=" + StoreNum + ", corNum=" + corNum + ", storeName=" + storeName + ", storeContent="
				+ storeContent + ", storeTel=" + storeTel + ", storeGroupName=" + storeGroupName + ", storeAddress="
				+ storeAddress + ", storeZip=" + storeZip + ", enrollDate=" + enrollDate + ", id=" + id + ", outYN="
				+ outYN + ", useYN=" + useYN + ", storeTitleImg=" + storeTitleImg + "]";
	}

	
	

	
	
}
