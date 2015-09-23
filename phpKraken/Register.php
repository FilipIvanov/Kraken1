<?php 
	$con=mysqli_connect("sql14.000webhost.com","a4107973_filip","IsakAsimov1943","a4107973_filip");


	$name = $_POST["name"];
	$age = $_POST["age"];
	$username = $_POST["username"];
	$password = $_POST["password"];
	



	$statement = mysqli_prepare($con, "INSERT INTO User(name, age, username, password) VALUES (?,?,?,?)");
	mysqli_stmt_bind_param($statement,"siss", $name, $age, $username, $password );
	mysqli_stmt_execute($statement);

	mysqli_stmt_close($statement);



	mysqli_close($con);


?>