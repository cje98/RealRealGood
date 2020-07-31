<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
	<%
		String qrImg = (String)(request.getSession().getAttribute("qrImg"));
	%>
	<%if(qrImg != null) {%>
		document.write("<img src=\"<%=request.getContextPath()%>/resources/qrcode/<%=qrImg%>\">");
	<%}%>
	<%
		session.removeAttribute("qrImg");
	%>
</script>
</head>
<body>
</body>
</html>