  <?php 
  $con=mysqli_connect("mysql14.000webhost.com","a4107973_filip","IsakAsimov1943","a4107973_filip");

 

  $statement = mysql_query(" SELECT SUM(ver_number)  FROM `Payment` ");
  $row = mysql_fetch_assoc($statement);
 


  mysqli_stmt_execute($statement);
  

  mysqli_stmt_store_result($statement);

    

  mysqli_stmt_bind_result($statement,$total);

  $sumTotal = array();
  while (mysqli_stmt_fetch($statement)){
   
    $sumTotal[] = $statement;
  }

  echo json_encode($sumTotal);
  mysqli_stmt_close($statement);  

  mysqli_close($con);

  ?>