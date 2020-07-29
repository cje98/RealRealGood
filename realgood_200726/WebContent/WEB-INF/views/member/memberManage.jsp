<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%
	List<Member> list = (List<Member>)request.getAttribute("list");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 관리 페이지</title>
<style>
	
</style>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
</head>
<body>
    <%@ include file="../common/header.jsp"%>
    <div class="row my-5">
        <%@ include file="../common/adminSideMenu.jsp"%>
        <div class="col-sm-7">
            <form action="<%=request.getContextPath()%>/member/memberManage.do" method="POST">
                    <h1>회원 관리 페이지</h1>
                    <div class="row mb-3 form-row">
                <table class="table table-hover">
                        <tr>
                            <td>체크여부</td>
                            <td>No.</td>
                            <td>아이디</td>
                            <td>이름</td>
                            <td>닉네임</td>
                            <td>성별</td>
                            <td>전화번호</td>
                            <td>회원등급</td>
                            <td>탈퇴여부</td>
                            <td>가입날짜</td>
                        </tr>
                        <% if(list.isEmpty()){ %>
							<tr>
								<td colspan="10">조회할 회원이 없습니다.</td>
							</tr>
						<% }else{ %>
	               		    <% for(int i = 0; i < list.size(); i++) { %>
		                        <tr>
		                            <td><input type="checkbox" name="manageMember"></td>
		                            <td><%= i+1 %></td>
		                            <td><%= list.get(i).getId() %></td>
		                            <td><%= list.get(i).getName() %></td>
		                            <td><%= list.get(i).getNickName() %></td>
		                            <td><%= list.get(i).getGender() %></td>
		                            <td><%= list.get(i).getTel() %></td>
		                            <td><%= list.get(i).getGradeName() %></td>
		                            <td><%= list.get(i).getOutYN() %></td> 
		                            <td><%= list.get(i).getEnrollDate() %></td>
		                        </tr>
	                        <% } %>
                        <% } %>
                         
                      
                </table>
                    </div>
                
                <button id="deleteBtn" class="btn btn-primary">삭제하기</button>
                                   전체<input id="checkAll" type="checkbox" name="check" >
            </form>
                
        </div>
    </div>
    <script>
    
    $("#deleteBtn").on("click", function() {

        var rowData = new Array();
        var idSum = "";
        var checkbox = $("input[name=manageMember]:checked");
		var id = "";
        // 체크된 체크박스 값을 가져온다
        $.each(checkbox, function(index, item) {
           // checkbox.parent() : checkbox의 부모는 <td>이다.
           // checkbox.parent().parent() : <td>의 부모이므로 <tr>이다.
           var tr = checkbox.parent().parent().eq(index);
           var td = tr.children();

           // 체크된 row의 모든 값을 배열에 담는다.
           rowData.push(tr.text());

           // td.eq(0)은 체크박스 이므로  td.eq(1)의 값부터 가져온다.
           id = td.eq(2).text();

           // 가져온 값을 배열에 담는다.
           //tdArr.push(id);

           console.log(id);
           
           idSum += id + " ";
        });
        if(confirm("정말로 삭제하시겠습니까?")){
        	location.href = "<%=request.getContextPath()%>/member/memberDelete.do?id=" + idSum;
        }
     });
	    	
    
	    $(document).ready(function(){
	    	// 전체 선택 시 체크박스 모두 선택 표시
		    $("#checkAll").click(function(){
		        if($("#checkAll").is(":checked")){
		           $("input[name=manageMember]:checkbox").prop("checked", true);
		        } else {
		           $("input[name=manageMember]:checkbox").prop("checked", false);
		        }
		    });
		    
	    });
	    	
		 	// 전체 선택 중 하나 선택 해제 시 전체 선택 표시 해제
		   
		//  	$("input[name=manageMember]:checkbox").click(function(){
		//        if($("input[name='manageMember']:checkbox").length == ){
		//            $("#checkAll").prop("checked", true);
		//        }else{
		//            $("#checkAll").prop("checked", false);
		//        }
		//    });
	    
   
    </script>
    <%@ include file="../common/footer.jsp"%><br>
</body>
</html>