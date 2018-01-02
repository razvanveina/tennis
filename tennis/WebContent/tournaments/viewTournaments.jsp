<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="com.ssn.tennis.common.*, com.ssn.tennis.model.enums.*, java.util.*, java.text.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<style>
.tooltip {
    position: relative;
    display: inline-block;
}

.tooltip .tooltiptext {
    visibility: hidden;
    width: 120px;
    background-color: black;
    color: #fff;
    text-align: center;
    border-radius: 6px;
    padding: 5px 0;
    position: absolute;
    z-index: 1;
    top: -5px;
    left: 105%;
}

.tooltip:hover .tooltiptext {
    visibility: visible;
}
</style>
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
  int numberOfPlayersNeeded=t.getFormat()!=null? t.getMaxPlayers() : -1; 
  boolean hasEnoughPlayers=t.getParticipants().size()==numberOfPlayersNeeded;
  if(t.isFinished()){
    %>
    <TR bgcolor="#acb7a3">
    <%
  }else if(t.isStarted()){
  %>
  <TR bgcolor="#aec66b">
  <%
}else if(hasEnoughPlayers){
%>
  <TR bgcolor="#d9ff7c">
<%}else { %>
    <TR bgcolor="#d9ff9f">
    <%} %>
      <TD><%= df.format(t.getStartDate()) %></TD>
      <TD><A href="viewTournamentForm.jsp?name=<%=t.getName() %>"><%= t.getName() %></A></TD>
      <TD><%= t.getType() %></TD>
      <TD><%= t.getFormat().getName() %></TD>
      <TD><%= t.getMaxPlayers() %></TD> 
      <TD><% if(!t.isStarted()){%><A href=<%=(t.isStarted() ? "" :"addPlayersToTournamentForm.jsp?name="+t.getName())%>><%= t.getParticipantsAsString()%></A>
      <%}else{ %>
      <%=t.getParticipantsAsString() %>
      <%} %></TD> 
      <TD><%= t.getParticipants().size() %></TD>
      <TD><%= t.getStatus() %></TD>
      <%
      if(t.isStarted()){
        %>
        <TD>
        <div class="tooltip">          
        <img src="../img/start2.png" width="25" height="20">
        <span class="tooltiptext">Tournament has already started</span>

        </div>
</TD>
     <%
      }else if(!hasEnoughPlayers){
        %>
        <TD> 
        <div class="tooltip">
        <img src="../img/start1.png" width="25" height="20">
        <span class="tooltiptext">Not enough players</span>
        </div>
        </TD>
     
    <%} else {%>
        <TD>
        <div class="tooltip">
         <a href="startTournament.jsp?name=<%=t.getName() %>"> 
        <img src="../img/start.png" width="25" height="20">
        </a>
        <span class="tooltiptext">Start tournament</span>
        </div></TD>
    <%} %>
<% } %>

</TABLE>
 <br />

<FORM action="addTournamentForm.jsp" method="POST">
<INPUT type="submit" value="Add tournament"/>
</FORM>
</BODY>
</HTML>     
