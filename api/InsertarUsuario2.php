<?php
$hostname_localhost="localhost";
$database_localhost="bdusuario";
$username_localhost="root";
$password_localhost="";

$json=array();

if (isset($_GET["id"]) && isset($_GET["nombres"]) && isset($_GET["apellidos"])){        
        $id=$_GET["id"];
        $nombres=$_GET["nombres"];
        $apellidos=$_GET["apellidos"];
        $idgenero=$_GET["idgenero"];
        $idregion=$_GET["idregion"];
        $idespecialidad=$_GET["idespecialidad"];

        $conexion=mysqli_connect($hostname_localhost,$username_localhost,$password_localhost,$database_localhost);
        
        $insertar= "INSERT INTO usuario (id, nombres, apellidos, idgenero, idregion, idespecialidad)
        VALUES ( '{$id}', '{$nombres}', '{$apellidos}','{$idgenero}','{$idregion}', '{$idespecialidad}' )";
        
        $resultado=mysqli_query($conexion,$insertar);

        if($resultado){
            $consulta = "select * from usuario where id = '{$id}'";
            $resultado=mysqli_query($conexion, $consulta);
            if ($registro=mysqli_fetch_array($resultado)){
                $json["usuario"][]=$registro;
            }
            mysqli_close($conexion);
            header('Content-Type: application/json');
            echo json_encode($json);

        }
        else{
             $resultado["id"]=0;
             $resultado["nombres"]="No se logró registrar";   
             $resultado["apellidos"]="No se logró registrar";   
             $json["usuario"][]=$resultado;
             echo json_decode($json);

        }
}
else{
    $resultado["id"]=0;
    $resultado["nombres"]="Ws Error";   
    $resultado["apellidos"]="Ws error";   
    $json["usuario"][]=$resultado;
    echo json_decode($json);
}
?>
