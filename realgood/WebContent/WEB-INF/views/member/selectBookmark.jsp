<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- <%
	List<BuyList> list = (List<BuyList>)request.getAttribute("bList");
%> --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>즐겨찾기</title>
</head>
<body>
    <%@ include file="../common/header.jsp"%>
    <div class="row my-5">
        <%@ include file="sideMenu.jsp"%>
        <div class="col-sm-5">
            <form action="<%=request.getContextPath()%>/member/selectBookmark.do" method="POST"
                onsubmit="return validate();">
                    <h1>즐겨찾기</h1>
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