<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Product (MVC)</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">

</head>
<body>

	<h3 style="margin: 20px" align="center">Add New Product</h3>


	<div class="container">
		<div class="row">

			<div class="col-12" align="center">

				<form action="<c:url value='/mvc/products' />" method="post">

					<table>
						<tr>
							<td>Name</td>
							<td><input type="text" name="name"></td>
						</tr>
						<tr>
							<td>Type</td>
							<td><input type="text" name="type"></td>
						</tr>
						<tr>
							<td>Price</td>
							<td><input type="number" name="price"></td>
						</tr>
						<tr>
							<td colspan="2"><input type="submit" value="Save" /></td>
						</tr>
					</table>
				</form>
			</div>
		</div>
	</div>


</body>
</html>