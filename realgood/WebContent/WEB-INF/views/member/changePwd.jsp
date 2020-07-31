<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>패스워드 변경</title>
</head>

<body>
    <%@ include file="../common/header.jsp"%>
    <div class="row my-5">
        <%@ include file="sideMenu.jsp"%>
        <div class="col-sm-7">
            <form action="<%=request.getContextPath()%>/member/changePwd.do" method="POST" onsubmit="return validate();">
                <table class="table table-hover">
                    <h1>비밀번호 변경</h1>
                    <div class="row mb-3 form-row">
                        <tr>
                            <td>현재 비밀번호</td>
                            <td><input type="password" placeholder="현재 비밀번호 입력" size="50" name="pwd"></td>
                        </tr>
                        <tr>
                            <td>신규 비밀번호</td>
                            <td><input type="password" placeholder="신규 비밀번호 입력" size="50" id="pw1" name="pw1"> </td>
                        </tr>
                        <tr>
                            <td> </td>
                            <td><span id="pw1Ck" name="pw1Ck">영어 + 숫자, 총 6~12글자를 입력해주세요.</span></td>
                        </tr>
                        <tr>
                            <td>신규 비밀번호 확인</td>
                            <td><input type="password" placeholder="신규 비밀번호 확인" size="50" id="pw2" name="pw2"> </td>
                        </tr>
                        <tr>
                            <td> </td>
                            <td><span id="pw2Ck" name="pw2Ck"></span></td>
                        </tr>
                        <tr>
                            <td><button class="btn btn-primary">변경하기</button>
                                <button type="reset" class="btn btn-secondary">취소하기</button></td>

                        </tr>
                    </div>
                </table>
            </form>
        </div>
        <script>
            var pwdCheck = {
                "pw" : true,
                "pw1": false,
                "pw2": false
            };
            var $pw1 = $("#pw1");
            var $pw2 = $("#pw2");
            var $pw = $("#pw1, #pw2");
            $pw
                .on("input",
                    function () {
                        //영어 대,소문자 + 숫자, 총 6~12글자
                        var regExp = /^[A-Za-z0-9]{6,12}$/;

                        // 비밀번호1 유효성 검사
                        if (!regExp.test($("#pw1").val())) {
                            $("#pw1Ck").text("영어 + 숫자, 총 6~12글자를 입력해주세요.")
                                .css("color", "red");
                            pwdCheck.pw1 = false;
                        } else {
                            $("#pw1Ck").text("사용 가능한 패스워드입니다.").css(
                                "color", "green");
                            pwdCheck.pw1 = true;
                        }

                        // 비밀번호1이 유효하지 않은 상태로 비밀번호 2를 작성하는 경우
                        if (!pwdCheck.pw1 && $pw2.val().length > 0) {
                            swal("유효한 비밀번호를 작성해 주세요.");
                            // header include 때문에 사용 가능
                            $pw2.val("");
                            $("#pw2Ck").text("비밀번호가 다릅니다.").css(
                                "color", "red");
                            pwdCheck.pw2 = false;
                            $pw1.focus();
                        } else if (pwdCheck.pw1
                            && $pw2.val().length > 0) {
                            if ($("#pw1").val().trim() != $("#pw2").val()
                                .trim()) {
                                $("#pw2Ck").text("비밀번호가 다릅니다.").css(
                                    "color", "red");
                                pwdCheck.pw2 = false;
                            } else {
                                $("#pw2Ck").text("비밀번호 같습니다.").css(
                                    "color", "green");
                                pwdCheck.pw2 = true;
                            }
                        }

                    });
            function validate() {
                for ( var key in pwdCheck) {
				if (!pwdCheck[key]) {

					var msg;
					switch (key) {
					case "pw1":
						msg = "신규 비밀번호를 ";
						break;
					case "pw2":
						msg = "신규 비밀번호 확인을 ";
						break;
					}
					alert(msg + "잘못 입력 하셨습니다.");
					var el = "#" + key;
					$(el).focus();
					return false;
				}
            }
        }
        </script>
        <%@ include file="../common/footer.jsp"%><br>
    </div>
</body>

</html>