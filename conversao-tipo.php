<?php

$valor = "3";

$valor *= 2;
$valor *= 2.3;
$novoValor = (string) $valor;   
var_dump(get_debug_type($novoValor));

var_dump($novoValor);

settype($novoValor, "integer");

var_dump($novoValor);

var_dump(get_debug_type($novoValor));
