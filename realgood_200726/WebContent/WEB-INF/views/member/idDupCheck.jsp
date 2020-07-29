<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 중복 검사</title>
</head>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">

<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>


<style>
	body>*{
		margin-left: 75px;
	}
</style>
<body onload="idValue();">
	<h4 class="mt-3">아이디 중복 검사</h4>
	<br>
	<form action="<%= request.getContextPath() %>/member/idDupCheck.do" id="idCheckForm" method="post">
		<input type="email" id="id" name="id">
		<input type="submit" value="중복확인">
	</form>
	<br>
	
	<!-- 사용 가능 여부 메세지 출력 -->
	<span>
	<%
		if(request.getAttribute("result") != null){
			
			int result = (int)request.getAttribute("result");
			
			if(result > 0 ){
		%>
			이미 사용중인 아이디 입니다.
		<% 	}else{ %>
			사용 가능한 아이디 입니다.
		<% 
				}
			}
		%>
		

		
	</span>
	
	<br>
	<br>
	
	<div>
		<input type="button" id="cancel" value="취소" onclick="window.close();">
		<input type="button" id="confirmId" value="확인" onclick="confirmId();">
	</div>
	
	<script type="text/javascript">
		var id;
		var result;
		
		// 팝업창이 오픈 완료 된 후 자동으로 실행되는 함수
		// -> 팝업창 최초 오픈 시 if문이 동작되고
		//    중복 체크 버튼으로 인한 팝업창 재 요청 시 else문이 실행됨. 
		function idValue(){
			
			id = "<%= request.getParameter("id")%>";

			result = <%= request.getAttribute("result") %>;
			
			if(id == "null"){
				id = opener.document.signUpForm.id.value;
				// opener : 팝업창을 열은 부모창
				// document : 문서
				// signUpForm : 부모창 회원가입 form 태그의 name 속성
				// id : 부모창 form 태그 내부에 name속성이 id인 태그 선택
				// value : 해당 input 태그의 값
				// == 부모창 id에 input 태그에 입력된 값 얻어오기
				
			}
			// 부모 폼에 있는 id 값을 팝업창으로 옮기는 것
			document.getElementById("id").value = id;
			//69번줄에 있는 id를 의미
			
			
		}
		
		//확인버튼을 눌렀을 경우 부모창에 전달할 값 제어
		function confirmId(){ // 중복체크 확인 시 회원가입창 아이디 부분 비활성화
		
			// 중복 체크 결과 result가 0 일 경우(중복 X)
			if(result == 0){
				
				opener.document.signUpForm.id.value 
				= document.getElementById("id").value;
				
				// 부모창에 hidden 타입 요소의 value값을 true로 변경
				opener.document.signUpForm.idDup.value = true;
				
			}else{
				
				opener.document.signUpForm.idDup.value = false;
				
			}
			if(opener != null){
				opener.checkForm = null;
				self.close();
			}
		
		}
		
		
		
		$("#idCheckForm").submit(function(){
			var regExp = /^[\w]{4,}@[\w]+(\.[\w]+){1,3}$/;
			if(!regExp.test($("#id").val())){
				alert("유효하지 않은 아이디 형식 입니다.");
				return false;
      }
		});
		
		
	</script>
<body>

</body>
</html>