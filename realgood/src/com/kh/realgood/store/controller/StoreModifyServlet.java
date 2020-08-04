package com.kh.realgood.store.controller;

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
import javax.servlet.http.HttpSession;

import com.kh.realgood.common.MyFileRenamePolicy;
import com.kh.realgood.store.model.dto.Store;
import com.kh.realgood.store.model.dto.StoreImg;
import com.kh.realgood.store.model.dto.StoreMenu;
import com.kh.realgood.store.model.service.StoreService;
import com.oreilly.servlet.MultipartRequest;


@WebServlet("/store/storeModify.do")
public class StoreModifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			HttpSession session = request.getSession();
			int storeNo = ((Store)session.getAttribute("loginStore")).getStoreNum();
			
			// 가게 번호로 storeImg 가져와서 저장하기
			List<StoreImg> storeImg = new StoreService().selectImg(storeNo);
			
			// 이미지 정보를 요청객체에 저장하기
			request.setAttribute("storeImg", storeImg);
			
			// 가게 번호로 가게 메뉴 가져와서 저장하기
			List<StoreMenu> storeMenu = new StoreService().selectMenu(storeNo);

			
			// 메뉴 정보를 요청객체에 저장하기
			request.setAttribute("storeMenu", storeMenu);
		}catch (Exception e) {
			e.printStackTrace();
		}
		// 가게정보 수정 버튼을 누르면 storeModify.jsp 화면으로 이동하기
		String path = "/WEB-INF/views/store/storeModify.jsp";
		RequestDispatcher view = request.getRequestDispatcher(path);
		// 요청 위임 객체를 통해 path에 위임시키기위해 view로 저장
		view.forward(request, response);// forward 방식으로
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// [전송된 파일 저장하기]
		int maxSize = 1024 * 1024 * 10; //10MB -> 전송 파일의 크기(용량)을 제한한다.
		
		String root = request.getSession().getServletContext().getRealPath("/");
		// C:\workspace\semiproject\realgood_200724장영인\WebContent\
		
		// images 폴더 위치 추출
		String filePath = root + "resources\\images";
		
		MultipartRequest mRequest = new MultipartRequest(request, filePath, maxSize, "UTF-8", new MyFileRenamePolicy());

		String storeContent = mRequest.getParameter("storeContent");
		String code = mRequest.getParameter("code");
		
		String phone1 = mRequest.getParameter("phone1");
		String phone2 = mRequest.getParameter("phone2");
		String phone3 = mRequest.getParameter("phone3");
		
		String phone = phone1 + "-" + phone2 + "-" + phone3;
	
		String post = mRequest.getParameter("post");
		
		String address1 = mRequest.getParameter("address1");		
		String address2 = mRequest.getParameter("address2");
		
		String address = address1 + ", " + address2; 
		
		Store store = new Store(storeContent, phone, code, address, post);
		
		List<StoreImg> fList = new ArrayList<StoreImg>();
		
		Enumeration<String> files = mRequest.getFileNames();
		// 폼에서 전송된 파일들의 name 속성값을 반복접근하는 객체
		
		StoreImg temp = null;
		while(files.hasMoreElements()) {
			// files.hasMoreElements(() : 다음 반복 접근할 요소가 있으면 true
			String name = files.nextElement();
			// files.nextElement() : 다음 요소를 얻어옴.  -> name에는 "img4" 이런식으로 name 속성값 저장됨
			
			// 요청 객체 중 전달받은 name 속성을 가진 요소가 있을 경우
			// (파일이 지정된 name 속성을 가진 input태그에 잘 올려져 있는가?)
			if(mRequest.getFilesystemName(name) != null) {
				// mRequest.getFilesystemName(name)
				// -> name 속성값과 일치하는 input 태그 요소에
				//	  작성된 파일명을 변환한 (rename)한 값을 반환 
				temp = new StoreImg();
				
				temp.setOriginImgName(mRequest.getOriginalFileName(name));
				temp.setRealImgName(mRequest.getFilesystemName(name));
				
				int fileLevel = 0;
				switch(name){
				case "img1" : fileLevel = 0 ; break; 
				case "img2" : fileLevel = 1 ; break; 
				case "img3" : fileLevel = 2 ; break; 
				case "img4" : fileLevel = 3 ; break; 
				}
				
				temp.setFileLevel(fileLevel);
				
				temp.setFilePath(filePath);
				
				fList.add(temp);
				
			}
		}
		
		
		System.out.println("이미지 : " + fList);
		
		String[] menuName = mRequest.getParameterValues("menuName");
		String[] menuPrice = mRequest.getParameterValues("menuPrice");
		String[] menuContents = mRequest.getParameterValues("menuContents");
		String[] menuMakeTime = mRequest.getParameterValues("menuMakeTime");
		
		List<String> list = new ArrayList<String>();
		List<StoreMenu> menu = new ArrayList<StoreMenu>();
		
		int tmp = 0;
		for(int i=0; i < (menuName.length * 4); i++) {
			if(i % 4 == 0) {
				list.add(i, menuName[tmp]);
			}else if(i % 4 == 1) {
				list.add(i, menuPrice[tmp]);
			}else if(i % 4 == 2) {
				menuContents[tmp] = menuContents[tmp].length() == 0 ? " " : menuContents[tmp];
				list.add(i, menuContents[tmp]);
			}else if(i % 4 == 3) {
				menuMakeTime[tmp] = menuMakeTime[tmp].length() == 0 ? " " : menuMakeTime[tmp];
				list.add(i, menuMakeTime[tmp]);
				tmp++;
			}
		}
		
		
		if(list != null) {
			for(int i=0; i < list.size()/4 ; i++) {
				StoreMenu storeMenu = new StoreMenu(menuName[i], Integer.parseInt(menuPrice[i]), menuContents[i], menuMakeTime[i]);	
				menu.add(storeMenu);
			}
		}
		
		String status = null;
		String msg = null;
		String text = null;
		
		try {
			HttpSession session = request.getSession();
			Store loginStore = (Store)session.getAttribute("loginStore");
			int storeNo = ((Store)session.getAttribute("loginStore")).getStoreNum();
			
			int result = new StoreService().updateStore(store, fList, menu, storeNo);
			
			if(result > 0) {
				status = "success";
				msg = "가게 정보 수정 성공";
				text = "가게 정보가 성공적으로 수정되었습니다.";
				
				
				loginStore.setStoreContent(store.getStoreContent());
				loginStore.setStoreGroupName(store.getStoreGroupName());
				
//				response.sendRedirect("storeModify.do");
				
				
			}else {
				status = "error";
				msg = "가게 정보 수정 실패";
				text = "다시 시도해주세요!";
			}
			
			request.getSession().setAttribute("status", status);
			request.getSession().setAttribute("msg", msg);
			request.getSession().setAttribute("text", text);
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		doGet(request, response);
	}
	
	
	
	

}
