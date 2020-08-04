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
			
			
			if(!mList.isEmpty()) {
				request.setAttribute("mList", mList);
				Member loginMember = (Member)session.getAttribute("loginMember");
				
				if(loginMember != null) {
					int memberNo = loginMember.getNo();
					request.setAttribute("memberNo", memberNo);
				}
				
				String path = "/WEB-INF/views/store/storeDetail.jsp";
				RequestDispatcher view = request.getRequestDispatcher(path);
				view.forward(request, response);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		
		}
		
		
	
	
	
	
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
