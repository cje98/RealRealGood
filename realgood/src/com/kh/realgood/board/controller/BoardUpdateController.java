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

@WebServlet("/board/updateForm.do")
public class BoardUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		RequestDispatcher view = null;

		
		try {
			
			Board board = new BoardService().updateView(boardNo);
			if(board != null) {
				List<Attachment> fList = new BoardService().selectFiles(boardNo);
				
				if(!fList.isEmpty()) {
					request.setAttribute("fList", fList);
				}
				
				String path = "/WEB-INF/views/board/boardUpdateForm.jsp";
				request.setAttribute("board", board);
				view = request.getRequestDispatcher(path);
				view.forward(request, response);
				
			}
			
		}catch (Exception e) {
			e.printStackTrace();

		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
