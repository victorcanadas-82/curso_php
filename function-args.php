<?php

$a = 50;

function soma(&$numeroA, $numeroB = 10) {
   
    var_dump($numeroB);
    $numeroA += $numeroB;
    echo $numeroA  . "<br/>";
}

soma($a, 5);
soma(
    numeroB: 30,
    numeroA: $a
);

echo "Agora fora da função: $a" . "<br/>";
