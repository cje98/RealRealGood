package com.kh.realgood.member.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.realgood.member.model.service.MemberService;

@WebServlet("/member/menuPay.do")
public class MemberMenuPayController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String input = request.getParameter("input").replaceAll("원", "");
		int storeNo = Integer.parseInt(request.getParameter("storeNum"));
		String memberId = request.getParameter("memberId");

		String[] buyListTmp = input.split("-|\\+x");
		
		List<String> buyList = new ArrayList<String>(); // 메뉴를 담을 리스트 변수
		String tmpString = ""; // 구분자를 넣어(||) 분리했던 3개의 값을 List에 담을 변수이다 
		
		// 메뉴 얻어오는 buyListTmp의 경우 -와 +x를 구분으로 자르기 때문에 3개의 결과가 한개의 결과이다
		// 그러므로 3개로 나눴을 때 나머지가 0 이면 한개의 메뉴(메뉴명, 가격, 개수)가 된다.
		for (int i = 0; i < buyListTmp.length; i++) {
			tmpString += buyListTmp[i] + "||"; // 데이터를 불러와 구분자를 넣어준다 (||)
			if(((i+1)%3) == 0 && i != 0) { // 처음 시작할 때 또는 i의 값이 +1을 했을 때 3이면 한개의 메뉴이므로 초기화작업 진행 밑 List에 담도록 한다 (i는 0부터 시작하므로 +1을 했음)  
				buyList.add(tmpString);
				tmpString = "";  // 다음 값이 있을 경우 재사용해야하므로 초기화작업 진행
			}
		}
		
		try {
			int result = new MemberService().menuPay(buyList, storeNo, memberId);
			if(result > 0) {
				response.getWriter().print(result);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
