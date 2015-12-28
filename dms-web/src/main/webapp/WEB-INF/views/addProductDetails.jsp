<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../favicon.ico">

    <title>Add Product Details</title>

    <!-- Bootstrap core CSS -->
    <link href="../../resources/css/bootstrap.min.css" rel="stylesheet">

    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <link href="../../assets/css/ie10-viewport-bug-workaround.css" rel="stylesheet">

    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
    <script src="../../assets/js/ie-emulation-modes-warning.js"></script>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>
<body>
    <div class="container">
    	<form clas="form-horizontal" >
			<div>
				<img alt="" src="../../resources/images/manage_product.png">
				<h3><u>Add Product</u></h3>
			</div>
		
			<div class="form-group">
    			<label for="productname" class="col-sm-2 control-label">Product Name</label>
			    <div class="col-sm-10">
			      <input type="text" class="form-control" id="productName" placeholder="Product Name">
			    </div>
  			</div>
			  <div class="form-group">
			    <label for="description" class="col-sm-2 control-label">Description</label>
			    <div class="col-sm-10">
			      <input type="text" class="form-control" id="description" placeholder="Description">
			    </div>
			  </div>
			  
			  <input type="hidden" name="orgId" value="">
		
	</form>
    </div>
	
	
	
</body>
</html>