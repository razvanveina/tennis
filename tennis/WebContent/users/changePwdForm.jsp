<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="../css/style.css">
<script src="../js/functions.js"></script>
<title>Settings</title>
</head>
<body>

<%@include file="../checkLogin.jsp" %>
<%@include file="../menu.jsp" %> 

<%
User user = (User) session.getAttribute("user");
%>
<B>Change password</B>
<FORM action="changePwd.jsp" method="POST">
<INPUT type="hidden" name="olduser" value="<%= user.getUser() %>"/>
Old password: <INPUT type="password" name="oldpass" /><BR/>
New password: <INPUT type="password" name="newpass" /><BR/>
<INPUT type="submit" value="Change password"/>
</FORM>