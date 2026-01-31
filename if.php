<?php

$a = 10;
$b = $a - 1;

if ($a > $b){
    echo "Sim";
}
elseif ($b % 2 == 0){
    echo "B é par";
}
else{
    echo "Não";
}
echo "<br/>";

echo $a < $b ? "Sim" : "Não";
