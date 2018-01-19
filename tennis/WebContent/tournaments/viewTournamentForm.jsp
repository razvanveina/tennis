<%@page import="javax.xml.crypto.Data"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="com.ssn.tennis.common.*, java.util.*, java.text.*, com.ssn.tennis.model.classification.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View tournament</title>
<link rel="stylesheet" type="text/css" href="../css/style.css"> 
</head>
<BODY> 
<%@include file="../checkLogin.jsp" %> 
<%@include file="../checkAdminRights.jsp" %> 

<%
String tournamentName = request.getParameter("name");
Tournament tournament=Database.getInstance().getTournamentByName(tournamentName);
ArrayList<Match> matches=tournament.getMatches();
%>
<FORM action="addTournamentScore.jsp?name=<%=tournament.getName() %>" method="POST">
<TABLE>
    <TR>
      <TH>Match</TH>
      <TH>Group</TH>
      <TH>Players</TH>
      <TH>Team 1 score</TH>
      <TH>Team 2 score</TH>
      <!-- <TH>Email</TH> -->
    </TR>

<% 
for (Match match: matches) { 
  int matchNumber=match.getFormat().getNumber();
  if(match.isPlayed()){
    %>
    <TR bgcolor="#cccccc">
    <%
    }else{%>
  
    <TR bgcolor="#f2f2f2">
    <%} %>
      <TD><INPUT type="hidden" name=id<%=matchNumber%>/><%=matchNumber%></TD>
      <TD><INPUT type="hidden" name="group"+<%=matchNumber%>/><b><%= match.getFormat().getStageInfo() %></b></TD>
      <%
      Team team1 = match.getTeam1();
      Team team2 = match.getTeam2();
      %>
      <TD><INPUT type="hidden" name="players"+<%=matchNumber%>><b><%= match.toString()%></b></TD>
      <TD><INPUT type="text" name="sc1_<%=matchNumber%>" value="<%=match.getPoints1()%>" autocomplete="off" <%=tournament.isFinished()? " disabled":""%>/> </TD>
      <TD><INPUT type="text" name="sc2_<%=matchNumber%>" value="<%=match.getPoints2()%>" autocomplete="off" <%=tournament.isFinished()? " disabled":""%>/> </TD>
    </TR>
<% } %>

</TABLE>
 <br />
 <%if(!tournament.isFinished()){ %>
<INPUT type="submit" value="Save"/>
<%} %>
</FORM>

 <br />
<% for (int i = 0; i < tournament.getFormat().getGroupNames().length; i++) {
  Classification cls = tournament.getClassification(tournament.getFormat().getGroupNames()[i]);
  %>
  <TABLE>
  <TR>
  <TH colspan="6">Group <%=tournament.getFormat().getGroupNames()[i] %></TH>
  </TR>
  <TR>
  <TH>Pos</TH><TH>Team</TH><TH>Won</TH><TH>Lost</TH><TH>Points for</TH><TH>Points against</TH>
  </TR>
  <% 
  int counter = 0;
  for (ClassificationLine cl : cls.getCls()) {
  %>
  <TR>
  <TD><%=++counter%></TD><TD><b><%=cl.getTeam() %></b></TD><TD><%=cl.getWon() %></TD>
  <TD><%=cl.getLost() %></TD><TD><%=cl.getGf()%></TD><TD><%=cl.getGa() %></TD>
  </TR>
  
<% } %>
  </TABLE>
  
<% } %>
</BODY>
</HTML>     
