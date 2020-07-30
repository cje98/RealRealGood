<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.kh.realgood.store.model.dto.Store"%>
<%@page import="java.util.List"%>
<%
	List<Store> sList = (List<Store>)request.getAttribute("sList");
%>
<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Jekyll v4.0.1">
    <title>상세페이지 전</title>

    <link rel="canonical" href="https://getbootstrap.com/docs/4.5/examples/blog/">


    <!-- Bootstrap core CSS -->
    <link href="<%=request.getContextPath()%>/resources/css/storeDetailAll.css" rel="stylesheet"> <!-- 부트스트랩 -->
    <link href="<%=request.getContextPath()%>/resources/css/storeDetail.css" rel="stylesheet"> <!-- 블로그 -->
    

    <style>
      .bd-placeholder-img {
        font-size: 1.125rem;
        text-anchor: middle;
        -webkit-user-select: none;
        -moz-user-select: none;
        -ms-user-select: none;
        user-select: none;
      }

      p { margin:20px 0px; }

      .paginate{

        text-align: center;
        padding: 50px 20px;
        margin-left: 180px;
      }

      @media (min-width: 768px) {
        .bd-placeholder-img-lg {
          font-size: 3.5rem;
        }
      }
    </style>
    <!-- Custom styles for this template -->
    <link href="https://fonts.googleapis.com/css?family=Playfair+Display:700,900" rel="stylesheet">
  </head>
  <body>
  <%@ include file="../common/header.jsp" %>
    <div class="container">
  <header class="">
    
  </header>

  

<main role="main" class="container">
  <div class="row">
    <div class="col-md-8 blog-main">
      <h3 class="pb-4 mb-4 font-italic border-bottom">
      	상세페이지 전
      </h3>


      <div class="container">
        <div class="row">
          <div class="pag">
            <ul class="pagination">
               <%-- 주소가 서울~이면서 그룹명이 한식인 가게 조회 --%>
              <li class="page-item"><a class="page-link" href="<%=request.getContextPath()%>/store/search.do?group=한식">한식</a></li>
              <li class="page-item"><a class="page-link" href="<%=request.getContextPath()%>/store/search.do?group=중식">중식</a></li>
              <li class="page-item"><a class="page-link" href="<%=request.getContextPath()%>/store/search.do?group=일식">일식</a></li>
              <li class="page-item"><a class="page-link" href="<%=request.getContextPath()%>/store/search.do?group=분식">분식</a></li>
              <li class="page-item"><a class="page-link" href="<%=request.getContextPath()%>/store/search.do?group=치킨">치킨</a></li>
              <li class="page-item"><a class="page-link" href="<%=request.getContextPath()%>/store/search.do?group=족발">족발</a></li>
              <li class="page-item"><a class="page-link" href="<%=request.getContextPath()%>/store/search.do?group=피자">피자/양식</a></li>
              <li class="page-item"><a class="page-link" href="<%=request.getContextPath()%>/store/search.do?group=디저트">디저트</a></li>
              <li class="page-item"><a class="page-link" href="<%=request.getContextPath()%>/store/search.do?group=야식">야식</a></li>
            </ul>
          </div>
        </div>
      </div>
        

      <div class="blog-post">
        <h2 class="blog-post-title"></h2>
        <p class="blog-post-meta"></p>

        <p></p>


