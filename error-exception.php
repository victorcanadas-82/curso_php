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

throw new Exception("Essa é minha exceção personalizada!");

echo "Depois da exceção";