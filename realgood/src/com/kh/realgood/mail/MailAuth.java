package com.kh.realgood.mail;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class MailAuth extends Authenticator{
	
	@Override
	protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication("gksrltjd93@gmail.com", "yjyiuqzyfyxjevsy");
	}
}
