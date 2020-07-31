<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="<%=request.getContextPath()%>/resources/css/findIdPw.css" rel="stylesheet">
</head>
<body>
    <%@ include file="../common/header.jsp"%>
    <div class="row my-5">
        <%@ include file="sideIdPwdMenu.jsp"%>
        <div class="col-sm-5">
            <form action="newPwd.do" method="POST" onsubmit="return validate();">
                    <h1>비밀번호 찾기</h1>
                <table class="table table-hover">
                    <div class="row mb-3 form-row">
                    	<tr>
<<<<<<< HEAD
                    		<td>이름</td>
                    		<td><input type="text" name="name" required></td>
=======
                    		<td><label for="id">아이디</label></td>
                    		<td>
                    			<input type="email" class="form-control" name="id" id="id"
								maxlength="22" placeholder="아이디를 입력하세요" autocomplete="off"
								required> 
							</td>
							<td>
								<span id="idCheck"></span>
							</td>
>>>>>>> branch 'master' of https://github.com/cje98/RealRealGood.git
                        </tr>
                        <tr>
                        	<td><label for="mailCertify">메일인증</label></td>
                    		<td>
                    			<input type="text" class="form-control" name="mailCertify" id="mailCertify"
								maxlength="22" placeholder="인증번호를 입력해주세요" autocomplete="off"
								required>
								<input type="hidden" name="mailCertifyCode" id="mailCertifyCode"
								value="false">
								<span id="mailCertifyNote"></span>
							</td>
							<td>
								<button type="button" class="btn btn-primary" id="mailCertifyBtn">코드받기</button>
								<button type="submit" class="btn btn-primary" id="mailCertifyEnterBtn">확인</button>
								<div id="loadingImg"></div>
							</td>
                        </tr>
                    </div>
                </table>
              
            </form>
        </div>
    </div>
    
    <script>
    $("#id").on("input", function() {
		
   
    	
		$.ajax({
			url : "selectId.do",
			type : "post",
			data : {"id":$("#id").val()},
			success : function(result){
				if(result > 0)$("#idCheck").text("아이디가 존재합니다.").css("color","green");
				else $("#idCheck").text("아이디가 존재하지 않습니다.").css("color","red");
			}, error : function(result){
				console.log("ajax 통신불가");
			}
			
		});
		
	});
	
	$("#mailCertifyBtn").on("click", function(){
		if($("#idCheck").text().trim().length != 0) {
			loadingPrint("loading.gif");
			$.ajax({
				url : "mailSend.do",
				type : "post",
				data : {"toEmail" : $("#id").val()},
				success : function(result) {
					$("#mailCertifyCode").text(result);
					swal("입력하신 메일로 인증코드를 전송하였습니다 확인 후 작성해주세요");
					$("#mailCertifyBtn").text("재전송");
				}, error : function() {
					console.log("ajax 통신 에러 발생");
					swal("에러 발생으로 인해 처음부터 다시 작성 바랍니다.");
				}
			});
			
		} else {
			alert("아이디를 먼저 작성해주세요.");
		}
	});
	
	
	//$("#mailCertifyEnterBtn").on("click", function(){
		
	function vaildate(){
		if($("#mailCertify").val() == $("#mailCertifyCode").text()) {
			alert("인증 번호가 일치 합니다");
			$("#mailCertify").prop("disabled","true");
			$("#mailCertifyBtn").prop("disabled","true");
			$("#id").prop("readonly","true");

			$("#mailCertifyEnterBtn").prop("disabled","true");
			signUpCheck.mailCode = true;
			return true;
		} else {
			alert("인증 번호가 일치하지 않습니다");
			signUpCheck.mailCode = false;
			return false;
		}
		//var id = $("#id").val();
		//location.href="newPwd.do?id=" + id;
	}
	//});
	
	
	
	function loadingPrint(imageName) {
	    LoadingWithMask("<%=request.getContextPath()%>/resources/images/common/" + imageName);
	    setTimeout("closeLoadingWithMask()", 3000);
	}
	 
	function LoadingWithMask(gif) {
	    //화면의 높이와 너비를 구합니다.
	    var maskHeight = $(document).height();
	    var maskWidth  = window.document.body.clientWidth;
	     
	    //화면에 출력할 마스크를 설정해줍니다.
	    var mask       ="<div id='mask' style='position:absolute; z-index:9000; background-color:#000000; display:none; left:0; top:0;'></div>";
	    var loadingImg ='';
	      
	    loadingImg +=" <img src='"+ gif +"' style='position: absolute; display: block; margin: 0px auto;'/>";
	    //화면에 레이어 추가
	    $('body')
	        .append(mask)
	 
	    //마스크의 높이와 너비를 화면 것으로 만들어 전체 화면을 채웁니다.
	    $('#mask').css({
	            'width' : maskWidth,
	            'height': maskHeight,
	            'opacity' :'0.3'
	    });
	  
	    //마스크 표시
	    $('#mask').show();
	  
	    //로딩중 이미지 표시
	    $('#loadingImg').append(loadingImg);
	    $('#loadingImg').show();
	}
	 
	function closeLoadingWithMask() {
	    $('#mask, #loadingImg').hide();
	    $('#mask, #loadingImg').empty(); 
	}
    </script>
    
    
    
    <%@ include file="../common/footer.jsp"%><br>
</html>