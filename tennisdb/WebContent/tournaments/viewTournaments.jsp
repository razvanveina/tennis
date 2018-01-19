<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="com.ssn.tennis.common.*, com.ssn.tennis.model.enums.*, java.util.*, java.text.*, java.net.*, java.nio.charset.*"%>
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
    content="";
    position: absolute;
    z-index: 1;
    top: -5px;
    left: 105%;
    opacity: 0;  
    transition: opacity 2s; 
    
}

.tooltip:hover .tooltiptext {
    opacity: 1;
    transition-delay: 1s;
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
<%
ArrayList<Tournament> tournaments = Database.getInstance().getTournaments(); 
%>

<FORM action="addTournamentForm.jsp" method="POST">
<INPUT type="submit" value="Add tournament"/>
</FORM>

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
      <TH width="30"></TH>
      <TH width="30"></TH>
      <TH width="30"></TH>
      <TH width="30"></TH>
      <TH>Winner</TH>
      <!-- <TH>Email</TH> -->
    </TR>

<% 
int playersChosen=0;
for (int i = tournaments.size() - 1; i >= 0; i--) { 
  Tournament t = tournaments.get(i);
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
      <TD><% if(!t.isStarted()){%><A href=<%=(t.isStarted() ? "" :"addPlayersToTournamentForm.jsp?name="+URLEncoder.encode(t.getName(), StandardCharsets.UTF_8.toString()))%>><%= t.getParticipantsAsString()%></A>
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
        <span class="tooltiptext" flow="right">Tournament has already started</span>
        </div>
        </TD>
         <TD>        
                   
        <img src="../img/edit1.png" width="25" height="20">

       
        </TD>

     <%
      }else if(!hasEnoughPlayers){
        %>
        <TD> 
        <div class="tooltip">
        <img src="../img/start1.png" width="25" height="20">
        <span class="tooltiptext"><%=t.getParticipants().size()>numberOfPlayersNeeded? "Too many players":"Not enough players" %></span>
        </div>
        </TD>
         <TD>
            <% if (t.getStatus() != TournamentStatus.FINISHED) { %>
                 
         <div class="tooltip">
         <a href="addTournamentForm.jsp?name=<%= URLEncoder.encode(t.getName(), StandardCharsets.UTF_8.toString()) %>"> 
        <img src="../img/edit.png" width="25" height="20">
        </a>
        <span class="tooltiptext">Edit tournament</span>
        </div>
        <% } %>
        </TD>
     
    <%} else {%>
        <TD>
        <div class="tooltip">
         <a href="startTournament.jsp?name=<%= URLEncoder.encode(t.getName(), StandardCharsets.UTF_8.toString()) %>"> 
        <img src="../img/start.png" width="25" height="20">
        </a>
        <span class="tooltiptext">Start tournament</span>
        </div></TD>
        <TD>
         <% if (t.getStatus() != TournamentStatus.FINISHED) { %>                
         <div class="tooltip">
         <a href="addTournamentForm.jsp?name=<%= URLEncoder.encode(t.getName(), StandardCharsets.UTF_8.toString()) %>"> 
        <img src="../img/edit.png" width="25" height="20">
        </a>
        <span class="tooltiptext">Edit tournament</span>
        </div>
        <% } %>
        </TD>
        
    <%} %>
    <TD>
         <div class="tooltip">
         <a href="duplicateTournament.jsp?name=<%= URLEncoder.encode(t.getName(), StandardCharsets.UTF_8.toString()) %>"> 
        <img src="../img/duplicate.png" width="25" height="20">
        </a>
        <span class="tooltiptext">Duplicate tournament</span>
        </div>
        </TD>
        
            <TD>
            <% if (t.getStatus() != TournamentStatus.FINISHED) { %>
         <div class="tooltip">
         <a href="deleteTournament.jsp?name=<%= URLEncoder.encode(t.getName(), StandardCharsets.UTF_8.toString()) %>"> 
        <img src="../img/delete.png" width="25" height="20">
        </a>
        <span class="tooltiptext">Delete tournament</span>
        </div>
        <% }else{ %>
        <img src="../img/delete1.png" width="25" height="20">
        <%} %>
        </TD>
        <TD><%=t.getWinner()!=null? t.getWinner():"" %></TD>
        
<% } %>       

</TABLE>
 <br />

</BODY>
</HTML>     