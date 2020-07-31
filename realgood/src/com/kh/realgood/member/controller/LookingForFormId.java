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

import com.kh.realgood.member.model.dto.BuyList;
import com.kh.realgood.member.model.dto.Member;
import com.kh.realgood.member.model.service.MemberService;

@WebServlet("/member/lookingForFormId.do")
public class LookingForFormId extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = "/WEB-INF/views/member/lookingForId.jsp";
		RequestDispatcher view = request.getRequestDispatcher(path);
		view.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String name = request.getParameter("name");
		
		String phone1 = request.getParameter("phone1");
		String phone2 = request.getParameter("phone2");
		String phone3 = request.getParameter("phone3");		
		String tel = phone1 + "-" + phone2 + "-" + phone3;
		
		try {
			List<String> list = new MemberService().findId(name,tel);
			request.setAttribute("idList", list);
			if(list.size() > 0) {
				String path = "/WEB-INF/views/member/lookingForId.jsp";
				RequestDispatcher view = request.getRequestDispatcher(path);
				view.forward(request, response);
			}else {
				String status = null;
				String msg = null;
				String text = null;
				status = "error";
				msg = "아이디 찾기 실패	";
				text = "입력 하신 정보로 찾을 수 없습니다.";
				request.getSession().setAttribute("status", status);
				request.getSession().setAttribute("msg", msg);
				request.getSession().setAttribute("text", text);
				
				response.sendRedirect(request.getHeader("referer"));
			}
			
		} catch (Exception e) {
		} {
			
		}
	}

}
