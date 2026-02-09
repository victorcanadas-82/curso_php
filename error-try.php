<?php

function manipuladorDeErro($exception) 
{

   // var_dump($exception);

    echo $exception->getMessage()."<br/>";
    echo $exception->getFile()."<br/>";
    echo $exception->getLine()."<br/>";
    echo $exception->getCode()."<br/>";

}

set_exception_handler("manipuladorDeErro");

try {

    throw new Exception("Essa é minha exceção personalizada!");

} catch(Exception $e) {

    //echo $e->getMessage()."<br/>";

    echo "Temos um erro";
} 

$exibeErro = fn($error) => throw new Exception($error);

echo $exibeErro("Esse é um erro usando arrow function");

echo "Depois da exceção";