<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="<%=request.getContextPath()%>/resources/css/findIdPw.css" rel="stylesheet">
</head>
<body>
    <%@ include file="../common/header.jsp"%>
    <div class="row my-5">
        <%@ include file="sideIdPwdMenu.jsp"%>
        <div class="col-sm-5">
            <form action="<%=request.getContextPath()%>/member/lookingForFormId.do" method="POST"
                onsubmit="return validate();">
                <table class="table table-hover">
                    <h1>아이디 찾기</h1>
                    <div class="row mb-3 form-row">
                    	<tr>
                    		<td>이름</td>
                    		<td><input type="text" id="name" name="name" required></td>
                        </tr>
                        <tr>
                        	<td><label for="phone1">전화번호</label></td>
                        	<td><!-- 전화번호 -->
				<div class="row mb-4 form-row">
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
				</div></td>
                        </tr>
                        	<%
								List<String> list = (List<String>)request.getAttribute("idList");
	                        	if (list == null){ 
	                        %>   	
	                        <tr>
	                        	<td>정보를 입력해주세요.</td>
	                        	<span></span>
	                        	<%}else { %>
	                        	<td>찾은 결과 : </td>
	                        	<td>
	                        	<script>
	                        	$("#name").value(name);
	                        	$("#phone1").value(phone1);
	                        	$("#phone2").value(phone2);
	                        	$("#phone3").value(phone3);
	                        	</script>
	                        	<% for(int i=0; i<list.size(); i++){ %>
	                        	<span><%= list.get(i) %><br></span>
	                        	<%} %>
	                        	<%} %>
                        	
                        	
                        	</td>
                        </tr>
                        <tr>
                        	<td><button class="btn btn-primary">아이디 찾기</button>
                        </tr>
                </table>
            </form>
        </div>
    </div>
    <script>
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
			signUpCheck.phone3 = false;
		} else {
			$("#checkPhone").text("유효한 전화번호입니다.").css("color", "green");
			signUpCheck.phone2 = true;
			signUpCheck.phone3 = true;
		}
	});
 
	
    </script>
    <%@ include file="../common/footer.jsp"%><br>
</body>
</html>