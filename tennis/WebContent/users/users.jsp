<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="com.ssn.tennis.common.*, java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View users</title>
<link rel="stylesheet" type="text/css" href="../css/style.css">
</head>
<BODY> 
<%@include file="../checkLogin.jsp" %> 
<%@include file="../checkAdminRights.jsp" %> 
<%@include file="../menu.jsp" %>

<%
ArrayList<User> users = Database.getInstance().getUsers(); 
%>

<TABLE>
    <TR>
      <TH>User</TH>
      <TH>Name</TH>
      <TH>Surname</TH>
      <!-- <TH>Email</TH> -->
    </TR>

<% for (User user : users) { %>

    <TR>
      <TD><A href="editUserForm.jsp?name=<%=user.getUser() %>"><%= user.getUser() %></A></TD>
      <TD><%= user.getName() %></TD>
      <TD><%= user.getSurname() %></TD>
<%--       <TD><A href="mailto:<%= user.getEmail() %>"><%= user.getEmail() %></A></TD> --%>    
    </TR>
<% } %>

</TABLE>
 <br />
<FORM action="addUserForm.jsp" method="POST">
<INPUT type="submit" value="Add user"/>
</FORM>

</BODY>
</HTML>     