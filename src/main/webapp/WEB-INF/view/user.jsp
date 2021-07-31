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
	<jsp:include page="/menu.jsp"></jsp:include><br />
	<form action="${pageContext.request.contextPath}/user/" method="POST">
		<input type="hidden" name="id" value="${users.id}" /> Id <input
			type="text" name="userId" value="${users.userId}" /> <br /> <br />
		Password <input type="text" name="password" /> <br /> <br /> <input
			type="submit" value="submit" /><br /> <br />
	</form>
	<a href="${pageContext.request.contextPath}/user/">Show all Users</a>
</body>
</html>