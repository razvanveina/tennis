<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="com.ssn.tennis.model.*" import="com.ssn.tennis.common.*"%>    
<% 
  String rel = Utils.getParentsFromServletPath(request.getServletPath()); 
  User userM = (User)session.getAttribute("user");  
%> 
<A href="<%=rel %>info.jsp">Info</A>
<A href="<%=rel %>results/results.jsp">Test results</A>
<A href="<%=rel %>results/finalResults.jsp">Final results</A>
<% 
	if (userM != null && userM.isAdmin()) {
%>
<A href="<%=rel %>users/addUserForm.jsp">Add user</A>
<A href="<%=rel %>users/users.jsp">View users</A>
<A href="<%=rel %>tests/addTestForm.jsp">Add test</A>
<A href="<%=rel %>tests/tests.jsp">View tests</A>
<A href="<%=rel %>results/addResultForm.jsp">Edit results</A>
<A href="<%=rel %>results/results.jsp">View results</A>
<A href="<%=rel %>questions/addChoicesQuestionForm.jsp">Add question</A>
<A href="<%=rel %>questions/questions.jsp">Questions</A>
<%
	} 
%>
<% if (userM != null) { %>
<A href="<%=rel %>issues/createIssueForm.jsp">Create issue</A>
<A href="<%=rel %>issues/myIssues.jsp?filter=mine">My issues</A>
<A href="<%=rel %>users/changePwdForm.jsp">Settings</A>
<A href="<%=rel %>logout.jsp">Logout <%=userM.toString() %></A>
<% } else { %>
<A href="<%=rel %>index.jsp">Login</A>
<% } %>
<BR/><BR/>
<!--  div id="myDiv"><h2>Let AJAX change this text</h2></div-->
