<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="../css/style.css">
<title>Add player to tournament</title>
</head>
  <style type="text/css">
  
  .box1 { 
    float: left;
    padding: 15px;
    width:25%;
  }
  .box2   {
  text-align: center;
  font-size: 30px;
  vertical-align: -50px;
   padding: 15px;
    float:right;
    min-height:230px;
    width:55%;
  }
.clearfix::after {
    content:"";
    clear: right;
    display: table;
}


  </style>  
<body>
<%
String name = request.getParameter("name");
Tournament tournament = Database.getInstance().getTournamentByName(name); 
%>
<%@include file="../checkLogin.jsp" %>
<%@include file="../checkAdminRights.jsp" %> 

<%
ArrayList<User> users= Database.getInstance().getUsers();
ArrayList<User> lastUsers=tournament.getParticipants();
boolean hasOldParticipants=!lastUsers.isEmpty();
int checkCount=0;
Collections.sort(users, new Comparator<User>() {
  @Override
  public int compare(User u1, User u2) {
      String s1 = u1.getUser();
      String s2 = u2.getUser();
      return s1.compareToIgnoreCase(s2);
  }

});
%>
<div class="clearfix">
	<div class="box1">
		<FORM
			action="addPlayersToTournament.jsp?name=<%=tournament.getName() %>"
			method="POST">
			<legend><b>Choose users: </b></legend>
      <br>
			<fieldset>
				<%       
        
        for(User user : users){
          boolean isChecked= (hasOldParticipants && lastUsers.contains(user));
          String checked=(isChecked? "checked":"");
          %>
				<INPUT type="checkbox" name="usersArray[]"
					value="<%=user.getUser() %>" <%= checked %> onchange='checkboxes()'/>
			<b>(<%=user.getUser()%>)</b> <%=user.getName()%> <%=user.getSurname()%> 
				<BR />
        

				<%}%>
			</fieldset>
      <br>
				<INPUT type="submit" value="Add"/>
		</FORM>
	</div>
  <div class="box2"> Players selected: <p id="checkedPlayers" align=center><%=lastUsers.size() %>
</p></div>
  </div>
  <script language="JavaScript" type="text/javascript">

  function checkboxes()
  {
   var inputElems = document.getElementsByTagName("input"),
    count = 0;

    for (var i=0; i<inputElems.length; i++) {       
       if (inputElems[i].type == "checkbox" && inputElems[i].checked == true){
          count++;
       }
    }
    document.getElementById("checkedPlayers").innerHTML = count;

 }

</script>

  </BODY>
</HTML>  