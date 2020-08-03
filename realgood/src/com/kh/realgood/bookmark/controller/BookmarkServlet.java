package com.kh.realgood.bookmark.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.realgood.bookmark.model.service.BookmarkService;

@WebServlet("/store/favorite.do")
public class BookmarkServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int storeNo = Integer.parseInt(request.getParameter("storeNo"));
		int memberNo = Integer.parseInt(request.getParameter("memberNo"));
		
		
		try {
			int result = new BookmarkService().insertNo(storeNo, memberNo);
			
			if(result > 0) {
				String str = "즐겨찾기에 추가되었습니다. [마이페이지->내가 즐겨찾는 가게]에서 확인하세요.";
				response.getWriter().print(str);	
			}else {
				String str = "즐겨찾기 저장에 실패했습니다.";
			}
			
			
		}catch (Exception e) {
			
		}
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
