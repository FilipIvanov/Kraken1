<?php 
	$con=mysqli_connect("mysql14.000webhost.com","a4107973_filip","IsakAsimov1943","a4107973_filip");


	$orderNumber = $_POST["orderNumber"];

	$statement = mysqli_prepare($con, "SELECT m.item_name, m.item_size, m.item_description FROM `Order` o, `Menu` m, `MenuType` mt WHERE o.menu_id = m.menu_id and m.menu_type_id = mt.menu_type_id and o.order_number = ?");

	mysqli_stmt_bind_param($statement, "s", $orderNumber);
	mysqli_stmt_execute($statement);
	

	mysqli_stmt_store_result($statement);
		

	mysqli_stmt_bind_result($statement, $itemName, $itemSize, $itemDescription);

	$order = array();
	while (mysqli_stmt_fetch($statement)){
		$o = array();
		$o[itemName] = $itemName; 	
		$o[itemSize] = $itemSize;
		$o[itemDescription] = $itemDescription;
		$order[] = $o;
	}

	echo json_encode($order);
	mysqli_stmt_close($statement);	

	mysqli_close($con);


?>