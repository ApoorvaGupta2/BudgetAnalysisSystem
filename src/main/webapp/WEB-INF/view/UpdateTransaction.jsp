<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<meta charset="ISO-8859-1">
<jsp:include page="/headerLink.jsp"></jsp:include>
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="/menu.jsp"></jsp:include>
	<form action="${pageContext.request.contextPath}/transaction/update" method="POST">
      	  <fmt:formatDate value="${transactions.date}" pattern="yyyy-MM-dd" var ="fmtDate"/><br/>
      Transaction type <input type="text" name="type" id="type" value="${transactions.type}"></input><br/><br/>
      Category: <input type="text" name="category" id="category" value="${transactions.categoryName}"></input><br/><br/>
      Date<input type="date" id="date" name="date" value="${fmtDate}"></input><br/><br/>
      Detail<input type="text" id="detail" name="detail" value="${transactions.detail}"></input><br/><br/>
      Amount<input type="text" id="amount" name="amount" value="${transactions.amount}"></input><br/><br/>
      		<input type="hidden" name="id" value="${transactions.id}"/>
      		<input type="hidden" name="categoryId" value="${transactions.categoryId}"/>
      <input type="submit" value="Submit" />
</form>
<a href="${pageContext.request.contextPath}/transaction/ShowTransaction/">Show all Transactions</a>
</body>
</html>