<?php
if (PHP_SAPI == 'cli')
	die('Este ejemplo sólo se puede ejecutar desde un navegador Web');

/** Incluye PHPExcel */


require_once dirname(__FILE__) . '/C:/xxampp/php/pear/PHPExcel.php';
#require_once dirname(__FILE__) . 'C:\\xampp\\php\\PEAR\\PHPExcel.php';
#require_once dirname(__FILE__) . '/C:/xampp/php/PEAR/PHPExcel.php';
#require_once __DIR__ . "C:\\xampp\\php\\pear\\PEAR\\PHPExcel.php";


// Crear nuevo objeto PHPExcel
$objPHPExcel = new PHPExcel();

// Propiedades del documento
$objPHPExcel->getProperties()->setCreator("Edgar Espinosa")
							 ->setLastModifiedBy("Edgar Espinosa")
							 ->setTitle("Office 2010 XLSX Documento de prueba")
							 ->setSubject("Office 2010 XLSX Documento de prueba")
							 ->setDescription("Documento de prueba para Office 2010 XLSX, generado usando clases de PHP.")
							 ->setKeywords("office 2010 openxml php")
							 ->setCategory("Archivo con resultado de prueba");



// Combino las celdas desde A1 hasta E1
$objPHPExcel->setActiveSheetIndex(0)->mergeCells('A1:E1');

$objPHPExcel->setActiveSheetIndex(0)
            ->setCellValue('A1', 'CONSULTA POR PROYECTO')
            ->setCellValue('A2', 'PROYECTO')
            ->setCellValue('B2', 'EMPLEADO')
            ->setCellValue('C2', 'ACTIVIDAD')
			->setCellValue('D2', 'VIATICOS')
			->setCellValue('E2', 'RECURSOS-FISICOS')
            ->setCellValue('F2', 'COSTO-DEPRECIABLE')
            ->setCellValue('G2', 'ENTREGABLES')
            ->setCellValue('H2', 'DIAS')
            ->setCellValue('I2', 'FECHA')
            ->setCellValue('J2', 'INICIO')
            ->setCellValue('K2', 'FIN')
            ->setCellValue('L2', 'TRABAJADO')
            ->setCellValue('M2', 'COSTO-HORA-HOMBRE');
			
// Fuente de la primera fila en negrita
$boldArray = array('font' => array('bold' => true,),'alignment' => array('horizontal' => PHPExcel_Style_Alignment::HORIZONTAL_CENTER));

$objPHPExcel->getActiveSheet()->getStyle('A1:E2')->applyFromArray($boldArray);		

	
			
//Ancho de las columnas
$objPHPExcel->getActiveSheet()->getColumnDimension('A')->setWidth(8);	
$objPHPExcel->getActiveSheet()->getColumnDimension('B')->setWidth(30);	
$objPHPExcel->getActiveSheet()->getColumnDimension('C')->setWidth(15);	
$objPHPExcel->getActiveSheet()->getColumnDimension('D')->setWidth(20);	
$objPHPExcel->getActiveSheet()->getColumnDimension('E')->setWidth(15);			
$objPHPExcel->getActiveSheet()->getColumnDimension('F')->setWidth(30);	
$objPHPExcel->getActiveSheet()->getColumnDimension('G')->setWidth(30);	
$objPHPExcel->getActiveSheet()->getColumnDimension('H')->setWidth(30);	
$objPHPExcel->getActiveSheet()->getColumnDimension('I')->setWidth(30);	
$objPHPExcel->getActiveSheet()->getColumnDimension('J')->setWidth(30);	
$objPHPExcel->getActiveSheet()->getColumnDimension('K')->setWidth(30);	
$objPHPExcel->getActiveSheet()->getColumnDimension('L')->setWidth(30);	
$objPHPExcel->getActiveSheet()->getColumnDimension('M')->setWidth(30);	

