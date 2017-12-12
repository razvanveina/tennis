<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="com.ssn.tennis.model.*" import="com.ssn.tennis.common.*"%>    
<% 
  String rel = Utils.getParentsFromServletPath(request.getServletPath()); 
  User userM = (User)session.getAttribute("user");  
%> 
<A href="<%=rel %>info.jsp">Info</A>
<% 
	if (userM != null && userM.isAdmin()) {
%>
<A href="<%=rel %>users/addUserForm.jsp">Add user</A>
<A href="<%=rel %>users/users.jsp">View users</A>
<%
	} 
%>
<% if (userM != null) { %>
<A href="<%=rel %>users/changePwdForm.jsp">Settings</A>
<A href="<%=rel %>logout.jsp">Logout <%=userM.toString() %></A>
<% } else { %>
<A href="<%=rel %>index.jsp">Login</A>
<% } %>
<BR/><BR/>
<!--  div id="myDiv"><h2>Let AJAX change this text</h2></div-->
