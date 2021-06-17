<%-- 
    Document   : product
    Created on : Jun 16, 2021, 10:24:24 PM
    Author     : huygrogbro
--%>
<%@page import="Model.Product"%>
<%@page import="Model.ProductBrand"%>
<%@page import="java.util.ArrayList"%>
<%ArrayList brandSel = (ArrayList)request.getAttribute("brand");%>
<%ArrayList productList = (ArrayList)request.getAttribute("productlist");%> 
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
					<h1 class="h3 mb-2 text-gray-800">Shoes</h1>
					<div class="card shadow mb-4">
						
						<div class="card-header py-3">
							<h6 class="m-0 font-weight-bold text-primary">Shoes</h6>
						</div>
						
						<div class="card-body"> 
							<div class="table-responsive"> 
								<table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
									<thead>
										<tr>
											<th>ShoeId</th>
											<th>ShoeName</th>
											<th>ShoeImage</th>
											<th>ShoeBrand</th>
											<th>ShoeColor</th>
											<th>ShoeType</th>
											<th>ShoeSize</th> 
											<th>Quantity</th> 
											<th>Price</th>
											<th>ShoeDesc</th>
											<th>Update</th> 
											<th>Delete</th>
										</tr>
									</thead>
									<tbody>
										<!--For each shoes -->
										
										<%for(int record = 0; record < productList.size(); record++) {%>
											<% Product productChild = (Product)productList.get(record);%>
											<tr>
												<td><%=productChild.getProductShoeId()%></td>
												<td><%=productChild.getProductShoeName()%></td>
												<td><% if(productChild.getProductImage() != null && !productChild.getProductImage().isEmpty()) {%>
														<img src="data:image/jpg;base64,<%=productChild.getProductImage()%>" style="width: 100px; height: 100px"/>
													<% } else { %> 
														<form method="post" action="uploadImageProduct" enctype="multipart/form-data">
															Photo:<input type="file" name="photo" size="50" required>
															<input type="hidden" name="productId" value="<%=productChild.getProductShoeId()%>">
															<input type="submit" value="Upload image">
														</form> 
													<% } %>
												</td>
												<td><%=productChild.getProductShoeBrand()%></td>
												<td><%=productChild.getProductShoeColor()%></td>
												<td><%=productChild.getProductShoeType()%></td>
												<td><%=productChild.getProductShoeSize()%></td>
												<td><%=productChild.getProductShoeQuantity()%></td>
												<td><%=productChild.getProductShoePrice()%></td>
												<td><%=productChild.getProductDescription()%></td>
												<td><button type="button" class="btn btn-outline-warning" data-toggle="modal" data-target="#exampleProductUpdate">Update</button></td>
												<!-- Update product modal -->
												<div class="modal fade" id="exampleProductUpdate" tabindex="-1" role="dialog" aria-labelledby="exampleModalLongTitle" aria-hidden="true">
													<div class="modal-dialog" role="document">
														<div class="modal-content">
															<div class="modal-header">
																<h5 class="modal-title" id="exampleModalLongTitle">Update product</h5>
																<button type="button" class="close" data-dismiss="modal" aria-label="close">
																	<span aria-hidden="true">&times;</span>
																</button>
															</div>
															<form name="updateProduct" action="updateProduct" method="post">
																<div class="modal-body">
																	<!-- Id shoe-->
																	<!-- Parameter shoeId-->
																	<input type="hidden" name="shoeId" value="<%=productChild.getProductShoeId()%>">
																	<!-- End id shoe-->
																	
																	<!-- Name shoe-->
																	<!-- Parameter shoeName -->
																	<div>
																		<label>Name</label>
																		<input type="text" name="shoeName" value="<%=productChild.getProductShoeName()%>" required>
																	</div>
																	<!-- End name shoe-->

																	<!-- Brand shoe-->
																	<!-- Parameter brand-->
																	<div>
																		<label>Choose a brand</label>
																		<select id="brand" name="brand" required>
																			<% for(int recordz = 0; recordz<brandSel.size();recordz++) {%>
																				<% ProductBrand brandList = (ProductBrand)brandSel.get(recordz);%>
																				<option value="<%=brandList.getId()%>"><%=brandList.getBrandName()%></option>
																			<% }%>
																		</select>
																	</div>
																	<!-- End Brand Shoe-->

																	<!-- Color-->
																	<!-- Parameter shoeColor-->
																	<div>
																		<label>Choose a color</label>
																		<input type="text" name="shoeColor" value="<%=productChild.getProductShoeColor()%>" required>
																	</div> 
																	<!-- End color--> 

																	<!-- Shoe type -->
																	<!-- Parameter type -->
																	<div>
																		<label>Type</label>
																		<select id="type" name="type" required>
																			<option value="Man">Man</option>
																			<option value="Women">Women</option>
																			<option value="Child">Child</option>
																		</select>
																	</div>
																	<!-- End type-->

																	<!-- Shoe size-->
																	<!-- Parameter shoeSize-->
																	<div>
																		<label>Size</label>
																		<select id="size" name="shoeSize" required>
																			<option value="22.8">22.8</option>
																			<option value="23.1">23.1</option>
																			<option value="23.5">23.5</option>
																			<option value="23.8">23.8</option>
																			<option value="24.1">24.1</option>
																			<option value="24.5">24.5</option>
																			<option value="24.8">24.8</option>
																			<option value="25.1">25.1</option>
																			<option value="25.4">25.4</option>
																			<option value="25.7">25.7</option>
																			<option value="26">26</option>
																			<option value="26.7">26.7</option>
																			<option value="27.3">27.3</option>
																			<option value="27.9">27.9</option>
																			<option value="28.6">28.6</option>
																			<option value="29.2">29.2</option>
																		</select>
																	</div> 
																	<!-- End shoe size-->

																	<!-- Shoe Quantity--> 
																	<!-- Parameter quantity-->
																	<div>
																		<label>Quantity</label>
																		<input type="number" name="quantity" value="<%=productChild.getProductShoeQuantity()%>" required>
																	</div>
																	<!-- End Shoe Quantity-->

																	<!-- Shoe Price -->
																	<!-- Parameter price-->
																	<div>
																		<label>Price</label>
																		<input type="number" name="price" value="<%=productChild.getProductShoePrice()%>"required>
																	</div>
																	<!-- End Shoe Price-->

																	<!-- Shoe Desc -->
																	<!-- Parameter description-->
																	<div> 
																		<label>Description</label>
																		<input type="text" name="description" value="<%=productChild.getProductDescription()%>" required>
																	</div>
																	<!-- End shoe Desc -->

																</div>	
																<div class="modal-footer">
																	<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
																	<button type="submit" class="btn btn-primary">Update</button>
																</div>

															</form>
														</div>
													</div>
												</div> 
												<!-- End update product modal-->
												<td><button type="button" class="btn btn-outline-danger" data-toggle="modal" data-target="#exampleModalCenter">Delete</button></td>
												<!-- Modal -->
												<div class="modal fade" id="exampleModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
													<div class="modal-dialog modal-dialog-centered" role="document">
	  													<div class="modal-content">
															<div class="modal-header">
		  														<h5 class="modal-title" id="exampleModalLongTitle">Modal title</h5>
																<button type="button" class="close" data-dismiss="modal" aria-label="Close">
																	<span aria-hidden="true">&times;</span>
		  														</button>
															</div>
															<div class="modal-body">
																Do you want to delete?
															</div>
															<div class="modal-footer">
		 	 													<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
																<form  name="deleteProduct" action="deleteProduct" method="post">																			
																	<input type="hidden" name="shoeId" value="<%=productChild.getProductShoeId().toString()%>">
		  															<button type="submit" class="btn btn-primary">Delete</button>
																</form>
															</div>
	  													</div>
													</div>
  												</div>	
											</tr>
										<% }%>
										
										
										
									</tbody>
									<tfoot>
										<tr>
											<th>ShoeId</th>
											<th>ShoeName</th>
											<th>ShoeImage</th>
											<th>ShoeBrand</th>
											<th>ShoeColor</th>
											<th>ShoeType</th>
											<th>ShoeSize</th> 
											<th>Quantity</th> 
											<th>Price</th>
											<th>ShoeDesc</th>
											<th>Update</th> 
											<th>Delete</th>
										</tr>
									</tfoot>
								</table> 
								<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModalLong">ADD Product</button>
								
								<!--Product brand modal-->
								<div class="modal fade" id="exampleModalLong" tabindex="-1" role="dialog" aria-labelledby="exampleModalLongTitle" aria-hidden="true">
									<div class="modal-dialog" role="document">
										<div class="modal-content">
											<div class="modal-header">
												<h5 class="modal-title" id="exampleModalLongTitle">Add product</h5>
												<button type="button" class="close" data-dismiss="modal" aria-label="close">
													<span aria-hidden="true">&times;</span>
												</button>
											</div>
											<form name="uploadProduct" action="uploadProduct" method="post">
												<div class="modal-body">
													<!-- Name shoe-->
													<!-- Parameter shoeName -->
													<div>
													<label>Name</label>
													<input type="text" name="shoeName" required>
													</div>
													<!-- End name shoe-->
													
													<!-- Brand select -->
													<!-- Parameter brand -->
													<div>
													<label>Choose a brand</label>
													<select id="brand" name="brand" required>
														<% for(int record = 0; record<brandSel.size();record++) {%>
														<% ProductBrand brandList = (ProductBrand)brandSel.get(record);%>
														<option value="<%=brandList.getId()%>"><%=brandList.getBrandName()%></option>
														<% }%>
													</select>
													</div>
													<!-- End brand select -->
													
													<!-- Color-->
													<!-- Parameter shoeColor-->
													<div>
													<label>Choose a color</label>
													<input type="text" name="shoeColor" required>
													</div> 
													<!-- End color--> 
													
													<!-- Shoe type -->
													<!-- Parameter type -->
													<div>
														<label>Type</label>
														<select id="type" name="type" required>
															<option value="Man">Man</option>
															<option value="Women">Women</option>
															<option value="Child">Child</option>
														</select>
													</div>
													<!-- End type-->
													
													<!-- Shoe size-->
													<!-- Parameter shoeSize-->
													<div>
														<label>Size</label>
														<select id="size" name="shoeSize" required>
															<option value="22.8">22.8</option>
															<option value="23.1">23.1</option>
															<option value="23.5">23.5</option>
															<option value="23.8">23.8</option>
															<option value="24.1">24.1</option>
															<option value="24.5">24.5</option>
															<option value="24.8">24.8</option>
															<option value="25.1">25.1</option>
															<option value="25.4">25.4</option>
															<option value="25.7">25.7</option>
															<option value="26">26</option>
															<option value="26.7">26.7</option>
															<option value="27.3">27.3</option>
															<option value="27.9">27.9</option>
															<option value="28.6">28.6</option>
															<option value="29.2">29.2</option>
														</select>
													</div> 
													<!-- End shoe size-->
													
													<!-- Shoe Quantity--> 
													<!-- Parameter quantity-->
													<div>
														<label>Quantity</label>
														<input type="number" name="quantity" required>
													</div>
													<!-- End Shoe Quantity-->
													
													<!-- Shoe Price -->
													<!-- Parameter price-->
													<div>
														<label>Price</label>
														<input type="number" name="price" required>
													</div>
													<!-- End Shoe Price-->
													
													<!-- Shoe Desc -->
													<!-- Parameter description-->
													<div> 
														<label>Description</label>
														<input type="text" name="description" required>
													</div>
													<!-- End shoe Desc -->
													
													<!--
													 ShoeImage
													 Parameter photo 
													<div>
														<label>Image</label>
														<input type="file" name="photo" size="50" required>
													</div>
													 End ShoeImage-->					
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
