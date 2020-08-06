package com.kh.realgood.board.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.realgood.board.model.dto.Board;
import com.kh.realgood.board.model.service.BoardService;

@WebServlet("/board/insertForm.do")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		
		String path = null;
		RequestDispatcher view = null;
		
		int storeNum = Integer.parseInt(request.getParameter("storeNum"));
		
		try {
			
			path = "/WEB-INF/views/board/reviewBoard.jsp";
			
			view = request.getRequestDispatcher(path);
			view.forward(request, response);	
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
