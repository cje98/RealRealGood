package com.kh.realgood.board.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.realgood.board.model.dto.Attachment;
import com.kh.realgood.board.model.dto.Board;
import com.kh.realgood.board.model.service.BoardService;
import com.kh.realgood.common.MyFileRenamePolicy;
import com.kh.realgood.member.model.dto.Member;
import com.kh.realgood.store.model.dto.StoreInfoMenu;
import com.kh.realgood.store.model.service.StoreService;
import com.oreilly.servlet.MultipartRequest;


@WebServlet("/board/insert.do")
public class BoardReviewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String status = null;
		String msg = null;
		String text = null;
		String errorMsg = null;
		
		String path = null;
		RequestDispatcher view = null;
		
		try {
			
			int maxSize = 1024 * 1024 * 10;
			String root = request.getSession().getServletContext().getRealPath("/");
			String filePath = root + "resources\\uploadImages";
			MultipartRequest mRequest = new MultipartRequest(request, filePath, maxSize, "UTF-8", new MyFileRenamePolicy());
			
			String boardContent = mRequest.getParameter("content");
			int storeNum = Integer.parseInt(mRequest.getParameter("storeNum"));
			int starScore = mRequest.getParameter("starScore").length() != 0 ? Integer.parseInt(mRequest.getParameter("starScore")) : 0;
			
			String memberId = ((Member)request.getSession().getAttribute("loginMember")).getId();
			Board board = new Board(boardContent, memberId, storeNum);
			board.setStarScore(starScore);
			// 파일정보 저장할 List 생성
			List<Attachment> attachList = new ArrayList<Attachment>();
			
			// 전송된 파일의 name 속성값을 모두 반환
			Enumeration<String> files = mRequest.getFileNames();
			
			Attachment temp = null;
			while(files.hasMoreElements()) {
				
				String name = files.nextElement();
				
				// 요청 객체 중 전달 받은 name 속성을 가진 요소가 있을 경우
				if(mRequest.getFilesystemName(name) != null) {
					temp = new Attachment();
					
					temp.setFileOriginName(mRequest.getOriginalFileName(name));
					temp.setFileChangeName(mRequest.getFilesystemName(name));
					
					int fileLevel = 0;
					
					switch (name) {
					case "img1": fileLevel =0; break;
					case "img2": fileLevel =1; break;
					case "img3": fileLevel =2; break;
					case "img4": fileLevel =3; break;
					}
					
					temp.setFileLevel(fileLevel);
					
					temp.setFilePath(filePath);
					
					attachList.add(temp);
				}
			}
			
			
			int result = new BoardService().insertBoard(board, attachList);

			
			Board reviewList = new BoardService().reviewInfo2(storeNum);
			
			if(result>0) {
				status = "success";
				msg = "리뷰 작성 완료";
				text = "작성을 완료하였습니다";
				
			}else {
				status = "error";
				msg="리뷰 작성 실패";
				text = "작성을 실패하였습니다";
				path = request.getHeader("referer");
			}
			
//			path = "/WEB-INF/views/board/review.jsp";
			path = "reviewCheck.do?boardNo="+result;
			request.setAttribute("board", board);
			request.setAttribute("reviewList", reviewList);
			request.setAttribute("storeNum", storeNum);
			request.getSession().setAttribute("msg", msg);
			request.getSession().setAttribute("status", status);
			request.getSession().setAttribute("text", text);
			
			view = request.getRequestDispatcher(path);
			view.forward(request, response);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
