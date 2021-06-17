<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Register</title>

    <!-- Custom fonts for this template-->
    <link href="<%=request.getContextPath()%>/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link
        href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
        rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="<%=request.getContextPath()%>/Css/sb-admin-2.min.css" rel="stylesheet">
    <script src="https://cdn.rawgit.com/PascaleBeier/bootstrap-validate/v2.2.0/dist/bootstrap-validate.js" ></script>
<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
</head>

<body class="bg-gradient-primary">
		<script type="text/javascript">
			var MsgReg = '<%=session.getAttribute("register")%>';
			if(MsgReg!=="null") {
				if(MsgReg==="failed") {
					function alertUser() {
						alert("Username already use");
					}	
				}
				<%session.removeAttribute("register");%>
			}
			
		</script>
    <div class="container">

        <div class="card o-hidden border-0 shadow-lg my-5">
            <div class="card-body p-0">
                <!-- Nested Row within Card Body -->
                <div class="row">
                    <div class="col-lg-5 d-none d-lg-block bg-register-image"></div>
                    <div class="col-lg-7">
                        <div class="p-5">
                            <div class="text-center">
                                <h1 class="h4 text-gray-900 mb-4">Create an Account!</h1>
                            </div>
				<form class="user" name="Register" action="RegisterProcess" method="post">
                                <div class="form-group">
                                    <input type="text" class="form-control form-control-user" id="exampleInputEmail"
                                        placeholder="UserName" name="uname">
                                </div>
                                <div class="form-group row">
                                    <div class="col-sm-6 mb-3 mb-sm-0">
                                        <input type="password" class="form-control form-control-user"
                                            id="exampleInputPassword" placeholder="Password" name="pass">
                                    </div>
                                    <div class="col-sm-6">
                                        <input type="password" class="form-control form-control-user"
                                            id="exampleRepeatPassword" placeholder="Repeat Password" name="passrepeat">
                                    </div>
                                </div>
				<!-- Catch event register here -->
                                <input type="submit" onclick="return validate()" value="Register Account" class="btn btn-primary btn-user btn-block">
                                    
                               
                            </form>
                            <hr>
                            <div class="text-center">
				    <a class="small" href="${pageContext.request.contextPath}/Login">Already have an account? Login!</a>
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

    <script>
	    bootstrapValidate('#exampleInputEmail', 'min:8:Enter at least 8 characters!|max:20:Please do not more than 20 characters|required:UserName can not empty')
	    bootstrapValidate('#exampleInputPassword', 'min:8:Enter at least 8 characters!|max:20:Please do not more than 20 characters|required:Password can not empty|matches:#exampleRepeatPassword:Your password should match')
	    bootstrapValidate('#exampleRepeatPassword', 'required:Password repeat can not empty')
    </script>
    
    <script type="text/javascript">
	function validate() {
		var uname=document.Register.uname.value;
		if(uname=="") {
			alert("Please enter user name");
			document.Register.uname.focus();
			return false;
		} 
		if((uname.length<8) && (uname.length>20)) {
			alert("The length of username between 8 and 20");
			document.Register.uname.focus();
			return false;
		}
		var pass=document.Register.pass.value;
		var passRepeat=document.Register.passrepeat.value;
		if(pass == "") {
			alert("Please enter password");
			document.Register.pass.focus();
			return false;
		} else if((pass.length<8) && (pass.length>20)) {
			alert("The length of password between 8 and 20");
			document.Register.pass.focus();
			return false;
		} else if(pass!= passRepeat) {
			alert("Two password must match");
			document.Register.pass.focus();
			return false;
		}
		
		
	}    
	
</script>
</body>

</html>