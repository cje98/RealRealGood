<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
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
		action="insertNewPwd.do" method="post">
		<h1 class="h3 mb-3 font-weight-normal">비밀번호 재설정</h1>
		<br>
		<br> <label for="newPwd" class="sr-only"></label> <input type="password" id="newPwd" name="newPwd"
			class="form-control" placeholder="비밀번호 입력(필수)" required autofocus>
			<span id="check1"></span>
		<label for="checkPwd" class="sr-only"></label> <input
			type="password" id="checkPwd" name="checkPwd" class="form-control"
			placeholder="비밀번호 확인" required>
			<span id="check2"></span>
		<button id="checkBtn" class="btn btn-lg btn-primary btn-block" type="submit">확인</button>
		<p class="mt-5 mb-3 text-muted">&copy; 2020-2023</p>
	</form>
	<script>
		// 비밀번호 유효성검사
		var signUpCheck = {
			"pwd1" : false,
			"pwd2" : false,
		};
		
		var $pwd1 = $("#newPwd");
		var $pwd2 = $("#checkPwd");
		var $pwd = $("#newPwd, #checkPwd");
		// 비밀번호 유효성 및 일치 검사
		$pwd.on("input",
			function() {
				//영어 대,소문자 + 숫자, 총 6~12글자
				var regExp = /^[A-Za-z0-9]{6,12}$/;

				// 비밀번호1 유효성 검사
				if (!regExp.test($pwd1.val())) {
					$("#check1").text("비밀번호 형식이 유효하지 않습니다.").css("color","red");
					signUpCheck.pwd1 = false;
				} else {
					$("#check1").text("비밀번호 형식이 유효합니다.").css("color","green");
					signUpCheck.pwd1 = true;
				}
				// 비밀번호1이 유효하지 않은 상태로 비밀번호 2를 작성하는 경우
				if (!signUpCheck.pwd1 && $("#checkPwd").val().length > 0) {
					swal("유효한 비밀번호를 작성해 주세요.");
					// header include 때문에 사용 가능
					$pwd2.val("");
					$pwd1.focus();
				} else if (signUpCheck.pwd1
						&& $("#checkPwd").val().length > 0) {
					if ($("#newPwd").val().trim() != $("#checkPwd").val().trim()) {
						$("#check2").text("비밀번호 불일치").css(
								"color", "red");
						signUpCheck.pwd2 = false;
					} else {
						$("#check2").text("비밀번호 일치").css(
								"color", "green");
						signUpCheck.pwd2 = true;
					}
				}

		});
		

	
			
	
	</script>
	
	
	
	
	
	<%@ include file="../common/footer.jsp"%>
</body>
</html>