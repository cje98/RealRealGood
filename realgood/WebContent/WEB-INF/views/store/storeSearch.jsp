<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@page import="com.kh.realgood.store.model.vo.PageInfo"%>
<%@page import="com.kh.realgood.store.model.dto.StoreImg"%>
<%@page import="com.kh.realgood.store.model.dto.Store"%>
<%@page import="java.util.List"%>
<%
   PageInfo pInfo = (PageInfo) request.getAttribute("pInfo");

   List<Store> storeList = (List<Store>) request.getAttribute("storeList");

   int currentPage = pInfo.getCurrentPage();
   int listCount = pInfo.getListCount();
   int maxPage = pInfo.getMaxPage();
   int startPage = pInfo.getStartPage();
   int endPage = pInfo.getEndPage();

   String group = pInfo.getGroup();
   String address = pInfo.getAddress();

   int prev = (currentPage - 1) / 10 * 10;
   int next = (currentPage + 9) / 10 * 10 + 1;
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>store list page</title>

<style>
.pagination {
   justify-content: center;
}

#searchForm {
   position: relative;
}

#searchForm>* {
   top: 0;
}

.boardTitle>img {
   width: 50px;
   height: 50px;
}

.ico-star1 {
   color: rgb(255, 168, 0);
}
</style>
</head>
<body>

   <%@ include file="../common/header.jsp"%>

   <ul class="nav nav-tabs nav-fill">
      <li class="nav-item"><a class="nav-link"
         href="<%=request.getContextPath()%>/store/search.do?group=&address=<%=address%>&cp=1"><strong>전체</strong></a></li>
      <li class="nav-item"><a class="nav-link"
         href="<%=request.getContextPath()%>/store/search.do?group=치킨&address=<%=address%>&cp=1">치킨</a></li>
      <li class="nav-item"><a class="nav-link"
         href="<%=request.getContextPath()%>/store/search.do?group=중식&address=<%=address%>&cp=1">중식</a></li>
      <li class="nav-item"><a class="nav-link"
         href="<%=request.getContextPath()%>/store/search.do?group=한식&address=<%=address%>&cp=1">한식</a></li>
      <li class="nav-item"><a class="nav-link"
         href="<%=request.getContextPath()%>/store/search.do?group=분식&address=<%=address%>&cp=1">분식</a></li>
      <li class="nav-item"><a class="nav-link"
         href="<%=request.getContextPath()%>/store/search.do?group=일식&address=<%=address%>&cp=1">일식</a></li>
      <li class="nav-item"><a class="nav-link"
         href="<%=request.getContextPath()%>/store/search.do?group=피자/양식&address=<%=address%>&cp=1">피자/양식</a></li>
      <li class="nav-item"><a class="nav-link"
         href="<%=request.getContextPath()%>/store/search.do?group=족발&address=<%=address%>&cp=1">족발</a></li>
      <li class="nav-item"><a class="nav-link"
         href="<%=request.getContextPath()%>/store/search.do?group=디저트&address=<%=address%>&cp=1">디저트</a></li>
      <li class="nav-item"><a class="nav-link"
         href="<%=request.getContextPath()%>/store/search.do?group=야식&address=<%=address%>&cp=1">야식</a></li>
   </ul>

   <div class="col-sm-11" style="display: flex; margin: 25px 0 0 20px">


      <%
         if (!storeList.isEmpty()) {
            for (int i = 0; i < storeList.size(); i++) {
               Store store = storeList.get(i);
               if (i == 0) {
      %>
      <div class="row mb-6">
         <%
            }
                  if (i % 2 == 0) {
                     if (storeList.size() == 1) {
         %>
            <div class="col-auto">
                     <% } else {%>
            <div class="col-md-6">
                     <% } %>
            <div
               class="row no-gutters border rounded overflow-hidden flex-md-row mb-4 shadow-sm h-md-250 position-relative">
               <div class="col p-4 d-flex flex-column position-static">
                  <strong class="d-inline-block mb-6 text-primary"><%=store.getStoreGroupName()%></strong>
                  <input type="hidden" value="<%=store.getStoreNum()%>">
                  <h3 class="mb-0"><%=store.getStoreName()%></h3>
                  <div class="mb-1 text-muted"><%=store.getStoreTel()%></div>
                  <p class="card-text mb-auto"><%=store.getStoreContent()%></p>
                  <p class="card-text mb-auto"><%=store.getStoreAddress()%></p>
                  <p>
                     <span class="ico-star1">★ <%=store.getStoreGpaScore()%></span><span class="riple"> 리뷰 <%=store.getRipleCount()%></span>
                  </p>
                  <a class="stretched-link"></a>

               </div>
               <div class="col-auto d-none d-lg-block">
                  <img width="200" height="250"
                     src="<%=request.getContextPath()%>/resources/images/<%=store.getStoreTitleImg()%>" />
               </div>

            </div>
         </div>
         <%
            } else {
         %>
         <div class="col-md-6">
            <div
               class="row no-gutters border rounded overflow-hidden flex-md-row mb-4 shadow-sm h-md-250 position-relative">
               <div class="col p-4 d-flex flex-column position-static">
                  <strong class="d-inline-block mb-6 text-primary"><%=store.getStoreGroupName()%></strong>
                  <input type="hidden" value="<%=store.getStoreNum()%>">
                  <h3 class="mb-0"><%=store.getStoreName()%></h3>
                  <div class="mb-1 text-muted"><%=store.getStoreTel()%></div>
                  <p class="card-text mb-auto"><%=store.getStoreContent()%></p>
                  <p class="card-text mb-auto"><%=store.getStoreAddress()%></p>
                  <p>
                     <span class="ico-star1">★ <%=store.getStoreGpaScore()%></span><span class="riple"> 리뷰 <%=store.getRipleCount()%></span>
                  </p>
                  <a class="stretched-link"></a>
               </div>
               <div class="col-auto d-none d-lg-block">
                  <img width="200" height="250"
                     src="<%=request.getContextPath()%>/resources/images/<%=store.getStoreTitleImg()%>" />
               </div>
            </div>
         </div>
         <%
            }
                  if (i == (storeList.size() - 1)) {
         %>
      </div>
      <%
         }
      %>
      <br>
      <%
         }
         }
      %>


      <div class="row" style="margin-left: 30px">
         <div id="map" style="width: 350px; height: 300px;"></div>
      </div>





   </div>


   <div style="clear: both">
      <ul class="pagination">
         <%
            if (currentPage > 10) {
         %>
         <!-- 맨 처음 페이지로 이동[<<] -->
         <li><a class="page-link"
            href="<%=request.getContextPath()%>/store/search.do?group=<%=group%>&address=<%=address%>&cp=1">&lt;&lt;</a>
         </li>

         <!-- 이전 순번의 페이징 바로 이동[<] -->
         <li><a class="page-link"
            href="<%=request.getContextPath()%>/store/search.do?group=<%=group%>&address=<%=address%>&cp=<%=prev%>">&lt;</a>
         </li>
         <%
            }
         %>
         <!-- 10개의 페이지 목록 -->
         <%
            for (int p = startPage; p <= endPage; p++) {
         %>
         <%
            if (p == currentPage) {
         %>
         <li><a class="page-link"><%=p%></a> <%
    } else {
 %>
         <li><a class="page-link"
            href="<%=request.getContextPath()%>/store/search.do?group=<%=group%>&address=<%=address%>&cp=<%=p%>"><%=p%></a></li>
         <%
            }
         %>
         <%
            }
         %>
         <%
            if (next < maxPage) {
         %>
         <!-- 다음 페이징바 [>] -->
         <li><a class="page-link"
            href="<%=request.getContextPath()%>/store/search.do?group=<%=group%>&address=<%=address%>&cp=<%=next%>">&gt;</a></li>

         <!-- 마지막 페이지로 이동 [>>] -->
         <li><a class="page-link"
            href="<%=request.getContextPath()%>/store/search.do?group=<%=group%>&address=<%=address%>&cp=<%=maxPage%>">&gt;&gt;</a></li>
         <%
            }
         %>




      </ul>


   </div>







   <script type="text/javascript"
      src="//dapi.kakao.com/v2/maps/sdk.js?appkey=c97c952ea049cff96693a7ef6202037d&libraries=services"></script>

   <script>
   var mapContainer = document.getElementById('map');
   var mapOption = {
       center: new daum.maps.LatLng(33.4500302,126.5711251),
       level: 3
   };  

   var map = new daum.maps.Map(mapContainer, mapOption); 

   var geocoder = new daum.maps.services.Geocoder();
   var listData = [
   <%if (!storeList.isEmpty()) {
            for (int i = 0; i < storeList.size(); i++) {%>
      <%if (storeList.size() - 1 != i) {%>
            '<%=storeList.get(i).getStoreAddress()%>',
         <%} else {%>
            '<%=storeList.get(i).getStoreAddress()%>'
         <%}%>
      <%}
         }%>
   ];
   
   listData.forEach(function(addr, index) {
       geocoder.addressSearch(addr, function(result, status) {
           if (status === daum.maps.services.Status.OK) {
               var coords = new daum.maps.LatLng(result[0].y, result[0].x);

               var marker = new daum.maps.Marker({
                   map: map,
                   position: coords
               });
               var infowindow = new daum.maps.InfoWindow({
                   content: '<div style="width:150px;text-align:center;padding:6px 0;">' + listData[index] + '</div>',
                   disableAutoPan: true
               });
               
               map.setCenter(coords);

           } 
       });
   });

   
   
   
   </script>


   <script>
     $(".stretched-link").on("click", function(){
        
        var name = $(this).parent().children().eq(1).val();

      location.href="<%=request.getContextPath()%>/store/detail.do?storeNum="+name;        
      
     }).on("mouseenter", function(){
      $(this).parent().css("cursor","pointer");
   });
  
  
  </script>

   <%@ include file="../common/footer.jsp"%>
</body>
</html>