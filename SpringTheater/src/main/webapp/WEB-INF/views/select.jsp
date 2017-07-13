<%@page import="com.sa.data.screen"%>
<%@page import="com.sa.data.Seats"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style>
.heading {
	text-align: center;
	color: black;
	background-color: lime;
	margin-left: 150px;
	margin-right: 150px;
	box-shadow: 10px 10px 5px #888888;
}

form {
	margin-left: 350px;
}

table {
	width: 300px;
	border: 1px solid black;
	margin-left: 175px;
	margin-right: auto;
	border-spacing: 5px;
	box-shadow: 10px 10px 5px #888888;
}
.buttons{
margin-top : 25px;
margin-left: 400px;
}
</style>
</head>
<body>

	<%
		Seats seats = new Seats();
		ArrayList<Seats> list = (ArrayList<Seats>) request.getAttribute("seats");
		int rows = Integer.valueOf(request.getAttribute("rows").toString());
		int i = 0;
		int couner=0;
	%>
	<h1 class="heading">Selected Screen:${selectedscreen}</h1>
	<form action="confirmation" method="post">
	<input type="hidden" name="screen_id" value="${selectedscreen}">
	<table>
		<%for ( ;i < rows; i++) {%>
		<tr>
		<%for (int j = 0; j < rows; j++) {%>
		<td>
			<%if(couner<list.size()){%>
			<% seats = list.get(couner);%>
			<%out.println(seats.getRow()); %>
			<input type="checkbox" name="selected" value="<%out.println(seats.getRow());%>" <%if(seats.isReserved()){out.println("disabled");}else{out.println(" ");}%>>
			<%couner++; %>
			<% }else{ break;}%>
			
		<% } %>		
		<% } %>
	</table>
	<div class="buttons"><input type="submit" value="book"> <input type="reset" value="Reset"></div>
		
	</form>
</body>
</html>