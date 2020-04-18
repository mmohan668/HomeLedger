<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Welcome to home ledger</title>
<link rel="stylesheet" href="/css/bootstrap.min.css">
<link rel="stylesheet" href="/css/style.css">
<script type="text/javascript" src="/js/jquery.min.js"></script>
<script type="text/javascript" src="/js/bootstrap/3.4.1/bootstrap.min.js"></script>
<script type="text/javascript" src="/js/bootstrap/4.4.1/bootstrap.min.js"></script>
<script type="text/javascript" src="/js/jquery.js"></script>
<script>
if ( window.history.replaceState ) {
  window.history.replaceState( null, null, window.location.href );
}
</script>
</head>
<body>

	<nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand">Home Ledger</a>
			</div>
			<ul class="nav navbar-nav">
				<li><a href="/welcome">Home</a></li>
				<li><a href="/login">Login</a></li>
				<li><a href="/register">Register</a></li>
			</ul>
		</div>
	</nav>
	<br>
	<br>
	<c:choose>
		<c:when test="${MODE=='WELCOME'}">
			<div class="container">
				<div class="jumbotron">
					<h1>Home Ledger</h1>
					<p>A ledger is the principal book or computer file for
						recording and totaling economic transactions measured in terms of
						a monetary unit of account by account type, with debits and
						credits in separate columns and a beginning monetary balance and
						ending monetary balance for each account.</p>
				</div>
			</div>
		</c:when>
		<c:when test="${MODE=='LOGIN'}">
			<div class="container logon-container">
				<h2>Please Login</h2>
				<hr>
				<c:if test="${status=='failed'}">
					<script type="text/javascript">
						$(function() {
							$('.alert-danger').show("slow")
						})
					</script>
				</c:if>
				<c:if test="${status=='register-success'}">
					<script type="text/javascript">
						$(function() {
							$('.alert-success').show("slow")
						})
					</script>
				</c:if>
				<c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION}">
					<script type="text/javascript">
						$(function() {
							$('.alert-danger').show("slow")
						})
					</script>
					<c:remove var = "SPRING_SECURITY_LAST_EXCEPTION" scope = "session" />
				</c:if>
				<div class="alert alert-danger">
					<strong>OOPS!</strong> Invalid Username/Password.
				</div>
				<div class="alert alert-success">
					<strong>Congratulations!</strong> You registration is success full. Please login to continue.
				</div>
				<form action="/login" method="post">
					<div class="form-group">
						<label for="email">Email:</label> <input type="text"
							class="form-control form-control1" id="email" name="username"
							placeholder="Enter email / Mobile Mumber" required="required">
					</div>
					<div class="form-group">
						<label for="pwd">Password:</label> <input type="password"
							class="form-control form-control1" class="text-length" id="pwd" name="password"
							placeholder="Enter password" required="required">
					</div>
					<div class="checkbox">
						<label><input type="checkbox"> Remember me</label>
					</div>
					<div class="checkbox">
						<span><a href="#">Forgot Password?</a></span>
					</div>
					<button type="submit" class="btn btn-default">Submit</button>
				</form>
			</div>
		</c:when>
		<c:when test="${MODE=='REGISTER'}">
			<div class="container">
				<h2>Registration</h2>
				<hr>
				<c:if test="${status=='success'}">
					<script type="text/javascript">
						$(function() {
							$('.alert-success').show("slow")
						})
					</script>
				</c:if>
				<c:if test="${status=='failed'}">
					<script type="text/javascript">
						$(function() {
							$('.alert-danger').show("slow")
						})
					</script>
				</c:if>
				<div class="alert alert-success">
					<strong>Success!</strong> You have successfully registered for home
					ledger
				</div>
				<div class="alert alert-danger">
					<strong>OOPS!</strong> This user already exists. Please try using different Email and Mobile number.
				</div>
				<form action="/register" method="post" modelAttribute="User">
					<div class="form-group">
						<label for="First Name">First Name:</label> <input type="text"
							class="form-control" id="firstName" name="firstName"
							placeholder="Enter First Name" required="required" value="${user.firstName}">
					</div>
					<div class="form-group">
						<label for="Last Name">Last Name:</label> <input type="text"
							class="form-control" id="lastName" name="lastName"
							placeholder="Enter Last Name" value="${user.lastName}">
					</div>
					<div class="form-group">
						<label for="email">Email:</label> <input type="email"
							class="form-control" id="email" name="emailId"
							placeholder="Enter Email" required="required" value="${user.emailId}">
					</div>
					<div class="form-group">
						<label for="Mobile">Mobile:</label> <input type="tel"
							class="form-control" id="mobile" name="mobileNumber"
							placeholder="Enter Mobile Mumber" pattern="[0-9]{10}"
							title="Must contain 10 numbers" required="required" value="${user.mobileNumber}">
					</div>
					<div class="form-group">
						<label for="pwd">Password:</label> <input type="password"
							class="form-control" class="text-length" id="pwd" name="password"
							placeholder="Enter password"
							pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}"
							title="Must contain at least one number and one uppercase and lowercase letter, and at least 8 or more characters"
							required="required">
					</div>
					<button type="submit" class="btn btn-default">Submit</button>
					<button type="reset" class="btn btn-default">Reset</button>
				</form>
			</div>
		</c:when>
	</c:choose>
</body>
</html>