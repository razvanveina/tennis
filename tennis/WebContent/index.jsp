<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="com.ssn.tennis.model.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Ping masters</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<% User user = (User)session.getAttribute("user"); 
if (user == null) {
%>
<%@include file="menu.jsp" %>  
<FORM action="login.jsp" method="POST">
User: <INPUT type="text" name="user"/><BR/>
Password: <INPUT type="password" name="pass"/>
<INPUT type="submit" value="Login"/>
</FORM>
<%
} else {
%>
<%@include file="menu.jsp" %> 
<%
}
%>

</body>
</html>