<?php 
	$con=mysqli_connect("mysql14.000webhost.com","a4107973_filip","IsakAsimov1943","a4107973_filip");

	$menuId = $_POST["menuId"];
	$orderNumber = $_POST["orderNumber"];
	


	$statement = mysqli_prepare($con, "INSERT INTO `Order`(menu_id,order_number) VALUES (?,?)");
	mysqli_stmt_bind_param($statement,"is", $menuId, $orderNumber );
	mysqli_stmt_execute($statement);

	mysqli_stmt_close($statement);



	mysqli_close($con);


?>