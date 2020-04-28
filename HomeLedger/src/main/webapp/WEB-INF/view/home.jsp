<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home Ledger</title>
<link rel="stylesheet" href='<c:url value="/css/bootstrap.min.css"/>'>
<link rel="stylesheet" href='<c:url value="/css/table.css"/>'> 
<link rel="stylesheet" href='<c:url value="/css/style.css"/>'>
<link href='<c:url value="css/addons/datatables.min.css"/>' rel="stylesheet">
<script type="text/javascript" src='<c:url value="/js/jquery.min.js"/>'></script>
<script type="text/javascript" src='<c:url value="/js/bootstrap/3.4.1/bootstrap.min.js"/>'></script>
<script type="text/javascript" src='<c:url value="/js/bootstrap/4.4.1/bootstrap.min.js"/>'></script>
<script type="text/javascript" src='<c:url value="/js/jquery.js"/>'></script>
<script type="text/javascript" src='<c:url value="js/addons/datatables.min.js"/>'></script>

<script>
if ( window.history.replaceState ) {
  window.history.replaceState( null, null, window.location.href );
}
history.pushState(null, document.title, location.href);
window.addEventListener('popstate', function (event)
{
  history.pushState(null, document.title, location.href);
});
$(function () {  
    $(document).keydown(function (e) {  
        return (e.which || e.keyCode) != 116;  
    });  
}); 
</script>

