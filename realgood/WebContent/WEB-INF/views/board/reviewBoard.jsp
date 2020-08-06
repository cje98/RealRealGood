<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.kh.realgood.store.model.dto.StoreInfoMenu"%>
<%@page import="com.kh.realgood.board.model.dto.Board"%>
<%@page import="com.kh.realgood.store.model.vo.PageInfo"%>
<%@page import="com.kh.realgood.store.model.dto.Store"%>
<%@page import="java.util.List"%>
    
<% 
	
	String storeNum = (String)request.getParameter("storeNum");

	
	List<Member> list = (List<Member>)request.getAttribute("list");

	
%>
    
<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Jekyll v4.0.1">
    <title>리뷰 게시판</title>

    <link rel="canonical" href="https://getbootstrap.com/docs/4.5/examples/navbar-static/">

    <!-- Bootstrap core CSS -->
 	<link type="text/css" href="<%=request.getContextPath()%>/resources/css/bootstrap.css" rel="stylesheet">
    
    <style>
      .bd-placeholder-img {
        font-size: 1.125rem;
        text-anchor: middle;
        -webkit-user-select: none;
        -moz-user-select: none;
        -ms-user-select: none;
        user-select: none;
      }

      @media (min-width: 768px) {
        .bd-placeholder-img-lg {
          font-size: 3.5rem;
        }
      }

      ol, ul, li{
        list-style: none;
        display: inline-block;
        margin-right: 24px;
      }

      .ReviewWritingPage__Container{
        background-color: white;
        height: 300px;
      }
      
      .ReviewEditor__Editor__Wrap{
        border: solid 1px;    
        height: 300px;    

      }
      
       .insert-label {
      display: inline-block;
      width: 80px;
      line-height: 40px
	    }
	    
	    .boardImg{
	    	cursor : pointer;
	    }

      .ReviewEditor__Editor{
        width: 100%;
        border: none;
        overflow: hidden; overflow-wrap: break-word; height: 150px;
      }
      textarea {
		resize:none;
        border: none;
      }

      .ReviewEditor__TextLengthStateBox{
        float: right; 
        display: inline;
      }

      .pickerList{
        margin-left: 0px;
      }

      .ReviewWritingPage__Container{
        margin: 11px auto 62px;
        width: 750px;
      }

      .ReviewWritingPage__Row{
       margin-bottom: 10px;
      }


	#star {
	  display: flex;
	}
	
	.star {
	  font-size: 2rem;
	  margin: 10px 0;
	  cursor: pointer;
	}
	
	.star:not(.on) {
	  color: #ccc;
	}
	
	.star.on {
	  color: orange;
	}


    </style>
  </head>

  <body>
	<%@ include file="../common/header.jsp"%>
    
      <section class="ReviewWritingPage__Container">
      
        <div class="ReviewWritingPage__Row">
          <h5 class="RestaurantSubMessage__RestaurantName"><strong> </strong></h5>
          <div class="RestaurantSubMessage__SubMessageWrap">
            <span class="RestaurantSubMessage__SubMessage">에 대한 솔직한 리뷰를 써주세요.</span>
          </div>
        </div>
    
    <form action="<%=request.getContextPath()%>/board/insert.do?storeNum=<%=storeNum%>" method="post" 
				  enctype="multipart/form-data" role="form" onsubmit="return validate();"> 
    
        <div class="ReviewWritingPage__ContentWrap">
          <div class="ReviewWritingPage__FormWrap">
            <div class="ReviewWritingPage__EditorWrap">
              <div class="ReviewEditor">
                <div class="ReviewEditor__Editor__Wrap">
                  <div class="ReviewWritingPage__RestaurantRecommendPickerWrap">
                    <div class="pickerList">
                        <div class="star-container" id="star">
						<input type="hidden" id="starScore" name="starScore">
			            <span class="star">★</span>
			            <span class="star">★</span>
			            <span class="star">★</span>
			            <span class="star">★</span>
			            <span class="star">★</span>
			          
			      		   </div>

                    </div>
                  </div>
                  <textarea name="content" id="content" class="ReviewEditor__Editor" maxlength="200"  placeholder=" 주문하신 메뉴는 어떠셨나요? 식당의 분위기와 서비스도 궁금해요!" ; style="resize: none; height: 230px"></textarea>
                </div>
                <p class="ReviewEditor__TextLengthStateBox">
                  <span class="ReviewEditor__CurrentTextLength">0</span>
                  <span class="ReviewEditor__TextLengthStateDivider">/</span>
                  <span class="ReviewEditor__MaxTextLength" >200</span>
                </p>
              </div>
            </div>
    
            
            <div class="DraggablePictureContainer">
                <ul class="DraggablePictureContainer__PictureList muuri" style="height: 98px;">
                  <li class="DraggablePictureContainer__PictureItem DraggablePictureContainer__PictureItem--button muuri-item muuri-item-shown dz-clickable" style="left: 0px; top: 0px; transform: translateX(0px) translateY(0px); display: block; touch-action: none; user-select: none; -webkit-user-drag: none; -webkit-tap-highlight-color: rgba(0, 0, 0, 0);">
				
			

				<div class="form-inline mb-2">
					<div class="mr-2 boardImg" id="contentImgArea1">
						<img id="contentImg0" width="150" height="150">
					</div>

					<div class="mr-2 boardImg" id="contentImgArea2">
						<img id="contentImg1" width="150" height="150">
					</div>

					<div class="mr-2 boardImg" id="contentImgArea3">
						<img id="contentImg2" width="150" height="150">
					</div>
					
					<div class="mr-2 boardImg" id="contentImgArea4">
						<img id="contentImg3" width="150" height="150">
					</div>
				</div>
				
				
				
				<div id="fileArea">
					<!--  multiple 속성
						- input 요소 하나에 둘 이상의 값을 입력할 수 있음을 명시 (파일 여러개 선택 가능)
					 -->
				<!--onchange : input 태그의 value값이 변했을 때 loadimg(this,1 : 현재 요소, 이벤트가 발생한 요소 + 1)라는 함수를 호출해라 -->
					<input type="file" id="img1" name="img1" onchange="LoadImg(this,1)"> 
					<input type="file" id="img2" name="img2" onchange="LoadImg(this,2)"> 
					<input type="file" id="img3" name="img3" onchange="LoadImg(this,3)"> 
					<input type="file" id="img4" name="img4" onchange="LoadImg(this,4)">
				</div>

                  </li>
                </ul>
                
                <div class="ReviewWritingPage__PictureWrap" style="margin-top: 40px">
                  <div class="ReviewPictureCounter" style="top: 93px; left: 89px; display: block;">
                    <span class="ReviewPictureCounter__CurrentLength">0</span>
                    <span class="ReviewPictureCounter__Divider">/</span>
                    <span class="ReviewPictureCounter__MaxLength">4</span>
                  </div>
                <div class="DraggablePictureContainer__GuideLayer">
                  <span class="DraggablePictureContainer__GuideMessage">사진을 여기에 놓으세요.</span>
                </div>
              </div>
            </div>
          </div>
        </div>
        <div class="ReviewWritingPage__ButtonsWrap">
          <div class="ReviewWritingPage__Buttons">
            <button class="ReviewWritingPage__CancelButton"  style="float: right; margin-left: 35px;" > 취소</button>
            <button class="ReviewWritingPage__SubmitButton ReviewWritingPage__SubmitButton--Deactive" style="float: right;">리뷰 등록하기</button>
          </div>
        </div>
        
     </form>
        
      </section>
      
  </body>


 <script>
		// 오늘 날짜 출력 
		var today = new Date();
		var month = (today.getMonth()+1);

		var str = today.getFullYear() + "-"
				+ (month < 10 ? "0"+month : month) + "-"
				+ today.getDate();
		$("#today").html(str);

		// 유효성 검사 
		function validate() {
		
			if ($("#content").val().trim().length == 0) {
				alert("내용을 입력해 주세요.");
				$("#content").focus();
				return false;
			}
		}
		
		 // 이미지 공간을 클릭할 때 파일 첨부 창이 뜨도록 설정하는 함수
	    $(function () {
	       $("#fileArea").hide();

	      $("#contentImgArea1").click(function () {
	        $("#img1").click();
	      });
	      $("#contentImgArea2").click(function () {
	        $("#img2").click();
	      });
	      $("#contentImgArea3").click(function () {
	        $("#img3").click();
	      });
	      $("#contentImgArea4").click(function () {
	        $("#img4").click();
	      });

	    });
		 

	    // 각각의 영역에 파일을 첨부 했을 경우 미리 보기가 가능하도록 하는 함수
	    function LoadImg(value, num) { // value에 현재 요소 this가 담겨있음
	      if (value.files && value.files[0]) {
	    	  // value.files : 현재 요소에 파일이 업로드 되어 있는가?
   			  // value.files[0] : multiple 속성 사용으로 인해 여러 파일이 업로드 되었을 때 첫 번째 파일이 존재하는가?
   					  
	        var reader = new FileReader();
	        // 자바스크립트 FileReader
        	// 웹 애플리케이션이 비동기적으로 데이터를 읽기 위하여 읽을 파일을 가리키는 File 혹은 Blob객체를 이용해 파일의 내용을 읽고 사용자의 컴퓨터에 저장하는 것을 가능하게 해주는 객체
					
        	// FileReader.onload - 파일리더를 다 읽었을 때 어떤 동작을 할 것인가?
					// load 이벤트의 핸들러. 이 이벤트는 읽기 동작이 성공적으로 완료 되었을 때마다 발생합니다.
	        reader.onload = function (e) { //e : 읽어 들일 파일
	        	//console.log(e.target.result);
	        	// e.target.result : 내가 읽어 들인 파일.의 결과
	        	// -> 파일 읽기 동작을 성공한 객체에(fileTag) 올라간 결과(이미지 또는 파일) 브라우저상에서 해석 시 하나의 이미지가 됨
	        	
	          switch (num) {
	            case 1:
	              $("#contentImg0").attr("src", e.target.result);
	              break;
	            case 2:
	              $("#contentImg1").attr("src", e.target.result);
	              break;
	            case 3:
	              $("#contentImg2").attr("src", e.target.result);
	              break;
	            case 4:
	              $("#contentImg3").attr("src", e.target.result);
	              break;
	          }
	        }

	        reader.readAsDataURL(value.files[0]);
	        // FileReader.readAsDataURL()
	      	// 지정된의 내용을 읽기 시작합니다. Blob완료되면 result속성 data:에 파일 데이터를 나타내는 URL이 포함 됩니다.
	      }
	    }

       </script>
       
       
         <script>
		(function () {
		    var starEls = document.querySelectorAll('#star span.star');
		    var rate = 0;
		
		    loop(starEls, function (el, index) {
		        el.addEventListener('click', function () {
		            rating(index + 1);
		        });
		    });
		
		    function loop(list, func) {
		        Array.prototype.forEach.call(list, func);
		    }
		
		    function rating(score) {
		        loop(starEls, function (el, index) {
		            if (index < score) {
		                el.classList.add('on');
		            } else {
		                el.classList.remove('on');
		            }
		        });
		
		        rate = score;
		        $("#starScore").val(rate);
		        console.log($("#starScore").val());
		    }
		})();

   </script>
       
       
       
       

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
      <script>window.jQuery || document.write('<script src="../assets/js/vendor/jquery.slim.min.js"><\/script>')</script><script src="../assets/dist/js/bootstrap.bundle.js"></script></body>
      
</html>
