<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>

	input[type="number"]::-webkit-outer-spin-button,
	input[type="number"]::-webkit-inner-spin-button {
	    -webkit-appearance: none;
	    margin: 0;
	}

</style>
</head>
<body>
<%@ include file="../common/header.jsp"%>
	<div class="py-5 text-center">
		<h2>회원 가입</h2>
	</div>

	<div class="row">
		<div class="col-md-6 offset-md-3">

			<form class="form-horizontal" role="form" method="post"
				action="<%=request.getContextPath()%>/member/signup.do"
				name="signUpForm"=>

				<!-- 아이디 -->
				<div class="row mb-5 form-row">
					<div class="col-md-3">
						<label for="id">* 아이디</label>
					</div>
					<div class="col-md-6">
						<input type="email" class="form-control" name="id" id="id"
							maxlength="22" placeholder="아이디를 입력하세요" autocomplete="off"
							required> <input type="hidden" name="idDup" id="idDup"
							value="false">
					</div>
					<!-- ajax 중복검사 시 필요없음 -->
					<div class="col-md-3">
						<button type="button" class="btn btn-primary" id="idDupCheck">중복검사</button>
						<!--  duplicate - 중복의 -->
					</div>

				</div>



				<!-- 비밀번호 -->
				<div class="row mb-3 form-row">
					<div class="col-md-3">
						<label for="pwd1">* 비밀번호</label>
					</div>
					<div class="col-md-6">
						<input type="password" class="form-control" id="pwd1" name="pwd1"
							maxlength="12" placeholder="대소문자,숫자로 이뤄진  6자 이상 12이하" required>
					</div>

					<div class="col-md-6 offset-md-3">
						<span id="checkPwd1">&nbsp;</span>
					</div>
				</div>

				<br>
				<!-- 비밀번호 확인 -->
				<div class="row mb-3 form-row">
					<div class="col-md-3">
						<label for="pwd2">* 비밀번호 확인</label>
					</div>
					<div class="col-md-6">
						<input type="password" class="form-control" id="pwd2"
							maxlength="12" placeholder="비밀번호 확인" required>
					</div>

					<div class="col-md-6 offset-md-3">
						<span id="checkPwd2">&nbsp;</span>
					</div>
				</div>
				<br>



				<!-- 이름 -->
				<div class="row mb-3 form-row">
					<div class="col-md-3">
						<label for="name">* 이름</label>
					</div>
					<div class="col-md-6">
						<input type="text" class="form-control" id="name" name="name"
							required>
					</div>

					<div class="col-md-6 offset-md-3">
						<span id="checkName">&nbsp;</span>
					</div>
				</div>



				<!-- 주민번호 -->
				<!-- <div class="row mb-3 form-row" id="juminNumber">
					<label for="juminNumber" class="col-md-3 control-label">*
						주민 번호</label>
					<div class="col-md-3">
						<input type="tel" class="form-control onlyNumber" id="jumin_front"
							name="juminFront" data-rule-required="true" placeholder="숫자만 "
							maxlength="6">
					</div>

					<div class="col-md-3">
						<input type="tel" class="form-control onlyNumber" id="jumin_rear"
							name="juminRear" data-rule-required="true" placeholder="입력하세요."
							maxlength="7">
					</div>
				</div> -->


				<!-- 별명 -->
				<div class="row mb-3 form-row">
					<div class="col-md-3">
						<label for="nickName">* 별명</label>
					</div>
					<div class="col-md-6">
						<input type="text" class="form-control" id="nickName"
							name="nickName" required>
					</div>

					<div class="col-md-6 offset-md-3">
						<span id="checkNikcName">&nbsp;</span>
					</div>
				</div>

				<!-- 전화번호 -->
				<div class="row mb-3 form-row">
					<div class="col-md-3">
						<label for="phone1">* 전화번호</label>
					</div>
					<!-- 전화번호1 -->
					<div class="col-md-3">
						<select class="custom-select" id="phone1" name="phone1" required>
							<option>010</option>
							<option>011</option>
							<option>016</option>
							<option>017</option>
							<option>019</option>
						</select>
					</div>

					<!-- 전화번호2 -->
					<div class="col-md-3">
						<input type="number" class="form-control phone" id="phone2"
							maxlength="4" name="phone2" required>
					</div>

					<!-- 전화번호3 -->
					<div class="col-md-3">
						<input type="number" class="form-control phone" id="phone3"
							maxlength="4" name="phone3" required>
					</div>

					<div class="col-md-6 offset-md-3">
						<span id="checkPhone">&nbsp;</span>
					</div>
				</div>


				<div class="form-group">
					<label for="inputGender" class="col-lg-2 control-label">성별</label>
					<div class="col-lg-10">
						<select class="form-control" id="gender" name="gender">
							<option value="M">남</option>
							<option value="F">여</option>
						</select>
					</div>
				</div>


				<div class="form-group">
					<label for="inputEmailReceiveYn" class="col-lg-2 control-label">이메일
						수신여부</label>
					<div class="col-lg-10">
						<label class="radio-inline"> <input type="radio"
							id="emailReceiveYn" name="emailReceive" value="Y" checked>
							동의합니다.
						</label> <label class="radio-inline"> <input type="radio"
							id="emailReceiveYn" name="emailReceive" value="N"> 동의하지
							않습니다.
						</label>
					</div>
				</div>
				<div class="form-group">
					<label for="inputPhoneNumber" class="col-lg-2 control-label">SMS
						수신여부</label>
					<div class="col-lg-10">
						<label class="radio-inline"> <input type="radio"
							id="smsReceiveYn" name="smsReceive" value="Y" checked>
							동의합니다.
						</label> <label class="radio-inline"> <input type="radio"
							id="smsReceiveYn" name="smsReceive" value="N"> 동의하지 않습니다.
						</label>
					</div>
				</div>


				<hr class="mb-4">
				<button class="btn btn-primary btn-lg btn-block" type="submit">가입하기</button>
			</form>
		</div>
	</div>
	<br>
	<br>

