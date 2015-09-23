<?php 
	$con=mysqli_connect("mysql14.000webhost.com","a4107973_filip","IsakAsimov1943","a4107973_filip");




	$streetName = $_POST["streetName"];
	$streetNumber = $_POST["streetNumber"];
	$floorNumber = $_POST["floorNumber"];
	
	



	$statement = mysqli_prepare($con, "INSERT INTO Address(street_name, street_num, floor_num) VALUES (?,?,?)");
	mysqli_stmt_bind_param($statement,"sii", $streetName, $streetNumber, $floorNumber);
	mysqli_stmt_execute($statement);

	mysqli_stmt_close($statement);



	mysqli_close($con);


?>