package com.kh.realgood.store.model.dto;

import java.sql.Date;

public class StoreImg {
	private int storeImgNum;
	private int storeNum;
	private String originImgName;
	private String realImgName;
	private Date enrollDate;
    private String filePath;
    private int fileLevel;
    private String fileStatus;

	public StoreImg() {
	}
	
	
	
	



	public StoreImg(String realImgName, int fileLevel) {
		super();
		this.realImgName = realImgName;
		this.fileLevel = fileLevel;
	}






	public StoreImg(int storeImgNum, String realImgName, String filePath, int fileLevel) {
		super();
		this.storeImgNum = storeImgNum;
		this.realImgName = realImgName;
		this.filePath = filePath;
		this.fileLevel = fileLevel;
	}





	public StoreImg(int storeImgNum, int storeNum, String originImgName, String realImgName, Date enrollDate) {
		super();
		this.storeImgNum = storeImgNum;
		this.storeNum = storeNum;
		this.originImgName = originImgName;
		this.realImgName = realImgName;
		this.enrollDate = enrollDate;
	}



	public StoreImg(int storeNum, String originImgName, String realImgName, Date enrollDate) {
		super();
		this.storeNum = storeNum;
		this.originImgName = originImgName;
		this.realImgName = realImgName;
		this.enrollDate = enrollDate;
	}
	
	public StoreImg(String realImgName) {
		super();
		this.realImgName = realImgName;
	}

	
	public int getStoreImgNum() {
		return storeImgNum;
	}

	public void setStoreImgNum(int storeImgNum) {
		this.storeImgNum = storeImgNum;
	}

	public int getStoreNum() {
		return storeNum;
	}
	public void setStoreNum(int storeNum) {
		this.storeNum = storeNum;
	}
	public String getOriginImgName() {
		return originImgName;
	}
	public void setOriginImgName(String originImgName) {
		this.originImgName = originImgName;
	}
	public String getRealImgName() {
		return realImgName;
	}
	public void setRealImgName(String realImgName) {
		this.realImgName = realImgName;
	}
	public Date getEnrollDate() {
		return enrollDate;
	}
	public void setEnrollDate(Date enrollDate) {
		this.enrollDate = enrollDate;
	}
	
	

	public String getFilePath() {
		return filePath;
	}



	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}



	public int getFileLevel() {
		return fileLevel;
	}



	public void setFileLevel(int fileLevel) {
		this.fileLevel = fileLevel;
	}



	public String getFileStatus() {
		return fileStatus;
	}



	public void setFileStatus(String fileStatus) {
		this.fileStatus = fileStatus;
	}



	@Override
	public String toString() {
		return "StoreImg [storeNum=" + storeNum + ", originImgName=" + originImgName + ", realImgName=" + realImgName
				+ ", enrollDate=" + enrollDate + "]";
	}
	
}
