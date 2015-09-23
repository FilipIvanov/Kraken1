<?php 
	$con=mysqli_connect("mysql14.000webhost.com","a4107973_filip","IsakAsimov1943","a4107973_filip");


	

	$customerName = $_POST["customerName"];
	$customerSurname = $_POST["customerSurname"];
	$customerAddress = $_POST["customerAddress"];
	$customerPhone = $_POST["customerPhone"];
	$customerRegular = $_POST["customerRegular"];
	



	$statement = mysqli_prepare($con, "INSERT INTO Customer(customer_name, customer_surname, customer_address, customer_phone,customer_regular) VALUES (?,?,?,?,?)");
	mysqli_stmt_bind_param($statement,"ssssi", $customerName, $customerSurname, $customerAddress, $customerPhone,$customerRegular);
	mysqli_stmt_execute($statement);

	mysqli_stmt_close($statement);



	mysqli_close($con);


?>