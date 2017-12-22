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
      <TH>Format</TH>
      <TH>Max players</TH>
      <TH>Players</TH>
      <TH>No of players</TH>
      <TH>Status</TH>
      <!-- <TH>Email</TH> -->
    </TR>

<% 
int playersChosen=0;
for (Tournament t : tournaments) { 
  DateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.ENGLISH);
  int numberOfPlayersNeeded=t.getMatches().isEmpty()? -1:t.getFormat().getMaxPlayers();
  boolean hasEnoughPlayers=t.getParticipants().size()==numberOfPlayersNeeded;
if(hasEnoughPlayers){
%>
  <TR bgcolor="#ccff99">
<%}else{ %>
    <TR bgcolor="#ffff99">
    <%} %>
      <TD><%= df.format(t.getStartDate()) %></TD>
      <TD><A href="viewTournamentForm.jsp?name=<%=t.getName() %>"><%= t.getName() %></A></TD>
      <TD><%= t.getType() %></TD>
      <TD><%= t.getFormat().getName() %></TD>
      <TD><%= t.getFormat().getMaxPlayers() %></TD> 
      <TD><A href="addPlayersToTournamentForm.jsp?name=<%=t.getName() %>"><%= t.getParticipantsAsString()%></A></TD> 
      <TD><%= t.getParticipants().size() %></TD>
      <TD><%= t.getStatus() %></TD>
     <TD> <a href="viewTournamentForm.jsp?name=<%=t.getName()%>">
  <img src="/img/start.png" alt="start">
</a>
</TD>
<% } %>

</TABLE>
 <br />
<FORM action="addTournamentForm.jsp" method="POST">
<INPUT type="submit" value="Add tournament"/>
</FORM>
</BODY>
</HTML>     
