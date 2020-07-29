package com.kh.realgood.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.realgood.member.model.dto.Member;
import com.kh.realgood.member.model.service.MemberService;

@WebServlet("/member/signup.do")
public class SignupController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SignupController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect(request.getHeader("referer"));
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		
		// 파라미터 받기
		String memberId = request.getParameter("id");
		String memberPwd = request.getParameter("pwd1");

		String memberName = request.getParameter("name");
		
				
		char gender = request.getParameter("gender").charAt(0);
		
		
		String phone1 = request.getParameter("phone1");
		String phone2 = request.getParameter("phone2");
		String phone3 = request.getParameter("phone3");		
		
		String phone = phone1 + "-" + phone2 + "-" + phone3;
		
		String memberNickname = request.getParameter("nickName");
		
		String emailReceive = request.getParameter("emailReceive");
		
		String smsReceive = request.getParameter("smsReceive");
		
		// 멤버에 담아서 service에 보내기
		Member member = new Member(memberId, memberPwd, memberName, gender, phone, memberNickname, emailReceive, smsReceive);
				
		member.setPwd(memberPwd);
		
		try {
			int result = new MemberService().signUp(member);
			
			String status = null;
			String msg = null;
			String text = null;
			
			if(result > 0) {
				status = "success";
				msg = "회원 가입 성공";
				text = "회원 가입을 환영합니다.";
			}else {
				status = "error";
				msg = "회원 가입 실패";
				text = "회원 가입 중 문제가 발생했습니다. 문제가 지속 될 경우 문의 바람";
			}
			
			request.getSession().setAttribute("status", status);
			request.getSession().setAttribute("msg", msg);
			request.getSession().setAttribute("text", text);
			
			response.sendRedirect(request.getContextPath());

			
		} catch (Exception e) {
			e.printStackTrace();
			
			request.setAttribute("errorMsg", "회원 가입 중 오류 발생");
			
			String path = "/WEB-INF/views/common/errorPage.jsp";
			RequestDispatcher view = request.getRequestDispatcher(path);
			view.forward(request, response);
			
			
		}
		
		

	}

}
