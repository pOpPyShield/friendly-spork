<%-- 
    Document   : brand.jsp
    Created on : Jun 16, 2021, 7:03:19 AM
    Author     : huygrogbro
--%>

<%@page import="Model.ProductBrand"%>
<%@page import="java.util.ArrayList"%>
<%ArrayList brandList = (ArrayList) request.getAttribute("brandlist");%>
<jsp:include page="headtable.jsp"/>
			<script type="text/javascript">
				var MsgUpload = '<%=session.getAttribute("uploadBrand")%>';
				
				if(MsgUpload!=="null") {
					if(MsgUpload==="success") {
						function alertUser() {
							alert("Upload brand success.");
						}	
					} else if(MsgUpload === "failed") {
						function alertUser() {
							alert("Upload brand failed.");
						}
					}
					<%session.removeAttribute("uploadBrand");%>
				}
			</script>
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
					<h1 class="h3 mb-2 text-gray-800">Brand</h1>
					<div class="card shadow mb-4">
						
						<div class="card-header py-3">
							<h6 class="m-0 font-weight-bold text-primary">Brand</h6>
						</div>
						
						<div class="card-body"> 
							<div class="table-responsive"> 
								<table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
									<thead>
										<tr>
											<th>BrandId</th>
											<th>BrandName</th>
										</tr>
									</thead>
									<tbody>
										<!--For each brand -->
										
										<% for (int record= 0;record<brandList.size();record++) {%>
											<% ProductBrand productBrand = (ProductBrand)brandList.get(record);%>
											<tr>
												<td><%= productBrand.getId()%></td>
												<td><%= productBrand.getBrandName()%></td>
											</tr>
										<% }%>
										
										
									</tbody>
									<tfoot>
										<tr>
											<th>BrandId</th> 
											<th>BrandName</th>
										</tr>
									</tfoot>
								</table> 
								<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal">ADD Brand</button>
								
								<!--Product brand modal-->
								<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModallabel" aria-hidden="true">
									<div class="modal-dialog" role="document">
										<div class="modal-content">
											<div class="modal-header">
												<h5 class="modal-title" id="exampleModalLabel">Add brand</h5>
												<button type="button" class="close" data-dismiss="modal" aria-label="close">
													<span aria-hidden="true">&times;</span>
												</button>
											</div>
											<form name="uploadBrand" action="uploadBrand" method="post">
												<div class="modal-body">

													<label>Name Brand</label>
													<input type="text" name="BrandName">
												</div>
												<div class="modal-footer">
													<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
													<button type="submit" class="btn btn-primary">Save</button>
												</div>
											</form>
										</div>
									</div>
								</div>
							</div> 
						</div>
					</div>
				</div>
			</div>
		</div>
	</div> 
<jsp:include page="footertable.jsp"/>
