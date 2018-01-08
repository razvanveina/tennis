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
<div class="clearfix">
<div class="addBox1">
User: <br>
Password: <br>
<br>
<INPUT type="submit" value="Login"/>
</div>
<div class="addBox2">
<INPUT type="text" name="user"/><BR/>
<INPUT type="password" name="pass"/>
</div>
</div>
</FORM>
<%
} else {
%>
<%@include file="menu.jsp" %> 
 <img class="bottomImg" src="./img/0022.gif">

<%
}
%>

</body>
</html>