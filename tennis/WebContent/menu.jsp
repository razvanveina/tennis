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
<ul>
<li><A href="<%=rel %>users/users.jsp"><strong>&nbsp&nbspUsers&nbsp</strong></A></li>
<li><A href="<%=rel %>tournaments/viewTournaments.jsp"><strong>&nbspTournaments&nbsp</strong></A></li>
<li><A href="<%=rel %>users/teams.jsp"><strong>&nbspTeams&nbsp</strong></A></li>
<li><A href="<%=rel %>users/changePwdForm.jsp"><strong>&nbspSettings&nbsp</strong></A></li>
<li><A href="<%=rel %>logout.jsp"><strong> &nbspLogout <%=userM.toString() %> &nbsp</strong></A></li>
</ul>
<% } else { %>
<A href="<%=rel %>index.jsp">Login</A>
<% } %>
<BR/><BR/>
<!--  div id="myDiv"><h2>Let AJAX change this text</h2></div-->
