<?php

$x = 0;

while ((int)date("s") % 2 === 0){
    $x++;
}

var_dump($x);
