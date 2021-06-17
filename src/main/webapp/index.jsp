<%-- 
    Document   : index
    Created on : Jun 13, 2021, 1:43:08 PM
    Author     : huygrogbro
--%>

<%@page import="DAO.UserDao"%>
<%@page import="Model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
        <head>
                <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
                <title>Home page</title>
  
        </head>
        <body>
		<script type="text/javascript">
				var MsgUpload = '<%=session.getAttribute("imageUpload")%>';
				
				if(MsgUpload!=="null") {
					if(Msg==="success") {
						function alertUser() {
							alert("Upload image success!!");
						}
						
					} else {
						function alertUser() {
							alert("Upload image failed.");
						}
						
					}
				}
				
				
		</script>
		<%
			
			if(session.getAttribute("username") != null) {	
		%>
				<h1><%=session.getAttribute("username")%></h1> 
				<a href="Logout">Logout</a> 
				<%UserDao usrDao = new UserDao();
					String userNameCheckImage = (String) session.getAttribute("username");
					User usrImage = usrDao.displayImageBlob(userNameCheckImage);
					if(usrImage.getBase64Image() != null && !usrImage.getBase64Image().isEmpty()) {
				%>
						<img src="data:image/jpg;base64,<%=usrImage.getBase64Image()%>"/>
				<% 
					} else {
				%>
						<form method="post" action="uploadServlet" enctype="multipart/form-data">
							Photo:<input type="file" name="photo" size="50" required>
							<input type="hidden" name="username" value="<%=session.getAttribute("username")%>">
							<input type="submit" value="Upload image">
						</form> 
				<%
					}
				%>
				
		<%
			} else { 
		%>
				<div class="navbar">
					<div class="header">
						<ul class="navbar_right">
							<li class="navbar_right-login"><a href="${pageContext.request.contextPath}/Login">Login</a></li>	
						</ul>
					</div>
				</div>
		<% 
			}
		%>

        </body>
	
		
</html>
