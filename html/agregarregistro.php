<!DOCTYPE html>
<html>
<head>
	<title> Registros </title>
</head>
   <style> 
input[type=submit] {
  background-color: #4CAF50;
  border: none;
  color: white;
  padding: 16px 32px;
  text-decoration: none;
  margin: 4px 2px;
  cursor: pointer;
}
input[type=text] {
  
  padding: 12px 5px;
  margin: 8px 0;
  box-sizing: border-box;
  border: 2px solid red;
  border-radius: 4px;
    }
    div {
  border-radius: 5px;
  background-color: #f2f2f2;
  padding: 20px;
}
</style>
<body>
	<form method="post" action="guardardatos.php">
	<label> PROYECTO</label><br>
	<input type="text" name="proyecto" required/><br>
        <label>USUARIO</label><br>
	<input type="text" name="usuario" required/><br>
        <label>CONTRASEÑA</label><br>
	<input type="text" name="contra" required/><br>
	
	<input type="submit" value="Guardar"/>
	<!--<input type="submit" value="Eliminar"/> -->
	</form>
	<br>

	<br>
	
<a href="index.php">menu</a>
</body>
</html>