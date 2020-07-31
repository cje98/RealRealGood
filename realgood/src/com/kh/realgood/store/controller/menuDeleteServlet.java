package com.kh.realgood.store.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.realgood.store.model.service.StoreService;
import com.oreilly.servlet.MultipartRequest;


@WebServlet("/store/menuDelete.do")
public class menuDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// MultipartRequest mRequest = new MultipartRequest(request, "UTF-8");
		
		int menuNum = Integer.parseInt(request.getParameter("menuNum"));
			
		System.out.println(menuNum);
		
		try {
			int result = new StoreService().deleteMenu(menuNum);
			
			/*String status = null;
			String msg = null;
			
			if(result > 0) {
				status = "success";
				msg = "메뉴 삭제 성공";
			}else {
				status = "error";
				msg = "메뉴 삭제 실패";
			}
			
			request.getSession().setAttribute("status", status);
			request.getSession().setAttribute("msg", msg);*/
			
			response.getWriter().print(result);
			
		}catch (Exception e) {
			
		}
		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
