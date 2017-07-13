<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>confirmation</title>
<style type="text/css">
table {
	width: 700px;
	border: 1px solid black;
	margin:auto;
	border-spacing: 5px;
	box-shadow: 10px 10px 5px #888888;
}
.heading {
	text-align: center;
	color: black;
	background-color: lime;
	margin-left: 150px;
	margin-right: 150px;
	box-shadow: 10px 10px 5px #888888;
}
.buttons{
margin-top : 25px;
margin-left: 300px;
}
</style>
</head>
<body>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
	<form action="customer">
	<h1 class="heading">Booked Screen:${screen}</h1>
	<table>
	<tbody>
	<tr>
	<th><h1>Booking Confirmation</h1>
	<tr>
	<td><h2>Successfully Booked ${count} ticket(s) for Screen no:${screen}</h2>
	<tr>
	<td><h3>Ticket number(s) are:</h3>
	<td style="text-align: left;"><c:forEach var="bookedTic" items="${selected}" >
		<c:out value="${bookedTic}"></c:out>
	</c:forEach>
	<tr><td>
	<input class="buttons" type="submit" value="Book Again"> 
	</tbody>
	</table>
	</form>
</body>
</html>