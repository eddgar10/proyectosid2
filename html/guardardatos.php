<!DOCTYPE html>
<html>
<head>
	<title> Guardar </title>
</head>
<body>
<?php
	
$link = mysqli_connect("localhost","root","","aft");
 
// Check connection
if($link === false){
    die("ERROR: Could not connect. " . mysqli_connect_error());
}
 
// Attempt insert query execution
$sql = "INSERT INTO usuarios (proyecto, usr,pswd) VALUES ('$_POST[proyecto]','$_POST[usuario]','$_POST[contra]')";
if(mysqli_query($link, $sql)){
    echo "Usuario agregado a la campaña, clic en Menú para regresars.";
} else{
    echo "ERROR: Could not able to execute $sql. " . mysqli_error($link);
}
 
// Close connection
mysqli_close($link);

		
		echo "Usuario registrado";
?>
    
    <a href="index.php">menu</a>
</body>
</html>


