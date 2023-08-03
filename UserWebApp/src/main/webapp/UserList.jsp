<%@page import="javax.naming.Context"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.sql.ResultSet" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>ResultSet Table</h1>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Surname</th>
            <th>Age</th>
            <th>Edit</th>
            <th>Delete</th>
        </tr>
            <% 
       
        ResultSet resultSet = (ResultSet) application.getAttribute("resultset");
        while (resultSet.next()) {
            int id = resultSet.getInt("ID");
            String name = resultSet.getString("user_name");
            String surname = resultSet.getString("user_surname");
            int age = resultSet.getInt("age");
        %>
        <tr>
            <td><%= id %></td>
            <td><%= name %></td>
            <td><%= surname %></td>
            <td><%= age %></td>
            
            <td><a href= "<%=request.getContextPath()%>/editservlet?id=<%=id%>" >Edit</a> </td>
            <td><a href= "<%=request.getContextPath()%>/edit.jsp?id=<%=id%>" >Delete</a> </td>
        </tr>
        <% } %>
   
    </table>
     <a href="home.html">Home</a>
</body>
</html>