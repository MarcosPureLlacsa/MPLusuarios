<?php
$hostname_localhost="localhost";
$database_localhost="bdusuario";
$username_localhost="root";
$password_localhost="";

$json=array();
    if(isset($_GET["id"])){
        $id=$_GET['id'];
        
        $conexion=mysqli_connect($hostname_localhost,$username_localhost,$password_localhost,$database_localhost);
        
        //reemplazar con un storeProcedure
        $consulta= "select u.id, u.nombres, u.apellidos, g.genero, r.region, e.especialidad from usuario u  
        INNER join genero g on u.idgenero=g.idgenero 
        INNER join region r on u.idregion=r.idregion 
        INNER join especialidad e on u.idespecialidad = e.idespecialidad
        where u.id= '{$id}'";
        
        $resultado=mysqli_query($conexion,$consulta);

        if($registro=mysqli_fetch_array($resultado)){
            $json['usuario'][]=$registro;
        }
        else{
            $resultar["id"]=0;
            $resultar["nombres"]='No registra'; 
            $resultar["apellidos"]='No registra';
            $json['usuario'][]=$resultar;
            }
        mysqli_close($conexion);

    

        echo json_encode($json);
    }
    else{
            $resultar["success"]=0;
            $resultar["message"]='WS no retorna el id';
            $json['usuario'][]=$resultar;
            
            echo json_encode($json);
        }
?>


