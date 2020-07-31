package com.kh.realgood.store.model.dto;

import java.sql.Date;

public class StoreMenu {
	private int menuNo;
	private String menuName;
	private int price;
	private int storeNo;
	private Date storeEnrolldate;
	private char cancleYN;
	private String menuContents;
	private String menuMakeTime;
	
	public StoreMenu() {
		// TODO Auto-generated constructor stub
	}
	
	
	

	public StoreMenu(String menuName, int price, String menuContents, String menuMakeTime) {
		super();
		this.menuName = menuName;
		this.price = price;
		this.menuContents = menuContents;
		this.menuMakeTime = menuMakeTime;
	}




	public StoreMenu(int menuNo, String menuName, int price, String menuContents, String menuMakeTime) {
		super();
		this.menuNo = menuNo;
		this.menuName = menuName;
		this.price = price;
		this.menuContents = menuContents;
		this.menuMakeTime = menuMakeTime;
	}



	public StoreMenu(int menuNo, String menuName, int price, int storeNo, Date storeEnrolldate, char cancleYN,
			String menuContents, String menuMakeTime) {
		super();
		this.menuNo = menuNo;
		this.menuName = menuName;
		this.price = price;
		this.storeNo = storeNo;
		this.storeEnrolldate = storeEnrolldate;
		this.cancleYN = cancleYN;
		this.menuContents = menuContents;
		this.menuMakeTime = menuMakeTime;
	}

	public int getMenuNo() {
		return menuNo;
	}

	public void setMenuNo(int menuNo) {
		this.menuNo = menuNo;
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

	public int getStoreNo() {
		return storeNo;
	}

	public void setStoreNo(int storeNo) {
		this.storeNo = storeNo;
	}

	public Date getStoreEnrolldate() {
		return storeEnrolldate;
	}

	public void setStoreEnrolldate(Date storeEnrolldate) {
		this.storeEnrolldate = storeEnrolldate;
	}

	public char getCancleYN() {
		return cancleYN;
	}

	public void setCancleYN(char cancleYN) {
		this.cancleYN = cancleYN;
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

	@Override
	public String toString() {
		return "StoreMenu [menuNo=" + menuNo + ", menuName=" + menuName + ", price=" + price + ", storeNo=" + storeNo
				+ ", storeEnrolldate=" + storeEnrolldate + ", cancleYN=" + cancleYN + ", menuContents=" + menuContents
				+ ", menuMakeTime=" + menuMakeTime + "]";
	}
	
	
}
