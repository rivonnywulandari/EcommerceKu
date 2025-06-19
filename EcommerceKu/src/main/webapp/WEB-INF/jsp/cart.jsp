<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Cart | EcommerceKu</title>
<link rel="stylesheet"
    href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
    integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
    crossorigin="anonymous">
<script type="text/javascript" src="../js/jquery.min.js"></script>
</head>
<body>
<h1 style="margin: 20px" align="center">My Cart</h1>

<div class="container">
    <div class="row">

        <div class="col-md-1"></div>
        <div class="col-md-10">

            <a class="btn btn-primary mb-3"
                href="<c:url value='/mvc/products?action=user' />">Product Catalog</a>
            <a class="btn btn-danger mb-3 float-right"
                href="<c:url value='/mvc/products?action=logout' />">Logout</a>

            <table class="table table-bordered">
                <thead class="thead-light">
                    <tr>
                        <th>Name</th>
                        <th>Price</th>
                        <th>Type</th>
                        <th>Quantity</th>
                        <th>Total</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="item" items="${sessionScope.cart}">
                        <tr>
                            <td>${item.name}</td>
                            <td>${item.price}</td>
                            <td>${item.type}</td>
                            <td>${item.quantity}</td>
                            <td>${item.price * item.quantity}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

            <h5 style="text-align: right; margin-top: 20px;">
                Total: <span style="font-weight: bold;">${totalPrice}</span>
            </h5>

           <form action="<c:url value='/mvc/products?action=placeOrder' />" method="post">
                <input style="float:inline-end" type="submit" class="btn btn-primary mt-3" value="Place Order" />
            </form>
        </div>
        <div class="col-md-1"></div>

    </div>
</div>
</body>
</html>
