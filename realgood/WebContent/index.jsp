<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>main page</title>
<style>
/* navi */
.menu-img {
	width: 320px;
	height: 300px;
}

h2, p {
	font-size: 18px;
}

.col-md-4 {
	/* border: 1px solid black; */
	margin: 15px 0;
}

.col-md-4>h2 {
	color: black;
	margin: 10px;
	/* writing-mode: vertical-lr; */
}

#jumbotron-back {
	background-color: pink;
}

.jumbotron p, .display-3 {
	color: white;
}

main {
	height: 100%;
	background-color: white;
}

.btn-secondary, .btn-primary {
	background-color: #007bff;
	border-color: #007bff;
}

.btn-lg {
	background-color: rgb(255, 128, 65);
	border-color: rgb(255, 128, 65)
}

.col-md-4 {
	position: relative;
	width: 100%; /* for IE 6 */
}

.col-md-4 h2 {
	position: absolute;
	/* top: 200px; 
            left: 0; 
            width: 100%;  */
	margin: auto;
}

.search-input {
	height: 50px;
}

.btn-primary {
	width: 100px;
	height: 50px;
}
</style>
</head>
<body>
	<%@ include file="WEB-INF/views/common/header.jsp"%>
	<main role="main"> <!-- Main jumbotron for a primary marketing message or call to action -->
	<div class="jumbotron" id="jumbotron-back"
		style="background-image: url(resources/images/뒷배경.jpg); background-size: 100%;">
		<div class="container">
			<h1 class="display-3">
				솔직한 리뷰, 믿을 수 있는 평점! <br>맛집어때?
			</h1>
			<p>Honest reviews, reliable Rating! How about a restaurant?</p>

		</div>
	</div>




	<div class="container">
		<!-- Example row of columns -->
		<div class="row">
			<div class="col-md-4">
				<p>
					<a href="<%=request.getContextPath()%>/store/search.do?group=한식"><img class="menu-img" src="resources/images/한식.png"
						alt="이미지 없음"></a><br>
				</p>
			</div>
			<div class="col-md-4">
				<p>
					<a href="<%=request.getContextPath()%>/store/search.do?group=중식"><img class="menu-img" src="resources/images/중식.png"
						alt="이미지 없음"></a><br>
				</p>
			</div>
			<div class="col-md-4">

				<p>
					<a href="<%=request.getContextPath()%>/store/search.do?group=일식"><img class="menu-img" src="resources/images/일식.png"
						alt="이미지 없음"></a><br>
				</p>
			</div>
			<div class="col-md-4">
				<p>
					<a href="<%=request.getContextPath()%>/store/search.do?group=분식"><img class="menu-img" src="resources/images/분식.png"
						alt="이미지 없음"></a><br>
				</p>
			</div>
			<div class="col-md-4">
				<p>
					<a href="<%=request.getContextPath()%>/store/search.do?group=치킨"><img class="menu-img" src="resources/images/치킨.png"
						alt="이미지 없음"></a><br>
				</p>
			</div>
			<div class="col-md-4">
				<p>
					<a href="<%=request.getContextPath()%>/store/search.do?group=족발"><img class="menu-img" src="resources/images/족발.png"
						alt="이미지 없음"></a><br>
				</p>
			</div>
			<div class="col-md-4">
				<div></div>
				<a href="<%=request.getContextPath()%>/store/search.do?group=피자"><img class="menu-img" src="resources/images/피자.png"
					alt="이미지 없음"></a><br>
			</div>
			<div class="col-md-4">
				<p>
					<a href="<%=request.getContextPath()%>/store/search.do?group=디저트"><img class="menu-img"
						src="resources/images/디저트.png" alt="이미지 없음"></a><br>
				</p>
			</div>
			<div class="col-md-4">
				<p>
					<a href="<%=request.getContextPath()%>/store/search.do?group=야식"><img class="menu-img" src="resources/images/야식.png"
						alt="이미지 없음"></a><br>
				</p>
			</div>



		</div>

		<hr>

	</div>
	<!-- /container --> </main>
	<%@ include file="WEB-INF/views/common/footer.jsp"%>
</body>
</html>