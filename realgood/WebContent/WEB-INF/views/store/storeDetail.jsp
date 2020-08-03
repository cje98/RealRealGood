<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.kh.realgood.store.model.dto.StoreInfoMenu"%>
<%@page import="com.kh.realgood.store.model.dto.Store"%>
<%@page import="java.util.List"%>
<%
	List<StoreInfoMenu> mList = (List<StoreInfoMenu>)request.getAttribute("mList");
%>

<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Jekyll v4.0.1">
    <title>상세페이지 </title>

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
    </style>
    <!-- Custom styles for this template -->
    <link href="https://fonts.googleapis.com/css?family=Playfair+Display:700,900" rel="stylesheet">
    <!-- Custom styles for this template -->
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
  </head>
  <body>
  <%@ include file="../common/header.jsp" %>
    <div class="container">
  <header class="">
    
  </header>

  
  

  <div class="row mb-2">
    <div class="col-md-6">
      <div class="row no-gutters border rounded overflow-hidden flex-md-row mb-4 shadow-sm h-md-250 position-relative">
        <div class="col p-4 d-flex flex-column position-static">
          <h3 class="mb-0">업체 사진</h3>
          <a href="#" class="stretched-link">Continue reading</a>
        </div>
        <div class="col-auto d-none d-lg-block">
          <svg class="bd-placeholder-img" width="200" height="250" xmlns="http://www.w3.org/2000/svg" preserveAspectRatio="xMidYMid slice" focusable="false" role="img" aria-label="Placeholder: Thumbnail"><title>Placeholder</title><rect width="100%" height="100%" fill="#55595c"/><text x="50%" y="50%" fill="#eceeef" dy=".3em">Thumbnail</text></svg>
        </div>
      </div>
    </div>
    <div class="map-image">
      <div class="row no-gutters border rounded overflow-hidden flex-md-row mb-4 shadow-sm h-md-250 position-relative">
        <div class="col p-4 d-flex flex-column position-static">
          <h3 class="mb-0">지도</h3>
          <a href="#" class="stretched-link">Continue reading</a>
        </div>
      </div>
    </div>
  </div>
</div>

