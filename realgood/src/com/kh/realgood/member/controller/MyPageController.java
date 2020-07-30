package com.kh.realgood.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.realgood.member.model.dto.Member;
import com.kh.realgood.member.model.service.MemberService;

@WebServlet("/member/myPage.do")
public class MyPageController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = "/WEB-INF/views/member/mypage.jsp";
		RequestDispatcher view = request.getRequestDispatcher(path);
		view.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Member loginMember = (Member)session.getAttribute("loginMember");
		
		request.setCharacterEncoding("UTF-8");
		
		String phone1 = request.getParameter("phone1");
		String phone2 = request.getParameter("phone2");
		String phone3 = request.getParameter("phone3");		
		String tel = phone1 + "-" + phone2 + "-" + phone3;
		
		String emailReceive = request.getParameter("emailReceive");
		String smsReceive = request.getParameter("smsReceive");
		
//		String id = request.getParameter("id");
		String id = loginMember.getId();
		
		Member member = new Member(id,tel, emailReceive, smsReceive);
		
		try {
			
			int result = new MemberService().updateMember(member);
			
			String status = null;
			String msg = null;
			String text = null;
			
			if(result > 0) {
				status = "`";
				msg = "회원 수정 성공";
				text = "회원 수정을 성공하였습니다.";
				
				// 갱신
				loginMember.setTel(member.getTel());
				loginMember.setEmailReceive(member.getEmailReceive());
				loginMember.setSmsReceive(member.getSmsReceive());
				
				session.setAttribute("loginMember", loginMember);
			}else {
				status = "error";
				msg = "회원 수정 실패";
				text = "회원 수정 중 문제가 발생했습니다. 문제가 지속 될 경우 문의 바랍니다";
			}
			
			request.getSession().setAttribute("status", status);
			request.getSession().setAttribute("msg", msg);
			request.getSession().setAttribute("text", text);
			
			response.sendRedirect("myPage.do");
			
		} catch (Exception e) {
			
		}
	}

}
