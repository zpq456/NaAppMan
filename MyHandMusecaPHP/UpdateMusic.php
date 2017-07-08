<?php
	$message = $_POST["message"];
	$id = $_POST["id"];

	$query = "UPDATE musiclist set ". $message;
	$query = $query . "where Tagid = '" . $id . "'";

	$enc = mb_detect_encoding($query, array("UTF-8","EUC-KR","SJIS"));
	if($query != "UTF-8"){
		$query = iconv ($enc,"UTF-8" , $query );
	}

	$conn = mysqli_connect("localhost","root","autoset","my_museca_db");
	mysqli_query($conn, $query);

	mysqli_close($conn);

?>