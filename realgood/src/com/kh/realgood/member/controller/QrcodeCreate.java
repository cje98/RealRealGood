package com.kh.realgood.member.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.kh.realgood.member.model.dto.Member;
import com.kh.realgood.member.model.service.MemberService;

@WebServlet("/member/qrcodeCreate.do")
public class QrcodeCreate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// qr코드값 가져오기
		String qrNum = request.getParameter("qrNum");
		// 아이디값 확인하기 - 기본키이용 (세션값을 통해 현재 로그인한 사람이 조회한 qr코드가 맞는지를 통한 검증을 위해)
		int loginMemberNum = ((Member)request.getSession().getAttribute("loginMember")).getNo();
		
		
        try {
        	// qr코드 값을 이용하여 메뉴 번호 받아오기
        	int buyNum = new MemberService().selectMenuNum(qrNum, loginMemberNum);
        	
        	if(buyNum > 0) {
	        	String writePath = request.getSession().getServletContext().getRealPath("/resources/qrcode");
	            File file = null;
	            // 큐알이미지를 저장할 디렉토리 지정
	            file = new File(writePath);
	            if(!file.exists()) {
	                file.mkdirs();
	            }
	            // 코드인식시 링크걸 URL주소
	            String codeurl = new String(qrNum.getBytes("UTF-8"), "ISO-8859-1");
	            // 큐알코드 바코드 색상값 - 블랙
	            int qrcodeColor =   0xff0c0702;
	            // 큐알코드 배경색상값 - 화이트
	            int backgroundColor = 0xFFFFFFFF;
	             
	            QRCodeWriter qrCodeWriter = new QRCodeWriter();
	            // 3,4번째 parameter값 : width/height값 지정
	            BitMatrix bitMatrix = qrCodeWriter.encode(codeurl, BarcodeFormat.QR_CODE,200, 200);
	            //
	            MatrixToImageConfig matrixToImageConfig = new MatrixToImageConfig(qrcodeColor,backgroundColor);
	            BufferedImage bufferedImage = MatrixToImageWriter.toBufferedImage(bitMatrix,matrixToImageConfig);
	            // ImageIO를 사용한 바코드 파일쓰기
	            ImageIO.write(bufferedImage, "png", new File(writePath + "/" + buyNum + ".png"));
	            
	            request.getSession().setAttribute("qrImg", buyNum+".png");
	            
	            String path = "/WEB-INF/views/member/qrCodePrint.jsp";
				RequestDispatcher view = request.getRequestDispatcher(path);
				view.forward(request, response);
        	} else {
				String msg = "조회 실패!";
				String status = "error";
				String text = "존재하는 qr코드 번호가 없습니다.";
				
				request.getSession().setAttribute("msg", msg);
				request.getSession().setAttribute("status", status);
				request.getSession().setAttribute("text", text);
				response.sendRedirect(request.getHeader("referer"));
        	}
             
        } catch (Exception e) {
            e.printStackTrace();
        }  

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
