<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	boolean isSave = false; 
	String saveId = "";	
	Cookie[] cookies = request.getCookies();	
	
	if(cookies != null){
		for(Cookie c : cookies){
			
			if("saveId".equals(c.getName())){
				saveId = c.getValue(); 
				isSave = true;
			}
		}
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
</head>

<style>
.form-signin {
  width: 100%;
  max-width: 430px;
  padding: 15px;
  margin: auto;
}
.form-signin .checkbox {
  font-weight: 400;
}
.form-signin .form-control {
  position: relative;
  box-sizing: border-box;
  height: auto;
  padding: 10px;
  font-size: 16px;
}
.form-signin .form-control:focus {
  z-index: 2;
}
.form-signin input[type="email"] {
  margin-bottom: -1px;
  border-bottom-right-radius: 0;
  border-bottom-left-radius: 0;
}
.form-signin input[type="password"] {
  margin-bottom: 10px;
  border-top-left-radius: 0;
  border-top-right-radius: 0;
}

.btn-outline-secondary{
  margin-bottom: 10px;
  max-width: 130px;
  float: right;
  margin-left: 5px;

}

label{
  margin-bottom: 0px;
  margin-top: 10px;
}


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
}
</style>

<body class="text-center">
	<%@ include file="../common/header.jsp"%>
	<form class="form-signin"
		action="<%=request.getContextPath()%>/member/login.do" method="post">
		<h1 class="h3 mb-3 font-weight-normal">맛집 어때</h1>
		<br>
		<br> <label for="inputEmail" class="sr-only">Email
			address</label> <input type="email" id="memberId" name="memberId"
			class="form-control" placeholder="이메일 주소 입력(필수)" value="<%=saveId%>" required autofocus>
		<label for="inputPassword" class="sr-only">Password</label> <input
			type="password" id="memberPwd" name="memberPwd" class="form-control"
			placeholder="비밀번호 입력" required>
		<div class="checkbox mb-3">
			<label> <input type="checkbox" name="saveId" <%= isSave ? "checked" : "" %>>
				아이디 저장하기
			</label>
		</div>
		<a class="btn btn-outline-secondary"
			href="<%=request.getContextPath()%>/member/lookingForFormPwd.do">비밀번호
			찾기</a> <a class="btn btn-outline-secondary"
			href="<%=request.getContextPath()%>/member/lookingForFormId.do">아이디
			찾기</a>
		<button class="btn btn-lg btn-primary btn-block" type="submit">로그인</button>
		<p class="mt-5 mb-3 text-muted">&copy; 2020-2023</p>
	</form>
	<%@ include file="../common/footer.jsp"%>
</body>
</html>