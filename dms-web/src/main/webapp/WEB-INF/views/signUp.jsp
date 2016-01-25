
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Sign Up</title>
<link href="../resources/css/bootstrap.min.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="../resources/css/app/signin.css" rel="stylesheet">

<meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
	<div class="container" >
		<form class="form-signup" action="${pageContext.request.contextPath}/web/users/signUp" method="post">
			<div class="form-group">
				<label class="col-sm-2" control-label> First Name</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" name="firstName" placeholder="First Name">
				</div>				
			</div>
			<div class="form-group">
				<label class="col-sm-2" control-label> Last Name</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" name="lastName" placeholder="Last Name">
				</div>				
			</div>
			
			<div class="form-group">
				<label class="col-sm-2" control-label> Login Id</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" name="loginId" placeholder="Login Id">
			</div>
			<div class="form-group">
				<label class="col-sm-2" control-label> Password</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" name="password" placeholder="Login Id">
			</div>				
			</div><div class="form-group">
				<label class="col-sm-2" control-label> Email</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" name="email" placeholder="User Email">
				</div>				
			</div>
			<div class="form-group">
				<label class="col-sm-2" control-label> Organization Name</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" name="orgName" placeholder="Organization Name">
				</div>				
			</div>
			<div class="form-group">
				<label class="col-sm-2" control-label> Organization Type</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" name="orgType" placeholder="Organization Type">
				</div>				
			</div>
			<div class="form-group">
    			<div class="col-sm-offset-2 col-sm-10">
      				<button type="submit" class="btn btn-default">Sign Up</button>
    			</div>
			</div>
		</form>
	</div>

</body>
</html>