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

import com.kh.realgood.store.model.dto.Store;
import com.kh.realgood.store.model.dto.StoreImg;
import com.kh.realgood.store.model.service.StoreService;

@WebServlet("/store/search.do")
public class StoreSearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public StoreSearchController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String group = request.getParameter("group");
		String addr = request.getParameter("address");
		
		try {
			List<Store> addrArr = new StoreService().searchAddr(group, addr);
			HttpSession session = request.getSession();
			
			// 요청성공
			if(!addrArr.isEmpty()) {
				session.setAttribute("addrArr", addrArr);
				
				String path = "/WEB-INF/views/store/storeSearch.jsp";
				RequestDispatcher rd = request.getRequestDispatcher(path);
				rd.forward(request, response);
			} else {
				String msg = "검색 실패!";
				String status = "error";
				String text = "조회결과가 없는듯 합니다.";
				
				request.getSession().setAttribute("msg", msg);
				request.getSession().setAttribute("status", status);
				request.getSession().setAttribute("text", text);
				response.sendRedirect(request.getHeader("referer"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			
			request.setAttribute("errorMsg", "검색 결과 출력 과정에서 문제가 발생했습니다.");
			
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/errorPage.jsp");
			view.forward(request, response);
		}
	}

}
