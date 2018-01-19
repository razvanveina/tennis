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

<%
ArrayList<Team> teams = Database.getInstance().getTeams();

HashMap<String, Team> teamsMap=new HashMap<String, Team>();
for(Team t:teams){
  teamsMap.put(t.toString(), t);
}
teams=new ArrayList(teamsMap.values());
%>

<TABLE>
    <TR bgcolor="#cccccc">
      <TH>Team</TH>
      <TH>Team Rating</TH>
      <TH>Won</TH>
      <TH>Lost</TH>
      <TH>Stars</TH>
      <!-- <TH>Email</TH> -->
    </TR>

<% for (Team team : teams) { %>

    <TR bgcolor="#f2f2f2">
      <TD><b><%= team.toString() %></b></TD>
      <% int sum = team.getWon()+team.getLost(); %>
      <TD><%= sum != 0 ? 1000*team.getWon()/(team.getWon()+team.getLost()) : "--" %></TD>
      <TD><%= team.getWon() %></TD>
      <TD><%= team.getLost() %></TD>
       <TD align="center">
      <% 
      int stars=Database.getInstance().getTeamStars(team);
     while(stars>=10){
       %>
       <img src="../img/star10.png" width="20" height="20"> 
    <%
    stars=stars-10;
     }      
      for(int i=0; i<stars; i++){ %>
      <img src="../img/star.png" width="10" height="10">
      <%} %>
      </TD>
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
