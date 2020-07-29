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
    <form class="form-findPw">
  <h1 class="h3 mb-3 font-weight-normal">비밀번호 찾기</h1><br><br>
  <label for="inputId" class="sr-only">Find Id</label>
  <input type="text" id="inputId" class="form-control" placeholder="아이디를 입력해주세요." required autofocus>
  <label for="inputName" class="sr-only">Find Name</label>
  <input type="text" id="inputName" class="form-control" placeholder="이름을 입력해주세요" required>
  <label for="inputPNum" class="sr-only">Find PNum</label>
  <input type="text" id="inputPNum" class="form-control" placeholder="주민번호 입력해주세요." required><br>


  <button class="btn btn-fi btn-primary btn-block" type="submit">완료</button>
  <p class="mt-5 mb-3 text-muted">&copy; 2020-2023</p>
</form>
<%@ include file="../common/footer.jsp"%>
</body>
</html>