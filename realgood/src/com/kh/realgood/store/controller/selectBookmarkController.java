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

import com.kh.realgood.member.model.dto.Board;
import com.kh.realgood.member.model.dto.Member;
import com.kh.realgood.member.model.service.MemberService;
import com.kh.realgood.store.model.dto.Store;
import com.kh.realgood.store.model.service.StoreService;
import com.kh.realgood.store.model.vo.PageInfo;

@WebServlet("/member/selectBookmark.do")
public class selectBookmarkController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		Member loginMember = (Member)session.getAttribute("loginMember");
		
		
		String group = request.getParameter("group");
		String addr = request.getParameter("address");
		String currentPage = request.getParameter("cp");
		int memberNum = loginMember.getNo();
		
		addr = addr == null ? "" : addr;
		group = group == null ? "" : group;
		
		try {
			PageInfo pInfo = new StoreService().getPageInfo(currentPage, group, addr);
			List<Store> storeList = new StoreService().myBookmark(pInfo,memberNum);
			request.setAttribute("pInfo", pInfo);
			request.setAttribute("storeList", storeList);
			
			
			session = request.getSession();
			
			
			
			// 요청성공
			if(!storeList.isEmpty()) {
				session.setAttribute("storeList", storeList);
				
				String path = "/WEB-INF/views/store/selectBookmark.jsp";
				RequestDispatcher rd = request.getRequestDispatcher(path);
				rd.forward(request, response);
			} else {
				String msg = "검색 실패!";
				String status = "error";
				String text = "조회결과가 없습니다.";
				
				request.getSession().setAttribute("msg", msg);
				request.getSession().setAttribute("status", status);
				request.getSession().setAttribute("text", text);
				
				String path = "/WEB-INF/views/store/storeSearch.jsp";
				RequestDispatcher rd = request.getRequestDispatcher(path);
				rd.forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			
			request.setAttribute("errorMsg", "검색 결과 출력 과정에서 문제가 발생했습니다.");
			
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/errorPage.jsp");
			view.forward(request, response);
		}
	}

}
