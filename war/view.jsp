<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.google.appengine.api.users.User" %>
<%@ page import="com.google.appengine.api.users.UserService" %>
<%@ page import="com.google.appengine.api.users.UserServiceFactory" %>
<%@ page import="java.util.List" %>
<%@ page import="javax.jdo.Query" %>
<%@ page import="com.server.petition.Petition" %>
<%@ page import="com.server.petition.Signee" %>
<%@ page import="javax.jdo.PersistenceManager" %>
<%@ page import="com.server.petition.PMF" %>
<%@ page import="com.google.appengine.api.datastore.Key" %>
<%@ page import="com.google.appengine.api.datastore.KeyFactory" %>
	
	<html>
	<head>
		<title> Welcome to Petition Signer</title>
		<style type="text/css">
			body {
				background-color:#CCCCCC;
				color:#333333;
				font-family:"Trebuchet MS", sans-serif, Verdana;
				font-size:13px;
				line-height:16px;
			}
	
			#main-container {
				-moz-box-shadow:0 3px 15px rgba(0, 0, 0, 0.25);
				background-color:#FFFFFF;
				margin:24px auto;
				padding:4px;
				width:970px;
			}
	
			#header {
				background-color:#A4C639;
				color:#FFFFFF;
				padding:24px;
				margin:0;
			}
	
			h1 {
				font-size:48px;
				font-family:"Trebuchet MS", sans-serif, Verdana;
				font-weight:bold;
				line-height:48px;
				margin:0;
				padding:0;
			}
	
			p {
				margin:0;
				padding-top:15px;
				text-align:left;
				font-size:15px;
				font-weight:bold;
			}
			
			a {
				color:#FFFFFF;
				text-decoration:none;
			}		
	
			a:hover {
				color:#FFFFFF;
				text-decoration:underline;
			}
	
			.title {
				color:#333333;
				padding-left:170px;
			}	
			
			ul.petition {
				background-color:#FFFFFF;
				width:920px;
				margin:auto;
				padding-top:20px;
				padding-bottom:45px;
				border-top-style:solid;
				border-color:#CCCCCC;
				border-top-width:0.25px;
			}	
			
			ul.petition:hover {
				background-color:#D9D6D9;
			}
			
			li {
				list-style-type:none;
				display:inline;
				font:1.6em 'Lucida Grande',sans-serif;
			}
	
			.sl {
				float:left;
				width:45%;
				overflow:hidden;
				white-space:nowrap;
			}
	
			.country {
				padding-right:20px;
				float:right;
			}
	
		</style>
	</head>
	<body>
		<%
		    UserService userService = UserServiceFactory.getUserService();
		    User user = userService.getCurrentUser();
		%>
	
		<div id="main-container">
	      <div id="header">
	        <h1>Petition Signer</h1>
				<p>Hello, <em><%= user.getNickname() %></em>! 
					<span style="float:right;">
						<a href="<%= userService.createLogoutURL("http://www.petitionsigner.appspot.com") %>">Logout</a></span>
				</p>
	      </div>
	      <hr/>
	      <% 	
	      String id = (String) request.getParameter("id");
	      	String userid=id;
      	String userid1=id+"9999999999999999999999999999999999999999999999999999999999";
      	
      	Key k1 = KeyFactory.createKey(Signee.class.getSimpleName(),
						userid);
						
		Key k2 = KeyFactory.createKey(Signee.class.getSimpleName(),
				userid1);
      	PersistenceManager pm = PMF.get().getPersistenceManager();
      	
      	      
      	String queryStr = "select from " + Signee.class.getName() + 
          " where key > :p1 && key <= :p2";    
	         
	        Query q=pm.newQuery(queryStr);
	        
		    
	      	try {
		        List<Signee> signees = (List<Signee>) q.execute(k1,k2);
		        
		        if (!signees.isEmpty()) {
		            int index=1;
	            	for (Signee s:signees) {
	      %>     
	      <div>
	      	<ul class="petition">
	      	<span class="sl"><li><%=index++%></li>
		    <li><a href="/ShowImage?sid=<%=s.getKey().getName()%>" class="title"><%=s.getSigneeName()%></a></li>
		    </span>
		    <span class="country">
		  	
		  	<li style="padding-right:120px;"><%=s.getSigneemail()%></li>
		  	<li><%=s.getSigneeContact()%></li>
		  	</span>
		  	</ul>
	      	<%
	  				}
	   			}
	       	}
	   		catch(Exception e){}
	      	%>
	      </div>	
	    </div>
	</body>
	</html>
	
	