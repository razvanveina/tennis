<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="../css/style.css">
<title>Add player to tournament</title>
</head>
<body>
<%
String name = request.getParameter("name");
Tournament tournament = Database.getInstance().getTournamentByName(name); 
%>
<%@include file="../checkLogin.jsp" %>
<%@include file="../checkAdminRights.jsp" %> 
<%@include file="../menu.jsp" %> 

<%
ArrayList<User> users= Database.getInstance().getUsers();
ArrayList<User> lastUsers=tournament.getParticipants();
boolean hasOldParticipants=!lastUsers.isEmpty();
int checkCount=0;
%>

	<element>
		<FORM
			action="addPlayersToTournament.jsp?name=<%=tournament.getName() %>"
			method="POST">
			<legend>Choose users</legend>
			<fieldset>
				<%       
        for(User user : users){
          boolean isChecked= (hasOldParticipants && lastUsers.contains(user));
          String checked=(isChecked? "checked":"");
          %>
				<INPUT type="checkbox" name="usersArray[]"
					value="<%=user.getUser() %>" <%= checked %>/>
			<b>(<%=user.getUser()%>)</b> <%=user.getName()%> <%=user.getSurname()%> 
				<BR />
        

				<%}%>
				<INPUT type="submit" value="Add" />
			</fieldset>

<p></p>
		</FORM>
	</element>
</BODY>
</HTML>  