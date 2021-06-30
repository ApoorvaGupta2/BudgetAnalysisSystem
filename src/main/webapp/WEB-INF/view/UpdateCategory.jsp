<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<jsp:include page="/headerLink.jsp"></jsp:include>
<title>Insert title here</title>

<script type="text/javascript">
function load() {
	var type = "${category.type}";
	document.getElementById("type_"+type).checked = true;
}
</script>
</head>
<body onload="load()">
	<jsp:include page="/menu.jsp"></jsp:include>
	<form action="${pageContext.request.contextPath}/category/update" method="POST">
		Category<input type="text" name="name" id="name" value ="${category.name}"/><br/><br/>
		Income<input type ="radio" name="type" id="type_INCOME" value="INCOME"/>
		Expense<input type ="radio" name="type" id="type_EXPENSE" value="EXPENSE"/><br/><br/>
		<input type="hidden" name="id" id="id" value="${category.id}"/>
		<input type="submit" value="submit"/>
	</form>
	<a href="${pageContext.request.contextPath}/category/ShowCategory/">Show all Categories</a>
</body>
</html>