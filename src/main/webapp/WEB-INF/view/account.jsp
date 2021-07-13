<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<jsp:include page="/headerLink.jsp"></jsp:include>
</head>
<body>
	<jsp:include page="/menu.jsp"></jsp:include><br />
	<form action="${pageContext.request.contextPath}/account/" method="POST">
		Name <input type="text" name="name" /> <br />
		<br /> Type <select name="type">
			<option id="type_" value="">Select</option>
			<option id="type_BANK" value="BANK">Bank</option>
			<option id="type_CC" value="CC">CC</option>
			<option id="type_WALLET" value="WALLET">Wallet</option>
			<option id="type_UPI" value="UPI">UPI</option>
		</select><br />
		<br /> Opening Balance <input type="number" name="openingBalance" min="0"step="0.01" /><br />
		<br /> <input type="submit" value="submit" />
	</form>
	<br/><a href="showaccount/">Show all accounts</a>
</body>
</html>