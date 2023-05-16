<?php
$hostname_localhost="localhost";
$database_localhost="bdusuario";
$username_localhost="root";
$password_localhost="";

$json=array();
if(isset($_GET["tabla"])){
        $tabla=$_GET["tabla"];
        $conexion=mysqli_connect($hostname_localhost,$username_localhost,$password_localhost,$database_localhost);
        $consulta= "select * from $tabla";
        
        $resultado=mysqli_query($conexion,$consulta);

        while($registro=mysqli_fetch_array($resultado)){
            $json[$tabla][]=$registro;
        }

        mysqli_close($conexion);
        //header('Content-Type: aplication/json');
        echo json_encode($json);
    }
?>