<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="<%=request.getContextPath()%>/resources/css/findIdPw.css" rel="stylesheet">
</head>
<body class="text-center">
<%@ include file="../common/header.jsp"%>
    <form class="form-findId">
  <img class="mb-4" src="../assets/brand/bootstrap-solid.svg" alt="" width="72" height="72">
  <h1 class="h3 mb-3 font-weight-normal">아이디 찾기</h1><br><br>
  <label for="inputName" class="sr-only">Find name</label>
  <input type="text" id="inputName" class="form-control" placeholder="이름 입력(필수)" required autofocus>
  <label for="inputPNum" class="sr-only">Find PNum</label>
  <input type="text" id="inputPNum" class="form-control" placeholder="주민번호 입력" required><br>

  <button class="btn btn-fi btn-primary btn-block" type="submit">완료</button>
  <p class="mt-5 mb-3 text-muted">&copy; 2020-2023</p>
</form>
<%@ include file="../common/footer.jsp"%>
</body>
</html>