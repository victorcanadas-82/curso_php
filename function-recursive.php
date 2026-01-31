<?php

function recursivo($a) {

    if ($a < 100) {
        echo $a . " ";
        recursivo(++$a);
    }

}

recursivo(0);

