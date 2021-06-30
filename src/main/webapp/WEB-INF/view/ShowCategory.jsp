<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta charset="ISO-8859-1">
<jsp:include page="/headerLink.jsp"></jsp:include>
<title>Insert title here</title>

<script type="text/javascript">
function updateCategory(id){
	document.forms["updateCategoryForm"]["id"].value = id;
	document.forms["updateCategoryForm"].submit();
}
	
	function deleteCategory(id){
		document.forms["deleteCategoryForm"]["id"].value = id;
		document.forms["deleteCategoryForm"].submit();
	}
</script>
</head>

<body>
	<jsp:include page="/menu.jsp"></jsp:include><br/>
	<table border="1">
	<tr>
		<th>#</th>
		<th>Category</th>
		<th>Type</th>
	</tr>
	<c:forEach items="${category}" var="categories" varStatus="status">
		<tr>
			<td>${status.count}</td>
			<td>${categories.name}</td>
			<td>${categories.type}</td>
			<td>${categories.budget}</td>
			<td><input type="button" name ="updateCategory" value="update" onClick="updateCategory(${categories.id})"/></td>
			<td><input type="button" value="delete" name="deleteCategory"  onClick="deleteCategory(${categories.id})"/></td>
		</tr>
	</c:forEach>
</table>
	<form action="${pageContext.request.contextPath}/category/update" 
	method="GET" name="updateCategoryForm" id="updateCategoryForm">
	<input type="hidden" name="id" id="id" value=""/>
	</form>
	<form action="${pageContext.request.contextPath}/category/delete" 
	method="POST" name="deleteCategoryForm" id="deleteCategoryForm">
	<input type="hidden" name="id" id="id" value=""/>
	</form><br/>
	<a href ="${pageContext.request.contextPath}/category/">Add new Category</a>
</body>
</html>