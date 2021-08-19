<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta charset="ISO-8859-1">
<jsp:include page="/headerLink.jsp"></jsp:include>
</head>
<body>
	<jsp:include page="/menu.jsp"></jsp:include>
	<form action="${pageContext.request.contextPath}/transaction/contra/" method="POST"><br/>
		Transaction Type: Contra <input type="hidden" name="type" value="CONTRA"/><br /> <br /> 
		From Account: <select name="fromAccountId">
					<c:forEach items="${accounts}" var="account">
						<option value="${account.id}">${account.name} (${account.type} - ${account.accountNumber})</option> 	
					</c:forEach>
				</select><br/> <br />
		To Account: <select name="toAccountId">
			<c:forEach items="${accounts}" var="account">
				<option value="${account.id}">${account.name} (${account.type} - ${account.accountNumber})</option> 	
			</c:forEach>
		</select><br/> <br />
		Date: <input type="date" id="date" name="date" onkeydown="return false"></input><br /> <br /> 
		Detail: <input type="text" id="detail" name="detail"></input><br /> <br /> 
		Amount: <input type="number" id="amount" name="amount" min ="0" step="0.01"></input><br /><br/>
		<br /> <input type="submit" value="Submit" />
	</form>
	<br/>
</body>
</html>