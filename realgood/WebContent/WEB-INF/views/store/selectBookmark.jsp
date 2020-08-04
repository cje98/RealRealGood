<%@page import="java.util.List"%>
<%@page import="com.kh.realgood.store.model.vo.PageInfo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- <%
	List<BuyList> list = (List<BuyList>)request.getAttribute("bList");
%> --%>
<%
	
	PageInfo pInfo = (PageInfo)request.getAttribute("pInfo");

	List<Store> storeList = (List<Store>)request.getAttribute("storeList");

	int currentPage = pInfo.getCurrentPage();
	int listCount = pInfo.getListCount();
	int maxPage = pInfo.getMaxPage();
	int startPage = pInfo.getStartPage();
	int endPage = pInfo.getEndPage();
	
	String group = pInfo.getGroup();
	String address = pInfo.getAddress();
	
	int prev = (currentPage-1)/10*10;
	int next = (currentPage+9)/10*10+1;
	
%>
<!DOCTYPE html>
<html>
<head>
<style>
       .pagination {
            justify-content: center;
        }
        #searchForm{
            position: relative;
        }

        #searchForm>*{
            top : 0;
        }
        
        .boardTitle > img{
           width: 50px;
           height: 50px;
        }
        
        .ico-star1 {
        	color : rgb(255, 168, 0);
        }
   </style>
<meta charset="UTF-8">
<title>즐겨찾기</title>
</head>
<body>
    <%@ include file="../common/header.jsp"%>
    <div class="row my-5">
        <%@ include file="../member/sideMenu.jsp"%>
        <div class="col-sm-10">
            <form action="<%=request.getContextPath()%>/member/selectBookmark.do" method="POST"
                onsubmit="return validate();">
                    <h1>즐겨찾기</h1>
                    <div class="row mb-3 form-row">
                <table class="table table-hover">
                    	<tr>
	
	
	<%
		if (!storeList.isEmpty()) {
			for (int i = 0; i < storeList.size(); i++) {
				Store store = storeList.get(i);
				if (i == 0) {
	%>
	<div class="row mb-6">
		<%
			}
					if (i % 2 == 0) {
		%>
		<div class="col-md-6">
			<div
				class="row no-gutters border rounded overflow-hidden flex-md-row mb-4 shadow-sm h-md-250 position-relative">
				<div class="col p-4 d-flex flex-column position-static">
					    <strong class="d-inline-block mb-2 text-primary"><%=store.getStoreGroupName()%></strong>
			        <input type="hidden" value="<%=store.getStoreNum()%>">
					<h3 class="mb-0"><%=store.getStoreName()%></h3>
					<div class="mb-1 text-muted"><%=store.getStoreTel()%></div>
					<p class="card-text mb-auto"><%=store.getStoreContent()%></p>
					<p class="card-text mb-auto"><%=store.getStoreAddress()%></p>
					<p><span class="ico-star1">★ <%=store.getStoreGpaScore() %></span></p>
		          <a class="stretched-link"></a>
					
				</div>
				<div class="col-auto d-none d-lg-block">
					<img width="200" height="250"
						src="<%=request.getContextPath()%>/resources/images/<%=store.getStoreTitleImg()%>" />
				</div>
			</div>
		</div>
		<%
			} else {
		%>
		<div class="col-md-6">
			<div
				class="row no-gutters border rounded overflow-hidden flex-md-row mb-4 shadow-sm h-md-250 position-relative">
				<div class="col p-4 d-flex flex-column position-static">
					    <strong class="d-inline-block mb-2 text-primary"><%=store.getStoreGroupName()%></strong>
			        <input type="hidden" value="<%=store.getStoreNum()%>">
					<h3 class="mb-0"><%=store.getStoreName()%></h3>
					<div class="mb-1 text-muted"><%=store.getStoreTel()%></div>
					<p class="card-text mb-auto"><%=store.getStoreContent()%></p>
					<p class="card-text mb-auto"><%=store.getStoreAddress()%></p>
					<p><span class="ico-star1">★ <%=store.getStoreGpaScore() %></span></p>
		          <a class="stretched-link"></a>
				</div>
				<div class="col-auto d-none d-lg-block">
					<img width="200" height="200"
						src="<%=request.getContextPath()%>/resources/images/<%=store.getStoreTitleImg()%>" />
				</div>
			</div>
		</div>
		<%
			}
					if (i == (storeList.size() - 1)) {
		%>
	</div>
	<%
		}
	%>
	<br>
	<%
		}
		}
	%>
	
	
	</div>
                        </tr>
                	</table>
                </div>
            </form>
        </div>
    </div>

       <script>
     $(".stretched-link").on("click", function(){
        
        var name = $(this).parent().children().eq(1).val();

		location.href="<%=request.getContextPath()%>/store/detail.do?storeNum="+name;        
		
     }).on("mouseenter", function(){
      $(this).parent().css("cursor","pointer");
   });
    </script>
    <%@ include file="../common/footer.jsp"%><br>
</body>
</html>