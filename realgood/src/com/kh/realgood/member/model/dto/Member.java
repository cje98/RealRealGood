package com.kh.realgood.member.model.dto;

import java.sql.Date;

public class Member {
	private int no;
	private String id;
	private String pwd;
	private String name;
	private char gender;
	private String gradeCode;
	private String gradeName;
	private String tel;
	private Date enrollDate;
	private String nickName;
	private String emailReceive;
	private char outYN;
	private String smsReceive;
	
	public Member() {
	}
	
	// 모두있는 기본 매개변수
	public Member(int no, String id, String pwd, String name, char gender, String gradeCode, String gradeName,
			String tel, Date enrollDate, String nickName, String emailReceive, char outYN, String smsReceive) {
		super();
		this.no = no;
		this.id = id;
		this.pwd = pwd;
		this.name = name;
		this.gender = gender;
		this.gradeCode = gradeCode;
		this.gradeName = gradeName;
		this.tel = tel;
		this.enrollDate = enrollDate;
		this.nickName = nickName;
		this.emailReceive = emailReceive;
		this.outYN = outYN;
		this.smsReceive = smsReceive;
	}
	
	
	
	// 로그인 DAO용 매개변수
	public Member(String id, String pwd, String name, char gender, String gradeCode, String tel, String nickName,
			String emailReceive, String smsReceive) {
		super();
		this.id = id;
		this.pwd = pwd;
		this.name = name;
		this.gender = gender;
		this.gradeCode = gradeCode;
		this.tel = tel;
		this.nickName = nickName;
		this.emailReceive = emailReceive;
		this.smsReceive = smsReceive;
	}
	
	// 회원가입용 매개변수
	public Member(int no, String id, String name, char gender, String gradeName, String tel, Date enrollDate,
			String nickName, String emailReceive, String smsReceive) {
		super();
		this.no = no;
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.gradeName = gradeName;
		this.tel = tel;
		this.enrollDate = enrollDate;
		this.nickName = nickName;
		this.emailReceive = emailReceive;
		this.smsReceive = smsReceive;
	}

	
	// 로그인용 매개변수
	public Member(String id, String pwd) {
		super();
		this.id = id;
		this.pwd = pwd;
	}

	// 관리자 회원 관리 매개변수
	public Member(int no, String id, String name, char gender, String gradeName, String tel, Date enrollDate,
			String nickName, char outYN) {
		super();
		this.no = no;
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.gradeName = gradeName;
		this.tel = tel;
		this.enrollDate = enrollDate;
		this.nickName = nickName;
		this.outYN = outYN;
	}

	// 마이페이지 매개변수
	public Member(String id, String tel, String emailReceive, String smsReceive) {
		super();
		this.id = id;
		this.tel = tel;
		this.emailReceive = emailReceive;
		this.smsReceive = smsReceive;
	}
	
	
	// 아이디 찾기 매개변수
	public Member(String id) {
		super();
		this.id = id;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public char getOutYN() {
		return outYN;
	}

	public void setOutYN(char outYN) {
		this.outYN = outYN;
	}

	public String getGradeCode() {
		return gradeCode;
	}


	public void setGradeCode(String gradeCode) {
		this.gradeCode = gradeCode;
	}



	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setEmailReceive(String emailReceive) {
		this.emailReceive = emailReceive;
	}

	public void setSmsReceive(String smsReceive) {
		this.smsReceive = smsReceive;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getMame() {
		return name;
	}
	public void setMame(String mame) {
		this.name = mame;
	}
	public char getGender() {
		return gender;
	}
	public void setGender(char gender) {
		this.gender = gender;
	}
	public String getGradeName() {
		return gradeName;
	}
	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public Date getEnrollDate() {
		return enrollDate;
	}
	public void setEnrollDate(Date enrollDate) {
		this.enrollDate = enrollDate;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getEmailReceive( ) {
		return emailReceive;
	}
	public void emailReceive(String emailReceive) {
		this.emailReceive = emailReceive;
	}
	
	public String getSmsReceive( ) {
		return smsReceive;
	}
	public void smsReceive(String smsReceive) {
		this.smsReceive = smsReceive;
	}
	
	public String getGenderName() {
		String name = null;
		if(this.gender == 'M') name = "남성";
		else                   name = "여성";
		return name;
	}

	@Override
	public String toString() {
		return "Member [no=" + no + ", id=" + id + ", pwd=" + pwd + ", name=" + name + ", juminFront="
				+ ", juminRear=" + ", gender=" + gender + ", gradeName=" + gradeName + ", tel=" + tel
				+ ", enrollDate=" + enrollDate + ", nickName=" + nickName + ", emailReceive=" + emailReceive
				+ ", outYN=" + outYN + ", smsReceive=" + smsReceive + "]";
	}

	
	
	
	
}