</head>
<body>
	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target="#myNavbar">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand">Home Ledger</a>
			</div>
			<div class="collapse navbar-collapse" id="myNavbar">
				<ul class="nav navbar-nav">
					<li><a href='<c:url value="/home"/>'>Home</a></li>
					<li class="dropdown"><a class="dropdown-toggle"
						data-toggle="dropdown" href="#">Organize Spends<span
							class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href='<c:url value="/organiseBills"/>'>BILLS</a></li>
							<li><a href='<c:url value="/organiseEssentials"/>'>DAILY ESSENTIALS</a></li>
							<li><a href='<c:url value="/organiseHealthCare"/>'>HEALTH CARE</a></li>
							<li><a href='<c:url value="/organiseShopping"/>'>SHOPPING</a></li>
							<li><a href='<c:url value="/organiseTravel"/>'>TRAVEL</a></li>
							<li><a href='<c:url value="/organiseVehicles"/>'>VEHICLES</a></li>
						</ul></li>
					<li class="dropdown"><a class="dropdown-toggle"
						data-toggle="dropdown" href="#">Add Spends<span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href='<c:url value="/addBillSpends"/>'>BILLS</a></li>
							<li><a href='<c:url value="/addDailyEssentialSpends"/>'>DAILY ESSENTIALS</a></li>
							<li><a href='<c:url value="/addHealthCareSpends"/>'>HEALTH CARE</a></li>
							<li><a href='<c:url value="/addShoppingSpends"/>'>SHOPPING</a></li>
							<li><a href='<c:url value="/addTravelSpends"/>'>TRAVEL</a></li>
							<li><a href='<c:url value="/addVehicleSpends"/>'>VEHICLE SPENDS</a></li>
							<li><a href='<c:url value="/addOtherSpends"/>'>OTHER SPENDS</a></li>
						</ul></li>
					<li class="dropdown"><a class="dropdown-toggle"
						data-toggle="dropdown" href="#">VIEW Spends<span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href='<c:url value="/viewAllSpends"/>'>VIEW ALL SPENDS</a></li>
							<li><a href="#">VIEW SPENDS BY TIME</a></li>
							<li><a href="#">VIEW SPENDS BY CATEGORY</a></li>
						</ul></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li><a href='<c:url value="/logout"/>'>Logout</a></li>
				</ul>
			</div>
		</div>
	</nav>
	<c:choose>
		<c:when test="${MODE=='HOME'}">
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
		<c:when test="${MODE=='ADD_BILL_TYPE'}">
			<div class="container">
				<h2>Add Bill Type</h2>
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
					<strong>Success!</strong> Bill type added successfully
				</div>
				<div class="alert alert-danger">
					<strong>OOPS!</strong> This bill type already exists
				</div>
				<form action='<c:url value="/addBillType"/>' method="post" modelAttribute="BillType">
					<div class="form-group">
						<label for="billType">Bill Type:</label> <input type="text"
							class="form-control" id="billTypeName" name="billTypeName"
							placeholder="Enter Bill Type" required="required"
							value="${billType.billTypeName }">
					</div>
					<button type="submit" class="btn btn-default">Submit</button>
				</form>
			</div>
		</c:when>
		<c:when test="${MODE=='EDIT_BILL_TYPE'}">
			<div class="container">
				<h2>Edit Bill Type</h2>
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
					<strong>Success!</strong> Bill type added successfully
				</div>
				<div class="alert alert-danger">
					<strong>OOPS!</strong> This bill type already exists
				</div>
				<form action='<c:url value="/editBillType"/>' method="post" modelAttribute="BillType">
					<input type="hidden" name="billTypeId" id="billTypeId"
						value="${billtype.billTypeId}">
					<div class="form-group">
						<label for="billType">Bill Type:</label> <input type="text"
							class="form-control" id="billTypeName" name="billTypeName"
							placeholder="Enter Bill Type" required="required"
							value="${billtype.billTypeName}">
					</div>
					<button type="submit" class="btn btn-default">Submit</button>
				</form>
			</div>
		</c:when>
		<c:when test="${MODE=='ADD_BILL_ACCOUNT'}">
			<div class="container">
				<h2>Add Bill Account</h2>
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
					<strong>Success!</strong> Bill account added successfully
				</div>
				<div class="alert alert-danger">
					<strong>OOPS!</strong> This bill account already exists
				</div>
				<form action='<c:url value="/addBillAccount"/>' method="post"
					modelAttribute="BillAccounts">
					<div class="form-group">
						<label for="billType">Bill Type:</label> <select
							class="form-control" id="billType" name="billType"
							required="required">
							<option value="">--Select--</option>
							<c:forEach var="billType" items="${billTypes}">
								<option value="${billType.billTypeName}">${billType.billTypeName}</option>
							</c:forEach>
						</select>
					</div>
					<div class="form-group">
						<label for="billType">Bill Account:</label> <input type="text"
							class="form-control" id="billAccount" name="billAccount"
							placeholder="Enter Bill Account" required="required"
							value="${bills.billAccount}">
					</div>
					<div class="form-group">
						<label for="billType">Bill Amount (Optional):</label> <input
							type="number" step="0.01" class="form-control" id="billAmount"
							name="billAmount" value="${bills.billAmount}" min="0"
							max="999999" placeholder="Enter Bill Amount" required="required">
					</div>
					<button type="submit" class="btn btn-default">Submit</button>
					<button type="reset" class="btn btn-default">Reset</button>
				</form>
			</div>
		</c:when>
		<c:when test="${MODE=='EDIT_BILL_ACCOUNT'}">
			<div class="container">
				<h2>Edit Bill Account</h2>
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
					<strong>Success!</strong> Bill account added successfully
				</div>
				<div class="alert alert-danger">
					<strong>OOPS!</strong> This bill account already exists
				</div>
				<form action='<c:url value="/editBillAccount"/>' method="post"
					modelAttribute="billAccounts">
					<input type="hidden" name="billId" value="${bills.billId}">
					<div class="form-group">
						<label for="billType">Bill Type:</label> <select
							class="form-control" id="billType" name="billType"
							required="required">
							<option value="${bills.billType}">${bills.billType}</option>
						</select>
					</div>
					<div class="form-group">
						<label for="billType">Bill Account:</label> <input type="text"
							class="form-control" id="billAccount" name="billAccount"
							placeholder="Enter Bill Account" required="required"
							value="${bills.billAccount}">
					</div>
					<div class="form-group">
						<label for="billType">Bill Amount (Optional):</label> <input
							type="number" step="0.01" class="form-control" id="billAmount"
							name="billAmount" value="${bills.billAmount}" min="0"
							max="999999" placeholder="Enter Bill Amount" required="required">
					</div>
					<button type="submit" class="btn btn-default">Submit</button>
					<button type="reset" class="btn btn-default">Reset</button>
				</form>
			</div>
		</c:when>
		<c:when test="${MODE=='BILLS'}">
			<div class="container">
				<div class="jumbotron">
					<div class="container">
						<h2 style="color: green">BILL TYPES</h2>
						<table class="table table-bordered"  id="dtBasicExample">
							<thead>
								<tr>
									<th>BILL TYPE</th>
									<th>EDIT</th>
									<th>DELETE</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="billType" items="${billTypes}">
									<tr>
										<td>${billType.billTypeName}</td>
										<td><a
											href='<c:url value="/editBillType?billTypeId=${billType.billTypeId}"/>'><span><img
													alt="Edit" src='<c:url value="/image/edit.png"/>' width="15px" height="15px"></span></a></td>
										<td><a
											href='<c:url value="/deleteBillType?billTypeId=${billType.billTypeId}"/>'><span><img
													alt="Delete" src='<c:url value="/image/delete.png"/>' width="20px"
													height="20px"></span></a></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						<a href='<c:url value="/addBillType"/>'><input type="button"
							class="btn btn-default" value="ADD NEW BILL TYPE"></a>
					</div>
					<div class="container">
						<h2 style="color: green">BILL ACCOUNT DETAILS</h2>
						<table class="table table-bordered" id="dtBasicExample1">
							<thead>
								<tr>
									<th>BILL TYPE</th>
									<th>BILL ACOUNT</th>
									<th>BILL AMOUNT</th>
									<th>EDIT</th>
									<th>DELETE</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="bill" items="${bills}">
									<tr>
										<td>${bill.billType}</td>
										<td>${bill.billAccount}</td>
										<td>${bill.billAmount}</td>
										<td><a href='<c:url value="/editBill?billId=${bill.billId}"/>'><span><img
													alt="Edit" src='<c:url value="/image/edit.png"/>' width="15px" height="15px"></span></a></td>
										<td><a href='<c:url value="/deleteBill?billId=${bill.billId}"/>'><span><img
													alt="Delete" src='<c:url value="/image/delete.png"/>' width="20px"
													height="20px"></span></a></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						<a href='<c:url value="/addBillAccount"/>'><input type="button"
							class="btn btn-default" value="ADD NEW BILL ACCOUNT"></a>
					</div>
				</div>
			</div>
		</c:when>
		<c:when test="${MODE=='VEHICLE'}">
			<div class="container">
				<div class="jumbotron">
					<div class="container">
						<h2 style="color: green">VEHICLE DETAILS</h2>
						<table class="table table-bordered" id="dtBasicExample">
							<thead>
								<tr>
									<th>VEHICLE MAKE</th>
									<th>VEHICLE MODEL</th>
									<th>VEHICLE NUMBER</th>
									<th>EDIT</th>
									<th>DELETE</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="veicle" items="${vehicles}">
									<tr>
										<td>${veicle.vehicleMake}</td>
										<td>${veicle.vehicleModel}</td>
										<td>${veicle.vehicleNumber}</td>
										<td><a href='<c:url value="/editVehicle?vehicleId=${veicle.vehicleId}"/>'><span><img
													alt="Edit" src='<c:url value="/image/edit.png"/>' width="15px" height="15px"></span></a></td>
										<td><a
											href='<c:url value="/deleteVehicle?vehicleId=${veicle.vehicleId}"/>'><span><img
													alt="Delete" src='<c:url value="/image/delete.png"/>' width="20px"
													height="20px"></span></a></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						<a href='<c:url value="/addVehicle"/>'><input type="button"
							class="btn btn-default" value="ADD VEHICLE"></a>
						<h2 style="color: green">VEHICLE SPENDS</h2>
						<table class="table table-bordered" id="dtBasicExample1">
							<thead>
								<tr>
									<th>VEHICLE SPEND NAME</th>
									<th>EDIT</th>
									<th>DELETE</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="veicleSpend" items="${vehicleSpends}">
									<tr>
										<td>${veicleSpend.spendName}</td>
										<td><a
											href='<c:url value="/editVehicleSpend?spendId=${veicleSpend.spendId}"/>'><span><img
													alt="Edit" src='<c:url value="/image/edit.png"/>' width="15px" height="15px"></span></a></td>
										<td><a
											href='<c:url value="/deleteVehicleSpend?spendId=${veicleSpend.spendId}"/>'><span><img
													alt="Delete" src='<c:url value="/image/delete.png"/>' width="20px"
													height="20px"></span></a></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						<a href='<c:url value="/addVehicleSpend"/>'><input type="button"
							class="btn btn-default" value="ADD VEHICLE SPENDS"></a>
					</div>
				</div>
			</div>
		</c:when>
		<c:when test="${MODE=='ADD_VEHICLE'}">
			<div class="container">
				<h2>Add Vehicle</h2>
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
					<strong>Success!</strong> Vehicle added successfully
				</div>
				<div class="alert alert-danger">
					<strong>OOPS!</strong> This Vehicle already exists
				</div>
				<form action='<c:url value="/addVehicle"/>' method="post" modelAttribute="Vehicle">
					<div class="form-group">
						<label for="vehicleMake">Vehicle Make:</label> <input type="text"
							class="form-control" id="vehicleMake" name="vehicleMake"
							placeholder="Enter Vehicle Make" required="required"
							value="${vehicle.vehicleMake}">
					</div>
					<div class="form-group">
						<label for="vehicleModel">Vehicle Model:</label> <input
							type="text" class="form-control" id="vehicleModel"
							name="vehicleModel" placeholder="Enter Vehicle Model"
							required="required" value="${vehicle.vehicleModel}">
					</div>
					<div class="form-group">
						<label for="vehicleNumber">Vehicle Number:</label> <input
							type="text" class="form-control" id="vehicleNumber"
							name="vehicleNumber" placeholder="Enter Vehicle Number"
							required="required" value="${vehicle.vehicleNumber}">
					</div>
					<button type="submit" class="btn btn-default">Submit</button>
					<button type="reset" class="btn btn-default">Reset</button>
				</form>
			</div>
		</c:when>
		<c:when test="${MODE=='EDIT_VEHICLE'}">
			<div class="container">
				<h2>Edit Vehicle</h2>
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
					<strong>Success!</strong> Bill account added successfully
				</div>
				<div class="alert alert-danger">
					<strong>OOPS!</strong> This bill account already exists
				</div>
				<form action='<c:url value="/editVehicle"/>' method="post" modelAttribute="Vehicle">
					<input type="hidden" id="vehicleId" name="vehicleId"
						value="${vehicle.vehicleId}">
					<div class="form-group">
						<label for="vehicleMake">Vehicle Make:</label> <input type="text"
							class="form-control" id="vehicleMake" name="vehicleMake"
							placeholder="Enter Vehicle Make" required="required"
							value="${vehicle.vehicleMake}">
					</div>
					<div class="form-group">
						<label for="vehicleModel">Vehicle Model:</label> <input
							type="text" class="form-control" id="vehicleModel"
							name="vehicleModel" placeholder="Enter Vehicle Model"
							required="required" value="${vehicle.vehicleModel}">
					</div>
					<div class="form-group">
						<label for="vehicleNumber">Vehicle Number:</label> <input
							type="text" class="form-control" id="vehicleNumber"
							name="vehicleNumber" placeholder="Enter Vehicle Number"
							required="required" value="${vehicle.vehicleNumber}">
					</div>
					<button type="submit" class="btn btn-default">Submit</button>
					<button type="reset" class="btn btn-default">Reset</button>
				</form>
			</div>
		</c:when>
		<c:when test="${MODE=='ADD_VEHICLE_SPEND'}">
			<div class="container">
				<h2>Add Vehicle Spend</h2>
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
					<strong>Success!</strong> Vehicle Spend added successfully
				</div>
				<div class="alert alert-danger">
					<strong>OOPS!</strong> This Vehicle Spend already exists
				</div>
				<form action='<c:url value="/addVehicleSpend"/>' method="post"
					modelAttribute="VehicleSpend">
					<div class="form-group">
						<label for="spendName">Vehicle Spend Name:</label> <input
							type="text" class="form-control" id="spendName" name="spendName"
							placeholder="Enter Vehicle Spend Name" required="required"
							value="${vehicleSpend.spendName}">
					</div>
					<button type="submit" class="btn btn-default">Submit</button>
					<button type="reset" class="btn btn-default">Reset</button>
				</form>
			</div>
		</c:when>
		<c:when test="${MODE=='EDIT_VEHICLE_SPEND'}">
			<div class="container">
				<h2>Add Vehicle Spend</h2>
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
					<strong>Success!</strong> Vehicle Spend added successfully
				</div>
				<div class="alert alert-danger">
					<strong>OOPS!</strong> This Vehicle Spend already exists
				</div>
				<form action='<c:url value="/editVehicleSpend"/>' method="post"
					modelAttribute="VehicleSpend">
					<input type="hidden" id="spendId" name="spendId"
						value="${vehicleSpend.spendId}">
					<div class="form-group">
						<label for="spendName">Vehicle Spend Name:</label> <input
							type="text" class="form-control" id="spendName" name="spendName"
							placeholder="Enter Vehicle Spend Name" required="required"
							value="${vehicleSpend.spendName}">
					</div>
					<button type="submit" class="btn btn-default">Submit</button>
					<button type="reset" class="btn btn-default">Reset</button>
				</form>
			</div>
		</c:when>
		<c:when test="${MODE=='DAILY_ESSENTIALS'}">
			<div class="container">
				<div class="jumbotron">
					<div class="container">
						<h2 style="color: green">DAILY ESSENTIAL NAMES</h2>
						<table class="table table-bordered" id="dtBasicExample">
							<thead>
								<tr>
									<th>DAILY ESSENTIAL NAME</th>
									<th>EDIT</th>
									<th>DELETE</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="dailyEssential" items="${dailyEssentials}">
									<tr>
										<td>${dailyEssential.dailyEssentialsName}</td>
										<td><a
											href='<c:url value="/editDailyEssentials?dailyEssentialsId=${dailyEssential.dailyEssentialsId}"/>'><span><img
													alt="Edit" src='<c:url value="/image/edit.png"/>' width="15px" height="15px"></span></a></td>
										<td><a
											href='<c:url value="/deleteDailyEssentials?dailyEssentialsId=${dailyEssential.dailyEssentialsId}"/>'><span><img
													alt="Delete" src='<c:url value="/image/delete.png"/>' width="20px"
													height="20px"></span></a></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						<a href='<c:url value="/addDailyEssentials"/>'><input type="button"
							class="btn btn-default" value="ADD NEW DAILY ESSENTIAL"></a>
					</div>
				</div>
			</div>
		</c:when>
		<c:when test="${MODE=='ADD_DAILY_ESSENTIALS'}">
			<div class="container">
				<h2>Add Daily Essential Name</h2>
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
					<strong>Success!</strong> Daily essential added successfully
				</div>
				<div class="alert alert-danger">
					<strong>OOPS!</strong> This daily essential already exists
				</div>
				<form action='<c:url value="/addDailyEssentials"/>' method="post"
					modelAttribute="DailyEssentials">
					<div class="form-group">
						<label for="dailyEssentialsName">Dailly Essentials Name:</label> <input
							type="text" class="form-control" id="dailyEssentialsName"
							name="dailyEssentialsName"
							placeholder="Enter Daily Essential Name" required="required"
							value="${dailyEssentials.dailyEssentialsName}">
					</div>
					<button type="submit" class="btn btn-default">Submit</button>
				</form>
			</div>
		</c:when>
		<c:when test="${MODE=='EDIT_DAILY_ESSENTIALS'}">
			<div class="container">
				<h2>Edit Daily Essential Name</h2>
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
					<strong>Success!</strong> Daily essential added successfully
				</div>
				<div class="alert alert-danger">
					<strong>OOPS!</strong> This daily essential already exists
				</div>
				<form action='<c:url value="/editDailyEssentials"/>' method="post"
					modelAttribute="DailyEssentials">
					<input type="hidden" name="dailyEssentialsId"
						value="${dailyEssentials.dailyEssentialsId}">
					<div class="form-group">
						<label for="dailyEssentialsName">Daily Essentials Name:</label> <input
							type="text" class="form-control" id="dailyEssentialsName"
							name="dailyEssentialsName"
							placeholder="Enter Daily Essential Name" required="required"
							value="${dailyEssentials.dailyEssentialsName}">
					</div>
					<button type="submit" class="btn btn-default">Submit</button>
				</form>
			</div>
		</c:when>
		<c:when test="${MODE=='SHOPPING'}">
			<div class="container">
				<div class="jumbotron">
					<div class="container">
						<h2 style="color: green">SHOPPING TYPES</h2>
						<table class="table table-bordered" id="dtBasicExample">
							<thead>
								<tr>
									<th>SHOPPING TYPE NAME</th>
									<th>EDIT</th>
									<th>DELETE</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="shoppingType" items="${shoppingTypes}">
									<tr>
										<td>${shoppingType.shoppingTypeName}</td>
										<td><a
											href='<c:url value="/editShoppingType?shoppingTypeId=${shoppingType.shoppingTypeId}"/>'><span><img
													alt="Edit" src='<c:url value="/image/edit.png"/>' width="15px" height="15px"></span></a></td>
										<td><a
											href='<c:url value="/deleteShoppingType?shoppingTypeId=${shoppingType.shoppingTypeId}"/>'><span><img
													alt="Delete" src='<c:url value="/image/delete.png"/>' width="20px"
													height="20px"></span></a></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						<a href='<c:url value="/addShoppingType"/>'><input type="button"
							class="btn btn-default" value="ADD NEW SHOPPING TYPE"></a>
					</div>
				</div>
			</div>
		</c:when>
		<c:when test="${MODE=='ADD_SHOPPING_TYPE'}">
			<div class="container">
				<h2>Add Shopping Type Name</h2>
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
					<strong>Success!</strong> Shopping type added successfully
				</div>
				<div class="alert alert-danger">
					<strong>OOPS!</strong> This shopping type already exists
				</div>
				<form action='<c:url value="/addShoppingType"/>' method="post"
					modelAttribute="Shopping">
					<div class="form-group">
						<label for="shoppingTypeName">Shopping Type Name:</label> <input
							type="text" class="form-control" id="shoppingTypeName"
							name="shoppingTypeName" placeholder="Enter Shopping Type Name"
							required="required" value="${shoppingType.shoppingTypeName}">
					</div>
					<button type="submit" class="btn btn-default">Submit</button>
				</form>
			</div>
		</c:when>
		<c:when test="${MODE=='EDIT_SHOPPING_TYPE'}">
			<div class="container">
				<h2>Edit Shopping Type Name</h2>
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
					<strong>Success!</strong> Shopping type added successfully
				</div>
				<div class="alert alert-danger">
					<strong>OOPS!</strong> This Shopping type already exists
				</div>
				<form action='<c:url value="/editShoppingType"/>' method="post"
					modelAttribute="Shopping">
					<input type="hidden" name="shoppingTypeId"
						value="${shoppingType.shoppingTypeId}">
					<div class="form-group">
						<label for="shoppingTypeName">Shopping Type Name:</label> <input
							type="text" class="form-control" id="shoppingTypeName"
							name="shoppingTypeName" placeholder="Enter Daily Essential Name"
							required="required" value="${shoppingType.shoppingTypeName}">
					</div>
					<button type="submit" class="btn btn-default">Submit</button>
				</form>
			</div>
		</c:when>
		<c:when test="${MODE=='HEALTH_CARE'}">
			<div class="container">
				<div class="jumbotron">
					<div class="container">
						<h2 style="color: green">HEALTH CARE SPEND TYPES</h2>
						<table class="table table-bordered" id="dtBasicExample">
							<thead>
								<tr>
									<th>HEALTH CARE SPEND TYPE NAME</th>
									<th>EDIT</th>
									<th>DELETE</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="healthCareType" items="${healthCareTypes}">
									<tr>
										<td>${healthCareType.healthCareSpendType}</td>
										<td><a
											href='<c:url value="/editHealthCareSpendType?healthCareSpendId=${healthCareType.healthCareSpendId}"/>'><span><img
													alt="Edit" src='<c:url value="/image/edit.png"/>' width="15px" height="15px"></span></a></td>
										<td><a
											href='<c:url value="/deleteHealthCareSpendType?healthCareSpendId=${healthCareType.healthCareSpendId}"/>'><span><img
													alt="Delete" src='<c:url value="/image/delete.png"/>' width="20px"
													height="20px"></span></a></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						<a href='<c:url value="/addHealthCareSpendType"/>'><input type="button"
							class="btn btn-default" value="ADD NEW HEALTH CARE SPEND TYPE"></a>
					</div>
				</div>
			</div>
		</c:when>
		<c:when test="${MODE=='ADD_HEALTH_CARE_TYPE'}">
			<div class="container">
				<h2>Add Health Care Spend Type Name</h2>
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
					<strong>Success!</strong> Health care spend type added successfully
				</div>
				<div class="alert alert-danger">
					<strong>OOPS!</strong> This Health care spend type already exists
				</div>
				<form action='<c:url value="/addHealthCareSpendType"/>' method="post"
					modelAttribute="HealthCare">
					<div class="form-group">
						<label for="healthCareSpendType">Health Care Spend Type
							Name:</label> <input type="text" class="form-control"
							id="healthCareSpendType" name="healthCareSpendType"
							placeholder="Enter Health Care Spend Type Name"
							required="required" value="${healthCare.healthCareSpendType}">
					</div>
					<button type="submit" class="btn btn-default">Submit</button>
				</form>
			</div>
		</c:when>
		<c:when test="${MODE=='EDIT_HEALTH_CARE_TYPE'}">
			<div class="container">
				<h2>Edit Health Care Spend Type Name</h2>
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
					<strong>Success!</strong> Health care spend type added successfully
				</div>
				<div class="alert alert-danger">
					<strong>OOPS!</strong> This health care spend type already exists
				</div>
				<form action='<c:url value="/editHealthCareSpendType"/>' method="post"
					modelAttribute="HealthCare">
					<input type="hidden" name="healthCareSpendId"
						value="${healthCare.healthCareSpendId}">
					<div class="form-group">
						<label for="healthCareSpendType">Health Care Spend Type
							Name:</label> <input type="text" class="form-control"
							id="healthCareSpendType" name="healthCareSpendType"
							placeholder="Enter Health Care Spend Name" required="required"
							value="${healthCare.healthCareSpendType}">
					</div>
					<button type="submit" class="btn btn-default">Submit</button>
				</form>
			</div>
		</c:when>
		<c:when test="${MODE=='ADD_BILL_SPENDS'}">
			<div class="container">
				<h4 style="color: brown;">
					<b>Add Bill Type Spends</b>
				</h4>
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
					<strong>Success!</strong> Particular added successfully
				</div>
				<div class="alert alert-danger">
					<strong>OOPS!</strong> Some thing went wrong
				</div>
				<form action='<c:url value="/addBillSpends"/>' method="post"
					modelAttribute="Particulars">
					<div class="form-group">
						<input type="hidden" id="particularDate" name="particularDate">
						<label for="particularDate">Particular Date:</label> <input
							type="date" class="form-control" id="pdate" name="pdate"
							placeholder="Select Particular Date" required="required">
					</div>
					<div class="form-group">
						<!-- <!-- <label for="particularCategory">Particular Category:
						</label> -->
						<input type="hidden" class="form-control" id="particularCategory"
							name="particularCategory"
							placeholder="Enter Health Care Spend Type Name"
							required="required" value="BILLS" readonly="readonly">
					</div>
					<div class="form-group">
						<label for="particularType">Particular Type:</label> <select
							class="form-control" id="particularType" name="particularType"
							required="required">
							<option value="">--Select--</option>
							<c:forEach var="billType" items="${billTypes}">
								<option value="${billType.billTypeName}">${billType.billTypeName}</option>
							</c:forEach>
						</select>
					</div>
					<div class="form-group">
						<label for="particularDescription">Particular Description:</label>
						<select class="form-control" id="particularDescription"
							name="particularDescription" required="required">
							<option value="">--Select--</option>
							<c:forEach var="bill" items="${bills}">
								<option value="${bill.billAccount}">${billType.billAccount}</option>
							</c:forEach>
						</select>
					</div>
					<div class="form-group">
						<label for="particularAmount">Particular Amount:</label> <input
							type="number" step="0.01" class="form-control"
							id="particularAmount" name="particularAmount"
							placeholder="Enter Particular Amount" required="required"
							value="${bills.billAmount}">
					</div>
					<div class="form-group">
						<label for="particularRemarks">Remarks:</label>
						<textarea cols="4" class="form-control" id="particularRemarks"
							name="particularRemarks" placeholder="Enter Remarks"></textarea>
					</div>
					<button type="submit" class="btn btn-default">Submit</button>
					<button type="reset" class="btn btn-default">Reset</button>
				</form>
			</div>
		</c:when>
		<c:when test="${MODE=='ADD_DAILY_ESSENTIAL_SPENDS'}">
			<div class="container">
				<h4 style="color: brown;">
					<b>Add Daily Essential Spends</b>
				</h4>
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
					<strong>Success!</strong> Particular added successfully
				</div>
				<div class="alert alert-danger">
					<strong>OOPS!</strong> Some thing went wrong
				</div>
				<form action='<c:url value="/addDailyEssentialSpends"/>' method="post"
					modelAttribute="Particulars">
					<div class="form-group">
						<input type="hidden" id="particularDate" name="particularDate">
						<label for="particularDate">Particular Date:</label> <input
							type="date" class="form-control" id="pdate" name="pdate"
							placeholder="Select Particular Date" required="required">
					</div>
					<div class="form-group">
						<!-- <!-- <label for="particularCategory">Particular Category:
						</label> -->
						<input type="hidden" class="form-control" id="particularCategory"
							name="particularCategory"
							placeholder="Enter Health Care Spend Type Name"
							required="required" value="DAILY ESSENTIALS" readonly="readonly">
					</div>
					<div class="form-group">
						<label for="particularType">Particular Type:</label> <select
							class="form-control" id="DailyEssentialparticularType"
							name="particularType" required="required">
							<option value="">--Select--</option>
							<c:forEach var="dailyEssential" items="${dailyEssntials}">
								<option value="${dailyEssential.dailyEssentialsName}">${dailyEssential.dailyEssentialsName}</option>
							</c:forEach>
						</select>
					</div>
					<div class="form-group">
						<label for="particularDescription">Particular Description:</label>
						<textarea cols="4" class="form-control"
							id="DailyEssentialparticularDescription"
							name="particularDescription"
							placeholder="Enter Particular Description" required="required"></textarea>
					</div>
					<div class="form-group">
						<label for="particularAmount">Particular Amount:</label> <input
							type="number" step="0.01" class="form-control"
							id="particularAmount" name="particularAmount"
							placeholder="Enter Particular Amount" required="required"
							value="${bills.billAmount}">
					</div>
					<div class="form-group">
						<label for="particularRemarks">Remarks:</label>
						<textarea cols="4" class="form-control" id="particularRemarks"
							name="particularRemarks" placeholder="Enter Remarks"></textarea>
					</div>
					<button type="submit" class="btn btn-default">Submit</button>
					<button type="reset" class="btn btn-default">Reset</button>
				</form>
			</div>
		</c:when>
		<c:when test="${MODE=='ADD_HEALTH_CARE_SPENDS'}">
			<div class="container">
				<h4 style="color: brown;">
					<b>Add Health Care Spends</b>
				</h4>
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
					<strong>Success!</strong> Particular added successfully
				</div>
				<div class="alert alert-danger">
					<strong>OOPS!</strong> Some thing went wrong
				</div>
				<form action='<c:url value="/addHealthCareSpends"/>' method="post"
					modelAttribute="Particulars">
					<div class="form-group">
						<input type="hidden" id="particularDate" name="particularDate">
						<label for="particularDate">Particular Date:</label> <input
							type="date" class="form-control" id="pdate" name="pdate"
							placeholder="Select Particular Date" required="required">
					</div>
					<div class="form-group">
						<!-- <!-- <label for="particularCategory">Particular Category:
						</label> -->
						<input type="hidden" class="form-control" id="particularCategory"
							name="particularCategory"
							placeholder="Enter Health Care Spend Type Name"
							required="required" value="HEALTH CARE" readonly="readonly">
					</div>
					<div class="form-group">
						<label for="particularType">Particular Type:</label> <select
							class="form-control" id="healthCareparticularType"
							name="particularType" required="required">
							<option value="">--Select--</option>
							<c:forEach var="healthCare" items="${healthCareSpends}">
								<option value="${healthCare.healthCareSpendType}">${healthCare.healthCareSpendType}</option>
							</c:forEach>
						</select>
					</div>
					<div class="form-group">
						<label for="particularDescription">Particular Description:</label>
						<textarea cols="4" class="form-control"
							id="healthCareparticularDescription" name="particularDescription"
							placeholder="Enter Particular Description" required="required"></textarea>
					</div>
					<div class="form-group">
						<label for="particularAmount">Particular Amount:</label> <input
							type="number" step="0.01" class="form-control"
							id="particularAmount" name="particularAmount"
							placeholder="Enter Particular Amount" required="required"
							value="${bills.billAmount}">
					</div>
					<div class="form-group">
						<label for="particularRemarks">Remarks:</label>
						<textarea cols="4" class="form-control" id="particularRemarks"
							name="particularRemarks" placeholder="Enter Remarks"></textarea>
					</div>
					<button type="submit" class="btn btn-default">Submit</button>
					<button type="reset" class="btn btn-default">Reset</button>
				</form>
			</div>
		</c:when>
		<c:when test="${MODE=='ADD_VEHICLE_SPENDS'}">
			<div class="container">
				<h4 style="color: brown;">
					<b>Add Vehicle Spends</b>
				</h4>
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
					<strong>Success!</strong> Particular added successfully
				</div>
				<div class="alert alert-danger">
					<strong>OOPS!</strong> Some thing went wrong
				</div>
				<form action='<c:url value="/addVehicleSpends"/>' method="post"
					modelAttribute="Particulars">
					<div class="form-group">
						<input type="hidden" id="particularDate" name="particularDate">
						<label for="particularDate">Particular Date:</label> <input
							type="date" class="form-control" id="pdate" name="pdate"
							placeholder="Select Particular Date" required="required">
					</div>
					<div class="form-group">
						<!-- <!-- <label for="particularCategory">Particular Category:
						</label> -->
						<input type="hidden" class="form-control" id="particularCategory"
							name="particularCategory"
							placeholder="Enter Health Care Spend Type Name"
							required="required" value="VEHICLE" readonly="readonly">
					</div>
					<div class="form-group">
						<label for="particularType">Vehicle:</label> <select
							class="form-control" id="VehicleparticularType"
							name="particularType" required="required">
							<option value="">--Select--</option>
							<c:forEach var="vehicle" items="${vehicles}">
								<option value="${vehicle.vehicleNumber}">${vehicle.vehicleNumber}</option>
							</c:forEach>
						</select>
					</div>
					<div class="form-group">
						<label for="particularDescription">Particular Description:</label>
						<select class="form-control" id="VehicleparticularDescription"
							name="particularDescription" required="required">
							<option value="">--Select--</option>
							<c:forEach var="vehicleSpend" items="${vehicleSpends}">
								<option value="${vehicleSpend.spendName}">${vehicleSpend.spendName}</option>
							</c:forEach>
						</select>
					</div>
					<div class="form-group">
						<label for="particularAmount">Particular Amount:</label> <input
							type="number" step="0.01" class="form-control"
							id="particularAmount" name="particularAmount"
							placeholder="Enter Particular Amount" required="required"
							value="${bills.billAmount}">
					</div>
					<div class="form-group">
						<label for="particularRemarks">Remarks:</label>
						<textarea cols="4" class="form-control" id="particularRemarks"
							name="particularRemarks" placeholder="Enter Remarks"></textarea>
					</div>
					<button type="submit" class="btn btn-default">Submit</button>
					<button type="reset" class="btn btn-default">Reset</button>
				</form>
			</div>
		</c:when>
		<c:when test="${MODE=='ADD_SHOPPING_SPENDS'}">
			<div class="container">
				<h4 style="color: brown;">
					<b>Add Shopping Spends</b>
				</h4>
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
					<strong>Success!</strong> Particular added successfully
				</div>
				<div class="alert alert-danger">
					<strong>OOPS!</strong> Some thing went wrong
				</div>
				<form action='<c:url value="/addShoppingSpends"/>' method="post"
					modelAttribute="Particulars">
					<div class="form-group">
						<input type="hidden" id="particularDate" name="particularDate">
						<label for="particularDate">Particular Date:</label> <input
							type="date" class="form-control" id="pdate" name="pdate"
							placeholder="Select Particular Date" required="required">
					</div>
					<div class="form-group">
						<!-- <!-- <label for="particularCategory">Particular Category:
						</label> -->
						<input type="hidden" class="form-control" id="particularCategory"
							name="particularCategory"
							placeholder="Enter Health Care Spend Type Name"
							required="required" value="SHOPPING" readonly="readonly">
					</div>
					<div class="form-group">
						<label for="particularType">Particular Type:</label> <select
							class="form-control" id="shoppingparticularType"
							name="particularType" required="required">
							<option value="">--Select--</option>
							<c:forEach var="shopping" items="${shoppings}">
								<option value="${shopping.shoppingTypeName}">${shopping.shoppingTypeName}</option>
							</c:forEach>
						</select>
					</div>
					<div class="form-group">
						<label for="particularDescription">Particular Description:</label>
						<textarea cols="4" class="form-control"
							id="shoppingparticularDescription" name="particularDescription"
							placeholder="Enter Particular Description" required="required"></textarea>
					</div>
					<div class="form-group">
						<label for="particularAmount">Particular Amount:</label> <input
							type="number" step="0.01" class="form-control"
							id="particularAmount" name="particularAmount"
							placeholder="Enter Particular Amount" required="required"
							value="${bills.billAmount}">
					</div>
					<div class="form-group">
						<label for="particularRemarks">Remarks:</label>
						<textarea cols="4" class="form-control" id="particularRemarks"
							name="particularRemarks" placeholder="Enter Remarks"></textarea>
					</div>
					<button type="submit" class="btn btn-default">Submit</button>
					<button type="reset" class="btn btn-default">Reset</button>
				</form>
			</div>
		</c:when>
		<c:when test="${MODE=='ADD_TRAVEL_SPENDS'}">
			<div class="container">
				<h4 style="color: brown;">
					<b>Add Travel Spends</b>
				</h4>
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
					<strong>Success!</strong> Particular added successfully
				</div>
				<div class="alert alert-danger">
					<strong>OOPS!</strong> Some thing went wrong
				</div>
				<form action='<c:url value="/addTravelSpends"/>' method="post"
					modelAttribute="Particulars">
					<div class="form-group">
						<input type="hidden" id="particularDate" name="particularDate">
						<label for="particularDate">Particular Date:</label> <input
							type="date" class="form-control" id="pdate" name="pdate"
							placeholder="Select Particular Date" required="required">
					</div>
					<div class="form-group">
						<!-- <!-- <label for="particularCategory">Particular Category:
						</label> -->
						<input type="hidden" class="form-control" id="particularCategory"
							name="particularCategory"
							placeholder="Enter Health Care Spend Type Name"
							required="required" value="TRAVEL" readonly="readonly">
					</div>
					<div class="form-group">
						<label for="particularType">Particular Type:</label> <select
							class="form-control" id="shoppingparticularType"
							name="particularType" required="required">
							<option value="">--Select--</option>
							<c:forEach var="travel" items="${travels}">
								<option value="${travel.travelSpendName}">${travel.travelSpendName}</option>
							</c:forEach>
						</select>
					</div>
					<div class="form-group">
						<label for="particularDescription">Travel From and To Places:</label>
						<textarea cols="4" class="form-control"
							id="shoppingparticularDescription" name="particularDescription"
							placeholder="Enter Particular Description" required="required"></textarea>
					</div>
					<div class="form-group">
						<label for="particularAmount">Particular Amount:</label> <input
							type="number" step="0.01" class="form-control"
							id="particularAmount" name="particularAmount"
							placeholder="Enter Particular Amount" required="required"
							value="${bills.billAmount}">
					</div>
					<div class="form-group">
						<label for="particularRemarks">Remarks:</label>
						<textarea cols="4" class="form-control" id="particularRemarks"
							name="particularRemarks" placeholder="Enter Remarks"></textarea>
					</div>
					<button type="submit" class="btn btn-default">Submit</button>
					<button type="reset" class="btn btn-default">Reset</button>
				</form>
			</div>
		</c:when>
		<c:when test="${MODE=='ADD_OTHER_SPENDS'}">
			<div class="container">
				<h4 style="color: brown;">
					<b>Add Other Spends</b>
				</h4>
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
					<strong>Success!</strong> Particular added successfully
				</div>
				<div class="alert alert-danger">
					<strong>OOPS!</strong> Some thing went wrong
				</div>
				<form action='<c:url value="/addOtherSpends"/>' method="post"
					modelAttribute="Particulars">
					<div class="form-group">
						<input type="hidden" id="particularDate" name="particularDate">
						<label for="particularDate">Particular Date:</label> <input
							type="date" class="form-control" id="pdate" name="pdate"
							placeholder="Select Particular Date" required="required">
					</div>
					<div class="form-group">
						<!-- <!-- <label for="particularCategory">Particular Category:
						</label> -->
						<input type="hidden" class="form-control" id="particularCategory"
							name="particularCategory"
							placeholder="Enter Health Care Spend Type Name"
							required="required" value="NOT APLLICABLE" readonly="readonly">
					</div>
					<div class="form-group">
						<!-- <label for="particularType">Particular Type:</label> -->
						<input type="hidden" class="form-control"
							id="shoppingparticularType" name="particularType"
							value="NOT APLLICABLE" required="required">
					</div>
					<div class="form-group">
						<label for="particularDescription">Particular Description:</label>
						<textarea cols="4" class="form-control"
							id="shoppingparticularDescription" name="particularDescription"
							placeholder="Enter Particular Description" required="required"></textarea>
					</div>
					<div class="form-group">
						<label for="particularAmount">Particular Amount:</label> <input
							type="number" step="0.01" class="form-control"
							id="particularAmount" name="particularAmount"
							placeholder="Enter Particular Amount" required="required"
							value="${bills.billAmount}">
					</div>
					<div class="form-group">
						<label for="particularRemarks">Remarks:</label>
						<textarea cols="4" class="form-control" id="particularRemarks"
							name="particularRemarks" placeholder="Enter Remarks"></textarea>
					</div>
					<button type="submit" class="btn btn-default">Submit</button>
					<button type="reset" class="btn btn-default">Reset</button>
				</form>
			</div>
		</c:when>
		<c:when test="${MODE=='TRAVEL'}">
			<div class="container">
				<div class="jumbotron">
					<div class="container">
						<h2 style="color: green">TRAVEL TYPES</h2>
						<table class="table table-bordered" id="dtBasicExample">
							<thead>
								<tr>
									<th>TRAVEL TYPE NAME</th>
									<th>EDIT</th>
									<th>DELETE</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="travelType" items="${travelTypes}">
									<tr>
										<td>${travelType.travelSpendName}</td>
										<td><a
											href='<c:url value="/editTravelType?travelSpendId=${travelType.travelSpendId}"/>'><span><img
													alt="Edit" src='<c:url value="/image/edit.png"/>' width="15px" height="15px"></span></a></td>
										<td><a
											href='<c:url value="/deleteTravelType?travelSpendId=${travelType.travelSpendId}"/>'><span><img
													alt="Delete" src='<c:url value="/image/delete.png"/>' width="20px"
													height="20px"></span></a></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						<a href='<c:url value="/addTravelType"/>'><input type="button"
							class="btn btn-default" value="ADD NEW TRAVEL TYPE"></a>
					</div>
				</div>
			</div>
		</c:when>
		<c:when test="${MODE=='ADD_TRAVEL_TYPE'}">
			<div class="container">
				<h2>Add Travel Type Name</h2>
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
					<strong>Success!</strong> Travel type added successfully
				</div>
				<div class="alert alert-danger">
					<strong>OOPS!</strong> This travel type already exists
				</div>
				<form action='<c:url value="/addTravelType"/>' method="post"
					modelAttribute="Travel">
					<div class="form-group">
						<label for="travelSpendName">Travel Type Name:</label> <input
							type="text" class="form-control" id="travelSpendName"
							name="travelSpendName" placeholder="Enter Travel Type Name"
							required="required" value="${travelType.travelSpendName}">
					</div>
					<button type="submit" class="btn btn-default">Submit</button>
				</form>
			</div>
		</c:when>
		<c:when test="${MODE=='EDIT_TRAVEL_TYPE'}">
			<div class="container">
				<h2>Edit Travel Type Name</h2>
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
					<strong>Success!</strong> Travel type added successfully
				</div>
				<div class="alert alert-danger">
					<strong>OOPS!</strong> This travel type already exists
				</div>
				<form action='<c:url value="/editTravelType"/>' method="post"
					modelAttribute="Travel">
					<input type="hidden" name="travelSpendId"
						value="${travelType.travelSpendId}">
					<div class="form-group">
						<label for="travelSpendName">Travel Type Name:</label> <input
							type="text" class="form-control" id="travelSpendName"
							name="travelSpendName" placeholder="Enter Daily Essential Name"
							required="required" value="${travelType.travelSpendName}">
					</div>
					<button type="submit" class="btn btn-default">Submit</button>
				</form>
			</div>
		</c:when>
		<c:when test="${MODE=='VIEW_ALL_SPENDS'}">
			<!-- <h3>
				<span>All Spends</span>
			</h3> -->
			<h6><span>Total Amount Spend :</span>&emsp;<span style="color: navy;">${totalAmount}</span></h6>
			<table class="container table table-striped table-sm" id="dtBasicExample">
				<thead>
					<tr>
						<th><h1>Particular Date</h1></th>
						<th><h1>Particular Category</h1></th>
						<th><h1>Particular Type</h1></th>
						<th><h1>Particular Description</h1></th>
						<th><h1>Particular Amount</h1></th>
						<th><h1>Particular Remarks</h1></th>
						<th><h1>Delete</h1></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="particular" items="${particulars}">
						<tr>
							<td>${particular.particularDate}</td>
							<td>${particular.particularCategory}</td>
							<td>${particular.particularType}</td>
							<td>${particular.particularDescription}</td>
							<td>${particular.particularAmount}</td>
							<td>${particular.particularRemarks}</td>
							<td><a href='<c:url value="/deleteParticular?particularId=${particular.particularId}"/>'><span><img
													alt="Edit" src='<c:url value="/image/delete.png"/>' width="15px" height="15px"></span></a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:when>
	</c:choose>

</body>
</html>