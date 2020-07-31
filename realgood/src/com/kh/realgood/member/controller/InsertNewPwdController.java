package com.kh.realgood.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.realgood.member.model.dto.Member;
import com.kh.realgood.member.model.service.MemberService;

@WebServlet("/member/insertNewPwd.do")
public class InsertNewPwdController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		String newPwd = request.getParameter("newPwd");
		
		try {
			
			int result = new MemberService().updatePwd(id, newPwd);
			
			if(result > 0) {
				request.getSession().setAttribute("status", "success");
				request.getSession().setAttribute("msg", "수정 성공");
				request.getSession().setAttribute("text", "비밀번호가 수정 되었습니다.");
				response.sendRedirect("login.do");
			}
			
		} catch (Exception e) {
		
			e.printStackTrace();
		}
		
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
