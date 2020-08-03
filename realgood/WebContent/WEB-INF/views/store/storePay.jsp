<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript"
	src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script type="text/javascript"
	src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>
</head>

<body>
	<script>
        $(function () {
            var IMP = window.IMP; // 생략가능
            IMP.init('imp45865089'); // 'iamport' 대신 부여받은 "가맹점 식별코드"를 사용
            var msg;

            IMP.request_pay({
                pg: 'kakaopay',
                pay_method: 'card',
                merchant_uid: 'merchant_' + new Date().getTime(),
                name: '(주)완전죠아 음식점 메뉴 결제',
                amount: opener.$("#totalSum").text()
        }, function (rsp) {
            if (rsp.success) {
                        msg = '결제가 완료되었습니다.';
                        msg += '\n고유ID : ' + rsp.imp_uid;
                        msg += '\n상점 거래ID : ' + rsp.merchant_uid;
                        msg += '\n결제 금액 : ' + rsp.paid_amount;
                        msg += '\n카드 승인번호 : ' + rsp.apply_num;
                        var memberIdTmp = "<%=request.getParameter("memberId")%>";
                            $.ajax({
                        	url : "../member/menuPay.do",
                        	data : {"input" : opener.$("#menuCal > p").text(), "storeNum" : <%=request.getParameter("storeNum")%>, "memberId" : memberIdTmp},
                        	type : "get",
                        	success : function(result) {
                        		if(result > 0) {
                        			msg += '\n\n마이페이지에서 구매내역을 확인 후 사용하시기 바랍니다.';
                        			alert(msg);
                        			self.close();
                        		}
                        	}, error : function() {
                        		console.log("Ajax 통신 실패");
                        		alert(msg);
                        	}
                        	});
            } else {
            	msg = '결제에 실패하였습니다.';
                msg += '에러내용 : ' + rsp.error_msg;
                //실패시 이동할 페이지 
				alert(msg);
                self.close();
				}
			});

		});
	</script>
</body>

</html>