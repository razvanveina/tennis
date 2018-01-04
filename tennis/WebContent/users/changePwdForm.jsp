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
<style>
  .addBox1 { 
    float: left;
    padding: 15px;
    width:6%;
    line-height:160%;
    
  }
  </style>
<body>

<%@include file="../checkLogin.jsp" %>
<%@include file="../menu.jsp" %> 

<%
User user = (User) session.getAttribute("user");
%>
<B>Change password</B>
<FORM action="changePwd.jsp" method="POST">
<INPUT type="hidden" name="olduser" value="<%= user.getUser() %>"/>

<div class="clearfix">
<div class="addBox1">
Old password:<br>
New password:<br>
<br>
<INPUT type="submit" value="Change password"/>
</div>
<div class="addBox2">
 <INPUT type="password" name="oldpass" /><BR/>
 <INPUT type="password" name="newpass" /><BR/>
 </div>
</FORM>
</div>
</body>