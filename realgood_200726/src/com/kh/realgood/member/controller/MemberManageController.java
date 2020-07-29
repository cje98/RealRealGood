package com.kh.realgood.member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.realgood.member.model.dto.Member;
import com.kh.realgood.member.model.service.MemberService;

@WebServlet("/member/memberManage.do")
public class MemberManageController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			List<Member> list = new MemberService().selectMember();
			
			
				request.setAttribute("list", list);
				String path = "/WEB-INF/views/member/memberManage.jsp";
				RequestDispatcher view = request.getRequestDispatcher(path);
				view.forward(request, response);
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMsg", "전체 회원 조회 중 오류 발생");
			
			String path = "/WEB-INF/views/common/errorPage.jsp";
			RequestDispatcher view = request.getRequestDispatcher(path);
			view.forward(request, response);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		
	}

}
