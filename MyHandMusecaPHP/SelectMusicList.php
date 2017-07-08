<?php 

	$message = $_POST['message']; //폼에서 입력한 메세지를 받음
	
	$conn = mysqli_connect("localhost","root","autoset","my_museca_db");
	if($message != " ")
		$query = "SELECT musicID, name, released, produce FROM musiclist "; 
		$query = $query . "where ". $message;

	$enc = mb_detect_encoding($query, array("UTF-8","EUC-KR","SJIS"));
	if($query != "UTF-8"){
		$query = iconv ($enc,"UTF-8" , $query );
	}

	if($result= mysqli_query($conn,$query)){
	$row_num = mysqli_num_rows($result);
	echo "{";
	 echo "\"ststus\":\"OK\","; 
	 echo "\"rownum\":\"$row_num\",";
	 echo "\"result\":";	
	  echo "[";	
	   for($i = 0; $i < $row_num; $i++){
	    $row = mysqli_fetch_array($result);
	     echo"{";
	     echo"\"musicID\":\"$row[musicID]\",";
	     echo"\"name\":\"$row[name]\",";
	     echo"\"released\":\"$row[released]\",";
	     echo"\"produce\":\"$row[produce]\"";
	     echo"}";
	     if($i<$row_num-1){
	      echo ",";
	     }
	    }	
	   echo "]";
	  echo "}";
	}
	else{
	 echo "failed to get data from database.";
	}
	
	mysqli_close($conn);
?>