<?php

$leitura = 20;
$eof = 250;
while($leitura <= $eof){
    if($leitura == 80){
        echo "Saindo do laço";
        break;
    }
$leitura += 20;
echo $leitura."<br/>";    

}
echo "<br/>";
echo "Fora do while";

for ($x = 1; $x <= 10; $x++){
    for($y = 1; $y <= 10; $y++){
        if($x * $y > 25){
            break 2;
        } 
        echo "$x x $y = ". ($x * $y);
        echo "<br/>";
    }
}