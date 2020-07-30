package com.kh.realgood.store.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.realgood.store.model.dto.Store;
import com.kh.realgood.store.model.service.StoreService;


@WebServlet("/store/storeAll.do")
public class StoreAllController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String addr = request.getParameter("address");
		try {
			List<Store> sList = new StoreService().searchStore(addr);
			if(!sList.isEmpty()) {
				request.setAttribute("sList", sList);
				String path = "/WEB-INF/views/store/storeAll.jsp";
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
