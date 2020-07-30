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
import com.kh.realgood.store.model.dto.Store;
import com.kh.realgood.store.model.service.StoreService;

@WebServlet("/member/purchase.do")
public class PurchaseController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		Member loginMember = (Member)session.getAttribute("loginMember");
		int memberNo = loginMember.getNo();
		
		try {
			List<BuyList> list = new MemberService().PurchaseList(memberNo);
			request.setAttribute("bList", list);
			
			String path = "/WEB-INF/views/member/purchase.jsp";
			RequestDispatcher view = request.getRequestDispatcher(path);
			view.forward(request, response);
			
		
	} catch (Exception e) {
		e.printStackTrace();
		
		request.setAttribute("errorMsg", "구매 내역 조회중 에러 발생");
		
		String path = "/WEB-INF/views/common/errorPage.jsp";
		RequestDispatcher view = request.getRequestDispatcher(path);
		view.forward(request, response);
	}
	}

}
