<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
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
		action="<%=request.getContextPath()%>/member/insertNewPwd.do" method="post">
		<h1 class="h3 mb-3 font-weight-normal">비밀번호 재설정</h1>
		<br>
		<br> <label for="newPwd" class="sr-only"></label> <input type="password" id="newPwd" name="newPwd"
			class="form-control" placeholder="비밀번호 입력(필수)" required autofocus>
		<label for="checkNewPwd" class="sr-only">비밀번호 확인</label> <input
			type="password" id="checkNewPwd" name="checkNewPwd" class="form-control"
			placeholder="비밀번호 확인" required>
		
		<button id="checkBtn" class="btn btn-lg btn-primary btn-block" type="submit">확인</button>
		<p class="mt-5 mb-3 text-muted">&copy; 2020-2023</p>
	</form>
	<script>
		// 비밀번호 유효성검사
		
		var $pwd1 = $("#newPwd");
		var $pwd2 = $("#checkNewPwd");
		var $pwd = $("#newPwd, #checkNewPwd");
		
		$("#checkBtn").on("click",
			function() {
				var regExp = /^[A-Za-z0-9]{6,12}$/;

				if(!regExp.test($("#pwd1").val())) {
					alert("비밀번호 형식이 유효하지 않습니다.");
					return false;
				}

				if($pwd2.val().length > 0) {
					swal("유효한 비밀번호를 작성해 주세요.");
					$pwd2.val("");
					$pwd1.focus();
				} else if (signUpCheck.pwd1
						&& $pwd2.val().length > 0) {
					if ($("#pwd1").val().trim() != $("#pwd2").val()
							.trim()) {
						$("#checkPwd2").text("비밀번호 불일치").css(
								"color", "red");
						signUpCheck.pwd2 = false;
					} else {
						$("#checkPwd2").text("비밀번호 일치").css(
								"color", "green");
						signUpCheck.pwd2 = true;
					}
				}

			});
	
	</script>
	
	
	
	
	
	<%@ include file="../common/footer.jsp"%>
</body>
</html>