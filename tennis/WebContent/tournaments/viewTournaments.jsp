<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="com.ssn.tennis.common.*, java.util.*, java.text.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View tournaments</title>
<link rel="stylesheet" type="text/css" href="../css/style.css">
</head>
<BODY> 
<%@include file="../checkLogin.jsp" %> 
<%@include file="../checkAdminRights.jsp" %> 
<%@include file="../menu.jsp" %>

<%
ArrayList<Tournament> tournaments = Database.getInstance().getTournaments(); 
%>

<TABLE>
    <TR>
      <TH>Date</TH>
      <TH>Name</TH>
      <TH>Type</TH>
      <TH>Players</TH>
      <TH>No of players</TH>
      <!-- <TH>Email</TH> -->
    </TR>

<% for (Tournament t : tournaments) { 
  DateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.ENGLISH);
%>

    <TR> 
      <TD><%= df.format(t.getStartDate()) %></TD>
      <TD><A href="editTournamentForm.jsp?name=<%=t.getName() %>"><%= t.getName() %></A></TD>
      <TD><%= t.getType() %></TD>
      <TD><A href="addPlayersToTournament.jsp?name=<%=t.getName() %>"><%= t.getParticipants().toString() %></A></TD> 
      <TD><%= t.getParticipants().size() %></TD>
<%--       <TD><A href="mailto:<%= user.getEmail() %>"><%= user.getEmail() %></A></TD> --%>    
    </TR>
<% } %>

</TABLE>
 
</BODY>
</HTML>     
