<?php

$empresa = function($site, $tel){

    return "https/;//$site<br/>Tel: $tel";
};

echo $empresa("hcode.com.br", "1121497360");

function somar($a, $b, $callback){

    $callback($a + $b);
}

echo somar(2, 3, function($resultado){

    var_dump($resultado);
});
