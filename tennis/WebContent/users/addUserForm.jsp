<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="../css/style.css">
<title>Add user</title>
</head>
<body>
<%@include file="../checkLogin.jsp" %>
<%@include file="../checkAdminRights.jsp" %> 
<%@include file="../menu.jsp" %> 

<FORM action="addUser.jsp" method="POST">
User: <INPUT type="text" name="user"/><BR/>
Password: <INPUT type="password" name="pass"/><BR/>
Name: <INPUT type="text" name="name"/><BR/>
Surname: <INPUT type="text" name="surname"/><BR/>
Admin: <INPUT type="checkbox" name="admin"/><BR/>
<INPUT type="submit" value="Add user"/>
</FORM>