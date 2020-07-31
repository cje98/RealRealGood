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

@WebServlet("/member/removeMember.do")
public class RemoveMemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = "/WEB-INF/views/member/removeMember.jsp";
		RequestDispatcher view = request.getRequestDispatcher(path);
		view.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		String url = request.getHeader("referer");
		String id = ((Member)request.getSession().getAttribute("loginMember")).getId();
		String gradeName = ((Member)request.getSession().getAttribute("loginMember")).getGradeName();
		int result = 0;
		
		String status = null;
		String msg = null;
		String text = null;
		try {
			if (gradeName.equals("관리자")) {
				status = "error";
				msg = "회원 탈퇴 실패";
				text = "관리자 계정은 삭제할 수 없습니다.";
			}else {
				result = new MemberService().removeUser(id,gradeName);
				if(result > 0) {
					status = "success";
					msg = "회원 탈퇴 성공";
					text = "정상적으로 회원이 탈퇴 되었습니다.";
					url=request.getContextPath();
					session.removeAttribute("loginMember");
				}else {
					status = "error";
					msg = "회원 탈퇴 실패";
					text = "회원 탈퇴 중 오류가 발생했습니다. 지속적인 오류 발생 시 문의바랍니다.";
				}
			}
			
			
			request.getSession().setAttribute("status", status);
			request.getSession().setAttribute("msg", msg);
			request.getSession().setAttribute("text", text);
			response.sendRedirect(url);
			
		} catch (Exception e) {
			
		}
		
		
		
		
		
		
	}

}
