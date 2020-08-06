<%@page import="com.kh.realgood.store.model.dto.StoreMenu"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.kh.realgood.store.model.dto.StoreImg"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>가게 정보 수정</title>
<style>
	#button{
		text-align : center;
	}
	
	#phone1, #phone2, #phone3{
		display : inline-block;
	}
	
	.storeImg{
		cursor : pointer;
	}
</style>
	 <!-- jQuery와 postcodify 를 로딩한다. -->
 <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
 <script src="//d1p7wdleee1q2z.cloudfront.net/post/search.min.js"></script> 
 <script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
</head>
<body>
 <%@ include file="../common/header.jsp" %>
 	<div class="row my-5">
	<%@ include file="sideMenu2.jsp"%>
<%
	
	// 합쳐져있는 전화번호 '-'로 쪼개기
	String[] phone = loginStore.getStoreTel().split("-"); // '-'를 기준으로 쪼개어서 담음
	// 합쳐져있는 주소 ','로 쪼개기
	String[] address = loginStore.getStoreAddress().split(","); // ','를 기준으로 쪼개어서 담음
	
	// 주소 중 일부 내용이 비어있을 경우
    int aLength = 0;
    if(address.length != 0) aLength = address.length;
    // aLength가 원래 수만큼 채워져있지않을 경우(여기서는 전체 주소가 2칸이므로 1칸일 경우를 뜻함 )
    if(aLength < 2){
       String[] newArr = new String[2]; // 전체주소 크기(2)만큼 배열 크기를 지정해주고
       
       for(int i=0;i<aLength ;i++){ // 2바퀴 반복했을때 
          newArr[i] = address[i]; // newArr[i]번째에 address[i]번째 주소를 저장한다
       }
       for(int i=aLength; i<newArr.length ; i++){ //aLength가 1이고 newArr.length가 2일 경우 newArr[1]에 ""를 저장
          newArr[i] = "";
       }
       address = newArr;
    }

