<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Product List</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<script type="text/javascript" src="../js/jquery.min.js"></script>
<script>
	$(document).ready(function() {
		$('.btn-delete').click(function() {
			const id = $(this).data('id');
			if (confirm('Yakin ingin menghapus produk ini?')) {
				$.post('<c:url value="/mvc/products" />', {
					action : 'delete',
					id : id
				}).done(function() {
					alert('Produk berhasil dihapus.');
					location.reload();
				}).fail(function() {
					alert('Gagal menghapus produk.');
				});
			}
		});
	});
</script>
<script type="text/javascript">
	$(document).ready(
			function() {
				$("#btnSubmit").click(
						function() {
							$.get("http://localhost:8080/HelloWeb2/hello.do",
									function(data, status) {
										console.log(data);
										alert("Data: " + data.author
												+ "\nStatus: " + status);
									})
						})
			})
</script>
</head>
<body>
	<h1 style="margin: 20px" align="center">PRODUCT CATALOG</h1>

	<div class="container">
		<div class="row">

			<div class="col-md-1"></div>

			<div class="col-md-10">
				<a class="btn btn-primary" style="margin-bottom: 5px;"
					href="<c:url value='/mvc/products?action=form-view' />">Add New
					Product</a> <a class="btn btn-primary"
					style="margin-bottom: 5px; float: inline-end;"
					href="<c:url value='/mvc/products?action=download' />">Download
					CSV</a>



				<table class="table table-bordered" border="3" width="100%"
					align="center">
					<thead>
						<tr style="background-color: yellow;">
							<td style="font-weight: bold;">ID</td>
							<td style="font-weight: bold;">Name</td>
							<td style="font-weight: bold;">Type</td>
							<td style="font-weight: bold;">Price</td>
							<td style="font-weight: bold;">Action</td>
						</tr>
					</thead>
					<tbody>

						<c:forEach items="${products}" var="product">
							<tr>
								<td>${product.id}</td>
								<td>${product.name}</td>
								<td>${product.type}</td>
								<td>${product.price}</td>
								<td><a class="btn btn-success"
									href="<c:url value='/mvc/products?action=view&id=${product.id}' />">View</a>
									&nbsp;&nbsp;&nbsp; <a href="javascript:void(0);"
									class="btn btn-danger btn-delete" data-id="${product.id}">
										Delete </a></td>
							</tr>
						</c:forEach>
					</tbody>

				</table>
			</div>

			<div class="col-md-1"></div>

		</div>
	</div>


</body>
</html>