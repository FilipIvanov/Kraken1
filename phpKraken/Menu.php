<?php 
	$con=mysqli_connect("mysql14.000webhost.com","a4107973_filip","IsakAsimov1943","a4107973_filip");



	

	$menuTypeId = $_POST["menuTypeId"];
	$itemName = $_POST["itemName"];
	$itemSize = $_POST["itemSize"];
	$itemDescription = $_POST["itemDescription"];
	



	$statement = mysqli_prepare($con, "INSERT INTO `Menu`(menu_type_id, item_name, item_size, item_description) VALUES (?,?,?,?)");
	mysqli_stmt_bind_param($statement,"isss", $menuTypeId, $itemName, $itemSize, $itemDescription);
	mysqli_stmt_execute($statement);

	mysqli_stmt_close($statement);



	mysqli_close($con);


?>