package com.kh.realgood.board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.realgood.board.model.dto.Attachment;
import com.kh.realgood.board.model.dto.Board;
import com.kh.realgood.board.model.service.BoardService;

@WebServlet("/board/reviewCheck.do")
public class BoardCheckController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String path = null;
		RequestDispatcher view = null;
		
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		Board reviewList = null;
		
		
		try {
			reviewList = new BoardService().reviewInfo2(boardNo);
			
			// 이미지 조회
			if(reviewList != null) {
				List<Attachment> fileList = new BoardService().selectFiles(boardNo);
				
				if(!fileList.isEmpty()) {
					request.setAttribute("fileList", fileList);
				}
				
			}
		}catch (Exception e) {
			e.printStackTrace();
		}


		path = "/WEB-INF/views/board/review.jsp";
		request.setAttribute("boardNo", boardNo);
		request.setAttribute("reviewList", reviewList);
		view = request.getRequestDispatcher(path);
		view.forward(request, response);	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
