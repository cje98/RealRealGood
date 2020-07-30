package com.kh.realgood.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.realgood.member.model.dto.Member;
import com.kh.realgood.member.model.service.MemberService;
import com.kh.realgood.store.model.dto.Store;

@WebServlet("/member/login.do")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LoginController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = "/WEB-INF/views/member/login.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String memberId = request.getParameter("memberId");
		String memberPwd = request.getParameter("memberPwd");
		
		// 3. Member 객체를 생성하여 id, pwd를 저장하여 한 번에 전달할 수 있게함.
		Member member = new Member(memberId, memberPwd);
		
		Store store = null;
		try {
			// 4. 비즈니스 로직 처리를 하는 Service 메소드를 호출하고
			//    반환값을 저장함.
			Member loginMember = new MemberService().loginMember(member);
			
			HttpSession session = request.getSession();
			
			// 요청성공
			if(loginMember != null) {
				
				//-- 로그인한 멤버의 등급명이 "사장회원"일 경우 store에 가게 정보를 저장한다.
				if(loginMember.getGradeName().equals("사장회원")) {
					store = new MemberService().loginMemberStore(loginMember.getId());
				}
				
				session.setAttribute("loginMember", loginMember);
				session.setAttribute("loginStore", store);
				
				session.setMaxInactiveInterval(1800); // 1800초 (30분) 
				response.sendRedirect(request.getContextPath());
			} else {
				String msg = "로그인 실패!";
				String status = "error";
				String text = "아이디 또는 비밀번호 확인 해주세요.";
				
				request.getSession().setAttribute("msg", msg);
				request.getSession().setAttribute("status", status);
				request.getSession().setAttribute("text", text);
				response.sendRedirect(request.getHeader("referer"));
			}

			
		} catch (Exception e) {
			e.printStackTrace();
			
			request.setAttribute("errorMsg", "로그인 과정에서 문제가 발생했습니다.");
			
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/errorPage.jsp");
			view.forward(request, response);
		}
		
	}

}
