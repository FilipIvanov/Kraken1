<?php 
	$con=mysqli_connect("mysql14.000webhost.com","a4107973_filip","IsakAsimov1943","a4107973_filip");


	$statement = mysqli_prepare($con, "SELECT * FROM `Customer`");

	
	mysqli_stmt_execute($statement);
	

	mysqli_stmt_store_result($statement);

		

	mysqli_stmt_bind_result($statement,$customerId, $customerName, $customerSurname);

	$customerInfo = array();
	while (mysqli_stmt_fetch($statement)){
		$c = array();
		$c[customerId] = $customerId;
		$c[customerName] = $customerName; 	
		$c[customerSurname] = $customerSurname;
		$customerInfo[] = $c;
	}

	echo json_encode($customerInfo);
	mysqli_stmt_close($statement);	

	mysqli_close($con);


?>