<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
            <form action="<%=request.getContextPath()%>/member/removeMember.do" method="POST"
                onsubmit="return validate();">
                <table class="table table-hover">
                    <h1>내가 작성한 게시글</h1>
                    <div class="row mb-3 form-row">
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