<%@page import="com.kh.realgood.board.model.dto.Board"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.kh.realgood.store.model.dto.StoreInfoMenu"%>
<%@page import="com.kh.realgood.store.model.dto.Store"%>
<%@page import="java.util.List"%>
<%
	List<StoreInfoMenu> mList = (List<StoreInfoMenu>)request.getAttribute("mList");
	int storeNo = Integer.parseInt(request.getParameter("storeNum"));
	
	int tmp = ((Member)session.getAttribute("loginMember")) != null ? ((Member)session.getAttribute("loginMember")).getNo() : -1;
	int starColor = session.getAttribute("starColor") == null ? 0 : (Integer)(session.getAttribute("starColor"));
	
	
	List<Board> boardList = (List<Board>)request.getAttribute("boardList");

	
	StoreInfoMenu storeInfoList = (StoreInfoMenu)request.getAttribute("storeInfoList");

%>

<!doctype html>
<html>
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Jekyll v4.0.1">
    <title>상세페이지 </title>

    <link rel="canonical" href="https://getbootstrap.com/docs/4.5/examples/blog/">

    <!-- Bootstrap core CSS -->
    <link type="text/css" href="<%=request.getContextPath()%>/resources/css/bootstrap.css" rel="stylesheet">
	<link type="text/css" href="<%=request.getContextPath()%>/resources/css/blog.css" rel="stylesheet">
	
    <style>
      .bd-placeholder-img {
        font-size: 1.125rem;
        text-anchor: middle;
        -webkit-user-select: none;
        -moz-user-select: none;
        -ms-user-select: none;
        user-select: none;
      }

      @media (min-width: 768px) {
        .bd-placeholder-img-lg {
          font-size: 3.5rem;
        }
        
       #totalSum{
       	 display : inline-block;
       }
       
       span{
       	 font-weight: bold;
       }
      }
      
      #favoBtn{
      	cursor : pointer;
      }
      
              #searchForm>*{
            top : 0;
        }
        
       
       .pagination {
            justify-content: center;
        }
        #searchForm{
            position: relative;
        }

        #searchForm>*{
            top : 0;
        }
        
        .boardTitle > img{
           width: 50px;
           height: 50px;
        }
      
    </style>
    
    
    
    <!-- Custom styles for this template -->
    <link href="https://fonts.googleapis.com/css?family=Playfair+Display:700,900" rel="stylesheet">
    <!-- Custom styles for this template -->
  </head>
  <body>
  	<%@ include file="../common/header.jsp"%>
  
    <div class="container">
  <header class="">
    
  </header>

	<%
	String color = "";
	if (starColor > 0) {
	    color = "rgb(255, 168, 0)";
	}else{
	    color = "black";
	}	
	%>
	
	
	
    <% if(storeInfoList != null){ %>
    
  <div class="row" style="flex-wrap: nowrap;">
    <div class="col-md-8" style="margin: 15px 10% 0 -5% ">
      <div class="row no-gutters border rounded overflow-hidden flex-md-row mb-4 shadow-sm h-md-250 position-relative">
        <div class="col p-4 d-flex flex-column position-static" >
          <h3 class="mb-0"></h3>
          <input type="hidden" value="<%=storeInfoList.getStoreImgNum() %>">
        </div>
      </div>
    </div>
    <div class="map-image" style="margin-top: 15px">
      <div class="row no-gutters border rounded overflow-hidden flex-md-row mb-4 shadow-sm h-md-250 position-relative">
        <div class="col p d-flex flex-column position-static">
        
            <div id="map" style="width:400px;height:300px;" ></div> <!--지도-->
   
        </div>
      </div>
    </div>
  </div>
</div>

	  <div class="row" style="flex-wrap: nowrap; margin-left: 15%">
	
    <div class="col-md-6 blog-main" style="display: inline-block;">
      <h3 class="pb-4 mb-4 font-italic border-bottom">
       	<%=storeInfoList.getStoreName() %>
      </h3>

      <div class="blog-post" >
        <h2 class="blog-post-title"></h2>
        <p class="blog-post-meta"><%=storeInfoList.getStoreContent() %> </p>
           
       <hr>
       
        <p >조회수</p>

          

		<table class="table">
		
 
	        
				<tr>
				    <th width="100px">주소</th>
				    <td><%=storeInfoList.getStoreAddress() %></td>  
				</tr>
				<tr>
				    <th>전화번호</th>
				    <td><%=storeInfoList.getStoreTel() %></td>                      
				</tr>
				<tr>
				    <th>음식종류</th>
				    <td><%=storeInfoList.getGroupName()%></td>             
				</tr>
				<tr>
				    <th>가격대</th>
				    <td><%=storeInfoList.getPriceMin()%>~
				    <%=storeInfoList.getPriceMax() %> 원
				    </td>                      
				</tr>
		<% } %>
		</table>
		
		<hr>
         
         <% if(loginMember != null) {%> 
	        <button type="button" class="btn btn-primary float-right" id="insertBtn" onclick="location.href = '../board/insertForm.do?storeNum=<%=storeNo%>';">리뷰 작성</button>
         <% } %> 
          <br><br><br><br>      
               
	            
	<% if (boardList != null){ %>
      <div id="tmp-area"></div>      

		<% if(boardList.size() > 10) { %>
		<button type="button" id="tmp">↓</button>
		<% } %>
		
		<script>
		var bList = [];

			<% for(Board b : boardList){ %>
				<%-- console.log(JSON.parse('<%=b%>')); --%>
				bList.push(JSON.parse('<%=b%>'));
			<% } %>

		//console.log(bList);
		var loopTmp = 0;
		var tmpSize = 1;
		var bSize = bList.length;
		
		if(bList.length != 0){
			for(var i=0; i < 10; i++){
				if(bSize <= i){
					   	tmpSize = i;
						break;
				}
				var $div = $("<div>");
				var $p = $("<p>");
				var $input = $("<input type=\"hidden\" value=\"" + bList[i].boardNo + "\">");
			    $p.addClass("pclass");

			   	$p.text(bList[i].nickName +  "        11111111111111                  "  + bList[i].boardContent +"1111111111111"+ bList[i].boardModifyDate);
			   	$("#tmp-area").append($div.append($p, $input));
			}
		}
		
		
		 $("#tmp").on("click",function(){
			 if(tmpSize != bSize){
			   tmpSize = ++tmpSize * 10;
			   for(var i=10*(++loopTmp)+1; i < tmpSize; i++) {
				   if(bSize == i){
					   tmpSize = i;
						break;
				   }
				   var $div = $("<div>");
				   var $p = $("<p>");
				   var $input = $("<input type=\"hidden\" value=\"" + bList[i].boardNo + "\">");
				   $p.addClass("pclass");
				   
				   $p.text(bList[i].nickName +  "글자"  + bList[i].boardContent + bList[i].boardModifyDate);
			       $("#tmp-area").append($div.append($p, $input)); 
			   }
		 }
		   }); 
		
</script>       

    <script>
     $("div").on("click",".pclass", function(){
	        
	        var name = $(this).parent().children().eq(1).val();
	        console.log(name);
	
	        location.href="<%=request.getContextPath()%>/board/reviewCheck.do?boardNo="+name+"&storeNum=<%=request.getParameter("storeNum")%>";        
			
	     }).on("mouseenter", function(){
	      $(this).parent().css("cursor","pointer");
	   });
	  
	  
	  </script>
	<%} %>
	            
        <br><br><br><br><br><br>
        
        
    
    
    

    

          

          
      </div>
  </div><!-- /.row -->
  
  
  <div class="col-md-3" style="max-width: 33%; display: inline-block; margin-left: 6%">
      <div class="p-4 mb-3 bg-light rounded">
        <h4 class="font-italic border-bottom">메뉴판</h4>
        <div id="menuCal" class="mb-0"></div>
        <span>총합 </span>
        <div id="totalSum"></div>
      </div>

      <div class="p-4">
        <h4 class="font-italic">메뉴 선택</h4>
        </div>
          <select class="custom-select" id="inputGroupSelect01">
            <option>메뉴를 선택해주세요.</option>
            <%if(!mList.isEmpty()) { %>
	          	<%for(int i = 0; i < mList.size(); i++ ) {%>
	              <option value="<%=mList.get(i).getMenuNum()%>"><%=mList.get(i).getMenuName() + "-" + mList.get(i).getPrice()+ "원"%></option>
	          	<%}%>
          	<%} else {%>
          		  <option>메뉴없음</option>
          	<%}%>
           
          </select>

         
          <div style="text-align: center;">
            <br><br><br><br><br><br><br><br>
            <button type="button" id="buyPurchase" class="btn btn-warning" >구매하기</button>
          </div>
  
  	</div>

