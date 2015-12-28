<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
 <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../favicon.ico">

    <title>Manage Products</title>

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
	<form action="">
	<div>
		<img alt="" src="../../resources/images/manage_product.png">
		<h3><u>Manage Products</u></h3>
	</div>
	
	<div>
		<a href="http://localhost:8080/dms-web/web/productRequest">Add Product</a>
	</div>
	
	<div>
		<table class="table table-hover">
			<thead>
				<tr>
					<th>#</th>
					<th>Product Name</th>
					<th>Description</th>
					<th>Product Code</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>1</td>
					<td>Sedan car</td>
					<td>Passenger Car</td>
					<td>PSG1</td>
				</tr>
			</tbody>
		</table>
	</div>
	</form>
	
</body>
</html>