%>
		 <div class="col-sm-8">
		 	<form method="POST" action="storeModify.do" 
		 	enctype="multipart/form-data" role="form" name="modifyForm" onsubmit="return validate();">
		 		<table class="table table-hover">
                    <h1>가게 정보 수정</h1>
                    <tr>
                        <td><label for="storeName">가게명(필수)</label></td>
                        <td>
	                        <div class="col-md-8">
	                        <h5 id="storeName"><%=loginStore.getStoreName()%></h5>
	                    	</div>    
	                    </td>
                    </tr>
                    <tr>
                    <!-- 사업자 번호 : 사업자 번호는 변경할 수 없으므로 정보를 그대로 가져와 보여준다.-->
                    	<td><label for="corNum1">사업자번호(필수)</label></td>
                    	<td>
                    		<div class="col-md-8">
                    		<h5 id="corNum"><%=loginStore.getCorNum()%></h5>
                    		</div>
                    		</td>
                    </tr>
                    <tr>
                    <!-- 개행문자 처리 해제 -->
                        <%
                        	loginStore.setStoreContent(loginStore.getStoreContent().replace("<br>", "\r\n"));
                        %>
                        <td><label for="storeContent">가게 설명(필수)</label></td>                     
                        <td>
                        	<div class="col-md-8">
                        	<textarea cols="20" span="10" class="form-control" id="storeContent"
                            	name="storeContent" maxlength="200" placeholder="가게 설명을 입력하세요.(최대 200자)" 
                            	required><%=loginStore.getStoreContent()%></textarea>
                          	 </div>
                        </td>
                  		
                    </tr>
                    <tr>
                    	<td><label for="code">업종 분류(필수)</label></td>
                    	<td>  
	                    	<div class="col-md-4">
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
                   		 	<div class="col-md-8">
                   		    <select class="custom-select col-md-3" id="phone1" name="phone1" required>
                                <option>010</option>
                                <option>02</option>
                            </select>
                             <input type="text" class="form-control phone col-md-3" id="phone2"  maxlength="4" name="phone2" value="<%=phone[1] %>" required>
                             <input type="text" class="form-control phone col-md-3" id="phone3"  maxlength="4" name="phone3" value="<%=phone[2] %>" required>
                             <span id="checkPhone">&nbsp;</span>
                             </div>
                   		 </td>
                    </tr>
                    <tr>
                    	<td>
                    		 <label for="postcodify_search_button">우편번호/주소(필수)</label>
                    	</td>
                    	<td>
                    		<div class="col-md-8">
                    		 <!-- 주소 -->
		                    <!-- 카카오 주소 API -->
		                    <!-- http://postcode.map.daum.net/guide -->
                    		<input type="text" name="post" id="sample6_postcode" placeholder="우편번호" value="<%=loginStore.getStoreZip()%>" required readonly>
							<input type="button" onclick="sample6_execDaumPostcode()" value="우편번호 찾기"><br>
							<input type="text" name="address1" id="sample6_address" placeholder="주소" size=50 value="<%=address[0]%>" required readonly><br>
							<input type="text" name="address2" id="sample6_detailAddress" placeholder="상세주소" value="<%=address[1]%>" required>
							</div>
                    	</td>
                    </tr>
                   <tr>
		            	<td>
		            		<label class="input-group-addon mr-6 insert-label">대표 이미지(선택)</label>
		            	</td>
		            	<td>
		            	
		            		<div class="col-md-8 storeImg" id="titleImgArea">
								<img id="titleImg" width="150" height="150" >
							</div>
						
		            	</td>
		            </tr>
		            <tr>
		            	<td>
		            		<label class="input-group-addon mr-6 insert-label">업로드 이미지(선택)</label>
		            	</td>
		            	<td>
		            		<span class="col-md-8 mr-2 storeImg" id="contentImgArea1">
							<img id="contentImg1" width="150" height="150">
							</span>
							
							<span class="col-md-8 mr-2 storeImg" id="contentImgArea2">
							<img id="contentImg2" width="150" height="150">
							</span>	
							
							<span class="col-md-8 mr-2 storeImg" id="contentImgArea3">
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
            	
				<script>
				<%
					List<StoreImg> storeImg = (List<StoreImg>)request.getAttribute("storeImg");
					String imgId = null;
					
					if(storeImg != null){
			    		for(StoreImg img : storeImg){
			    			switch(img.getFileLevel()){
			    			case 0 : imgId = "#titleImg"; break;
			    			case 1 : imgId = "#contentImg1"; break;
			    			case 2 : imgId = "#contentImg2"; break;
			    			case 3 : imgId = "#contentImg3"; break;
			    			}
		    		
		    			
		    				if(imgId != null){
				%>
					$("<%=imgId%>").attr("src", "<%=request.getContextPath()+"/resources/images/"+ img.getRealImgName()%>");
				
				<% 
		    		} }	}
				%>
				
				
				</script>
            
            	
            	
		 
                    	
	             <table class="table table-striped">
		         <thead>
		            <tr>
		               <th>메뉴 이름</th>
		               <th>가격</th>
		               <th>설명</th>
		               <th>최소 조리시간</th>
		               <th></th>
		            </tr>
		         </thead>
		         <tbody id="menutable">
		         	<%
		         		List<StoreMenu> storeMenu = (List<StoreMenu>)request.getAttribute("storeMenu"); 
		   	
		         		if(storeMenu != null){
		         			int tmptmp = 0;
		         	%>
		         		<%for(StoreMenu menu : storeMenu){ %>
		         			<tr id="menuLine">
		         				<td><input type="hidden" id="menuNum<%=tmptmp%>" name="menuNum" class="deleteNo" value="<%=menu.getMenuNo()%>" readonly> 
		         				    <input type="text" id="menuName<%=tmptmp%>" name="menuName" value="<%=menu.getMenuName()%>" readonly></td>
		         				<td><input type="text" id="menuPrice<%=tmptmp%>" name="menuPrice" value="<%=menu.getPrice()%>" readonly></td>
		         				<td><input type="text" id="menuContents<%=tmptmp%>" name="menuContents" value="<%=menu.getMenuContents()%>" readonly></td>
		         				<td><input type="text" id="menuMakeTime<%=tmptmp%>" name="menuMakeTime" value="<%=menu.getMenuMakeTime()%>" readonly></td>
		         				<!-- disabled는 form태그 내부에 작성시 값이 전송되지않음 -->
		         				<td><button type="button" id="deleteBtn" class="deleteBtn">삭제</button></td>
		         			</tr>
		         	<% tmptmp++; } }%>
		         </tbody>
		      </table>
	         <script>
	        $(".deleteBtn").on("click", function(e){
	        		 
	        	 if(confirm("정말 삭제 하시겠습니까?")) {
		        	console.log($(this).parent().parent().find("input[type='hidden']").val());
	        		
	        		 $.ajax({
	        			 url : "menuDelete.do",
	        			 data : {"menuNum" : $(this).parent().parent().find("input[type='hidden']").val()},
	        			 type : "get",
	        			 success : function(result) {
	        				 if (result > 0) {
	        					 // 갱신
	        					 swal("삭제 완료");
	        					 $(e.target).parent().parent().remove();
	        					 /* location.href="/storeModify.do"; // 확인 필요 */
	        				 } else {
	        					 swal("데이터가 이미 삭제되었거나 없습니다 새로고침 후 다시 진행 바랍니다.");
	        				 }
	        			 }, erorr : function() {
	        				 console.log("AJAX 통신 오류남 확인바람");
	        			 }
	        		 });
	        	 }
	         });
	         </script>
	         
		      <button type="button" id="addMenu" class="btn btn-info">+</button>
		      <button type="button" id="removeMenu" class="btn btn-info">-</button>
		   	
		    	<hr class="mb-4">
		      
	            <button class="btn btn-primary btn-lg" id="button" type="submit">가게 정보 수정 완료</button>

              	</form>
		 </div>
        
	
	
