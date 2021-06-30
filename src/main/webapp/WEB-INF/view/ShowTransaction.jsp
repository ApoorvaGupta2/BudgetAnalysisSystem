<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta charset="ISO-8859-1">
<jsp:include page="/headerLink.jsp"></jsp:include>
<title>Insert title here</title>

<script type="text/javascript">
function deleteTransaction(id) {
	document.forms["deleteTransactionForm"]["id"].value = id;
	document.forms["deleteTransactionForm"].submit();
	
}

function updateTransaction(id){
	document.forms["updateTransactionForm"]["id"].value = id;
	document.forms["updateTransactionForm"].submit();
}
</script>

</head>
<body>
	<jsp:include page="/menu.jsp"></jsp:include><br/>
	<table border="1">
		<tr>
			<th>#</th>
			<th>Transaction Type</th>
			<th>Category</th>
			<th>Date</th>
			<th>Detail</th>
			<th>Amount</th>
		</tr>
		<c:forEach items="${transactions}" var="transaction" varStatus="status">
			<tr>
				<td>${status.count}</td>
				<td>${transaction.type}</td>
				<td>${transaction.categoryName}</td>
				<td><fmt:formatDate value="${transaction.date}" pattern="dd/MM/yyyy" /></td>
				<td>${transaction.detail}</td>
				<td>${transaction.amount}</td>
				<td><input type="button" name="updateTransaction" value="update" onClick="updateTransaction(${transaction.id})"/></td>
				<td><input type="button" name="deleteTransaction" value="delete" onClick="deleteTransaction(${transaction.id})"/></td>
			</tr>
		</c:forEach>
	</table>
	<br />
	<form action="${pageContext.request.contextPath}/transaction/delete"
		method="POST" id="deleteTransactionForm" name="deleteTransactionForm">
		<input type="hidden" name="id" id="id" value="" />
	</form>
	<form action="${pageContext.request.contextPath}/transaction/update"
		method="GET" id="updateTransactionForm" name="updateTransactionForm">
		<input type="hidden" name="id" id="id" value="" />
	</form>
	<a href="${pageContext.request.contextPath}/transaction/">Add new Transaction</a>
</body>
</html>