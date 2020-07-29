package com.kh.realgood.store.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kh.realgood.store.model.dto.Store;
import com.kh.realgood.store.model.dto.StoreInfoMenu;
import com.kh.realgood.store.model.service.StoreService;

@WebServlet("/store/selectStore.do")
public class SelectStoreController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		String id = request.getParameter("id");
		
		try {
			
			StoreInfoMenu selectStore = new StoreService().selectStore(id);
			Gson gson = new GsonBuilder().setDateFormat("yyyy년 MM월 dd일").create();
			
			response.setCharacterEncoding("UTF-8");
			gson.toJson(selectStore, response.getWriter());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
