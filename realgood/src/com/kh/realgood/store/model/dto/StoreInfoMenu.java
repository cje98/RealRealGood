package com.kh.realgood.store.model.dto;

import java.sql.Date;


public class StoreInfoMenu {
	private String storeName; // 가게 이름
	private String storeContent; // 가게 설명
	private String storeTel; // 가게 전화 번호
	private String storeAddress; // 가게 주소
	private int menuNum;	// 메뉴 번호
	private String menuName; // 메뉴 이름
	private String menuMakeTime; // 만드는 시간
	private String menuContents; // 메뉴 설명
	private int price;
	private int priceMin;	// 메뉴 가격
	private int priceMax;	// 메뉴 가격
	private Date enrollDate; // 가게 등록 날짜
	private String id; // 회원 아이디
	private String groupName;
	private int storeImgNum;
	
	
	public StoreInfoMenu() {

	}
	

	
	
	
	
	
	public int getStoreImgNum() {
		return storeImgNum;
	}







	public void setStoreImgNum(int storeImgNum) {
		this.storeImgNum = storeImgNum;
	}







	public StoreInfoMenu(String storeName, String storeContent, String storeTel, String storeAddress, int menuNum,
			String menuName, String menuMakeTime, String menuContents, int price, int priceMin, int priceMax,
			Date enrollDate, String id, String groupName, int storeImgNum) {
		super();
		this.storeName = storeName;
		this.storeContent = storeContent;
		this.storeTel = storeTel;
		this.storeAddress = storeAddress;
		this.menuNum = menuNum;
		this.menuName = menuName;
		this.menuMakeTime = menuMakeTime;
		this.menuContents = menuContents;
		this.price = price;
		this.priceMin = priceMin;
		this.priceMax = priceMax;
		this.enrollDate = enrollDate;
		this.id = id;
		this.groupName = groupName;
		this.storeImgNum = storeImgNum;
	}







	public StoreInfoMenu(String storeName, String storeContent, String storeTel, String storeAddress, int menuNum,
			String menuName, String menuMakeTime, String menuContents, int price, Date enrollDate, String id) {
		super();
		this.storeName = storeName;
		this.storeContent = storeContent;
		this.storeTel = storeTel;
		this.storeAddress = storeAddress;
		this.menuNum = menuNum;
		this.menuName = menuName;
		this.menuMakeTime = menuMakeTime;
		this.menuContents = menuContents;
		this.price = price;
		this.enrollDate = enrollDate;
		this.id = id;
	}

	
	
	



	public StoreInfoMenu(String storeName, String storeContent, String storeTel, String storeAddress, String menuName,
			String menuMakeTime, String menuContents, int price, Date enrollDate, String id) {
		super();
		this.storeName = storeName;
		this.storeContent = storeContent;
		this.storeTel = storeTel;
		this.storeAddress = storeAddress;
		this.menuName = menuName;
		this.menuMakeTime = menuMakeTime;
		this.menuContents = menuContents;
		this.price = price;
		this.enrollDate = enrollDate;
		this.id = id;
	}
	
	
	public StoreInfoMenu(String storeTel, String storeAddress, String groupName, int priceMin, int priceMax, String storeName,String storeContent, int storeImgNum) {
		super();
		this.storeTel = storeTel;
		this.storeAddress = storeAddress;
		this.groupName = groupName;
		this.priceMin = priceMin;
		this.priceMax = priceMax;
		this.storeName = storeName;
		this.storeContent = storeContent;
		this.storeImgNum = storeImgNum;
	}

	



	public int getMenuNum() {
		return menuNum;
	}




	public void setMenuNum(int menuNum) {
		this.menuNum = menuNum;
	}




	

	public StoreInfoMenu(int menuNum, String menuName, String menuMakeTime, String menuContents, int price) {
		super();
		this.menuNum = menuNum;
		this.menuName = menuName;
		this.menuMakeTime = menuMakeTime;
		this.menuContents = menuContents;
		this.price = price;
	}




	public String getMenuName() {
		return menuName;
	}


	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}


	public int getPrice() {
		return price;
	}


	public void setPrice(int price) {
		this.price = price;
	}


	public String getMenuContents() {
		return menuContents;
	}


	public void setMenuContents(String menuContents) {
		this.menuContents = menuContents;
	}


	public String getMenuMakeTime() {
		return menuMakeTime;
	}


	public void setMenuMakeTime(String menuMakeTime) {
		this.menuMakeTime = menuMakeTime;
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


	public String getStoreAddress() {
		return storeAddress;
	}


	public void setStoreAddress(String storeAddress) {
		this.storeAddress = storeAddress;
	}


	public Date getEnrollDate() {
		return enrollDate;
	}


	public void setEnrollDate(Date enrollDate) {
		this.enrollDate = enrollDate;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}



	public int getPriceMin() {
		return priceMin;
	}







	public void setPriceMin(int priceMin) {
		this.priceMin = priceMin;
	}







	public int getPriceMax() {
		return priceMax;
	}







	public void setPriceMax(int priceMax) {
		this.priceMax = priceMax;
	}







	public String getGroupName() {
		return groupName;
	}







	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}







	@Override
	public String toString() {
		return "StoreInfoMenu [storeName=" + storeName + ", storeContent=" + storeContent + ", storeTel=" + storeTel
				+ ", storeAddress=" + storeAddress + ", menuNum=" + menuNum + ", menuName=" + menuName
				+ ", menuMakeTime=" + menuMakeTime + ", menuContents=" + menuContents + ", price=" + price
				+ ", priceMin=" + priceMin + ", priceMax=" + priceMax + ", enrollDate=" + enrollDate + ", id=" + id
				+ ", groupName=" + groupName + "]";
	}






	
	
}
