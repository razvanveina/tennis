<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="com.ssn.tennis.common.*, java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View teams</title>
<link rel="stylesheet" type="text/css" href="../css/style.css">
</head>
<BODY> 
<%@include file="../checkLogin.jsp" %> 
<%@include file="../checkAdminRights.jsp" %> 
<%@include file="../menu.jsp" %>

<%
ArrayList<Team> teams = Database.getInstance().getTeams();

%>

<TABLE>
    <TR bgcolor="#cccccc">
      <TH>Team</TH>
      <TH>Team Rating</TH>
      <TH>Won</TH>
      <TH>Lost</TH>
      <!-- <TH>Email</TH> -->
    </TR>

<% for (Team team : teams) { %>

    <TR bgcolor="#f2f2f2">
      <TD><b><%= team.toString() %></b></TD>
      <TD><%= 1000*team.getWon()/(team.getWon()+team.getLost()) %></TD>
      <TD><%= team.getWon() %></TD>
      <TD><%= team.getLost() %></TD>
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
