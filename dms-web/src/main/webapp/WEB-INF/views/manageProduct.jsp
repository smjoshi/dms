<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
		<div>
			<img alt="" src="https://s3-us-west-2.amazonaws.com/devdmsproducts01/global_default/manage_product.png">
		</div>
		<div>
			<h3><u>Manage Products</u></h3>
		</div>
	</div>
	
	<div>
		<a href="${pageContext.request.contextPath}/web/users/productRequest">Add Product</a>
	</div>
	
	<div>
		<table class="table table-hover">
			<thead>
				<tr>
					<th>Product Id</th>
					<th>Product Name</th>
					<th>Description</th>
					<th>Product Code</th>
				</tr>
			</thead>
			<c:if test="${not empty products }">	
				<tbody>
					<c:forEach items="${products}" var="product">
						<tr>
							<td>${product.productId}</td>
							<td>${product.productName}</td>
							<td>${product.description}</td>
							<td>${product.productCode}</td>
							<td><a href='#'><span class='glyphicon glyphicon-edit'></span></a></td>
							<td><a href='#'><span class='glyphicon glyphicon-trash'></span></a></td>
							<td><a href='#'><span class='glyphicon glyphicon-cog'></span></a></td>
						</tr>
					</c:forEach>
				</tbody>
			</c:if>
		</table>
	</div>
	</form>
	
</body>
</html>