</div>

          <script type="text/javascript">

  		$("#buyPurchase").click(function() {
			// 팝업창을 이용하여 아이디 유효성, 중복검사 진행
			<% if(loginMember != null) {%>
				window.open("buyPurchaseForm.do?storeNum="+<%=request.getParameter("storeNum")%>+"&memberId="+"<%=loginMember.getId()%>", "buyPurchase", "width=850, height=550");
				// 팝업창 요청 주소		팝업창의 이름(name)		팝업창 크기 설정  << 새로 입력 되는 것들
			<% } else { %>
				swal("로그인 이후 이용 바랍니다.");
			<% } %>
		});
          </script>
  
  
    <script>
          
          	var priceSum;
          	$("#inputGroupSelect01").on("change",function(){
          		var $p = $("<p>").text($("#inputGroupSelect01>option:checked").text());
          		var $minus = $("<button>").addClass("minusBtn").text("-");
          		var $span = $("<span>").text(1);
          		var $plus = $("<button>").addClass("plusBtn").text("+");
          		var $delete = $("<button>").addClass("deleteBtn").text("x");
          		$p.append($minus, $span, $plus, $delete);
          		
          		$("#menuCal").append($p);
          		$("#inputGroupSelect01>option").eq(0).prop("selected", true);
          		$("#inputGroupSelect01").blur();

          		totalSum();
          	});
          	
			function totalSum(){
				$menu = $("#menuCal > p"); // 선택된 메뉴를 모두 얻어옴
				
				// 메뉴 모양 -->  탕수육-11500원
				var sum = 0;
				if (priceSum == null) priceSum = 0;
				
				$menu.each(function(index, item){ // 메뉴 반복 접근
					var start = $(item).text().indexOf("-")+1;
					var end = $(item).text().indexOf("원");
					
					sum += new Number($(item).text().substring(start, end));
				});
				
				var $p = $("<p>").text(sum + priceSum);
				/* $p.attr({"name":"totalSum"}); */
				
				//$menu.eq($menu.length-1).remove();
          		$("#totalSum").html($p);
				
			} 
			
			
			// 동적으로 추가된 플러스 버튼 클릭 시
			$(document).on("click", ".plusBtn", function(){
				var count = $(this).prev().text();
				$(this).prev().text(new Number(count)+1);
				
				var selectMenu = $(this).parent().text();
				var start = selectMenu.indexOf("-")+1;
				var end = selectMenu.indexOf("원");
				
				var price = new Number(selectMenu.substring(start, end));
				if (priceSum == null) priceSum = 0;
				priceSum += price;
								
				var sum = new Number($("#totalSum > p").text());
				
				$("#totalSum > p").text(sum + price);
			});
			
			
			// 동적으로 추가된 마이너스 버튼 클릭 시
			$(document).on("click", ".minusBtn", function(){
				var count = $(this).next().text();
				if(count > 1){
					$(this).next().text(new Number(count)-1);
				
					var selectMenu = $(this).parent().text();
					
					var start = selectMenu.indexOf("-")+1;
					var end = selectMenu.indexOf("원");
					
					var start2 = selectMenu.indexOf("원")+2;
					var end2 = selectMenu.indexOf("+");
					
					var price = new Number(selectMenu.substring(start, end));
					var priceCount = new Number(selectMenu.substring(start2, end2));
					
					if(priceSum != null && priceSum != 0) 
						priceSum -= price * (priceCount);
									
					var sum = new Number($("#totalSum > p").text());
					
					$("#totalSum > p").text(sum - price);
				}
			});
			
			// 동적으로 추가된 삭제 버튼 클릭 시
			$(document).on("click", ".deleteBtn", function(){
				
				var selectMenu = $(this).parent().text();
				var start = selectMenu.indexOf("-")+1;
				var end = selectMenu.indexOf("원");
				
				var start2 = selectMenu.indexOf("원")+2;
				var end2 = selectMenu.indexOf("+");
				
				var price = new Number(selectMenu.substring(start, end));
				var priceCount = new Number(selectMenu.substring(start2, end2));
				
				if(priceSum != null && priceSum != 0) 
					priceSum -= price * (priceCount-1);
				$(this).parent().text("");
				totalSum();
				
			});
          
          </script>
          
           <script>
     
       	$("#favoBtn").on("click", function(){
      		// 비동기 통신으로 회원번호,가게 정보 저장
      		$.ajax({
      			url: "favorite.do",
      			data : {"storeNo": <%=storeNo%>, "memberNo": <%=tmp%>},
      			success : function(rNo){
      				if(rNo == 1) {
	      				alert("즐겨찾기에 추가되었습니다. [마이페이지->내가 즐겨찾는 가게]에서 확인하세요.");
      					$("#favoBtn").css("color", "rgb(255, 168, 0)");
      				}else if(rNo == 0){
      					alert("즐겨찾기가 삭제되었습니다.");
      					$("#favoBtn").css("color", "black");
      				}else if(rNo == -1){
      					alert("알 수 없는 에러 발생 하였습니다.");
      				}else{
      					alert("즐겨찾기 저장에 실패했습니다. 로그인 후 다시 시도해주세요.")
      				}
      				
      			}, error : function(str){
      				console.log("ajax통신실패");
      			}
      		});
      		
      	});
      
      </script>
  
  
  
  
  
  <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=c97c952ea049cff96693a7ef6202037d&libraries=services"></script>
    
    
    <script>
    
    var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
    mapOption = {
        center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
        level: 3 // 지도의 확대 레벨
    };  

	// 지도를 생성합니다    
	var map = new kakao.maps.Map(mapContainer, mapOption); 
	
	// 주소-좌표 변환 객체를 생성합니다
	var geocoder = new kakao.maps.services.Geocoder();
	
	// 주소로 좌표를 검색합니다
	geocoder.addressSearch('서울 서대문구 연세로 36', function(result, status) {

    // 정상적으로 검색이 완료됐으면 
     if (status === kakao.maps.services.Status.OK) {

        var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

        // 결과값으로 받은 위치를 마커로 표시합니다
        var marker = new kakao.maps.Marker({
            map: map,
            position: coords
        });
        

        // 인포윈도우로 장소에 대한 설명을 표시합니다
        var infowindow = new kakao.maps.InfoWindow({
            content: '<div style="width:150px;text-align:center;padding:6px 0;">독수리다방</div>'
        });
        infowindow.open(map, marker);

        // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
        map.setCenter(coords);
    } 
});    
    
    
    </script>
  
  

</main><!-- /.container -->

  <%@ include file="../common/footer.jsp" %>
</body>
</html>
