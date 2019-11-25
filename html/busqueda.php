<!DOCTYPE html>
<html>
<head>
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
	<title>CONSULTAS DE TIEMPOS POR USUARIO O PROYECTOS</title>
</head>
<body>
    
    
<div>
    <form action=consultaproyecto.php method="post">
        
    <label for="idproyecto">ID proyecto</label>
    <input type="text" id="fname" name="idproyecto" placeholder="ID proyecto...">
    
    <input type="submit" value"buscarproyecto" name="buscar proyecto">
</form>
    </div>
    
    
<div>
    <form action=consultaempleado.php method="post">
        
    <label for="idproyecto">Nombre de Empleado</label>
    <input type="text" id="fname" name="empleado" placeholder="nombre del empleado...">
        
    <input type="submit" value"buscarproyecto" name="buscar proyecto">
</form>
    </div>
    
    
<div>
    <form action=consultaproyectoyfecha.php method="post">
        
    <label for="idproyecto">ID proyecto</label>
    <input type="text" id="fname" name="idproyecto" placeholder="ID proyecto...">
    
    <label for="fecha inicial">Fecha Inicial</label>
    <input type="text" id="fname" name="fechainicio" placeholder="Fecha inicial...">                                        
        
    <label for="fecha final">Fecha Final</label>
    <input type="text" id="fname" name="fechafin" placeholder="Fecha final...">                                        
    
    <input type="submit" value"buscarproyecto" name="buscar proyecto">
</form>
    </div>

<div>
    <form action=consultaempleadoyfecha.php method="post">
        
    <label for="idproyecto">Nombre de Empleado</label>
    <input type="text" id="fname" name="empleado" placeholder="nombre del empleado...">
        
    <label for="idproyecto">Nombre proyecto</label>
    <input type="text" id="fname" name="idproyecto" placeholder="nombre del proyecto...">
        
    <label for="idproyecto">Fecha inicio</label>
    <input type="text" id="fname" name="fechainicio" placeholder="fecha inicial...">
        
    <label for="idproyecto">Fecha final</label>
    <input type="text" id="fname" name="fechafin" placeholder="fechafinal...">
        
    <input type="submit" value"buscarproyecto" name="buscar proyecto">
</form>
    </div>
    
</form>

</body>
</html>

