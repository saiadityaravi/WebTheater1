<%@page import="com.sa.DAO.TheaterDAO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style type="text/css">
.heading {
	text-align: center;
	color: black;
	background-color: lime;
	margin-left: 150px;
	margin-right: 150px;
	box-shadow: 10px 10px 5px #888888;
}

form {
	margin: auto;
}

table {
	width : 300px;
	border: 1px solid black;
	margin-left: auto;
	margin-right: auto;
	border-spacing: 50px;
	box-shadow: 10px 10px 5px #888888;
}
</style>
<title>Book tickets</title>
</head>
<body>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<div class="heading">
		<h1>
			<%=request.getAttribute("name")%>
		</h1>
		<hr>
		<h4><%=request.getAttribute("address")%></h4>
	</div>
	<form action="select" method="post">
		<table>
			<tbody>
				<tr>
					<th>Screen
					<th>Movie <c:forEach var="screen" items='${screen}'>
							<tr>
								<td>${screen.getId()}
								<td>${screen.getScreen()}
								<td><input type="radio" name="movie"
									value="${screen.getId()}" />
						</c:forEach>
				<tr>
					<td colspan="3" style="text-align: right;"><input
						type="submit" value="book"> <input type="reset"
						value="Reset">
			</tbody>
		</table>
	</form>

</body>
</html>