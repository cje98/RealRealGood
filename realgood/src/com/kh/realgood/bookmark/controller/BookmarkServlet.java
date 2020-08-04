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
	
		int result = 0;
		String str = "";
		int rNo = 0;
		try {
			if(memberNo != -1) { // 로그인이 된 상태라면
				// storeNo, memberNo를 보내서 bookmark에 정보가 저장되어있는지 체크
				result = new BookmarkService().checkBookmark(storeNo, memberNo);
				
				if(result > 0) {
					// 체크해서 만약 db에 있다면 delete(별 색깔x)/
					result = new BookmarkService().deleteBookmark(storeNo, memberNo);
					
						if(result > 0) { // 삭제가 됐다면
							
							rNo = 0;
						}
						
						
				}else { // 없다면 정보 insert(별 색깔o)
					result = new BookmarkService().insertNo(storeNo, memberNo);
						if(result > 0) {
							
							rNo = 1;
						}else {
							rNo = -1;
						}
				}
				
			}else { // 로그인이 안됐을때
				rNo=2;
			}
			response.getWriter().print(rNo);
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
