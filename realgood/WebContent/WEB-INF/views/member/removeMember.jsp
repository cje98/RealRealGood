<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>회원탈퇴</title>
</head>

<body>
    <%@ include file="../common/header.jsp"%>
    <div class="row my-5">
        <%@ include file="sideMenu.jsp"%>
        <div class="col-sm-5">
            <form action="<%=request.getContextPath()%>/member/removeMember.do" method="POST"
                onsubmit="return validate();">
                <table class="table table-hover">
                    <h1>회원 탈퇴</h1>
                    <div class="row mb-3 form-row">
                        <tr>
                            <td>아이디</td>
                            <td><%=loginMember.getId()%></td>
                        </tr>
                        <tr>
                            <td>이름</td>
                            <td><%=loginMember.getMame()%></td>
                        </tr>
                        <tr>
                            <td>닉네임</td>
                            <td><%=loginMember.getNickName()%></td>
                        </tr>
                        <tr>
                            <td>회원등급</td>
                            <td><%=loginMember.getGradeName()%></td>
                        </tr>
                        <tr>
                            <td><button class="btn btn-primary">탈퇴하기</button>
                        </tr>
                    </div>
                </table>
                <h2> 회원 탈퇴 시 게시글은 자동 삭제가 되지 않습니다.</h2>
                <h2> 회원 탈퇴 시 계정 복구가 불가능 합니다.</h2>
            </form>
        </div>
    </div>
    <script>
        function validate() {
        	return confirm("정말로 탈퇴하시겠습니까?");
            }
    </script>
    <%@ include file="../common/footer.jsp"%><br>
</body>

</html>