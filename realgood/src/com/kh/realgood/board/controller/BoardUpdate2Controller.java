package com.kh.realgood.board.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.realgood.board.model.dto.Attachment;
import com.kh.realgood.board.model.dto.Board;
import com.kh.realgood.board.model.service.BoardService;
import com.kh.realgood.common.MyFileRenamePolicy;
import com.oreilly.servlet.MultipartRequest;

@WebServlet("/board/update.do")
public class BoardUpdate2Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String status = null;
		String msg = null;
		String text = null;
		String errorMsg = null;
		
		String path = null;
		
		int maxSize = 1024 * 1024 * 10;

		String root = request.getSession().getServletContext().getRealPath("/");
		
		String filePath = root + "resources\\uploadImages\\";
		

		try {
			MultipartRequest mRequest = 
					new MultipartRequest(request, filePath, maxSize, "UTF-8", new MyFileRenamePolicy());
			
			List<Attachment> fList = new ArrayList<Attachment>();
			
			int boardNo = Integer.parseInt(mRequest.getParameter("boardNo"));
			
			String boardContent = mRequest.getParameter("content");
			
			Board board = new Board(boardNo, boardContent);
			
			
			Enumeration<String> files = mRequest.getFileNames();
			
			Attachment temp = null;
			while(files.hasMoreElements()) {
				String name = files.nextElement();
				// 현재 접근한 요소의 값(name 속성 값) 저장
				
				if(mRequest.getFilesystemName(name)!=null) {
					// name 속성 값을 가진 요소가 파일을 가지고 있다면
					// 해당 요소를 가진 파일이 업로드 되었다
					
					temp = new Attachment();
					
					temp.setFileOriginName(mRequest.getOriginalFileName(name));
					temp.setFileChangeName(mRequest.getFilesystemName(name));
					
	                  int fileLevel = 0;
	                  switch(name) {
	                  case "img1" : fileLevel = 0; break;
	                  case "img2" : fileLevel = 1; break;
	                  case "img3" : fileLevel = 2; break;
	                  case "img4" : fileLevel = 3; break;
	                  }
	                  
	                  temp.setFileLevel(fileLevel);
	                  
	                  temp.setFilePath(filePath);
	                  
	                  fList.add(temp);
	                  
	                  // 파일을 얻어와서 레벨을 나누고 리스트에 저장한 것
	                  // 해당 리스트를 디비에 가져가서 저장할 예정
				}
			}
			int result = new BoardService().updateView(board, fList);

			if(result > 0) {
				status = "success";
				msg = "게시글 수정 성공";
				path = "reviewCheck.do?boardNo="+result+"&storeNum="+request.getParameter("storeNum");
				
			}else {
				status = "error";
				msg = "게시글 수정 실패";
			}
			
			request.getSession().setAttribute("status", status);
			request.getSession().setAttribute("msg", msg);
			response.sendRedirect(path);
		
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
