<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Shopping App</title>

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">

</head>

</head>
<body>
	<header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: tomato">
			<div>
				<a class="navbar-brand">Add
					Items</a>
			</div>

			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/list"
					class="nav-link">Cart Items</a></li>
			</ul>

			<ul class="navbar-nav navbar-collapse justify-content-end">
				<li><a href="<%=request.getContextPath()%>/logout"
					class="nav-link">Logout</a></li>
			</ul>
		</nav>
	</header>
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
				<c:if test="${cart != null}">
					<form action="update" method="post">
				</c:if>
				<c:if test="${cart == null}">
					<form action="insert" method="post">
				</c:if>

				<caption>
					<h2>
						<c:if test="${cart != null}">
            			Edit Cart
            		</c:if>
						<c:if test="${cart == null}">
            			Add New Items
            		</c:if>
					</h2>
				</caption>

				<c:if test="${cart != null}">
					<input type="hidden" name="id" value="<c:out value='${cart.id}' />" />
				</c:if>

				<fieldset class="form-group">
					<label>Product Name</label> <input type="text"
						value="<c:out value='${cart.ProductName}' />" class="form-control"
						name="ProductName" required="required" minlength="5">
				</fieldset>
				
				<fieldset class="form-group">
					<label>Items Price</label> <input type="text"
						value="<c:out value='${cart.Price}' />" class="form-control"
						name="Price" minlength="5">
				</fieldset>

				<fieldset class="form-group">
					<label>Item Description</label> <input type="text"
						value="<c:out value='${cart.description}' />" class="form-control"
						name="description" minlength="5">
				</fieldset>

				

				<fieldset class="form-group">
					<label>Items Target Date</label> <input type="date"
						value="<c:out value='${cart.targetDate}' />" class="form-control"
						name="targetDate" required="required">
				</fieldset>

				<button type="submit" class="btn btn-success">Save</button>
				</form>
			</div>
		</div>
	</div>

	<jsp:include page="../common/footer.jsp"></jsp:include>
</body>
</html>
