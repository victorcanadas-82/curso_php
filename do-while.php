<?php

$x = 0;

do {
    $x++;
}

while ((int)date("s") % 2 === 0);
    
var_dump($x);
