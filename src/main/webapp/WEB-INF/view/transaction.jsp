<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta charset="ISO-8859-1">
<jsp:include page="/headerLink.jsp"></jsp:include>

<script type="text/javascript">
	function getCategory() {
		var type = document.getElementById("type").value;
		window.location.href = "${pageContext.request.contextPath}/transaction/?type="
				+ type;
	}

	function getTransactionType() {
		document.getElementById("type_${type}").selected = "true";
	}
</script>
</head>
<body onload="getTransactionType()">
	<jsp:include page="/menu.jsp"></jsp:include>
	<form action="${pageContext.request.contextPath}/transaction/"
		method="POST"><br/>
		Transaction Type: <select name="type" id="type"
			onchange="getCategory()">
			<option value="" id="type_">Select</option>
			<option value="INCOME" id="type_INCOME">Income</option>
			<option value="EXPENSE" id="type_EXPENSE">Expense</option>
		</select><br /> <br /> Category: <select id="category" name="categoryId">
			<c:forEach items="${categories}" var="category">
				<option value="${category.id}">${category.name}</option>
			</c:forEach>
		</select><br /> <br /> Date: <input type="date" id="date" name="date"
			onkeydown="return false"></input><br /> <br /> Detail: <input
			type="text" id="detail" name="detail"></input><br /> <br /> Amount:
		<input type="number" id="amount" name="amount" min ="0" step="0.01"></input><br />
		<br /> <input type="submit" value="Submit" />
	</form>
	<br/><a href="ShowTransaction/">Show all transactions</a>
</body>
</html>