<%@ include file="../common/footer.jsp"%>

	<script>
		// 각 유효성 검사 결과를 저장할 객체
		var signUpCheck = {
			"pwd1" : false,
			"pwd2" : false,
			"name" : false,
			"nickName" : false,
			"phone2" : false,
			"phone3" : false,
			"jumin_front" : false,
			"jumin_rear" : false,
			"gender" : false

		};

		// jQuery 변수 : 변수에 직접적으로 jQuery메소드를 사용할 수 있음.
		var $pwd1 = $("#pwd1");
		var $pwd2 = $("#pwd2");
		var $pwd = $("#pwd1, #pwd2");
		var $name = $("#name");
		var $nickName = $("#nickName");
		var $phone2 = $("#phone2");
		var $phone3 = $("#phone3");
		var $jumin_front = $("#jumin_front");
		var $jumin_rear = $("#jumin_rear");
		var $gender = $("#gender");

		$("#idDupCheck").click(function() {
			// 팝업창을 이용하여 아이디 유효성, 중복검사 진행
			window.open("idDupForm.do", "idDupForm", "width=450, height=250");
			// 팝업창 요청 주소		팝업창의 이름(name)		팝업창 크기 설정  << 새로 입력 되는 것들
		});

		$("#id").on("input", function() {

			if ($("#idDup").val() == "true") {
				// hidden 타입 태그 값이 true(중복 검사가 성공적으로 진행)일 때
				// -> 검사 완료된 값을 수정하려고 할 경우

				$("#idDup").val("false");
				signUpCheck.id = false;

				// 중복 검사 후 화면에서 아이디를 바꿨을 때 가입 진행이 안 되게 하기 위해
			}
		});

		// 비밀번호 유효성 및 일치 검사
		$pwd
				.on("input",
						function() {
							//영어 대,소문자 + 숫자, 총 6~12글자
							var regExp = /^[A-Za-z0-9]{6,12}$/;

							// 비밀번호1 유효성 검사
							if (!regExp.test($("#pwd1").val())) {
								$("#checkPwd1").text("비밀번호 형식이 유효하지 않습니다.")
										.css("color", "red");
								signUpCheck.pwd1 = false;
							} else {
								$("#checkPwd1").text("유효한 비밀번호 형식입니다.").css(
										"color", "green");
								signUpCheck.pwd1 = true;
							}

							// 비밀번호1이 유효하지 않은 상태로 비밀번호 2를 작성하는 경우
							if (!signUpCheck.pwd1 && $pwd2.val().length > 0) {
								swal("유효한 비밀번호를 작성해 주세요.");
								// header include 때문에 사용 가능
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

		// 이름 유효성 검사
		$name.on("input", function() {
			var regExp = /^[가-힣]{2,}$/; // 한글 두 글자 이상
			// this
			if (!regExp.test($("#name").val())) {
				$("#checkName").text("한글 두 글자 이상을 입력하세요.").css("color", "red");
				signUpCheck.name = false;
			} else {
				$("#checkName").text("유효한 이름 형식입니다.").css("color", "green");
				signUpCheck.name = true;
			}

		});

		// 전화번호 관련
		$(".phone").on("input", function() {

			// 전화번호 input 태그에 4글자 이상 입력하지 못하게 하는 이벤트
			if ($(this).val().length > $(this).prop("maxLength")) {
				$(this).val($(this).val().slice(0, $(this).prop("maxLength")));
			}

			// 전화번호 유효성 검사
			var regExp1 = /^\d{3,4}$/; // 숫자 3~4 글자
			var regExp2 = /^\d{4,4}$/; // 숫자 4 글자

			if (!regExp1.test($phone2.val()) || !regExp2.test($phone3.val())) {
				$("#checkPhone").text("전화번호가 유효하지 않습니다.").css("color", "red");
				signUpCheck.phone2 = false;
			} else {
				$("#checkPhone").text("유효한 전화번호입니다.").css("color", "green");
				signUpCheck.phone2 = true;
			}
		});

		// 닉네임 유효성 검사
		$nickName.on("input",
				function() {
					var regExp = /^[가-힣]{2,}$/; // 한글 두 글자 이상
					// this
					if (!regExp.test($("#nickName").val())) {
						$("#checkNikcName").text("한글 두 글자 이상을 입력하세요.").css(
								"color", "red");
						signUpCheck.name = false;
					} else {
						$("#checkNikcName").text("유효한 별명 형식입니다.").css("color",
								"green");
						signUpCheck.name = true;
					}

				});

		// submit 동작
		function validate() {

			// 아이디 중복 검사 결과 확인
			if ($("#idDup").val() == "true")
				signUpCheck.id = true;
			else
				signUpCheck.id = false;

			for ( var key in signUpCheck) {
				if (!signUpCheck[key]) {
					// 인덱스 

					var msg;
					switch (key) {
					case "id":
						msg = "아이디가 ";
						break;
					case "pwd1":
					case "pwd2":
						msg = "비밀번호가 ";
						break;
					case "name":
						msg = "이름이 ";
						break;
					case "phone2":
						msg = "전화번호가 ";
						break;
					case "nickName":
						msg = "닉네임이 ";
						break;
					case "juminNumber":
						msg = "주민번호가 ";
						break;
					}

					alert(msg + "유효하지 않습니다.");
					var el = "#" + key;
					$(el).focus();
					return false;
				}
			}
		}
	</script>
</body>
</html>