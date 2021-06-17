<%-- 
    Document   : login
    Created on : Jun 13, 2021, 2:25:53 PM
    Author     : huygrogbro
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

	<head>

		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
		<meta name="description" content="">
		<meta name="author" content="">

		<title>Login</title>

		<!-- Custom fonts for this template-->
		<link href="<%=request.getContextPath()%>/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
		<link
			href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
			rel="stylesheet">

		<!-- Custom styles for this template-->
		<link href="<%=request.getContextPath()%>/Css/sb-admin-2.min.css" rel="stylesheet">
		<script src="https://cdn.rawgit.com/PascaleBeier/bootstrap-validate/v2.2.0/dist/bootstrap-validate.js" ></script>

	</head>

	<body class="bg-gradient-primary">
			<script type="text/javascript">
				var MsgReg = '<%=session.getAttribute("register")%>';
				var MsgLogin = '<%=session.getAttribute("login")%>';
				if(MsgReg!=="null") {
					if(MsgReg==="success") {
						function alertUser() {
							alert("Register user success!!");
						}	
					}
					<%session.removeAttribute("register");%>
				}
				
				if(MsgLogin !== "null") {
					if(MsgLogin==="failed") {
						function alertUser() {
							alert("Wrong username or password");
						}
					}
					<%session.removeAttribute("login");%>
				}

			</script>
		<div class="container">

			<!-- Outer Row -->
			<div class="row justify-content-center">

				<div class="col-xl-10 col-lg-12 col-md-9">

					<div class="card o-hidden border-0 shadow-lg my-5">
						<div class="card-body p-0">
							<!-- Nested Row within Card Body -->
							<div class="row">
								<div class="col-lg-6 d-none d-lg-block bg-login-image"></div>
								<div class="col-lg-6">
									<div class="p-5">
										<div class="text-center">
											<h1 class="h4 text-gray-900 mb-4">Welcome Back!</h1>
										</div>
										<form class="user" action="LoginProcess" method="post">
											<div class="form-group">
												<input type="text" class="form-control form-control-user"
												       id="exampleInputEmail" aria-describedby="emailHelp"
												       placeholder="Enter User Name..." name="uname">
											</div>
											<div class="form-group">
												<input type="password" class="form-control form-control-user"
												       id="exampleInputPassword" placeholder="Password" name="pass">
											</div>
											<div class="form-group">
												<div class="custom-control custom-checkbox small">
													<input type="checkbox" class="custom-control-input" id="customCheck">
													<label class="custom-control-label" for="customCheck">Remember
														Me</label>
												</div>
											</div>
											<!-- Event login -->
											<input type="submit" class="btn btn-primary btn-user btn-block" value="Login">
										</form>
										<hr>
										<div class="text-center">
											<a class="small" href="${pageContext.request.contextPath}/Register">Create an Account!</a>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>

				</div>

			</div>

		</div>

		<!-- Bootstrap core JavaScript-->
		<script src="<%=request.getContextPath()%>/vendor/jquery/jquery.min.js"></script>
		<script src="<%=request.getContextPath()%>/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

		<!-- Core plugin JavaScript-->
		<script src="<%=request.getContextPath()%>/vendor/jquery-easing/jquery.easing.min.js"></script>

		<!-- Custom scripts for all pages-->
		<script src="<%=request.getContextPath()%>/js/sb-admin-2.min.js"></script>
		<script type="text/javascript"> window.onload = alertUser; </script>

	</body>

</html>
