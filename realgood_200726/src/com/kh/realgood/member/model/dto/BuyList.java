package com.kh.realgood.member.model.dto;

import java.sql.Date;

public class BuyList {
	private int buyNum; // 구매 번호
	private String buyMemberName; // 구매 유저 이름
	private int buyMemberNo; // 구매 유저 번호
	private int buyStoreNum; // 구매 가게 번호
	private String buyStoreName; // 구매 가게 이름
	private String buyMenuName; // 구매 메뉴 이름
	private int buyMenuNum; // 구매 메뉴 번호
	private Date buyDate; // 구매 날짜
	private String buyQrCodeNum ; // 큐알코드 번호
	private Date buyUsed; // 사용 날짜
	
	public BuyList() {
	}

	public BuyList(int buyNum, String buyMemberName, int buyMemberNo, int buyStoreNum, String buyStoreName,
			String buyMenuName, int buyMenuNum, Date buyDate, String buyQrCodeNum, Date buyUsed) {
		super();
		this.buyNum = buyNum;
		this.buyMemberName = buyMemberName;
		this.buyMemberNo = buyMemberNo;
		this.buyStoreNum = buyStoreNum;
		this.buyStoreName = buyStoreName;
		this.buyMenuName = buyMenuName;
		this.buyMenuNum = buyMenuNum;
		this.buyDate = buyDate;
		this.buyQrCodeNum = buyQrCodeNum;
		this.buyUsed = buyUsed;
	}

	public BuyList(int buyNum, int buyMemberNo, int buyStoreNum ,String buyStoreName, String buyMenuName, Date buyDate,
			String buyQrCodeNum, Date buyUsed) {
		super();
		this.buyNum = buyNum;
		this.buyMemberNo = buyMemberNo;
		this.buyStoreNum = buyStoreNum;
		this.buyStoreName = buyStoreName;
		this.buyMenuName = buyMenuName;
		this.buyDate = buyDate;
		this.buyQrCodeNum = buyQrCodeNum;
		this.buyUsed = buyUsed;
	}

	public int getBuyNum() {
		return buyNum;
	}

	public void setBuyNum(int buyNum) {
		this.buyNum = buyNum;
	}

	public String getBuyMemberName() {
		return buyMemberName;
	}

	public void setBuyMemberName(String buyMemberName) {
		this.buyMemberName = buyMemberName;
	}

	public int getBuyMemberNo() {
		return buyMemberNo;
	}

	public void setBuyMemberNo(int buyMemberNo) {
		this.buyMemberNo = buyMemberNo;
	}

	public int getBuyStoreNum() {
		return buyStoreNum;
	}

	public void setBuyStoreNum(int buyStoreNum) {
		this.buyStoreNum = buyStoreNum;
	}

	public String getBuyStoreName() {
		return buyStoreName;
	}

	public void setBuyStoreName(String buyStoreName) {
		this.buyStoreName = buyStoreName;
	}

	public String getBuyMenuName() {
		return buyMenuName;
	}

	public void setBuyMenuName(String buyMenuName) {
		this.buyMenuName = buyMenuName;
	}

	public int getBuyMenuNum() {
		return buyMenuNum;
	}

	public void setBuyMenuNum(int buyMenuNum) {
		this.buyMenuNum = buyMenuNum;
	}

	public Date getBuyDate() {
		return buyDate;
	}

	public void setBuyDate(Date buyDate) {
		this.buyDate = buyDate;
	}

	public String getBuyQrCodeNum() {
		return buyQrCodeNum;
	}

	public void setBuyQrCodeNum(String buyQrCodeNum) {
		this.buyQrCodeNum = buyQrCodeNum;
	}

	public Date getBuyUsed() {
		return buyUsed;
	}

	public void setBuyUsed(Date buyUsed) {
		this.buyUsed = buyUsed;
	}

	@Override
	public String toString() {
		return "BuyList [buyNum=" + buyNum + ", buyMemberName=" + buyMemberName + ", buyMemberNo=" + buyMemberNo
				+ ", buyStoreNum=" + buyStoreNum + ", buyStoreName=" + buyStoreName + ", buyMenuName=" + buyMenuName
				+ ", buyMenuNum=" + buyMenuNum + ", buyDate=" + buyDate + ", buyQrCodeNum=" + buyQrCodeNum
				+ ", buyUsed=" + buyUsed + "]";
	}
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
}
