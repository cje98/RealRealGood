<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.kh.realgood.member.model.dto.Member"%>
<%@page import="com.kh.realgood.store.model.dto.Store"%>
<%
	Member loginMember = (Member) session.getAttribute("loginMember");
	Store loginStore = (Store)session.getAttribute("loginStore");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
	integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk"
	crossorigin="anonymous">
<!-- sweetalert : alert창을 꾸밀 수 있게 해주는 라이브러리 https://sweetalert.js.org/ -->
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<script>
  	/*** 이전 페이지에서 전달받은 내용을 alert 창으로 표시하기 ***/
  	<%String msg = (String) (request.getSession().getAttribute("msg"));
			String status = (String) (request.getSession().getAttribute("status"));
			String text = (String) (request.getSession().getAttribute("text"));%>
  	<%if (msg != null) {%>
  		swal({
  			icon : "<%=status%>",
  			title : "<%=msg%>",
  			text : "<%=text%>"
	});
<%// Session에 존재하는 특정 키값의 속성 제거
				session.removeAttribute("msg");
				session.removeAttribute("status");
				session.removeAttribute("text");
			}%>
	
</script>
<style>
#nav-1 {
	background-color: rgb(230,167,177);
}

@media ( min-width : 769px) {
	.dropdown-menu .dropdown-toggle:after {
		border-top: .3em solid transparent;
		border-right: 0;
		border-bottom: .3em solid transparent;
		border-left: .3em solid;
	}
	.dropdown-menu .dropdown-menu {
		margin-left: 0;
		margin-right: 0;
	}
	.dropdown-menu li {
		position: relative;
	}
	.nav-item .submenu {
		display: none;
		position: absolute;
		left: 100%;
		top: -7px;
	}
	.nav-item .submenu-left {
		right: 100%;
		left: auto;
	}
	.dropdown-menu>li:hover {
		background-color: #f1f1f1
	}
	.dropdown-menu>li:hover>.submenu {
		display: block;
	}
}

/* .dropdown-toggle:visited>.dropdown-menu>li>.submenu {
      display: block;
    } */
</style>
</head>
<body>
	<nav class="navbar navbar-expand-md navbar-dark" id="nav-1">
		<a class="navbar-brand" href="<%=request.getContextPath()%>"><img
			src="<%=request.getContextPath()%>/resources/images/title.png"
			alt="이미지 없음"></a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarsExample03" aria-controls="navbarsExample03"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="navbarsExample03">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item active"><a class="nav-link"
					href="<%=request.getContextPath()%>"> 메인 </a></li>
				<%
					if (loginMember == null) {
				%>
				<li class="nav-item"><a class="nav-link"
					href="<%=request.getContextPath()%>/member/login.do"> 로그인 </a></li>
				<li class="nav-item"><a class="nav-link" href="<%=request.getContextPath()%>/member/signupTerms.do"> 회원가입 </a></li>
				<%
					} else {
				%>
				<li class="nav-item"><a class="nav-link disabled"><strong><%=loginMember.getNickName()%>님</strong></a></li>
				<li class="nav-item"><a class="nav-link"
					href="<%=request.getContextPath()%>/member/logout.do">로그아웃</a></li>
				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" href="#" data-toggle="dropdown">
						마이페이지 </a>
					<ul class="dropdown-menu">
						<!-- <li><a class="dropdown-item" href="<%=request.getContextPath()%>"/member/myPage.do" >회원정보수정&raquo</a> -->
						<li><a class="dropdown-item" href="<%=request.getContextPath()%>/member/myPage.do" data-toggle="collapse"
							data-target="#navbarsExample04" aria-controls="navbarsExample04">회원정보수정</a>
							<ul class="submenu dropdown-menu" id="navbarsExample04">
								<li><a class="dropdown-item" href="<%=request.getContextPath()%>/member/myPage.do"> 내정보수정</a></li>
								<li><a class="dropdown-item" href="<%=request.getContextPath()%>/member/changePwd.do"> 비밀번호 변경</a></li>
								<li><a class="dropdown-item" href="<%=request.getContextPath()%>/member/myBoadrCk.do"> 작성 게시글 확인</a></li>
								<li><a class="dropdown-item" href="<%=request.getContextPath()%>/member/purchase.do"> 구매내역</a></li>
								<li><a class="dropdown-item" href="<%=request.getContextPath()%>/member/removeMember.do"> 회원탈퇴</a></li>
							</ul>
						</li>
						<% if(loginMember.getGradeName().equals("사장회원") || loginMember.getGradeName().equals("관리자")) {%>	
							<li><a class="dropdown-item" href="#" data-toggle="collapse"
							data-target="#navbarsExample05" aria-controls="navbarsExample05">점주페이지</a>
							<ul class="submenu dropdown-menu" id="navbarsExample05">
								<%if(loginStore == null){ %>
								<li><a class="dropdown-item" href="<%=request.getContextPath()%>/store/storeInsert.do"> 가게정보등록</a></li>
								<%}else{ %>
								<li><a class="dropdown-item" href="<%=request.getContextPath()%>/store/storeModify.do"> 가게정보수정</a></li>
								<%} %>
							</ul></li>
							<% } if(loginMember.getGradeName().equals("관리자")){ %>
							<!-- <li><a class="dropdown-item" href="#"> 관리자정보수정&raquo </a> -->
							<li><a class="dropdown-item" href="#" data-toggle="collapse"
								data-target="#navbarsExample06" aria-controls="navbarsExample06">관리자정보수정</a>
								<ul class="submenu dropdown-menu" id="navbarsExample06">
									<li><a class="dropdown-item" href="<%=request.getContextPath()%>/store/adminApprove.do"> 관리자 승인/대기</a></li>
									<li><a class="dropdown-item" href="<%=request.getContextPath()%>/member/memberManage.do"> 회원관리 페이지</a></li>
								</ul></li>
							<% } %>
					<% } %>
					</ul></li>
				
			</ul>
			<form class="form-inline my-2 my-md-0" action="<%=request.getContextPath()%>/store/storeAll.do" method="post">
				<input class="form-control" type="text" id="address" name="address"
					placeholder="위치검색"> <input type="button"
					class="btn btn-secondary" onclick="sample5_execDaumPostcode()"
					value="위치검색"> &nbsp;
				<button class="btn btn-primary">검색</button>
			</form>
		</div>
	</nav>

	<script
		src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	<script>
		$(document).on('click', '.dropdown-item', function(e) {
			e.stopPropagation();
		});

		function sample5_execDaumPostcode() {
			new daum.Postcode({
				oncomplete : function(data) {
					var addr = data.address; // 최종 주소 변수

					// 주소 정보를 해당 필드에 넣는다.
					document.getElementById("address").value = addr;
				}
			}).open();
		}
	</script>
	<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
		integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"
		integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI"
		crossorigin="anonymous"></script>
</body>
</body>

</html>