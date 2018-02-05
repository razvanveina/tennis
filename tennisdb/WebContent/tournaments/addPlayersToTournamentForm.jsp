<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*, com.ssn.core.*"%>
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
    width:45%;
  }
  
    .box3   {
    float: left;
    padding: 15px;
    width:10%;
  
  }
.clearfix::after {
    clear: right;
    display: inline-block;
    
}


.button {
    border: 1px solid #ddd; /* Add a border to all links */
    margin-top: -1px; /* Prevent double borders */
    background-color: #f6f6f6; /* Grey background color */
    padding: 5px;
    align:center;
    width:45px;
    text-decoration: none; /* Remove default text underline */
    font-size: 14px; /* Increase the font-size */
    color: black; /* Add a black text color */
    display: none; /* Make it into a block element to fill the whole list */
 box-shadow: 2px 2px #b2b2b2;
}


.button:hover{
    background-color: #b2b2b2; /* Add a hover effect to all links, except for headers */
}

.show {display:block;}

  </style>  
<body>
<%
String name = request.getParameter("name");
Tournament tournament = ApplicationFactory.getInstance().getDatabase().getTournamentByName(name); 
%>
<%@include file="../checkLogin.jsp" %>
<%@include file="../checkAdminRights.jsp" %> 

<%
List<User> users= ApplicationFactory.getInstance().getDatabase().getUsers();
List<User> lastUsers=tournament.getParticipants(); 
boolean hasOldParticipants=!lastUsers.isEmpty();
int checkCount=0;
Collections.sort(users, new Comparator<User>() {
  @Override
  public int compare(User u1, User u2) {
      String s1 = u1.getSurname();
      String s2 = u2.getSurname();
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
				<label><INPUT type="checkbox" name="usersArray[]"
					value="<%=user.getUser() %>" id="<%=user.getUser()%>" <%= checked %> onchange='countCheckedCheckboxes()'><%=user.getName()%> <B><%=user.getSurname()%></B> </label>
				<BR />
        

				<%}%>
			</fieldset>
      <br>
			<INPUT  type="submit" value="Save"/>
		</FORM>
	</div>
  
  
  <div class="box3">
   <input type="text" id="selectedUser"  autocomplete="off" onkeyup="searchUsers()" onkeydown = "if (event.keyCode == 13) document.getElementById('button0').click()"  placeholder="Search by username.."><br>
  <dl id="myDropdown">
  <%
  for(User user:users){%>
  <dt> <input type="button" class="button" id="<%=user.getUser()%>" onclick='selectThisUser(this.value)' value="<%=user.getUser()%>"/></dt> 
  <%} %>
  </dl>
  </div>
  <br>
  <input type="button" id="button0" value="Add Player" onclick="checkName()"/>
  
  
  
  <div class="box2">
   Players selected: <p id="checkedPlayers" align=center><%=lastUsers.size() %></p><br>
   Players needed: <p ><%=tournament.getMaxPlayers() %>
   
  
</p></div>
  </div>
  
  

 

  <script language="JavaScript" type="text/javascript">

  //count the checked checkboxes
  function countCheckedCheckboxes()
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

  //Check the checkbox of the user entered 
  function checkName(){

	    	   document.getElementById(document.getElementById("selectedUser").value).checked = true;
	    	   document.getElementById("selectedUser").value="";
	    	   countCheckedCheckboxes();
	    	   searchUsers();
	        }
  
  
  function selectThisUser(username){
	  document.getElementById(username).checked=true;
	  countCheckedCheckboxes();
	  document.getElementById("selectedUser").value="";
	  document.getElementById("selectedUser").focus();
	  searchUsers();
  }
  
  
  function searchUsers(){
	  var input, filter, ul, li, a, i;
	    input = document.getElementById("selectedUser");
	    filter = input.value.toUpperCase();
	    ul = document.getElementById("myDropdown");
	    li = ul.getElementsByTagName('dt');
	    
	    for (i = 0; i < li.length; i++) {
	        a = li[i].getElementsByTagName("input")[0];
	        if (a.value.toUpperCase().indexOf(filter) > -1 && filter != "") {
	            a.style.display = "inline-block";
	        } else {
	            a.style.display = "none";
	        }
	    }
  }
  
  //hide the search dropdown if it loses focus
  window.onclick = function(event) {
	  if (!event.target.matches('.dropdown')) {
		  var  ul, li, a, i;
	     ul = document.getElementById("myDropdown");
	     li = ul.getElementsByTagName('dt');
	      
	      for (i = 0; i < li.length; i++) {
	          a = li[i].getElementsByTagName("input")[0];
	              a.style.display = "none";
	      }
	    }
	  }
  
  
</script>

  </BODY>
</HTML>  