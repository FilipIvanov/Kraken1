<?php 
	$con=mysqli_connect("mysql14.000webhost.com","a4107973_filip","IsakAsimov1943","a4107973_filip");


	$statement = mysqli_prepare($con, "SELECT * FROM MenuType");

	
	mysqli_stmt_execute($statement);
	

	mysqli_stmt_store_result($statement);

		

	mysqli_stmt_bind_result($statement, $menuTypeId, $menuType);

	$menuTypes = array();
	while (mysqli_stmt_fetch($statement)){
		$m = array();
		$m[menuTypeId] = $menuTypeId; 	
		$m[menuType] = $menuType;
		$menuTypes[] = $m;
	}

	echo json_encode($menuTypes);
	mysqli_stmt_close($statement);	

	mysqli_close($con);


?>