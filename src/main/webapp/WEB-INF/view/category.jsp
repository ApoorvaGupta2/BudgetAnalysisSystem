<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<jsp:include page="/headerLink.jsp"></jsp:include>
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="/menu.jsp"></jsp:include>
	<form action="${pageContext.request.contextPath}/category/" method="POST"><br/>
		Category<input type="text" name="name" id="name"/><br/><br/>
		Income<input type ="radio" name="type" value="INCOME"/>
		Expense<input type ="radio" name="type" value="EXPENSE"/><br/><br/>
		Budget<input type="text" name ="budget" id="budget"/><br/><br/>
		<input type="submit" value="submit"/>
	</form>
	<br/><a href="ShowCategory/">Show all Categories</a>
</body>
</html>