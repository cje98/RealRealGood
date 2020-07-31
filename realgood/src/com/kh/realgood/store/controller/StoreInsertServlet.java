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
import com.kh.realgood.member.model.dto.Member;
import com.kh.realgood.store.model.dto.Store;
import com.kh.realgood.store.model.dto.StoreImg;
import com.kh.realgood.store.model.dto.StoreMenu;
import com.kh.realgood.store.model.service.StoreService;
import com.oreilly.servlet.MultipartRequest;

@WebServlet("/store/storeInsert.do")
public class StoreInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = "/WEB-INF/views/store/storeInsert.jsp";
		RequestDispatcher view = request.getRequestDispatcher(path);
		view.forward(request, response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// [전송된 파일 저장하기]
		int maxSize = 1024 * 1024 * 10; //10MB -> 전송 파일의 크기(용량)을 제한한다.
		
		String root = request.getSession().getServletContext().getRealPath("/");
		// C:\workspace\semiproject\realgood_200724장영인\WebContent\
		
		// images 폴더 위치 추출
		String filePath = root + "resources\\images";
		
		MultipartRequest mRequest = new MultipartRequest(request, filePath, maxSize, "UTF-8", new MyFileRenamePolicy());
			
			
		// [파일 외 정보들 얻어오기 - enctype이 multipart이므로 MultipartRequest.getParameter()를 사용한다.]
		String storeName = mRequest.getParameter("storeName");
		String corNum1 = mRequest.getParameter("corNum1");
		String corNum2 = mRequest.getParameter("corNum2");
		String corNum3 = mRequest.getParameter("corNum3");
		
		String corNum = corNum1 + "-" + corNum2 + "-" + corNum3;
		
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
		
		
		Store storeInfo = new Store(corNum, storeName, storeContent, phone, code, address, post);
		
		System.out.println(storeInfo);
		
		// 이미지 파일 정보를 저장할 List 생성
		List<StoreImg> fList = new ArrayList<StoreImg>();
		

		// MultipartRequest에 담겨있는 파일 정보를 하나씩 꺼내옴
		// Enumeration : Iterator 과거 버전
		// Iterator : 컬렉션 객체에 저장된 요소 반복 접근자
		Enumeration<String> files = mRequest.getFileNames(); // 전송된 파일의 name 속성 값을 모두 반환 -> 파일이 역순으로 반환됨.
		
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
				
				// name 속성에 따라 파일 레벨 지정
				int fileLevel = 0;
				switch(name) {
				case "img1" : fileLevel = 0; break;
				case "img2" : fileLevel = 1; break;
				case "img3" : fileLevel = 2; break;
				case "img4" : fileLevel = 3; break;
				}
				
				temp.setFileLevel(fileLevel);
				temp.setFilePath(filePath);
				// 파일이 저장되어있는 경로 추가
				fList.add(temp);
			}
			
		}
		
	
		
		//----
		// 메뉴 추가
		String[] menuName = mRequest.getParameterValues("menuName");
		String[] menuPrice = mRequest.getParameterValues("menuPrice");
		String[] menuContents = mRequest.getParameterValues("menuContents");
		String[] menuMakeTime = mRequest.getParameterValues("menuMakeTime");
		
		
		// 넘어온 변수들이 String,int형이 아니기때문에 vo를 불러서 담을 수없음
		List<String> list = new ArrayList<String>();
		List<StoreMenu> list2 = new ArrayList<StoreMenu>();
		
		System.out.println(menuName);
		// i는 list의 인덱스, tmp는 행 역할
		int tmp = 0;
		for(int i=0; i < (menuName.length * 4); i++) {
			if(i % 4 == 0) {
				list.add(i, menuName[tmp]);
				
			}else if(i % 4 == 1){
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
		
		
		System.out.println(list.size());
		
		
		
		
		String status = null;
		String msg = null;
		String text = null;
		
		try {
			
			HttpSession session = request.getSession();
			String id = ((Member)session.getAttribute("loginMember")).getId();
			
			int result = new StoreService().storeInsert(storeInfo, id);
			
			int result2 = new StoreService().imgInsert(fList, result);
			
			if(!list.isEmpty()) {
				for(int i=0; i < (list.size()/4); i++) {
					StoreMenu storeMenu = new StoreMenu(menuName[i], Integer.parseInt(menuPrice[i]), menuContents[i], menuMakeTime[i]);
					storeMenu.setStoreNo(result);
					list2.add(storeMenu);
				}
				
			}
			
			int result3 = new StoreService().menuInsert(list2);
			
			if(result > 0 && result2 > 0 && result3 > 0) {
				
				status = "success";
				msg = "가게 정보 등록";
				text = "가게 정보가 등록되었습니다.";
				
				
			}else {
				status = "error";
				msg = "가게 등록 실패";
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