<main role="main" class="container">
  <div class="row">
    <div class="col-md-8 blog-main">
      <h3 class="pb-4 mb-4 font-italic border-bottom">
        	독수리 다방
      </h3>

      <div class="blog-post">
        <h2 class="blog-post-title">Sample blog post</h2>
        <p class="blog-post-meta">January 1, 2014 by <a href="#">Mark</a></p>

        <p>주소</p>
        
        <br><br><br><br><br><br>
        <h2>리뷰</h2>


        <div class="col-md-12">
          <div class="row no-gutters border rounded overflow-hidden flex-md-row mb-4 shadow-sm h-md-250 position-relative">
            <div class="member profile">
              <div class="col p-4 d-flex flex-column position-static" >
              <h3 class="mb-0"><img name="member-profile" src="evaluation.JPG" style="float: left;"></h3>
              </div>
              <div class="member-profile-name" >닉네임</div>
            </div>
            <div class="col-auto d-none d-lg-block">
           
               <svg class="bd-placeholder-img" width="400" height="250" xmlns="http://www.w3.org/2000/svg" preserveAspectRatio="xMidYMid slice" focusable="false" role="img" aria-label="Placeholder: Thumbnail">
               <rect width="100%" height="100%" fill="#55595c"/>
               <text x="50%" y="50%" fill="#eceeef" dy=".3em">리뷰 작성</text></svg>
            </div>
            <div class="col p-4 d-flex flex-column position-static" >
              <h3 class="mb-0"><img name="evaluation" src="evaluation.JPG" style="float: left;"></h3>
            </div>
          </div>
        </div>

        <div class="col-md-12">
          <div class="row no-gutters border rounded overflow-hidden flex-md-row mb-4 shadow-sm h-md-250 position-relative">
            <div class="member profile">
              <div class="col p-4 d-flex flex-column position-static" >
              <h3 class="mb-0"><img name="member-profile" src="evaluation.JPG" style="float: left;"></h3>
              </div>
              <div class="member-profile-name" >닉네임</div>
            </div>
            <div class="col-auto d-none d-lg-block">
              <svg class="bd-placeholder-img" width="400" height="250" xmlns="http://www.w3.org/2000/svg" preserveAspectRatio="xMidYMid slice" focusable="false" role="img" aria-label="Placeholder: Thumbnail">
                <title>Placeholder</title><rect width="100%" height="100%" fill="#55595c"/>
                <text x="50%" y="50%" fill="#eceeef" dy=".3em">리뷰 작성</text></svg>
            </div>
            <div class="col p-4 d-flex flex-column position-static" >
              <h3 class="mb-0"><img name="evaluation" src="evaluation.JPG" style="float: left;"></h3>
            </div>
          </div>
        </div>


               
        <br><br><br><br><br><br>
        <h3>Sub-heading</h3>
        <p>Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus.</p>
        
      </div><!-- /.blog-post -->

      <div class="blog-post">
        <h2 class="blog-post-title">Another blog post</h2>
        <p class="blog-post-meta">December 23, 2013 by <a href="#">Jacob</a></p>

        <p>Cum sociis natoque penatibus et magnis <a href="#">dis parturient montes</a>, nascetur ridiculus mus. Aenean eu leo quam. Pellentesque ornare sem lacinia quam venenatis vestibulum. Sed posuere consectetur est at lobortis. Cras mattis consectetur purus sit amet fermentum.</p>
        <blockquote>
          <p>Curabitur blandit tempus porttitor. <strong>Nullam quis risus eget urna mollis</strong> ornare vel eu leo. Nullam id dolor id nibh ultricies vehicula ut id elit.</p>
        </blockquote>
        <p>Etiam porta <em>sem malesuada magna</em> mollis euismod. Cras mattis consectetur purus sit amet fermentum. Aenean lacinia bibendum nulla sed consectetur.</p>
        <p>Vivamus sagittis lacus vel augue laoreet rutrum faucibus dolor auctor. Duis mollis, est non commodo luctus, nisi erat porttitor ligula, eget lacinia odio sem nec elit. Morbi leo risus, porta ac consectetur ac, vestibulum at eros.</p>
      </div><!-- /.blog-post -->

      <div class="blog-post">
        <h2 class="blog-post-title">New feature</h2>
        <p class="blog-post-meta">December 14, 2013 by <a href="#">Chris</a></p>

        <p>Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Aenean lacinia bibendum nulla sed consectetur. Etiam porta sem malesuada magna mollis euismod. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus.</p>
        <ul>
          <li>Praesent commodo cursus magna, vel scelerisque nisl consectetur et.</li>
          <li>Donec id elit non mi porta gravida at eget metus.</li>
          <li>Nulla vitae elit libero, a pharetra augue.</li>
        </ul>
        <p>Etiam porta <em>sem malesuada magna</em> mollis euismod. Cras mattis consectetur purus sit amet fermentum. Aenean lacinia bibendum nulla sed consectetur.</p>
        <p>Donec ullamcorper nulla non metus auctor fringilla. Nulla vitae elit libero, a pharetra augue.</p>
      </div><!-- /.blog-post -->

      <nav class="blog-pagination">
        <a class="btn btn-outline-primary" href="#">Older</a>
        <a class="btn btn-outline-secondary disabled" href="#" tabindex="-1" aria-disabled="true">Newer</a>
      </nav>

    </div><!-- /.blog-main -->

    <div class="col-md-4 blog-sidebar">
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
         
          <div style="text-align: center;">
            <br><br><br><br><br><br><br><br>
            <button type="button" id="buyPurchase" class="btn btn-warning" >구매하기</button>
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
          
        </div>
      </div>
    </class>
      



      <div class="p-4">
        <h4 class="font-italic">Elsewhere</h4>
        <ol class="list-unstyled">
          <li><a href="#">GitHub</a></li>
          <li><a href="#">Twitter</a></li>
          <li><a href="#">Facebook</a></li>
        </ol>
      </div>
    </aside><!-- /.blog-sidebar -->

  </div><!-- /.row -->

</main><!-- /.container -->

  <%@ include file="../common/footer.jsp" %>
</body>
</html>
