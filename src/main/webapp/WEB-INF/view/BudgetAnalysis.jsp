<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta charset="ISO-8859-1">
<jsp:include page="/headerLink.jsp"></jsp:include>
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="/menu.jsp"></jsp:include><br/>
	<table border="1">
		<tr>
			<th>#</th>
			<th>Category</th>
			<th>Budgeted Amount</th>
			<th>Actual Amount</th>
		</tr>
		<c:forEach items="${budget}" var="budget" varStatus="status">
			<tr>
				<td>${status.count}</td>
				<td>${budget.categoryName}</td>
				<td>${budget.budgetedAmount}</td>
				<td>${budget.actualAmount}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>