<!-- 업종분류 -->
<script type="text/javascript">
       /* 가게 업종 분류 */
		$("#code > option").each(function(index, item){
       		if( $(item).val() ==  "<%=loginStore.getStoreGroupName()%>" ){
       			$(item).prop("selected", "true");
       		}
       		
       	});
                   	
                    	
       	/* 전화번호  */
       	//$.each는 for문처럼 jQuery 객체 반복을함.
       	$.each($("#phone1 > option"), function(index, item){
       		// id 'phone1'의 option요소를 반복할건데
       		// item은 현재 접근 중인 요소이므로, 현재접근 중인 요소의 내용이 phone에있는 0번째 인덱스와 같다면
       		if($(item).text() == "<%=phone[0]%>"){
       			$(item).prop("selected", true); // 현재 접근중인 요소의 속성을 selected(선택)해라
       		}
       		
       	});
           	
           	
           	
          	/* 주소API  */
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
		                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
		                    if(extraAddr !== ''){
		                        extraAddr = ' (' + extraAddr + ')';
		                    }
		                    // 조합된 참고항목을 해당 필드에 넣는다.	
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
          	
         // 이미지 미리보기
         function LoadImg(value, num){
        	 if(value.files && value.files[0]){
        		 var reader = new FileReader();
        		 
        		 reader.onload = function(e){
        			 switch(num){
        			 case 1 : $("#titleImg").attr("src", e.target.result); break;
        			 case 2 : $("#contentImg1").attr("src", e.target.result); break;
        			 case 3 : $("#contentImg2").attr("src", e.target.result); break;
        			 case 4 : $("#contentImg3").attr("src", e.target.result); break;
        			 
        			 
        			 }
        		 }
        		 reader.readAsDataURL(value.files[0]);
        	 }
         }
          	
        /* 메뉴 칸수 추가하기 */
        var count = 0;
    	 $("#addMenu").click(
               function(){
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
    	
    	
    	// 각 유효성 검사 결과를 저장할 객체
    	/* var signUpCheck = {
    		"storeContent" : false
    	}; */

    	 /* 	var tmp = 0;

    	// jQuery 변수 : 변수에 직접적으로 jQuery메소드를 사용할 수 있음.
    	var $storeContent = $("#storeContent");
         
        $storeContent.on("input",function(){
        	var regExp = /^[a-zA-Z가-힣\d ]{1,200}$/;
        	if(!regExp.test($storeContent.val())){
        		signUpCheck.storeContent = false;
        		tmp = 1;
        	}else {
        		signUpCheck.storeContent = true;
        	}
        });
        
     	function validate() {

    	for (var key in signUpCheck) {
    		
    		if (!signUpCheck[key]) {
    			// 인덱스 
				if(tmp == 1){
	    			var msg;
	    			switch (key) {
	    			case "storeContent":
	    				msg = "가게 설명이 ";
	    				break;
	    			}
	
	    			alert(msg + "유효하지 않습니다.");
	    			var el = "#" + key;
	    			$(el).focus();
	    			
				}else{
					alert("수정x");
					
				}
    			
	    			return false;
    		}
    		}
     	} */
					
                    
</script>
	
	

 <%@ include file="../common/footer.jsp" %>
</body>
</html>