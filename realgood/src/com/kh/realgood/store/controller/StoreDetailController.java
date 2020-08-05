package com.kh.realgood.store.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.realgood.board.model.dto.Board;
import com.kh.realgood.board.model.service.BoardService;
import com.kh.realgood.member.model.dto.Member;
import com.kh.realgood.store.model.dto.StoreInfoMenu;
import com.kh.realgood.store.model.service.StoreService;

@WebServlet("/store/detail.do")
public class StoreDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int storeNo = Integer.parseInt(request.getParameter("storeNum"));
		
		try {
			
			List<StoreInfoMenu> mList = new StoreService().searchMenu(storeNo);
			HttpSession session = request.getSession();
			
			Member loginMember = (Member)session.getAttribute("loginMember");
			
			
			
			StoreInfoMenu storeInfoList = new StoreService().storeInfoList(storeNo);

			List<Board> boardList = new BoardService().getBoardList(storeNo);

			
			if(!mList.isEmpty()) {
				
				// 로그인되어있을때 로그인 한 멤버의 멤버 번호를 요청객체에 set하기
				if(loginMember != null) {
					int memberNo = loginMember.getNo();
					request.setAttribute("memberNo", memberNo);
					
					int result = new StoreService().checkStar(storeNo, memberNo);
					
					session.setAttribute("starColor", result);
					
				}
			}
			request.setAttribute("mList", mList);
			String path = "/WEB-INF/views/store/storeDetail.jsp";

			request.setAttribute("storeInfoList", storeInfoList);
			request.setAttribute("boardList", boardList);
			

			RequestDispatcher view = request.getRequestDispatcher(path);
			view.forward(request, response);
			
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMsg", "상세페이지 조회 안됨");
			request.getRequestDispatcher("/WEB-INF/views/common/errorPage.jsp").forward(request, response);
		}
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
