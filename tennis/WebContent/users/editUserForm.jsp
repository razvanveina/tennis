<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="../css/style.css">
<script src="../js/functions.js"></script>
<title>Edit user</title>
</head>
<body>
<%
String userName = request.getParameter("name");
User user = Database.getInstance().getUserByUsername(userName); 
%>

<%@include file="../checkLogin.jsp" %>
<%@include file="../checkAdminRights.jsp" %> 

<FORM action="editUser.jsp" method="POST">
<INPUT type="hidden" name="olduser" value="<%= user.getUser() %>"/>
User: <INPUT type="text" name="user" value="<%= user.getUser() %>"/><BR/>
Password: <INPUT type="password" name="pass" value="<%= user.getPassword() %>"/><BR/>
Name: <INPUT type="text" name="name" value="<%= user.getName() %>"/><BR/>
Surname: <INPUT type="text" name="surname" value="<%= user.getSurname() %>"/><BR/>
Admin: <INPUT type="checkbox" name="email" <%= user.isAdmin()?"checked":""%>/><BR/>
<INPUT type="submit" value="Save Changes"/>
<A href="delUser.jsp?name=<%= user.getUser() %>" onclick="return confirmDelete()">Delete user</A>
</FORM>