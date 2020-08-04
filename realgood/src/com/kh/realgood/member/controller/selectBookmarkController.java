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

import com.kh.realgood.member.model.dto.Board;
import com.kh.realgood.member.model.dto.Member;
import com.kh.realgood.member.model.service.MemberService;

@WebServlet("/member/selectBookmark.do")
public class selectBookmarkController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * request.setCharacterEncoding("UTF-8"); HttpSession session =
		 * request.getSession(); Member loginMember =
		 * (Member)session.getAttribute("loginMember"); int memberNo =
		 * loginMember.getNo();
		 * 
		 * try { List<Board> list = new MemberService().myBoardList(memberNo);
		 * request.setAttribute("cList", list);
		 */
			
			String path = "/WEB-INF/views/member/selectBookmark.jsp";
			RequestDispatcher view = request.getRequestDispatcher(path);
			view.forward(request, response);
		/*	
		
		}catch (Exception e) {
			
			e.printStackTrace();
			
			request.setAttribute("errorMsg", "즐겨찾기 조회중 에러 발생");
			
			String path = "/WEB-INF/views/common/errorPage.jsp";
			RequestDispatcher view = request.getRequestDispatcher(path);
			view.forward(request, response);
		}*/
	}

}
