<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.kh.realgood.board.model.dto.Attachment"%>
<%@page import="java.util.List"%>
<%@page import="com.kh.realgood.store.model.dto.StoreInfoMenu"%>
<%@page import="com.kh.realgood.board.model.dto.Board"%>
<%

Board reviewList = (Board)request.getAttribute("reviewList");

List<Attachment> fileList = (List<Attachment>)request.getAttribute("fileList");
%>    
  
<!doctype html>
<html>
  <head>
      <%@ include file="../common/header.jsp"%>
  
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Jekyll v4.0.1">
    <title>리뷰 상세페이지</title>

    <link rel="canonical" href="https://getbootstrap.com/docs/4.5/examples/pricing/">
   	<link type="text/css" href="<%=request.getContextPath()%>/resources/css/bootstrap.css" rel="stylesheet">
	<link type="text/css" href="<%=request.getContextPath()%>/resources/css/pricing.css" rel="stylesheet">
				

	
    <style>
      .bd-placeholder-img {
        font-size: 1.125rem;
        text-anchor: middle;
        -webkit-user-select: none;
        -moz-user-select: none;
        -ms-user-select: none;
        user-select: none;
      }
      
      img{
        float: left;
      }



      @media (min-width: 768px) {
        .bd-placeholder-img-lg {
          font-size: 3.5rem;
        }
      }

      .my-0 font-weight-normal{
        display: block;
      }

      li{
        text-align: left;
      }
     .ReviewCard__UserInfo{
      display: flex;
      }

      .container{
        margin-top: 40px;
      }
      
      #board-area{ margin-bottom:100px;}
	#board-content{ padding-bottom:150px;}

	.boardImgArea{
		height: 300px;
	}

	.boardImg{
		width : 100%;
		height: 100%;
		
		max-width : 300px;
		max-height: 300px;
		
		margin : auto;
	}
	
	/* 이미지 화살표 색 조정
	-> fill='%23000' 부분의 000을
	   RGB 16진수 값을 작성하여 변경 가능 
	 */
	.carousel-control-prev-icon {
 		background-image: url("data:image/svg+xml;charset=utf8,%3Csvg xmlns='http://www.w3.org/2000/svg' fill='%23000' viewBox='0 0 8 8'%3E%3Cpath d='M5.25 0l-4 4 4 4 1.5-1.5-2.5-2.5 2.5-2.5-1.5-1.5z'/%3E%3C/svg%3E") !important;
	}
	
	.carousel-control-next-icon {
  		background-image: url("data:image/svg+xml;charset=utf8,%3Csvg xmlns='http://www.w3.org/2000/svg' fill='%23000' viewBox='0 0 8 8'%3E%3Cpath d='M2.75 0l-1.5 1.5 2.5 2.5-2.5 2.5 1.5 1.5 4-4-4-4z'/%3E%3C/svg%3E") !important;
	}
	
	.replyWrite > table{
		width: 90%;
		align: center;
	}
	
	#replyContentArea{ width: 90%; }
	
	#replyContentArea > textarea{
	    resize: none;
    	width: 100%;
	}
	
	#replyBtnArea{
	    width: 100px;
	    text-align: center;
	}
	
	.rWriter{ margin-right: 30px;}
	.rDate{
		font-size: 0.7em;
		color : gray;
	}
	
	#replyListArea{
		list-style-type: none;
	}
      
    </style>
  </head>
  <body>


     	<% if(reviewList != null) { %>
     	
	<div class="container" style="width: 650px">
	  <div class="card-deck mb-3 text-center">
	    <div class="card mb-4 shadow-sm">
	      <div class="card-header" >
	        <div class="ReviewCard__UserInfo">
	            <span class="ReviewCard__UserName" style=""> 작성자 :  <%=reviewList.getNickName() %></span>
	
	          <div class="ReviewCard__CountInfo" style="float: right;" >
	            <div class="ReviewCard__UserReviewCountInfo" "><br>
	              <span class="ReviewCard__UserReviewCount">작성일 : <%=reviewList.getBoardModifyDate() %></span>
	            </div>
	
	            <div class="ReviewCard__UserFollowerCountInfo">
	              <span class="ReviewCard__UserFollowerCount"></span>
	            </div>
	          </div>
	        </div>
	      </div>
      
      <div class="card-body" >
        <h6 class="card-title pricing-card-title" style="float: left;">조회수 :  <%=reviewList.getReadCount() %> <small class="text-muted"></small></h6>
        <div class="list-unstyled mt-3 mb-4"  style="float: left;" style="margin-top: 20px">
          <div style="float: left;">
       		
        		<%=reviewList.getBoardContent()%>
        	
	      </div>
	    </div>
        <% if(fileList != null){ %>
		<div class="carousel slide m-3" id="carousel-325626">
                    
                    <div class="carousel-inner boardImgArea" style="width: 200px; height: 150px">
                     <% 
	                   		String src = null;
	                    	boolean flag = true;
                   			for(int i=0; i<4 ; i++) {
                   			 for(Attachment at : fileList){
	                    	  if(at.getFileLevel() == i){
	                    		 src = request.getContextPath()+"/resources/uploadImages/"+at.getFileChangeName();
	                    		 String imgClass = "carousel-item";
	                    	   
	                    		 if(flag){
	                    			imgClass += " active";
	                    			flag=false;
	                    		 }
                 	   %> 	  
	                    	  <div class="<%= imgClass%>">
	                          <img class="d-block w-100 boardImg" src="<%= src %>" />
	                          <input type="hidden" value=<%=fileList.get(i).getFileNo() %>>
	                         </div> 
	                    	
	                    <%  } } } %>
	        
	     			 </div>
	     			 
	     			 
	     			 
                    <a class="carousel-control-prev" href="#carousel-325626" data-slide="prev"><span class="carousel-control-prev-icon"></span> <span class="sr-only">Previous</span></a> <a class="carousel-control-next" href="#carousel-325626" data-slide="next"><span class="carousel-control-next-icon"></span> <span class="sr-only">Next</span></a>
                </div>
                <% } %>
		    </div>
		</div>
	</div>
</div>

	<div class="test" style="display: inline-block; margin-left: 53%">
			<% if(loginMember != null && (reviewList.getNickName().equals(loginMember.getNickName()  ))) {%>
			<a href="delete?no=" class="btn btn-primary float-right">삭제</a> 
			<a href="updateForm?no=" class="btn btn-primary float-right ml-1 mr-1">수정</a>
			<% } %>
			<a href="<%=request.getContextPath()%>/store/detail.do?storeNum=<%=request.getParameter("storeNum")%>" class="btn btn-primary float-right">목록으로</a>
			
				
	</div>  	
	<div style="display: inline-block; margin-left: 58%" >
<%-- 	<a href="<%=request.getContextPath()%>/store/detail.do?storeNum=<%=request.getParameter("storeNum")%>">상세 페이지로 돌아가기</a>--%>   	<%}%>
   	
   	</div>
   	<br><br><br><br><br>
	
	
	
	
		<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
		integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"
		integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI"
		crossorigin="anonymous"></script>
		    <%@ include file="../common/footer.jsp"%><br>
		
</body>
</html>


