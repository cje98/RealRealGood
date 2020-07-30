<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%
   String name = "한기성";
String email = "gksrltjd40@naver.com";
String phone = "010-2062-6342";
String address = "서울시 노원구 공릉동";
%>
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
                        msg += ' 카드 승인번호 : ' + rsp.apply_num;

                        alert(msg);
                        self.close();
                      <%-- location.href = '<%=request.getContextPath()%>/paySuccess.jsp?msg=' + msg; --%>
            } else {
                msg = '결제에 실패하였습니다.';
                msg += '에러내용 : ' + rsp.error_msg;
                //실패시 이동할 페이지 
                self.close();
            alert(msg);
            }
         });

      });
   </script>
</body>

</html>