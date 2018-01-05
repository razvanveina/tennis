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

<%
ArrayList<User> users = Database.getInstance().getUsers();
Collections.sort(users);
%>
<br>

<TABLE>
    <TR>
      <TH>User</TH>
      <TH>Name</TH>
      <TH>Surname</TH>
      <TH>Rating</TH>
      <TH>Won</TH>
      <TH>Lost</TH>
      <TH>Stars</TH>
      <!-- <TH>Email</TH> -->
    </TR>

<% for (User user : users) {
  int stars=Database.getInstance().getUserStars(user);
  %>

    <TR>
      <TD><A href="editUserForm.jsp?name=<%=user.getUser() %>"><%= user.getUser() %></A></TD>
      <TD><%= user.getName() %></TD>
      <TD><%= user.getSurname() %></TD>
      <TD><%= user.getRating() %></TD>
      <TD><%= user.getWon() %></TD>
      <TD><%= user.getLost() %></TD>
      <TD><%= stars%></TD>
<%--       <TD><A href="mailto:<%= user.getEmail() %>"><%= user.getEmail() %></A></TD> --%>    
    </TR>
<% } %>

</TABLE>
 <br />
 <%if(isAdmin){ %>
<FORM action="addUserForm.jsp" method="POST">
<INPUT type="submit" value="Add user"/>
</FORM>
<%} %>

</BODY>
</HTML>     
