<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.example.users.Users"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<% 

Users user = (Users)application.getAttribute("liste");

%>
		
		
<form action="editservlet" method="post">



		<table class="table table-hover">
			<tr>
				<td>Name</td>
				<td><input type="text" name="name" value="<%=user.getUserName() %>" /></td>
			</tr>
			<tr>
				<td>Surname</td>
				<td><input type="text" name="surname"  value="<%=user.getSurName() %>"/></td>
			</tr>
			<tr>
				<td>Age</td>
				<td><input type="text" name="age" value="<%=user.getAge()%>"/></td>
				<td> <input type="hidden" name="id" value="<%=user.getId()%>"/> </td>
			</tr>
			<tr>
				<td><input type="submit" value="update" /></td>
				<td><input type="reset" value="cancel" /></td>
				
			</tr>

		</table>
</body>
</html>