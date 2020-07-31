<%@page import="java.util.Arrays"%>
<%@page import="java.sql.Array"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지</title>
<style>
     
</style>
</head>
<body>
	<%@ include file="../common/header.jsp"%>
	 <div class="row my-5">
         <%@ include file="sideMenu.jsp"%>
         <%
         // header.jsp에서 Member가 import 되어 있으므로 현재 페이지에서 import할 필요 없음.
         // header.jsp에 이미 로그인 정보가 담겨있는 loginMember 변수가 선언되어 있음.
         //Member loginMember= (Member)request.getAttribute("member");
         
         // 하나의 String
         String[] phone = loginMember.getTel().split("-");
         String[] ynReceive = new String[4];
         String id = loginMember.getId();
         
         Arrays.fill(ynReceive," ");
        if(loginMember.getEmailReceive().equals("Y")){
        	 ynReceive[0] = "checked";
         }else{
        	 ynReceive[1] = "checked";
         }
         

         if(loginMember.getSmsReceive().equals("Y")){
        	 ynReceive[2] = "checked";
         }else{
        	 ynReceive[3] = "checked";
         }
         
         %>
         <div class="col-sm-8">
             <form action="<%=request.getContextPath()%>/member/myPage.do" method="POST">
                <table class="table table-hover">
                    <h1>내 정보</h1>
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
                        <td>전화번호</td>
                        <td><!-- 전화번호 -->
				<div class="row mb-3 form-row">
					<!-- 전화번호1 -->
					<div class="col-md-3">
						<select class="custom-select" id="phone1" name="phone1" required>
							<option>010</option>
							<option>011</option>
							<option>016</option>
							<option>017</option>
							<option>019</option>
						</select>
					</div>
					<script>
                        $.each($("#phone1 > option"), function(index, item){
                           // index : 현재 접근중인 인덱스 번호
                           // item : 현재 접근중인 요소
                           if($(item).text() == "<%=phone[0]%>") {
                              // 현재 접근한 요소의 내용이 
                              // phone[0] 인데스 값과 같다면
                              
                              $(item).prop("selected", true);
                           }
                           
                        });
                     </script>
					<!-- 전화번호2 -->
					<div class="col-md-3">
						<input type="number" class="form-control phone" id="phone2"
							maxlength="4" name="phone2" value="<%=phone[1]%>" required>
					</div>

					<!-- 전화번호3 -->
					<div class="col-md-3">
						<input type="number" class="form-control phone" id="phone3"
							maxlength="4" name="phone3" value="<%=phone[2]%>" required>
					</div>
				</div></td>
                    </tr>
                    <tr>
                        <td>성별</td>
                        <td><%=loginMember.getGenderName()%></td>
                    </tr>
                    <tr>
                        <td>회원구분</td>
                        <td><%=loginMember.getGradeName()%></td>
                    </tr>
                    <tr>
                        <td>이메일 수신여부</td>
                        <td><div class="form-group">
                            <div class="col-lg-10">
                                <label class="radio-inline"> <input type="radio"
                                    id="emailReceiveYn" name="emailReceive" value="Y" <%=ynReceive[0]%>>
                                    동의합니다.
                                </label> <label class="radio-inline"> <input type="radio"
                                    id="emailReceiveYn" name="emailReceive" value="N" <%=ynReceive[1]%>> 동의하지
                                    않습니다.
                                </label>
                            </div>
                        </div></td>
                    </tr>
                    <tr>
                        <td>SNS 수신여부</td>
                        <td><div class="form-group">
                            <div class="col-lg-10">
                                <label class="radio-inline"> <input type="radio"
                                    id="smsReceiveYn" name="smsReceive" value="Y" <%=ynReceive[2]%>>
                                    동의합니다.
                                </label> <label class="radio-inline"> <input type="radio"
                                    id="smsReceiveYn" name="smsReceive" value="N" <%=ynReceive[3]%>> 동의하지 않습니다.
                                </label>
                            </div>
                        </div></td>
                    </tr>
                    <tr>
                        <td><button class="btn btn-primary">변경하기</button>
                            <a href="myPage.do" class="btn btn-secondary" >취소하기</a>
                            </td>
                    </tr>
					
                </table>
             </form>
         </div>
        </div>
     
     <script>
     // 전화번호 관련
		$(".phone").on("input", function() {

			// 전화번호 input 태그에 4글자 이상 입력하지 못하게 하는 이벤트
			if ($(this).val().length > $(this).prop("maxLength")) {
				$(this).val($(this).val().slice(0, $(this).prop("maxLength")));
			}

			// 전화번호 유효성 검사
			var regExp1 = /^\d{3,4}$/; // 숫자 3~4 글자
			var regExp2 = /^\d{4,4}$/; // 숫자 4 글자

			if (!regExp1.test($phone2.val()) || !regExp2.test($phone3.val())) {
				$("#checkPhone").text("전화번호가 유효하지 않습니다.").css("color", "red");
				signUpCheck.phone2 = false;
			} else {
				$("#checkPhone").text("유효한 전화번호입니다.").css("color", "green");
				signUpCheck.phone2 = true;
			}
		});
		</script>



     <%@ include file="../common/footer.jsp"%><br>
</body>
</html>