<?php
include "conexao.php";

// Isso permite que você teste tanto pelo App (POST) quanto pelo Navegador (GET)
$email = isset($_REQUEST['email']) ? $_REQUEST['email'] : '';
$senha = isset($_REQUEST['senha']) ? $_REQUEST['senha'] : '';

if(!empty($email) && !empty($senha)){
    
    // Proteção básica contra SQL Injection para iniciantes
    $email = mysqli_real_escape_string($con, $email);
    $senha = mysqli_real_escape_string($con, $senha);

    $comando = "SELECT * FROM tab WHERE login = '$email' AND senha = '$senha'";
    $resulta = mysqli_query($con, $comando);

    if (mysqli_num_rows($resulta) > 0) {
        $r = mysqli_fetch_assoc($resulta);
        // Retorna o objeto JSON que o seu Java espera
        $retorno = array("status" => "ok", "login" => $r['login']);
    } else {
        $retorno = array("status" => "erro");
    }
} else {
    // Se você acessar o link direto sem passar parâmetros, cairá aqui
    $retorno = array("status" => "vazio", "mensagem" => "Envie o email e senha");
}

header('Content-Type: application/json');
echo json_encode($retorno);
mysqli_close($con);
?>