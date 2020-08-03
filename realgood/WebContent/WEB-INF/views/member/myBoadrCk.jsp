<%@page import="com.kh.realgood.member.model.dto.Board"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	List<Board> list = (List<Board>)request.getAttribute("cList");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내가 작성한 게시글</title>
</head>
<body>
    <%@ include file="../common/header.jsp"%>
    <div class="row my-5">
        <%@ include file="sideMenu.jsp"%>
        <div class="col-sm-5">
            <form action="<%=request.getContextPath()%>/member/myBoadrCk.do" method="POST"
                onsubmit="return validate();">
                <table class="table table-hover">
                    <h1>내가 작성한 게시글</h1>
                    <div class="row mb-3 form-row">
                    <tr>
                    	<td>순번</td>
                    	<td>가게 제목</td>
                    	<td>게시글 내용</td>
                    	<td>조회수</td>
                    	<td>게시글 수정(작성) 날짜</td>
                    </tr>
                    <% if(list.isEmpty()){ %>
							<tr>
								<td colspan="6">작성한 게시글이 존재하지 않습니다.</td>
							</tr>
						<% }else{ %>
	               		    <% for(int i = 0; i < list.size(); i++) { %>
		                        <tr>
		                            <td><%=i+1%></td>
		                            <td><a href="<%=request.getContextPath()%>/store/detail.do?storeNum=<%= list.get(i).getStoreNum() %>" class="getStoreNo"><%= list.get(i).getStroeName()%></a></td>
		                            <%if(list.get(i).getBoardContent().length() > 10){ %>
		                            <td><%= list.get(i).getBoardContent().substring(0,9)%>...</td>
		                            <% }else{ %>
		                            <td><%= list.get(i).getBoardContent()%></td>
		                            <% } %>
		                            <td><%= list.get(i).getReadCount()%></td>
		                            <td><%= list.get(i).getBoardModifyDt()%></td>
		                        </tr>
	                        <% } %>
                        <% } %>
                	</table>
                    </div>
                </table>
            </form>
        </div>
    </div>
    <script>
    </script>
    <%@ include file="../common/footer.jsp"%><br>
</body>
</html>