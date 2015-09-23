<?php 
	$con=mysqli_connect("mysql14.000webhost.com","a4107973_filip","IsakAsimov1943","a4107973_filip");

	$menuTypeId = $_POST["menuTypeId"];

	$query = "SELECT * FROM Menu where menu_type_id = ?";

	$statement = mysqli_prepare($con, $query);
	mysqli_stmt_bind_param($statement,"i", $menuTypeId);
	
	mysqli_stmt_execute($statement);
	
	mysqli_stmt_store_result($statement);

	mysqli_stmt_bind_result($statement, $menuId, $mtMenyTypeId, $itemName, $itemSize, $itemDescription);

	$menus = array();
	while (mysqli_stmt_fetch($statement)){
		$m = array();
		$m[menuId] = $menuId; 	
		$m[itemName] = $itemName;
		$m[itemSize] = $itemSize;
		$m[itemDescription] = $itemDescription;
		$m[menuTypeId] = $mtMenyTypeId;
		$menus[] = $m;
	}

	echo json_encode($menus);
	mysqli_stmt_close($statement);	

	mysqli_close($con);


?>