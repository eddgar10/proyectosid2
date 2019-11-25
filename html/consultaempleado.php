<!DOCTYPE html>
<html>
<head>
	<title>CONSULTA TIEMPOS TRABAJADOS DE EMPLEADO</title>
</head>
        <style>
#registros {
  font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
  border-collapse: collapse;
  width: 100%;
}

#registros td, #registros th {
  border: 1px solid #ddd;
  padding: 8px;
}

#registros tr:nth-child(even){background-color: #f2f2f2;}

#registros tr:hover {background-color: #ddd;}

#registros th {
  padding-top: 12px;
  padding-bottom: 12px;
  text-align: left;
  background-color: #4CAF50;
  color: white;
}
</style>
<body>
<table border="1" cellspacing=1 cellpadding=2 style="font-size: 8pt">
<table id="registros">
    <tr>
<td><font face="verdana"><b>proyecto</b></font></td>
<td><font face="verdana"><b>usuario</b></font></td>
<td><font face="verdana"><b>fecha</b></font></td>
<td><font face="verdana"><b>duración de la jornada</b></font></td>
    </tr>
	<?php
    
		$conexion=mysqli_connect("localhost","root","","aft") or
			die("Problemas con la conexion");
	
        
		$registros=mysqli_query($conexion,"select * from agentes where empleado ='$_POST[empleado]'") or
			die("Problema en select:".mysqli_error($conexion));
        
		while($row=mysqli_fetch_array($registros))
		{   
            
            echo "<tr><td width=\"12%\"><font face=\"verdana\">" . 
	    $row['proyecto'] . "</font></td>";   
            
            echo "<tr1><td width=\"12%\"><font face=\"verdana\">" . 
	    $row['empleado'] . "</font></td>";
            
            echo "<tr1><td width=\"12%\"><font face=\"verdana\">" . 
	    $row['fecha'] . "</font></td>";
            
            echo "<tr1><td width=\"12%\"><font face=\"verdana\">" . 
	    $row['trabajado'] . "</font></td>";
                        
		}
		mysqli_close($conexion);
	?>
</body>
</html>

