<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style type="text/css">
.heading {
	text-align: center;
	color: white;
}

.message {
	background-color: green;
	color: red;
}

.color {
	background-color: gray;
	margin-left: 100px;
	margin-right: 100px;
}

.tablecss {
	margin: auto;
	border: 1px solid black;
	border-spacing: 50px;
	box-shadow: 10px 10px 5px #888888;
}
</style>
<title>Admin</title>
</head>
<div class="color">
	<h1 class="heading">Create Theater</h1>
</div>
<form action="createTheater" method="post">

	<%
		if (request.getParameter("Message") != null) {
	%>
	<div class="message">
		<%
			request.getParameter("Message");
		%>
	</div>
	<%
		}
	%>
	<table class="tablecss">
		<tbody>
			<tr>
				<td>Theater Name :
				<td><input type="text" name="thearename"
					placeholder="Enter Theaer name" required>
			<tr>
				<td>Theater Address :
				<td><input type="text" name="address"
					placeholder="Enter Theaer address" required>
			<tr>
				<td>Screens :
				<td><input type="number" name="screens"
					placeholder="Enter number of screens" required>
			<tr>
				<td>Rows :
				<td><input type="number" name="rows"
					placeholder="Enter number of rows" required>
			<tr>
				<td>Seats;
				<td><input type="number" name="seats"
					placeholder="Enter  number of seats" required>
			<tr>
				<td colspan="2" style="text-align: right;"><input type="submit"
					value="create"> <input type="reset" value="Reset">
		</tbody>
	</table>
</form>
<div>
	<img alt="" src="">
</div>