<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta charset="ISO-8859-1">
<jsp:include page="/headerLink.jsp"></jsp:include>

<script type="text/javascript">
	function updateAccount(id){
		document.forms["updateAccountForm"]["id"].value = id;
		document.forms["updateAccountForm"].submit();
	}
	function deleteAccount(id){
		document.forms["deleteAccountForm"]["id"].value = id;
		document.forms["deleteAccountForm"].submit();
	}
</script>
</head>
<body>
<jsp:include page="/menu.jsp"></jsp:include><br/>
	<table border ="1">
		<tr>
			<th>#</th>
			<th>Name</th>
			<th>Type</th>
			<th>Opening Balance</th>
		</tr>
		<c:forEach items="${accounts}" var="account" varStatus="status">
			<tr>
				<td>${status.count}</td>
				<td>${account.name}</td>
				<td>${account.type}</td>
				<td>${account.openingBalance}</td>
				<td><input type="button" name="updateAccount" value="update" onClick="updateAccount(${account.id})"/></td>
				<td><input type="button" name="deleteAccount" value="delete" onClick="deleteAccount(${account.id})"/></td>
			</tr>
		</c:forEach>
	</table>
	<form name="updateAccountForm" id="updateAccountForm" action="${pageContext.request.contextPath}/account/update" method="GET">
	<input type="hidden" name="id" id="id"/>
	</form>
	<form name="deleteAccountForm" id="deleteAccountForm" action="${pageContext.request.contextPath}/account/delete" method="POST">
	<input type="hidden" name="id" id="id"/>
	</form>
</body>
</html>