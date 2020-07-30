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

@WebServlet("/member/changePwd.do")
public class ChangePwdController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = "/WEB-INF/views/member/changePwd.jsp";
		RequestDispatcher view = request.getRequestDispatcher(path);
		view.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String id = ((Member)request.getSession().getAttribute("loginMember")).getId();
		String oldPwd = request.getParameter("pwd");
		String newPwd = request.getParameter("pw1");
		
		try {
			int result = new MemberService().changePwd(id,oldPwd,newPwd);
			
			String status = null;
			String msg = null;
			String text = null;
			if(result > 0) {
				status = "success";
				msg = "비밀번호 변경 성공";
				text = "비밀번호 변경을 성공하였습니다.";
				
			}else if (result == 0){
				status = "error";
				msg = "비밀번호 변경 실패";
				text = "비밀번호 변경 중 문제가 발생했습니다. 문제가 지속 될 경우 문의 바랍니다";
			}else if (result == -1) {
				status = "error";
				msg = "비밀번호 변경 실패";
				text = "기존 비밀번호가 일치하지 않습니다.";
			}
				
			
			request.getSession().setAttribute("status", status);
			request.getSession().setAttribute("msg", msg);
			request.getSession().setAttribute("text", text);
			
			response.sendRedirect("myPage.do");
		} catch (Exception e) {
			
		}
		
		
		
	}

}
