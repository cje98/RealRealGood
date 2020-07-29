package com.kh.realgood.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.realgood.member.model.service.MemberService;

@WebServlet("/member/memberDelete.do")
public class MemberDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		
		String[] id = request.getParameterValues("id");
		//String[] idArr = id.split(" ");
		
		try {
			
			//int result = new MemberService().deleteMember(idArr);
			int result = new MemberService().deleteMember(id);
			
			if(result > 0) {
				request.getSession().setAttribute("status", "success");
				request.getSession().setAttribute("msg", "삭제 성공");
				request.getSession().setAttribute("text", "회원의 정보가 삭제 되었습니다.");
				response.sendRedirect("memberManage.do");
				
			}else {
				request.getSession().setAttribute("status", "error");
				request.getSession().setAttribute("msg", "삭제 실패");
				request.getSession().setAttribute("text", "삭제 실패하였습니다.");
				response.sendRedirect(request.getHeader("referer"));
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			
			request.setAttribute("errorMsg", "회원 삭제 중 오류 발생");
			
			String path = "/WEB-INF/views/common/errorPage.jsp";
			RequestDispatcher view = request.getRequestDispatcher(path);
			view.forward(request, response);
		}
			
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
