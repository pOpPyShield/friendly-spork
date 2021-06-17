<%-- 
    Document   : user
    Created on : Jun 16, 2021, 7:02:52 PM
    Author     : huygrogbro
--%>
<%@page import="Model.UserAdminManage"%>
<%@page import="java.util.ArrayList"%>
<%ArrayList usrList = (ArrayList)request.getAttribute("userlist"); %>
<jsp:include page="headtable.jsp"/>
	<div id="wrapper">
		<jsp:include page="sidebar.jsp"/>
		 <!-- Content Wrapper -->
		<div id="content-wrapper" class="d-flex flex-column">

			<!-- Main Content -->
			<div id="content">
				<jsp:include page="topbar.jsp"/>
				<!-- Begin Page Content -->
				<div class="container-fluid">
					<!-- Page Heading -->
					<h1 class="h3 mb-2 text-gray-800">User</h1>
					<div class="card shadow mb-4">
						
						<div class="card-header py-3">
							<h6 class="m-0 font-weight-bold text-primary">User</h6>
						</div>
						
						<div class="card-body"> 
							<div class="table-responsive"> 
								<table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
									<thead>
										<tr>
											<th>UserId</th>
											<th>Image</th>
											<th>UserName</th>
											<th>Role</th>
										</tr>
									</thead>
									<tbody>
										<!--For each user -->
											<% for (int record= 0;record<usrList.size();record++) {%>
											<% UserAdminManage usrAdminManage= (UserAdminManage)usrList.get(record);%>
											<tr>
												<td><%= usrAdminManage.getUserName()%></td>
												<td><% if(usrAdminManage.getBase64Image()!=null && !usrAdminManage.getBase64Image().isEmpty()) {%>
												<img src="data:image/jpg;base64,<%=usrAdminManage.getBase64Image()%>" style="width: 100px; height: 100px"/>
												<% } else { %>
													don't have
												<% } %>
												</td>
												<td><%= usrAdminManage.getUserName() %></td>
												<td><%= usrAdminManage.getRoleName() %></td>
											</tr>
											<% } %>
										
										
										
									</tbody>
									<tfoot>
										<tr>
											<th>UserId</th>
											<th>Image</th> 
											<th>UserName</th>
											<th>Role</th>
										</tr>
									</tfoot>
								</table> 
							</div> 
						</div>
					</div>
				</div>
			</div>
		</div>
	</div> 
<jsp:include page="footertable.jsp"/>
