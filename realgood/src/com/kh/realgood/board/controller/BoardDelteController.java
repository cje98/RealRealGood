package com.kh.realgood.board.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.realgood.board.model.service.BoardService;
import com.kh.realgood.member.model.service.MemberService;

@WebServlet("/board/delete.do")
public class BoardDelteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		 int boardNo = Integer.parseInt(request.getParameter("no"));
		 int storeNum = Integer.parseInt(request.getParameter("storeNo"));
		try {
			int result = new BoardService().deleteBoard(boardNo);
		
		 
		 if(result > 0) {
			 session.setAttribute("status", "success");
			 session.setAttribute("msg", "댓글 삭제 성공");
			 session.setAttribute("text", "댓글을 삭제 하였습니다.");
			 String path = "../store/detail.do?storeNum="+storeNum;
			 response.sendRedirect(path);
		 
		 }else {
			 session.setAttribute("status", "success");
			 session.setAttribute("msg", "댓글 삭제 성공");
			 session.setAttribute("text", "댓글을 삭제 하였습니다.");
			 request.getHeader("referer");
			 
			  }
			} catch (Exception e) {
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int boardNo = Integer.parseInt(request.getParameter("no"));
		System.out.println(boardNo);
	}

}
