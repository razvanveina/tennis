<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="../css/style.css">
<title>Add user</title>
</head>
  <style type="text/css">
  .box1 { 
    float: left;
    padding: 15px;
    width:3%;
    line-height:160%;
    
  }
  .box2   {
  text-align: left;
  font-size: 30px;
  vertical-align: -50px;
   padding: 15px;
  float:left;
   width:80%;
  }
.clearfix::after {
    content:"";
    clear: both;
    display: table;
}
</style>
<body>
<%@include file="../checkLogin.jsp" %>
<%@include file="../checkAdminRights.jsp" %> 
<%@include file="../menu.jsp" %> 

<FORM action="addUser.jsp" method="POST">
<div class="box1">
User: <br>
Password:<br>
Name:  <br>
Surname:<br>
Admin:<br>
</div>

<div class="box2">
<INPUT type="text" name="user"/><BR/>
<INPUT type="password" name="pass"/><BR/>
<INPUT type="text" name="name"/><BR/>
<INPUT type="text" name="surname"/><BR/>
<INPUT type="checkbox" name="admin"/><BR/>
    <br>
<INPUT type="submit" value="Add user"/>
</div>
    
</FORM>