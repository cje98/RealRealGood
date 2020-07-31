<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.kh.realgood.store.model.dto.StoreImg"%>
<%@page import="com.kh.realgood.store.model.dto.Store"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	List<Store> list = (List<Store>) session.getAttribute("addrArr");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>store list page</title>
</head>
<body>
	<c:import url="../common/header.jsp"></c:import>
	<%
		if (!list.isEmpty()) {
			for (int i = 0; i < list.size(); i++) {
				Store store = list.get(i);
				if (i == 0) {
	%>
	<div class="row mb-2">
		<%
			}
					if (i % 2 == 0) {
		%>
		<div class="col-md-6">
			<div
				class="row no-gutters border rounded overflow-hidden flex-md-row mb-4 shadow-sm h-md-250 position-relative">
				<div class="col p-4 d-flex flex-column position-static">
					<strong class="d-inline-block mb-2 text-primary"><%=store.getStoreGroupName()%></strong>
					<h3 class="mb-0"><%=store.getStoreName()%></h3>
					<div class="mb-1 text-muted"><%=store.getStoreTel()%></div>
					<p class="card-text mb-auto"><%=store.getStoreContent()%></p>
					<p class="card-text mb-auto"><%=store.getStoreAddress()%></p>
					<a href="#" class="stretched-link">상세 보기</a>
					
				</div>
				<div class="col-auto d-none d-lg-block">
					<img width="200" height="250"
						src="<%=request.getContextPath()%>/resources/images/<%=store.getStoreTitleImg()%>" />
				</div>
			</div>
		</div>
		<%
			} else {
		%>
		<div class="col-md-6">
			<div
				class="row no-gutters border rounded overflow-hidden flex-md-row mb-4 shadow-sm h-md-250 position-relative">
				<div class="col p-4 d-flex flex-column position-static">
					<strong class="d-inline-block mb-2 text-success"><%=store.getStoreGroupName()%></strong>
					<h3 class="mb-0"><%=store.getStoreName()%></h3>
					<div class="mb-1 text-muted"><%=store.getStoreTel()%></div>
					<p class="mb-auto"><%=store.getStoreContent()%></p>
					<p class="card-text mb-auto"><%=store.getStoreAddress()%></p>
					<a href="#" class="stretched-link">상세 보기</a>
				</div>
				<div class="col-auto d-none d-lg-block">
					<img width="200" height="250"
						src="<%=request.getContextPath()%>/resources/images/<%=store.getStoreTitleImg()%>" />
				</div>
			</div>
		</div>
		<%
			}
					if (i == (list.size() - 1)) {
		%>
	</div>
	<%
		}
	%>
	<br>
	<%
		}
		}
	%>
	<c:import url="../common/footer.jsp"></c:import>
</body>
</html>