/*Extraer datos de MYSQL*/
	# conectare la base de datos

	
		$registros=mysqli_query($conexion,"select * from conexionn where proyecto ='$_POST[idproyecto]' and empleado ='$_POST[empleado]' and fecha between '$_POST[fechainicio]' AND '$_POST[fechafin]'") or
			die("Problema en select:".mysqli_error($conexion));
        


    $con=@mysqli_connect('localhost', 'root', '', 'aft');
    if(!$con){
        die("imposible conectarse: ".mysqli_error($con));
    }
    if (@mysqli_connect_errno()) {
        die("Connect failed: ".mysqli_connect_errno()." : ". mysqli_connect_error());
    }
	$sql="select * from conexionn where proyecto ='$_POST[idproyecto]'"; 
	$query=mysqli_query($con,$sql);
	$cel=3;//Numero de fila donde empezara a crear  el reporte
	while ($row=mysqli_fetch_array($query)){
		$v1=$row['proyecto'];
		$v2=$row['empleado'];
		$v3=$row['actividad'];
		$v4=$row['viaticos'];
		$v5=$row['recursos-fisicos'];
        $v6=$row['costo-depreciable'];
        $v7=$row['entregables'];
        $v8=$row['dias'];
        $v9=$row['fecha'];
        $v10=$row['inicio'];
        $v11=$row['fin'];
        $v12=$row['trabajado'];
        $v13=$row['costo-hora-hombre'];
		
			$a="A".$cel;
			$b="B".$cel;
			$c="C".$cel;
			$d="D".$cel;
			$e="E".$cel;
            $f="F".$cel;
            $g="G".$cel;
            $h="H".$cel;
            $i="I".$cel;
            $j="J".$cel;
            $k="K".$cel;
            $l="L".$cel;
            $m="M".$cel;
			// Agregar datos
			$objPHPExcel->setActiveSheetIndex(0)
            ->setCellValue($a, $v1)
            ->setCellValue($b, $v2)
            ->setCellValue($c, $v3)
            ->setCellValue($d, $v4)
			->setCellValue($e, $v5)
            ->setCellValue($f, $v6)
            ->setCellValue($g, $v7)
            ->setCellValue($h, $v8)
            ->setCellValue($i, $v9)
            ->setCellValue($j, $v10)
            ->setCellValue($k, $v11)
            ->setCellValue($l, $v12)
            ->setCellValue($m, $v13);
			
	$cel+=1;
	}

/*Fin extracion de datos MYSQL*/
$rango="A2:$e";
$styleArray = array('font' => array( 'name' => 'Arial','size' => 10),
'borders'=>array('allborders'=>array('style'=> PHPExcel_Style_Border::BORDER_THIN,'color'=>array('argb' => 'FFF')))
);
$objPHPExcel->getActiveSheet()->getStyle($rango)->applyFromArray($styleArray);
// Cambiar el nombre de hoja de cálculo
$objPHPExcel->getActiveSheet()->setTitle('Reporte de proyecto');


// Establecer índice de hoja activa a la primera hoja , por lo que Excel abre esto como la primera hoja
$objPHPExcel->setActiveSheetIndex(0);


// Redirigir la salida al navegador web de un cliente ( Excel5 )
header('Content-Type: application/vnd.ms-excel');
header('Content-Disposition: attachment;filename="reporte.xls"');
header('Cache-Control: max-age=0');
// Si usted está sirviendo a IE 9 , a continuación, puede ser necesaria la siguiente
header('Cache-Control: max-age=1');

// Si usted está sirviendo a IE a través de SSL , a continuación, puede ser necesaria la siguiente
header ('Expires: Mon, 26 Jul 1997 05:00:00 GMT'); // Date in the past
header ('Last-Modified: '.gmdate('D, d M Y H:i:s').' GMT'); // always modified
header ('Cache-Control: cache, must-revalidate'); // HTTP/1.1
header ('Pragma: public'); // HTTP/1.0

$objWriter = PHPExcel_IOFactory::createWriter($objPHPExcel, 'Excel5');
$objWriter->save('php://output');
exit;