<html>
	<head>
		<meta charset="UTF-8">
		<link href="css/styles.css" type="text/css" rel="stylesheet" />
		<link rel="shortcut icon" href="media/icon.png">
		<title>Users Management System</title>
		<script src="js/registerForm.js"></script>
		<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
	</head>
	<body>
	<header>
		<h2>Users Management System</h2>
	</header>
	<nav>
		<form action="getUser" class="registerFormTable">
			<h3>Register Form</h3>
			<table class="registerFormTable">
				<tr>
					<td>
						<input type="hidden" name="userId" id="userId" value="" >
					</td>
				</tr>
				<tr>
					<td>
						<label for="firstName">First Name</label><br>
						<input type="text" name="firstName" id="firstName" value="" 
						maxlength="40" 
						onkeypress="return (event.charCode > 64 && event.charCode < 91) || (event.charCode > 96 && event.charCode < 123) || (event.charCode==32)" >
					</td>
				</tr>
				<tr>
					<td>
						<label for="lastName">Last Name</label><br>
						<input type="text" name="lastName" id="lastName" value="" 
						maxlength="40" 
						onkeypress="return (event.charCode > 64 && event.charCode < 91) || (event.charCode > 96 && event.charCode < 123) || (event.charCode==32)" >
					</td>
				</tr>
				<tr>
					<td>
						<label for="userName">User Name</label><br>
						<input type="text" name="userName" id="userName" value="" 
						maxlength="20" >
					</td>
				</tr>
				<tr>
					<td>
						<label for="userPassword">Password</label><br>
						<input type="password" name="userPassword" id="userPassword" value="" 
						maxlength="10" >
					</td>
				</tr>
				<tr>
					<td>
						<label for="userEmail">Email</label><br>
						<input type="text" name="userEmail" id="userEmail" value="" 
						maxlength="40"
						onfocusout="validateEmail()" >
					</td>
				</tr>
				<tr>
					<td>
						<input type="radio" name="userStatus" id="active" value="1" >
						<label for="active">Active</label>
						<input type="radio" name="userStatus" id="inactive" value="0" >
						<label for="inactive">Inactive</label>
					</td>
				</tr>
				<tr>
					<td>
						<input type="button" value="Submit" onclick="executeUpdate()">
						<input type="button" value="Clean" onclick="cleanForm()">
					</td>
				</tr>
			</table>
		</form>
	</nav>
	<article>
		<form>
			<table id="filterTable">
				<tr>
					<th>
						<label for="filter">Filter</label>
						<select name="filter" id="filter" onchange="readUsers()" >
							<option value="select" selected="selected">Select</option>
							<option value="all">All</option>
							<option value="active">Active</option>
							<option value="inactive">Inactive</option>
						</select>
					</th>
				</tr>
			</table>
		</form>
		<form id="contentForm">
			<table id="content">
				<tr>
					<th>First Name</th>
					<th>Last Name</th>
					<th>User Name</th>
					<th>Email</th>
					<th>Status</th>
					<th>Action</th>
				</tr>
			</table>
		</form>
	</article>
	</body>
</html>