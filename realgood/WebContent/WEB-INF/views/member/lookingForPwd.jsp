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
            <form action="<%=request.getContextPath()%>/member/purchase.do" method="POST"
                onsubmit="return validate();">
                <table class="table table-hover">
                    <h1>비밀번호 찾기</h1>
                    <div class="row mb-3 form-row">
                    	<tr>
                    		<td>이름</td>
                    		<td><input type="text" name="name" required></td>
                        </tr>
                </table>
            </form>
        </div>
    </div>
    <%@ include file="../common/footer.jsp"%><br>
</html>