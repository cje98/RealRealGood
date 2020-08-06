<%@page import="com.kh.realgood.member.model.dto.BuyList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	List<BuyList> list = (List<BuyList>)request.getAttribute("bList");
	int test = (Integer)(session.getAttribute("test"));
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>구매내역</title>
</head>
<body>
    <%@ include file="../common/header.jsp"%>
    <div class="row my-5">
        <%@ include file="sideMenu.jsp"%>
        <div class="col-sm-5">
            <form action="<%=request.getContextPath()%>/member/purchase.do" method="POST"
                onsubmit="return validate();">
                    <h1>구매내역</h1>
                    <div class="row mb-3 form-row">
                <table class="table table-hover">
                    	<tr>
                    		<td>구매번호</td> 
                            <td>가게명</td>
                            <td>구매 메뉴</td>
                            <td>구매 날짜</td>
                            <td>큐알 코드</td>
                            <td>사용 날짜</td>
                        </tr>
                        <% if(list.isEmpty()){ %>
							<tr>
								<td colspan="6">구매한 내역이 존재하지 않습니다.</td>
							</tr>
						<% }else{ %>
	               		    <% for(int i = 0; i < list.size(); i++) { %>
		                        <tr>
		                            <td><%=i+1%></td>
		                            <td><a href="<%=request.getContextPath()%>/store/detail.do?storeNum=<%= list.get(i).getBuyStoreNum() %>" class="getStoreNo"><%= list.get(i).getBuyStoreName()%></a></td>
		                            <td><%= list.get(i).getBuyMenuName()%></td>
		                            <td><%= list.get(i).getBuyDate()%></td>
		                            <td><a href="#" onclick="window.open('<%=request.getContextPath()%>/member/qrcodeCreate.do?qrNum=<%=list.get(i).getBuyQrCodeNum()%>','qrCode','resizable=no width=250 height=250');return false">사용하기</a></td>
		                            <%if(list.get(i).getBuyUsed() != null){ %>
		                            <td><%= list.get(i).getBuyUsed()%></td>
		                            <%}else { %>
		                            <td>사용 안함</td>
		                            <%} %>
		                        </tr>
	                        <% } %>
                        <% } %>
                	</table>
                </div>
            </form>
        </div>
    </div>
    <script>
    	
    </script>
    <%@ include file="../common/footer.jsp"%><br>
</body>
</html>