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
import com.kh.realgood.store.model.dto.Store;
import com.kh.realgood.store.model.service.StoreService;

@WebServlet("/store/adminApprove.do")
public class AdminApproveController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
				
				List<Store> aList = new StoreService().selectStoreList();
				
				request.setAttribute("aList", aList);
				
				String path = "/WEB-INF/views/store/adminApprove.jsp";
				RequestDispatcher view = request.getRequestDispatcher(path);
				view.forward(request, response);
				
			
		} catch (Exception e) {
			e.printStackTrace();
			
			request.setAttribute("errorMsg", "승인 내역 조회 중 오류 발생");
			
			String path = "/WEB-INF/views/common/errorPage.jsp";
			RequestDispatcher view = request.getRequestDispatcher(path);
			view.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
