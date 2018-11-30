<?php 
	require 'init_db.php';
	
	$type = $_GET['item_type'];
	
	if($type == 'fruits') {		
		$sql = "SELECT * FROM fruits";
		
		//for showing bangla text format
		mysqli_query($conn,'SET CHARACTER SET utf8');
		mysqli_query($conn,"SET SESSION collation_connection ='utf8_general_ci'");
		
		$result = mysqli_query($conn, $sql);
		$response_array_fruits = array();
		
		while($row = mysqli_fetch_array($result)) {
			array_push($response_array_fruits, array('name'=>$row['name'], 'imagepath'=>$row['imagepath'], 'calorie'=>$row['calorie']));
		}
		
		echo json_encode($response_array_fruits, JSON_UNESCAPED_UNICODE);
	}
	else if($type == 'vegetables') {
		$sql = "SELECT * FROM vegetables";
		
		//for showing bangla text format
		mysqli_query($conn,'SET CHARACTER SET utf8');
		mysqli_query($conn,"SET SESSION collation_connection ='utf8_general_ci'");
		
		$result = mysqli_query($conn, $sql);
		$response_array_vegetables = array();
		
		while($row = mysqli_fetch_array($result)) {
			array_push($response_array_vegetables, array('name'=>$row['name'], 'imagepath'=>$row['imagepath'], 'calorie'=>$row['calorie']));
		}
		
		echo json_encode($response_array_vegetables, JSON_UNESCAPED_UNICODE);
	}
	
	mysqli_close($conn);

?>