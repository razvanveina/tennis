<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="com.ssn.tennis.common.*, java.util.*, com.ssn.core.*, java.text.*"%>
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
String p1 = request.getParameter("p1");
String p2 = request.getParameter("p2");
Team team1 = ApplicationFactory.getInstance().getDatabase().getTeamById(Integer.parseInt(p1)); 
Team team2 = ApplicationFactory.getInstance().getDatabase().getTeamById(Integer.parseInt(p2));
int sum1 = team1.getWon() + team2.getLost();
int sum2 = team2.getWon() + team1.getLost();
if (sum1+sum2 == 0) {
return;  
}
int pr1 = 100 * sum1 / (sum1+sum2);
int pr2 = 100 - pr1;
DecimalFormat df = new DecimalFormat("#.00");
%>

<table>
<TR>
<tH></tH>
<th>
<%=team1.toString() %>
</th>
<th>
<%=team2.toString() %>
</th>
</TR>

<TR>
<td>
Won
</td>
<td>
<%=team1.getWon() %>
</td>
<td>
<%=team2.getWon() %>
</td>
<td>
<%=pr1%>%
</td>
<td>
<%=df.format(100.0/pr1)%> 
</td>
</TR>

<TR>
<td>
Lost
</td>
<td>
<%=team1.getLost() %>
</td>
<td>
<%=team2.getLost() %>
</td>
<td>
<%=pr2%>%
</td>
<td>
<%=df.format(100.0/pr2)%>
</td>
</TR>

<TR>
<td>
Stars
</td>
<td>
<%=team1.getStars() %>
</td>
<td>
<%=team2.getStars() %>
</td>
</TR>
</table>

<table>
<TR>
<tH colspan="5">H2H</tH>
</TR>

<%
List<Match> matches = ApplicationFactory.getInstance().getDatabase().getMatchesInvolvingTeams(team1, team2);

for (Match m : matches) { 
%>
<TR>
<td>
<%= m.getTournament().getStartDate() %>
</td>
<td>
<%=m.getTeam1() %>
</td>
<td>
<B>
<%=m.getPoints1() %>
</B>
</td>
<td>
<B>
<%=m.getPoints2() %>
</B>
</td>
<td>
<%=m.getTeam2() %>
</td>
</TR>

<%
}
%>

</table>
</BODY>
</HTML>     
