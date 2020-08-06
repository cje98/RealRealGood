<%@page import="com.kh.realgood.store.model.dto.Store"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% 
	List<Store> aList = (List<Store>)request.getAttribute("aList");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 승인/대기</title>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<style>
	 /* 팝업 레이어 */
  .idSelect {
     cursor: pointer;
  }
  
  .popupLayer {
     position: absolute;
     display: none;
     background-color: #ffffff;
     border: solid 2px #d0d0d0;
     width: 400px;
     height: 500px;
     padding: 10px;
  }
  .popupLayer div:nth-of-type(1) {
     position: absolute;
     top: 5px;
     right: 5px
  }
	
</style>
</head>
<body>
    <%@ include file="../common/header.jsp"%>
    <div class="row my-5">
        <%@ include file="../common/adminSideMenu.jsp"%>
        <div class="col-sm-5">
            <%-- <form action="<%=request.getContextPath()%>/store/adminApprove.do" method="POST"> --%>
                    <h1>관리자 승인/대기</h1>
                    <div class="row mb-3 form-row">
                  <table class="table table-hover">
                        <tr>
                            <td>체크여부</td>
                            <td>No.</td>
                            <td>가게명</td>
                            <td>가게주인아이디</td>
                            <td>승인 대기</td>
                        </tr>
                        <%if( aList.isEmpty()){ %>
                        <tr>
                        	<td cols="5">승인할 내역이 없습니다.</td>
                        </tr>
                        <% } else{ %>
	                        <% for(int i = 0; i < aList.size(); i++){ %>
	                        <tr>
	                            <td><input type="checkbox" name="adminStore"></td>
	                            <td><%= aList.get(i).getStoreNum() %></td>
	                            <td><%= aList.get(i).getStoreName()%></td>
	                            <td class="idSelect"><%= aList.get(i).getId() %></td>
	                            <td><%= aList.get(i).getUseYN() %></td>
	                        </tr>
	                        <% } %>
                        <% } %>
                </table>
                    </div>
                <button id="adminBtn" class="btn btn-primary">승인</button>
                                   전체<input id="checkAll" type="checkbox" name="check" >
            <!-- </form> -->
        </div>
    </div>
    
     <%@ include file="../common/footer.jsp"%>
    <div class="popupLayer">
     <div> <!-- x버튼 영역 -->
        <button type="button" class="close" onClick="closeLayer(this)">
           <span>x</span>
        </button>
     </div>
     <div id="storeInfo">
     		<h4 class="py-2"></h4>
     		<hr>
     		<p></p>
     		<p></p>
     		<p></p>
     		<p></p>
     		<p></p>
     		<p></p>
     		<p></p>
     		<p></p>
     		<p></p>
     </div>
   </div>
    <script>
    
    $("#adminBtn").on("click", function(){
    	
        var no = "";
        var noSum = "";
        var checkbox = $("input[name=adminStore]:checked");
		
		$.each(checkbox, function(index, item) {

		   var tr = checkbox.parent().parent().eq(index);
           var td = tr.children();
	
           no = td.eq(1).text();
           noSum += no + " ";
        });
        
           location.href = "<%=request.getContextPath()%>/store/storeAdmin.do?no=" + noSum;
        
    });

	    	
    
    
    $(document).ready(function(){
    	// 전체 선택 시 체크박스 모두 선택 표시
	    $("#checkAll").click(function(){
	        if($("#checkAll").is(":checked")){
	           $("input[name=adminStore]:checkbox").prop("checked", true);
	        } else {
	           $("input[name=adminStore]:checkbox").prop("checked", false);
	        }
	    });
	    
    });
    
    // 팝업 레이어
    
    $(document).on("click", ".idSelect" ,function(e){
		
		$.ajax({
			 url : "../store/selectStore.do", 
			data : {"id": $(this).text()},
			type : "post",
			dataType : "json",
			success : function(StoreInfoMenu){
				console.log(StoreInfoMenu);
				
				$("#storeInfo").text("");
				
				$h4 = $("<h4>").addClass("py-2").text(StoreInfoMenu.id);
				$hr = $("<hr>");
				$p1 = $("<p>").text("가게이름 : " + StoreInfoMenu.storeName);
				$p2 = $("<p>").text("가게설명 : " + StoreInfoMenu.storeContent);
				$p3 = $("<p>").text("가게번호 : " + StoreInfoMenu.storeTel);
				$p4 = $("<p>").text("가게주소 : " + StoreInfoMenu.storeAddress);
				$p5 = $("<p>").text("메뉴이름 : " + StoreInfoMenu.menuName);
				$p6 = $("<p>").text("가격 : " + StoreInfoMenu.price +"원");
				$p7 = $("<p>").text("걸리는 시간 : " + StoreInfoMenu.menuMakeTime);
				$p8 = $("<p>").text("메뉴설명 : " + StoreInfoMenu.menuContents);
				$p9 = $("<p>").text("가게등록일 : " + StoreInfoMenu.enrollDate);
				
				
				
				
				
				$("#storeInfo").append($h4, $hr, $p1, $p2, $p3, $p4, $p5, $p6, $p7, $p8, $p9);
				
				showLayer(e);
				
			}, error : function(){
				console.log("ajax 통신 실패");
			}				
		});
		
	});
	
  // 팝업 레이어 보여주기
  function showLayer(e){

     var divTop = e.pageY; // 이벤트가 발생한 page y좌표
     var divLeft = e.pageX; // 이벤트가 발생한 page x좌표

     $('.popupLayer').css({
        "top": divTop,
        "left": divLeft,
        "position": "absolute"
     }).show();
  }
  
  
  // 팝업 레이어 닫기 버튼
  function closeLayer( obj ) {
     $(obj).parent().parent().hide();
  }
	
    
    
    
    
    </script>
   
    
</body>
</html>