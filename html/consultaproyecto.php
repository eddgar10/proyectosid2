<!DOCTYPE html>
<html>
<head>
	<title>CONSULTA TIEMPOS TRABAJADOS DE PROYECTOS</title>
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
<td><font face="verdana"><b>empleado</b></font></td>
<td><font face="verdana"><b>fecha</b></font></td>
<td><font face="verdana"><b>Hora entrada</b></font></td>
<td><font face="verdana"><b>Hora salida</b></font></td>
<td><font face="verdana"><b>jornada</b></font></td>
    </tr>
	<?php
    
		$conexion=mysqli_connect("localhost","root","","aft") or
			die("Problemas con la conexion");
	
        
		$registros=mysqli_query($conexion,"select * from agentes where proyecto ='$_POST[idproyecto]'") or
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
	    $row['inicio'] . "</font></td>";
            
            echo "<tr1><td width=\"12%\"><font face=\"verdana\">" . 
	    $row['fin'] . "</font></td>";
            
            echo "<tr1><td width=\"12%\"><font face=\"verdana\">" . 
	    $row['trabajado'] . "</font></td>";
            
            
		}
		mysqli_close($conexion);
	?>
<a href="exportar-consulta-proyecto.php"> Generar reporte en Excel</a>

</body>
</html>