<% for(int i = 0; i < sList.size(); i++) {
	if(i % 2 == 0){
	%>
  <div class="row mb-2">
    <div class="col-md-6">
      <div class="row no-gutters border rounded overflow-hidden flex-md-row mb-4 shadow-sm h-md-250 position-relative">
        <div class="col p-4 d-flex flex-column position-static">
          <h3 class="mb-0">업체 사진</h3>
          <a class="stretched-link"><%= sList.get(i).getStoreNum()%></a>
          <a class="stretched-link"><%= sList.get(i).getStoreName()%></a>
          <%-- 가게명 보냄 --%>
        </div>
        <div class="col-auto d-none d-lg-block">
          <svg class="bd-placeholder-img" width="200" height="250" xmlns="http://www.w3.org/2000/svg" preserveAspectRatio="xMidYMid slice" focusable="false" role="img" aria-label="Placeholder: Thumbnail"><title>Placeholder</title><rect width="100%" height="100%" fill="#55595c"/><text x="50%" y="50%" fill="#eceeef" dy=".3em">Thumbnail</text></svg>
        </div>
      </div>
    </div>
	   <% }  else{ %>
	
    <div class="col-md-6">
      <div class="row no-gutters border rounded overflow-hidden flex-md-row mb-4 shadow-sm h-md-250 position-relative">
        <div class="col p-4 d-flex flex-column position-static">
          <h3 class="mb-0">업체 사진</h3>
         <a class="stretched-link"><%= sList.get(i).getStoreNum()%></a>
         <a class="stretched-link"><%= sList.get(i).getStoreName()%></a>
        </div>
        <div class="col-auto d-none d-lg-block">
          <svg class="bd-placeholder-img" width="200" height="250" xmlns="http://www.w3.org/2000/svg" preserveAspectRatio="xMidYMid slice" focusable="false" role="img" aria-label="Placeholder: Thumbnail"><title>Placeholder</title><rect width="100%" height="100%" fill="#55595c"/><text x="50%" y="50%" fill="#eceeef" dy=".3em">Thumbnail</text></svg>
        </div>
      </div>
    </div>
    
  </div>
  <% } } %>

	<%--

  <div class="row mb-2">
    <div class="col-md-6">
      <div class="row no-gutters border rounded overflow-hidden flex-md-row mb-4 shadow-sm h-md-250 position-relative">
        <div class="col p-4 d-flex flex-column position-static">
          <h3 class="mb-0">업체 사진</h3>
          <a class="stretched-link">Continue reading</a>
        </div>
        <div class="col-auto d-none d-lg-block">
          <svg class="bd-placeholder-img" width="200" height="250" xmlns="http://www.w3.org/2000/svg" preserveAspectRatio="xMidYMid slice" focusable="false" role="img" aria-label="Placeholder: Thumbnail"><title>Placeholder</title><rect width="100%" height="100%" fill="#55595c"/><text x="50%" y="50%" fill="#eceeef" dy=".3em">Thumbnail</text></svg>
        </div>
      </div>
    </div>


    <div class="col-md-6">
      <div class="row no-gutters border rounded overflow-hidden flex-md-row mb-4 shadow-sm h-md-250 position-relative">
        <div class="col p-4 d-flex flex-column position-static">
          <h3 class="mb-0">업체 사진</h3>
          <a class="stretched-link">Continue reading</a>
        </div>
        <div class="col-auto d-none d-lg-block">
          <svg class="bd-placeholder-img" width="200" height="250" xmlns="http://www.w3.org/2000/svg" preserveAspectRatio="xMidYMid slice" focusable="false" role="img" aria-label="Placeholder: Thumbnail"><title>Placeholder</title><rect width="100%" height="100%" fill="#55595c"/><text x="50%" y="50%" fill="#eceeef" dy=".3em">Thumbnail</text></svg>
        </div>
      </div>
    </div>
    
  </div>

  <div class="row mb-2">
    <div class="col-md-6">
      <div class="row no-gutters border rounded overflow-hidden flex-md-row mb-4 shadow-sm h-md-250 position-relative">
        <div class="col p-4 d-flex flex-column position-static">
          <h3 class="mb-0">업체 사진</h3>
          <a class="stretched-link">Continue reading</a>
        </div>
        <div class="col-auto d-none d-lg-block">
          <svg class="bd-placeholder-img" width="200" height="250" xmlns="http://www.w3.org/2000/svg" preserveAspectRatio="xMidYMid slice" focusable="false" role="img" aria-label="Placeholder: Thumbnail"><title>Placeholder</title><rect width="100%" height="100%" fill="#55595c"/><text x="50%" y="50%" fill="#eceeef" dy=".3em">Thumbnail</text></svg>
        </div>
      </div>
    </div>


    <div class="col-md-6">
      <div class="row no-gutters border rounded overflow-hidden flex-md-row mb-4 shadow-sm h-md-250 position-relative">
        <div class="col p-4 d-flex flex-column position-static">
          <h3 class="mb-0">업체 사진</h3>
          <a class="stretched-link">Continue reading</a>
        </div>
        <div class="col-auto d-none d-lg-block">
          <svg class="bd-placeholder-img" width="200" height="250" xmlns="http://www.w3.org/2000/svg" preserveAspectRatio="xMidYMid slice" focusable="false" role="img" aria-label="Placeholder: Thumbnail"><title>Placeholder</title><rect width="100%" height="100%" fill="#55595c"/><text x="50%" y="50%" fill="#eceeef" dy=".3em">Thumbnail</text></svg>
        </div>
      </div>
    </div>
    
  </div>


  <div class="row mb-2">
    <div class="col-md-6">
      <div class="row no-gutters border rounded overflow-hidden flex-md-row mb-4 shadow-sm h-md-250 position-relative">
        <div class="col p-4 d-flex flex-column position-static">
          <h3 class="mb-0">업체 사진</h3>
          <a class="stretched-link">Continue reading</a>
        </div>
        <div class="col-auto d-none d-lg-block">
          <svg class="bd-placeholder-img" width="200" height="250" xmlns="http://www.w3.org/2000/svg" preserveAspectRatio="xMidYMid slice" focusable="false" role="img" aria-label="Placeholder: Thumbnail"><title>Placeholder</title><rect width="100%" height="100%" fill="#55595c"/><text x="50%" y="50%" fill="#eceeef" dy=".3em">Thumbnail</text></svg>
        </div>
      </div>
    </div>


    <div class="col-md-6">
      <div class="row no-gutters border rounded overflow-hidden flex-md-row mb-4 shadow-sm h-md-250 position-relative">
        <div class="col p-4 d-flex flex-column position-static">
          <h3 class="mb-0">업체 사진</h3>
          <a class="stretched-link">Continue reading</a>
        </div>
        <div class="col-auto d-none d-lg-block">
          <svg class="bd-placeholder-img" width="200" height="250" xmlns="http://www.w3.org/2000/svg" preserveAspectRatio="xMidYMid slice" focusable="false" role="img" aria-label="Placeholder: Thumbnail"><title>Placeholder</title><rect width="100%" height="100%" fill="#55595c"/><text x="50%" y="50%" fill="#eceeef" dy=".3em">Thumbnail</text></svg>
        </div>
      </div>
    </div>
 	--%>
 
  </div>
  
  <script>
  	$(".stretched-link").on("click", function(){
  		
  		var no = $(this).parent().children().eq(1).text();
  		
  		location.href="<%=request.getContextPath()%>/store/detail.do?no="+no;
  		
  	}).on("mouseenter", function(){
		$(this).parent().css("cursor","pointer");
	});
  
  
  </script>






               
        <br><br><br><br><br><br>

       </div><!-- /.blog-post -->
      <div class="blog-post">
      </div><!-- /.blog-post -->
     
      <nav class="blog-pagination">
        
        <div class="container">
          <div class="row">
            <div class="paginate">
              <ul class="pagination">
                <li class="page-item"><a class="page-link" href="#">Previous</a></li>
                <li class="page-item"><a class="page-link" href="#">1</a></li>
                <li class="page-item"><a class="page-link" href="#">2</a></li>
                <li class="page-item"><a class="page-link" href="#">3</a></li>
                <li class="page-item"><a class="page-link" href="#">4</a></li>
                <li class="page-item"><a class="page-link" href="#">5</a></li>
                <li class="page-item"><a class="page-link" href="#">6</a></li>
                <li class="page-item"><a class="page-link" href="#">7</a></li>
                <li class="page-item"><a class="page-link" href="#">8</a></li>
                <li class="page-item"><a class="page-link" href="#">9</a></li>
                <li class="page-item"><a class="page-link" href="#">10</a></li>
                <li class="page-item"><a class="page-link" href="#">Next</a></li>
              </ul>
            </div>
          </div>
        </div>
        <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>

        

      </nav>

    </div><!-- /.blog-main -->



 
    <aside>

        <div class="map-image">
          <div class="row no-gutters border rounded overflow-hidden flex-md-row mb-4 shadow-sm h-md-250 position-relative">
            <div class="col p-4 d-flex flex-column position-static">
              <h3 class="mb-0">지도</h3>
              <a href="#" class="stretched-link">Continue reading</a>
            </div>
          </div>
        </div>


        <div class="side-sub-image">
          <div class="row no-gutters border rounded overflow-hidden flex-md-row mb-4 shadow-sm h-md-250 position-relative">
            <div class="col p-4 d-flex flex-column position-static">
              <h3 class="mb-0">지도</h3>
              <a href="#" class="stretched-link">Continue reading</a>
            </div>
          </div>
        </div>



      <class class="col-md-4 blog-sidebar">
        <div class="side-sub-image">
          <div class="row no-gutters border rounded overflow-hidden flex-md-row mb-4 shadow-sm h-md-250 position-relative">
            <div class="col p-4 d-flex flex-column position-static">
              <h3 class="mb-0">지도</h3>
              <a href="#" class="stretched-link">Continue reading</a>
            </div>
          </div>
        </div>
      </class>


      <class class="col-md-4 blog-sidebar">
        <div class="side-sub-image">
          <div class="row no-gutters border rounded overflow-hidden flex-md-row mb-4 shadow-sm h-md-250 position-relative">
            <div class="col p-4 d-flex flex-column position-static">
              <h3 class="mb-0">지도</h3>
              <a href="#" class="stretched-link">Continue reading</a>
            </div>
          </div>
        </div>
      </class>


      <class class="">
        <div class="side-sub-image">
          <div class="row no-gutters border rounded overflow-hidden flex-md-row mb-4 shadow-sm h-md-250 position-relative">
            <div class="col p-4 d-flex flex-column position-static">
              <h3 class="mb-0">지도</h3>
              <a href="#" class="stretched-link">Continue reading</a>
            </div>
          </div>
        </div>
      </class>

      <class class="">
        <div class="side-sub-image">
          <div>
          <div class="row no-gutters border rounded overflow-hidden flex-md-row mb-4 shadow-sm h-md-250 position-relative">
            <div class="col p-4 d-flex flex-column position-static">
              <h3 class="mb-0">지도</h3>
              <a href="#" class="stretched-link">Continue reading</a>
            </div>
          </div>
        </div>
           

      </class>


    </aside><!-- /.blog-sidebar -->

   

  </div><!-- /.row -->

</main><!-- /.container -->
<%@ include file="../common/footer.jsp" %>
</body>
</html>
    