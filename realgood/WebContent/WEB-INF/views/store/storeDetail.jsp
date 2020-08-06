<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.kh.realgood.store.model.dto.StoreImg"%>
<%@page import="com.kh.realgood.board.model.dto.Board"%>
<%@page import="com.kh.realgood.store.model.dto.StoreInfoMenu"%>
<%@page import="com.kh.realgood.store.model.dto.Store"%>
<%@page import="java.util.List"%>
<%
   List<StoreInfoMenu> mList = (List<StoreInfoMenu>)request.getAttribute("mList");
   int storeNo = Integer.parseInt(request.getParameter("storeNum"));
   
   int tmp = ((Member)session.getAttribute("loginMember")) != null ? ((Member)session.getAttribute("loginMember")).getNo() : -1;
   int starColor = session.getAttribute("starColor") == null ? 0 : (Integer)(session.getAttribute("starColor"));
   
   
   List<Board> boardList = (List<Board>)request.getAttribute("boardList");

   
   StoreInfoMenu storeInfo = (StoreInfoMenu)request.getAttribute("storeInfoList");

   
   List<StoreImg> storeImgList = (List<StoreImg>)request.getAttribute("storeImgList");

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
   <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
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
        
        /* 즐겨찾기 별모양 */
        .icon-hook:after {
        content: "";
        top: 0;
        left: 0;
        width: 100%;
        height: 100%;
      }
      
      .icon-hook > svg {
        pointer-events: none;
      }
      
      .star-dashes-1 {
        opacity: 0;
      }
      
      .star-check-1 {
        opacity: 1;
        stroke-dasharray: 22;
        stroke-dashoffset: 22;
        transform-origin: 50% 50%;
      }
      
      .active .star-dashes-1 {
        animation: flash-1 1s forwards;
      }
      
      .active .star-check-1 {
        animation: star-checked-1 1s forwards;
      }
      
      @keyframes flash-1 {
        50% {
          opacity: 1;
        }
      }
      
      @keyframes star-checked-1 {
        50% {
          stroke-dashoffset: 0;
        }
        75% {
          stroke-dashoffset: 0;
          transform: rotate(360deg) scale(1.5);
        }
        100% {
          stroke-dashoffset: 0;
        }
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

   <%-- <%
   String color = "";
   if (starColor > 0) {
       color = "rgb(255, 168, 0)";
   }else{
       color = "black";
   }   
   %> --%>
   
   
   
    <% if(storeInfo != null){ %>
    
          
        <% if(storeImgList != null){ %>
        <div class="row" style="flex-wrap: nowrap; width: 100%; height: 100%">
          <div class="col-md-11" style="margin: 25px 0 0 -15%" >
           
         <% 
           String src = null;
           for(int i=0; i<storeImgList.size() ; i++) {
             for(StoreImg si : storeImgList){
                if(si.getFileLevel() == i){
                  src = request.getContextPath()+"/resources/images/"+si.getRealImgName();
          %>      
          <div style="width: 400px; height: 300px; display: inline-block;">
             <img class="d-block w-100 boardImg" width="100%" height="100%" src="<%= src %>" />
             <input type="hidden" value=<%=storeImgList.get(i).getStoreImgNum()%>>
          </div> 
       <%  } } } %>
                <% }else{ %>
                                      
                   <img class="d-block w-100 boardImg" src="<%=request.getContextPath()%>/resources/images/맛집어때 로고.png" />
                   
              <%} %>
          
          
          
          
          
          
          
          
          
          
          <input type="hidden" value="<%=storeInfo.getStoreImgNum() %>">
    </div>
    <div class="map-image" style="margin: 25px 0 0 -5%">
      <div class="row no-gutters border rounded overflow-hidden flex-md-row mb-4 shadow-sm h-md-250 position-relative">
        <div class="col p d-flex flex-column position-static">
        
            <div id="map" style="width:400px;height:300px;" ></div> <!--지도-->
   
        </div>
      </div>
    </div>
  </div>
</div>

     <div class="row" style="flex-wrap: nowrap; margin-left: 10%">
   
    <div class="col-md-6 blog-main" style="display: inline-block;">
      <h3 class="pb-4 mb-4 font-italic border-bottom">
          <%=storeInfo.getStoreName() %> 
          <a href="#" class="contain-icon icon-hook">
         
            <svg class="star-icon star-icon-1" version="1.1" width="40px" height="40px" viewBox="0 0 105.602 102.931">
             <path class="main-star-1" fill="none" stroke="rgb(230, 167, 177)" stroke-width="6" stroke-miterlimit="10" d="M52.35,3.11c0.475-0.963,1.253-0.963,1.728,0  l12.211,24.742c0.475,0.963,1.734,1.877,2.796,2.032l27.305,3.968c1.063,0.154,1.303,0.894,0.534,1.644L77.167,54.754
             c-0.769,0.75-1.25,2.229-1.068,3.287l4.664,27.194c0.182,1.058-0.448,1.516-1.398,1.016L54.942,73.413
             c-0.951-0.5-2.506-0.5-3.456,0L27.064,86.252c-0.951,0.5-1.58,0.043-1.398-1.016l4.664-27.194c0.182-1.058-0.299-2.538-1.068-3.287
             L9.504,35.495c-0.769-0.75-0.529-1.489,0.534-1.644l27.305-3.968c1.063-0.154,2.321-1.069,2.796-2.032L52.35,3.11z"/>
             
             <path class="star-dashes-1" fill="#FFFFFF" stroke="#FFFFFF" stroke-width="5" stroke-linecap="round" stroke-miterlimit="10" d="M20.881,6.26
             l6.333,7.333 M103.214,63.961l-9.173-3.122 M78.519,13.835l5.724-7.818 M52.777,100.544l0.048-9.69 M11.823,61.737l-9.436,2.204"/>
             
             <path class="star-check-1" fill="none" stroke="rgb(255, 168, 0)" stroke-width="5" stroke-linecap="round" stroke-linejoin="round" stroke-miterlimit="10" d="
             M42.681,47.839l6.817,6.817 M63.747,39.016l-14.249,15.64"/>
           </svg>
          </a>
      </h3>
      
     <!-- 별모양 스크립트 -->
       <script type="text/javascript">
      // Get a list of all svg elements
         icons = document.querySelectorAll('.icon-hook');
         console.log(icons.length);
         // Cycle through list
         for (var i = 0; i < icons.length; i++) {
           icons[i].addEventListener('click', function(event) {
             event.preventDefault();
          
             var icon = this;
             var currentClass = icon.getAttribute('class'); // The starting class
   
             console.log(icon);
   
             if (currentClass.indexOf('active') > -1 || <%=loginMember == null%>) { 
               // Remove .active
               icon.setAttribute('class', currentClass.replace(' active', ''));
             } else { 
               // Add .active
               icon.setAttribute('class', currentClass + ' active');
             }
           }, false);
         }
         
         
         
         $(function(){
               if(<%=starColor%> != 0){
                  $(".star-icon.star-icon-1").addClass("active");
               }else{
                  $(".star-icon.star-icon-1").removeClass("active");
               }
            });
         
        
             $(".icon-hook").on("click", function(){
               // 비동기 통신으로 회원번호,가게 정보 저장
               $.ajax({
                  url: "favorite.do",
                  data : {"storeNo": <%=storeNo%>, "memberNo": <%=tmp%>},
                  success : function(rNo){
                     if(rNo == 1) {
                        //alert("즐겨찾기에 추가되었습니다. [마이페이지->내가 즐겨찾는 가게]에서 확인하세요.");
                        /* $("#favoBtn").css("color", "rgb(255, 168, 0)"); */
                     }else if(rNo == 0){
                        //alert("즐겨찾기가 삭제되었습니다.");
                        /* $("#favoBtn").css("color", "black"); */
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

      <div class="blog-post" >
        <h2 class="blog-post-title"></h2>
        <p class="blog-post-meta" style="height: 50px"><%=storeInfo.getStoreContent() %> </p>
           
      
       
        

          

      <table class="table">
      
 
           
            <tr>
                <th width="100px">주소</th>
                <td><%=storeInfo.getStoreAddress() %></td>  
            </tr>
            <tr>
                <th>전화번호</th>
                <td><%=storeInfo.getStoreTel() %></td>                      
            </tr>
            <tr>
                <th>음식종류</th>
                <td><%=storeInfo.getGroupName()%></td>             
            </tr>
            <tr>
                <th>가격대</th>
                <td><%=storeInfo.getPriceMin()%>~
                <%=storeInfo.getPriceMax() %> 원
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
        <div id="tmp-area">
            <table id="tableId" class="table"></table>
         
          </div>        

      <% if(boardList.size() > 5) { %>
      <div style="text-align: center;">
      <a id="tmp" style="width: 500px; color: red; font-weight: bold;" >더보기&nbsp;<i class="fa fa-angle-double-down"  ></i></a>
      </div>
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
         for(var i=0; i < 5; i++){
            if(bSize <= i){
                     tmpSize = i;
                     
                  break;
            }
            var $div = $("<div>"); //리뷰 한칸 영역
            
            var $input = $("<input type=\"hidden\" value=\"" + bList[i].boardNo + "\">");
            var $p = $("<p>"); // 리뷰 한 칸 영역
            var $div1 = $("<div>"); // 닉네임 들어가는 영역
            var $div2 = $("<div>"); // 내용 들어가는 영역
            
            var $br = $("<br>");
            var $hr = $("<hr>");
            
            
            var $tr = $("<tr>").addClass("pclass");
            var $td1 = $("<td width=\"180px\">");
            
            var $td2 = $("<td>");
            
            $td1.text(bList[i].nickName);
            $td2.html(bList[i].boardModifyDate +"<br>"+ bList[i].boardContent);
            
            $tr.append($td1.append($input), $td2);
            $("#tmp-area").append($("#tableId").append($tr));
         }
      }
      
      
       $("#tmp").on("click",function(){
          if(tmpSize != bSize){
            tmpSize = ++tmpSize + 5;
            for(var i=5*(++loopTmp)+1; i < tmpSize; i++) {
               if(bSize == i){
                  tmpSize = i;
                  $("#tmp").css("display","none");
                  break;
               }
               var $div = $("<div>");
               var $input = $("<input type=\"hidden\" value=\"" + bList[i].boardNo + "\">");
                   var $p = $("<p>");
               var $br = $("<br>");
               var $hr = $("<hr>");
               
               var $tr = $("<tr>").addClass("pclass");
               var $td1 = $("<td width=\"180px\">");
               
               var $td2 = $("<td>");
               
               $td1.text(bList[i].nickName);
               $td2.html(bList[i].boardModifyDate +"<br>"+ bList[i].boardContent);
               
               $tr.append($td1.append($input), $td2);
               $("#tmp-area").append($("#tableId").append($tr));
               
               
            /*     $p.addClass("pclass");
                $p.css({
                  width: "500px",
                  height: "250px",
                  });
                  $p.html("닉네임 : " + bList[i].nickName + "<br>" + bList[i].boardContent +"<br>"+ bList[i].boardModifyDate);
                  $("#tmp-area").append($div.append($p, $input, $hr)); */
            }
       }
         }); 
      
</script>       

    <script>
      $("div").on("click",".pclass", function(){
          
          var name = $(this).children().children().eq(0).val();
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
            // 팝업창 요청 주소      팝업창의 이름(name)      팝업창 크기 설정  << 새로 입력 되는 것들
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
  
  
  
  
  
  <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=13f6a2155d7fead8358eb6587155a7bf&libraries=services"></script>
    
    
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
   geocoder.addressSearch('<%=storeInfo.getStoreAddress()%>', function(result, status) {

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
            content: '<div style="width:150px;text-align:center;padding:6px 0;"><%=storeInfo.getStoreName()%></div>'
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