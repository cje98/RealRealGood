<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>가게 정보 등록</title>
<style>
  .boardImg{
    	cursor : pointer;
    }
    
   #corNum1, #corNum2, #corNum3{
   		display : inline-block;
   }
   
   #phone1, #phone2, #phone3{
		display : inline-block;
	}
	
	.storeImg{
		cursor : pointer;
	}
	
</style>

</head>
<body>
 <%@ include file="../common/header.jsp" %>
 	<div class="row my-5">
	<%@ include file="sideMenu.jsp"%>
            
             <div class="col-sm-8">
              <form method="POST" action="storeInsert.do" enctype="multipart/form-data" role="form"
              	class="needs-validation" name="insertForm" onsubmit="return validate();">
								<%-- action="<%=request.getContextPath() %>/store/storeInsert.do" --%>
				<table class="table table-hover">
		            <h1>가게 정보 등록</h1>
		            <tr>
		            	<td><label for="storeName">가게명(필수)</label></td>
		            	<td>
		            		<div class="col-md-8">
		            		 <input type="text" class="form-control" name="storeName" id="storeName" maxlength="20" placeholder="가게명을 입력하세요" autocomplete="off" required>
		            		 <!-- required : 필수 입력 항목으로 지정 -->
                            <!-- autocomplete="off" : input 태그 자동완성 기능을 끔 -->
                            </div>
		            	</td>
		            </tr>
		            
		            <tr>
			            <td><label for="corNum1">사업자번호(필수)</label></td>
			            <td>
			            	<!-- 사업자번호1 -->
				            <div class="col-md-3">
	                          <input type="text" class="form-control corNum" id="corNum1"  maxlength="3" name="corNum1" required>
	                        </div>
	                        <!-- 사업자번호2 -->
	                        <div class="col-md-3">
	                            <input type="text" class="form-control corNum" id="corNum2"  maxlength="2" name="corNum2" required>
	                        </div>
	                        
	                        <!-- 사업자번호3 -->
	                        <div class="col-md-3">
	                            <input type="text" class="form-control corNum" id="corNum3"  maxlength="5" name="corNum3" required>
	                        </div>
			            </td>
			       
		            </tr>
		            
		            <tr>
		            	<td>
		            		<label for="storeContent">가게 설명(필수)</label>
		            	</td>
		            	<td>
			            	<div class="col-md-8">
	                            <textarea col="20" span="10" class="form-control" id="storeContent" name="storeContent" maxlength="200" placeholder="가게 설명을 입력하세요.(최대 200자)" required></textarea> 
	                        </div>
		            	</td>
		            </tr>
		            
		            <tr>
		            	<td>
                            <label for="code">업종 분류(필수)</label>
		            	</td>
		            	<td>
		            		<div class="col-md-6">
                            <select class="form-control" id="code" name="code" required>
                            	<option value=100>치킨</option>
                            	<option value=200>중식</option>
                            	<option value=300>한식</option>
                            	<option value=400>분식</option>
                            	<option value=500>일식</option>
                            	<option value=600>피자/양식</option>
                            	<option value=700>족발</option>
                            	<option value=800>디저트</option>
                            	<option value=900>야식</option>
                            </select>
                        </div>
		            	</td>
		            </tr>
		            
		            <tr>
		            	<td>
	                        <label for="phone1">전화번호(필수)</label>
		            	</td>
		            	<td>
		            	 <!-- 전화번호1 -->
	                        <div class="col-md-8">
	                            <select class="custom-select col-md-3" id="phone1" name="phone1" required>
	                                <option>010</option>
	                                <option>02</option>
	                            </select>
	                            <input type="text" class="form-control phone col-md-3" id="phone2"  maxlength="4" name="phone2" required>
	                            <input type="text" class="form-control phone col-md-3" id="phone3"  maxlength="4" name="phone3" required>
	                        </div>
	                       
		            	</td>
                      
		            </tr>
		            
		            <tr>
			            <td>
			            	 <!-- 주소 -->
		                    <!-- 카카오 주소 API -->
		                    <!-- http://postcode.map.daum.net/guide -->
		                    <div class="row mb-3 form-row">
                        	<div class="col-md-6">
                            <label for="postcodify_search_button">우편번호/주소(필수)</label>
                     		 </div>
                     		 </div>
			            </td>
			            <td>
	                        <div>
	                        <input type="text" name="post" id="sample6_postcode" placeholder="우편번호" required readonly>
							<input type="button" onclick="sample6_execDaumPostcode()" value="우편번호 찾기"><br>
							<input type="text" name="address1" id="sample6_address" size=50 placeholder="주소" required readonly><br>
							<input type="text" name="address2" id="sample6_detailAddress" placeholder="상세주소">
			            	</div>
			            </td>
		            </tr>
		            
		            <tr>
		            	<td>
		            		<label class="input-group-addon mr-3 insert-label">대표 이미지(선택)</label>
		            	</td>
		            	<td>
		            		<div class="storeImg" id="titleImgArea">
								<img id="titleImg" width="150" height="150">
							</div>
		            	</td>
		            </tr>
		            <tr>
		            	<td>
		            		<label class="input-group-addon mr-3 insert-label">업로드 이미지(선택)</label>
		            	</td>
		            	<td>
		            		<span class="mr-2 storeImg" id="contentImgArea1">
							<img id="contentImg1" width="150" height="150">
							</span>
							
							<span class="mr-2 storeImg" id="contentImgArea2">
							<img id="contentImg2" width="150" height="150">
							</span>	
							
							<span class="mr-2 storeImg" id="contentImgArea3">
							<img id="contentImg3" width="150" height="150">
							</span>
							
							<!-- 파일 업로드 하는 부분 -->
							<div id="fileArea">
							<!--  multiple 속성
								- input 요소 하나에 둘 이상의 값을 입력할 수 있음을 명시 (파일 여러개 선택 가능)
							 -->
							<input type="file" id="img1" name="img1" onchange="LoadImg(this,1)"> 
							<input type="file" id="img2" name="img2" onchange="LoadImg(this,2)"> 
							<input type="file" id="img3" name="img3" onchange="LoadImg(this,3)"> 
							<input type="file" id="img4" name="img4" onchange="LoadImg(this,4)">
							</div>
		            	</td>
		            </tr>
		          
		        </table>
       
             
                    
                      
		      <table class="table table-striped">
		         <thead>
		            <tr>
		               <th>메뉴 이름</th>
		               <th>가격</th>
		               <th>설명</th>
		               <th>최소 조리시간</th>
		            </tr>
		         </thead>
		         <tbody id="menutable">
		         </tbody>
		      </table>
		      <button type="button" id="addMenu" class="btn btn-info">+</button>
		      <button type="button" id="removeMenu" class="btn btn-info">-</button>
		   
		   <script type="text/javascript">
		      var count = 0;
		      $("#addMenu").click(
		              function() {
	                     var menu = '<tr><td><input type="text" name="menuName" id="menuName'+count+'" placeholder="메뉴이름을 입력해주세요" required></td><td><input type="text" name="menuPrice" id="menuPrice'+count+'" placeholder="메뉴가격을 입력해주세요" required></td><td><input type="text" name="menuContents" id="menuContents'+count+'" placeholder="메뉴설명을 입력해주세요"></td><td><input type="text" name="menuMakeTime" id="menuMakeTime'+count+'" placeholder="최소조리시간 입력"></td></tr>';
	                     count++;
	                     $("#menutable").append(menu);
		
		                  });
		      
		      $("#removeMenu").on("click", function(){
		    		var $lastChild = $("#menutable > tr:last-child");
		    		
		    		if($lastChild.find("input[type='hidden']").val() == undefined){
			    		$("#menutable > tr:last-child").remove();
		    			
		    		}
		    		
		    	});
		   </script>
                

             <!-- jQuery와 postcodify 를 로딩한다. -->
             <script src="//d1p7wdleee1q2z.cloudfront.net/post/search.min.js"></script>
            
              <hr class="mb-4">
             <button class="btn btn-primary btn-lg" type="submit">가게 정보 등록 완료</button>
        </form>
       
	
	
	
	</div>
	
	
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script type="text/javascript">
    function sample6_execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var addr = ''; // 주소 변수
                var extraAddr = ''; // 참고항목 변수

                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    addr = data.roadAddress;
                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    addr = data.jibunAddress;
                }

                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
                if(data.userSelectedType === 'R'){
                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                        extraAddr += data.bname;
                    }
                    // 건물명이 있고, 공동주택일 경우 추가한다.
                    if(data.buildingName !== '' && data.apartment === 'Y'){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                }
                   
                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('sample6_postcode').value = data.zonecode;
                document.getElementById("sample6_address").value = addr;
                // 커서를 상세주소 필드로 이동한다.
                document.getElementById("sample6_detailAddress").focus();
            }
        }).open();
    }
    
    
    
 // 이미지 공간을 클릭할 때 파일 첨부 창이 뜨도록 설정하는 함수 (///파일 첨부 버튼을 대신 눌러주는 역할)
   $(function () {
      $("#fileArea").hide();

     $("#titleImgArea").click(function () {
       $("#img1").click();
     });
     $("#contentImgArea1").click(function () {
       $("#img2").click();
     });
     $("#contentImgArea2").click(function () {
       $("#img3").click();
     });
     $("#contentImgArea3").click(function () {
       $("#img4").click();
     });
     
     
   });
   
     // 각각의 영역에 파일을 첨부했을 경우 미리 보기가 가능하도록 하는 함수
  	function LoadImg(value, num){
  	if(value.files && value.files[0]){
  		var reader = new FileReader(); ///파일을 읽는 객체
  		
  		reader.onload = function(e){
  			
  			switch(num){
  			case 1 :
  				$("#titleImg").attr("src", e.target.result);
  				break;
  			case 2 :
  				$("#contentImg1").attr("src", e.target.result);
  				break;
  			case 3 :
  				$("#contentImg2").attr("src", e.target.result);
  				break;
  			case 4 :
  				$("#contentImg3").attr("src", e.target.result);
  				break;
  			}
  		}
  		reader.readAsDataURL(value.files[0]);
  	}
  }
     
 // 각 유효성 검사 결과를 저장할 객체
	/* var signUpCheck = {
		"storeName" : false,
		"storeContent" : false
		
	};

	// jQuery 변수 : 변수에 직접적으로 jQuery메소드를 사용할 수 있음.
	var $storeName = $("#storeName");
	var $storeContent = $("#storeContent");
     
     
    $storeName.on("input",function(){
	    var regExp = /^[a-zA-Z가-힣\d ]{1,20}$/; 
	    if(!regExp.test($storeName.val())){
	    	signUpCheck.storeName = false;
	    }else{	    	
	    	signUpCheck.storeName = true;
	    }
    });
    
	  
    	
    $storeContent.on("input",function(){
    	var regExp = /^[a-zA-Z가-힣\d ]{1,200}$/;
    	if(!regExp.test($storeContent.val())){
    		signUpCheck.storeContent = false;
    	}else {
    		signUpCheck.storeContent = true;
    	}
    });
    
 
    
    
 	function validate() {

	for ( var key in signUpCheck) {
		
		if (!signUpCheck[key]) {
			// 인덱스 

			var msg;
			switch (key) {
			case "storeName":
				msg = "가게명이 ";
				break;
			case "storeContent":
				msg = "가게 설명이 ";
				break;
			}

			alert(msg + "유효하지 않습니다.");
			var el = "#" + key;
			$(el).focus();
			
			return false;
		}
	
		}
 	}
 */
</script>

	
	
 
 
 <%@ include file="../common/footer.jsp" %>
</body>
</html>