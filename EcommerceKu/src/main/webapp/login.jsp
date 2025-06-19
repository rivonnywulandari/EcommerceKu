<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login | EcommerceKu</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">

</head>
<body
	style="margin: 0; padding: 0; height: 100vh; display: flex; justify-content: center; align-items: center; background-color: #fff;">

	<form method="post"
		action="<c:url value='/mvc/products?action=login' />"
		style="padding: 30px; background-color: #fff; border-radius: 8px; box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); width: 400px;">

		<h3 style="text-align: center; margin-bottom: 20px;">Login</h3>

		<div style="display: flex; align-items: center; margin-bottom: 15px;">
			<label for="username" style="width: 100px;">Username</label> <input
				type="text" id="username" name="username"
				style="flex: 1; padding: 6px;" />
		</div>

		<div style="display: flex; align-items: center; margin-bottom: 20px;">
			<label for="password" style="width: 100px;">Password</label> <input
				type="password" id="password" name="password"
				style="flex: 1; padding: 6px;" />
		</div>

		<%
		String error = (String) session.getAttribute("error");
		if (error != null) {
		%>
		<p style="color: red; text-align: center;">
			<b><%=error%></b>
		</p>
		<%
		}
		%>

		<div style="text-align: center;">
			<input class="btn btn-primary" type="submit" value="Login"
				style="padding: 8px 20px;" />
		</div>
	</form>


</body>

</html>