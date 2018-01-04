<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="com.ssn.tennis.model.*" import="com.ssn.tennis.common.*"%>    
<% 
  String rel = Utils.getParentsFromServletPath(request.getServletPath()); 
  User userM = (User)session.getAttribute("user");  
%> 
<% 
	if (userM != null && userM.isAdmin()) {
    //
%>
<%
	} 
%>
<% if (userM != null) { %>
<A href="<%=rel %>users/users.jsp">Users</A>
<A href="<%=rel %>tournaments/viewTournaments.jsp">Tournaments</A>
<A href="<%=rel %>users/teams.jsp">Teams</A>
<A href="<%=rel %>users/changePwdForm.jsp">Settings</A>
<A href="<%=rel %>logout.jsp">Logout <%=userM.toString() %></A>
<% } else { %>
<A href="<%=rel %>index.jsp">Login</A>
<% } %>
<BR/><BR/>
<!--  div id="myDiv"><h2>Let AJAX change this text</h2></div-->
