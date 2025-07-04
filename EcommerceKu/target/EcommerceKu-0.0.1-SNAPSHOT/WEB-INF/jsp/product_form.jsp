<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Product Form</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">

</head>
<body
	style="margin: 0; padding: 0; height: 100vh; display: flex; justify-content: center; align-items: center; background-color: #fff;">

	<form action="<c:url value='/mvc/products' />" method="post" onsubmit="return confirm('Yakin ingin menambahkan produk ini?')"
		style="padding: 30px; background-color: #fff; border-radius: 8px; box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); width: 400px;">

		<h3 style="text-align: center; margin-bottom: 20px;">Product Form</h3>

		<div style="display: flex; align-items: center; margin-bottom: 15px;">
			<label for="name" style="width: 100px;">Name</label> <input
				type="text" id="name" name="name" style="flex: 1; padding: 6px;" />
		</div>

		<div style="display: flex; align-items: center; margin-bottom: 15px;">
			<label for="type" style="width: 100px;">Type</label>
			<select id="type" name="type" class="form-select" style="flex: 1; padding: 6px;" aria-label="Pilih Type">
				<option selected>-Pilih Type-</option>
				<option value="Buku">Buku</option>
				<option value="Elektronik">Elektronik</option>
				<option value="Fashion">Fashion</option>
				<option value="Handphone">Handphone</option>
				<option value="Tablet">Tablet</option>
			</select>
		</div>

		<div style="display: flex; align-items: center; margin-bottom: 20px;">
			<label for="price" style="width: 100px;">Price</label> <input
				type="number" id="price" name="price" style="flex: 1; padding: 6px;" />
		</div>

		<div style="text-align: center;">
			<input class="btn btn-primary" type="submit" value="Save"
				style="padding: 8px 20px;" />
		</div>
	</form>

</body>

</html>