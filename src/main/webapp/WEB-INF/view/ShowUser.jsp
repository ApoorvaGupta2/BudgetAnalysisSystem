<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta charset="ISO-8859-1">
<jsp:include page="/headerLink.jsp"></jsp:include>

<script type="text/javascript">
	function updateUser(id){
		document.forms["updateUserForm"].action = document.forms["updateUserForm"].action+id;
		document.forms["updateUserForm"].submit();
	}
	
	function deleteUser(id){
		document.forms["deleteUserForm"].action = document.forms["deleteUserForm"].action+id;
		document.forms["deleteUserForm"].submit();
	}
</script>
</head>
<body>
	<jsp:include page="/menu.jsp"></jsp:include><br />
	<table border="1">
		<tr>
			<th>#</th>
			<th>Id</th>
		</tr>
		<c:forEach items="${users}" var="user" varStatus="status">
			<tr>
				<td>${status.count}</td>
				<td>${user.userId}</td>
				<td><input type="button" value="update" name="updateUser"
					onClick="updateUser(${user.id})" /></td>
				<td><input type="button" value="delete" name="deleteUser"
					onClick="deleteUser(${user.id})" /></td>
			</tr>
		</c:forEach>
	</table>
	<form name="updateUserForm" id="updateUserForm"
		action="${pageContext.request.contextPath}/user/" method="GET">
	</form>
	<form name="deleteUserForm" id="deleteUserForm"
		action="${pageContext.request.contextPath}/user/" method="POST">
	</form>
</body>
</html>