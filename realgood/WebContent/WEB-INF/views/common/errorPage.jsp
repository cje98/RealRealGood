<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String errorMsg = (String)request.getAttribute("errorMsg");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1 align="center"><%=errorMsg%></h1>
	<div align="center">
		<button onclick="history.back();">이전 페이지로 이동</button>
		<button onclick="location.href='<%=request.getContextPath()%>'">메인 페이지로 이동</button>
	</div>
</body>
</html>