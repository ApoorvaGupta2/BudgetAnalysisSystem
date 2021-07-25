<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<jsp:include page="/headerLink.jsp"></jsp:include>
</head>
<body>
<jsp:include page="/menu.jsp"></jsp:include><br/>
	<form action="${pageContext.request.contextPath}/account/update" method="POST">
	Name<input type="text" name="name" value="${accounts.name}"/><br/><br/>
	Type<input type="text" name="type" value="${accounts.type}"/><br/><br/>
	Opening Balance<input type="number" name="openingBalance" value="${accounts.openingBalance}"><br/><br/>
	<input type="hidden" name="id" value="${accounts.id}"/>
	<input type="submit" value="Submit" />
	</form>
</body>
</html>