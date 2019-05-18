<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- <p:user /> -->
	<!-- 确认支付form -->
	<form action="${pageContext.request.contextPath}/client/alipay.trade.page.pay.jsp" method="post">
		<h3>订单号：${oders_id},付款金额 ：${orders.money }</h3>
		<input type="hidden" name="WIDout_trade_no" value="${oders_id }" /> <input
			type="hidden" name="WIDtotal_amount" value="${orders.money }" /> <input
			type="hidden" name="WIDsubject" value="${oders_id }" /> <input
			type="hidden" name="WIDbody" value="${oders_id }" /> <input
			type="hidden" name="p3_Amt" value="${p3_Amt }" /> <input
			type="hidden" name="p4_Cur" value="${p4_Cur }" /> <input
			type="hidden" name="p5_Pid" value="${p5_Pid }" /> 
			<input type="hidden" name="p6_Pcat" value="${p6_Pcat }" /> 
			<input type="hidden" name="p7_Pdesc" value="${p7_Pdesc }" /> 
			<input type="hidden" name="p8_Url" value="${p8_Url }" /> 
			<input type="hidden" name="p9_SAF" value="${p9_SAF }" /> 
			<input type="hidden" name="pa_MP" value="${pa_MP }" /> 
			<input type="hidden" name="pr_NeedResponse" value="${pr_NeedResponse }" /> 
			<input type="hidden" name="hmac" value="${hmac }" /> 
			<input type="submit" value="确认支付" />
	</form>
</body>
</html>