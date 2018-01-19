<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<style>
  .addBox { 
    float: left;
    padding: 15px;
    width:7%;
    line-height:160%;
    
  }
  </style>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="../css/style.css">
<script src="../js/functions.js"></script>
<title>Edit user</title>
</head>
<script>

</script>
<body>
<%
String userName = request.getParameter("name");
User user = Database.getInstance().getUserByUsername(userName); 
%>

<%@include file="../checkLogin.jsp" %>
<%@include file="../checkAdminRights.jsp" %> 

<%if(isAdmin){ %>
<strong> Edit user</strong> 
<%}else{ %>
<strong> View user</strong> 
<%} %>
<div class="clearfix">
<div class="addBox">
User:  <br>
Name:  <br>
Surname:<br>
<%if(isAdmin){ %>
Reset password:
Admin:<br>
<br>
<A href="delUser.jsp?name=<%= user.getUser() %>" onclick="return confirmDelete()"><button>Delete</button></A>
<%} %>
</div>

<div class="addBox2">
<FORM action="editUser.jsp" method="POST">
<INPUT type="hidden" name="olduser" value="<%= user.getUser() %>" />
<INPUT type="text" name="user" value="<%= user.getUser() %>" <%=!isAdmin? " disabled":""%>/><BR/>
<INPUT type="text" name="name" value="<%= user.getName() %>" <%=!isAdmin? " disabled":""%>/><BR/>
<INPUT type="text" name="surname" value="<%= user.getSurname() %>" <%=!isAdmin? " disabled":""%>/><BR/>
<%if(isAdmin){ %>
<INPUT type="password" name="pass" />
<INPUT type="checkbox" name="admin" <%= user.isAdmin()?" checked ":""%>/><BR/>
 <br>
<INPUT type="submit" value="Save Changes"/>

<%} %>

</FORM>
</div>