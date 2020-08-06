package com.kh.realgood.store.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.realgood.store.model.service.StoreService;


@WebServlet("/store/storeAdmin.do")
public class StoreAdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String no = request.getParameter("no");
		String noArr[] = no.split(" ");
		
		try {
			int result = new StoreService().adminStore(noArr);
			
			if(result > 0) {
				request.getSession().setAttribute("status", "success");
				request.getSession().setAttribute("msg", "승인");
				request.getSession().setAttribute("text", "가게를 승인하였습니다.");
				response.sendRedirect("adminApprove.do");
				
			}else {
				request.getSession().setAttribute("status", "error");
				request.getSession().setAttribute("msg", "삭제 실패");
				request.getSession().setAttribute("text", "가게 승인을 실패하였습니다.");
				response.sendRedirect(request.getHeader("referer"));
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			
			request.setAttribute("errorMsg", "관리자 승인 중 오류 발생");
			
			String path = "/WEB-INF/views/common/errorPage.jsp";
			RequestDispatcher view = request.getRequestDispatcher(path);
			view.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
