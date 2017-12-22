<%@page import="javax.xml.crypto.Data"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="com.ssn.tennis.common.*, java.util.*, java.text.*"%>
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
<%@include file="../menu.jsp" %>

<%
String tournamentName = request.getParameter("name");
Tournament tournament=Database.getInstance().getTournamentByName(tournamentName);
ArrayList<Match> matches=tournament.getMatches();
%>
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
%>
    <TR >
      <TD><INPUT type="hidden" name=id<%=matchNumber%>/><%=matchNumber%></TD>
      <TD><INPUT type="hidden" name="group"+<%=matchNumber%>/><b><%= match.getFormat().getGroup() %></b></TD>
      <TD><INPUT type="hidden" name="players"+<%=matchNumber%>><b><%= match.toString()%></b></TD>
      <TD><INPUT type="text" name="sc1"+<%=matchNumber%>/> </TD>
      <TD><INPUT type="text" name="sc2"+<%=matchNumber%>/> </TD>
    </TR>
<% } %>

</TABLE>
 <br />
<FORM action="addTournamentScore.jsp?name=<%=tournament.getName() %>" method="POST">
<INPUT type="submit" value="Save"/>
</FORM>
</BODY>
</HTML>     
