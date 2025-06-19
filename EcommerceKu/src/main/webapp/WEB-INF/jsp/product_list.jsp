<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Product List | EcommerceKu</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<script type="text/javascript" src="../js/jquery.min.js"></script>


</head>
<body>
	<h1 style="margin: 20px" align="center">Product List EcommerceKu</h1>

	<div class="container">
		<div class="row">


			<div class="col-md-12">

				<%
				String username = (String) session.getAttribute("username");
				if (username != null) {
				%>
				<p>
					Selamat datang, <b><%=username%></b>
				</p>
				<%
				}
				%>

				<a class="btn btn-primary" style="margin-bottom: 10px;"
					href="<c:url value='/mvc/products?action=cart' />">My Cart</a> <a
					class="btn btn-danger"
					style="margin-bottom: 5px; float: inline-end;"
					href="<c:url value='/mvc/products?action=logout' />">Logout</a>

				<c:forEach var="product" items="${products}" varStatus="status">
					<c:if test="${status.index % 2 == 0}">
						<div class="row">
					</c:if>
					<div class="col-md-6 mb-4">
						<div class="card">
							<div class="card-body">
								<form action="<c:url value='/mvc/products?action=addToCart' />"
									method="post">
									<div class="form-group row">
										<label for="name" class="col-sm-2 col-form-label">Name</label>
										<div class="col-sm-10">
											<input type="text" class="form-control" id="name" readonly
												value="${product.name}">
										</div>
									</div>
									<div class="form-group row">
										<label for="Type" class="col-sm-2 col-form-label">Type</label>
										<div class="col-sm-10">
											<input type="text" class="form-control" id="Type" readonly
												value="${product.type}">
										</div>
									</div>
									<div class="form-group row">
										<label for="Price" class="col-sm-2 col-form-label">Price</label>
										<div class="col-sm-10">
											<input type="text" class="form-control" id="Price" readonly
												value="${product.price}">
										</div>
									</div>
									<input type="hidden" name="id" value="${product.id}" /> <input
										type="hidden" name="name" value="${product.name}" /> <input
										type="hidden" name="type" value="${product.type}" /> <input
										type="hidden" name="price" value="${product.price}" />
									<div class="form-group row">
										<label style="text-align: end;"
											Quantity" class="col-sm-8 col-form-label">Quantity</label>
										<div class="col-sm-4">
											<input type="number" name="quantity" class="form-control" id="Quantity"
												min="1">
										</div>
									</div>									
									<div class="form-group row">
										<label for="Price" class="col-sm-2 col-form-label"></label>
										<div class="col-sm-10">
											<button type="submit" style="float: inline-end;"
												submit" class="btn btn-success">Add to cart</button>
										</div>
									</div>

								</form>
							</div>
						</div>
					</div>

					<c:if test="${status.index % 2 == 1 || status.last}">
			</div>
			<!-- end row -->
			</c:if>
			</c:forEach>





		</div>


	</div>
	</div>


</body>
</html>