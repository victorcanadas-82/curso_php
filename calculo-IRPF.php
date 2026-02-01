<?php

function calcIRPF($salario, $dependentes)
{

    $deducaoPorDependente = 189.59;
    $salarioBase = $salario - ($dependentes * $deducaoPorDependente);

    if ($salarioBase <= 1903.98) {
        return 0;
    } elseif ($salarioBase <= 2826.65) {
        return ($salarioBase * 0.075) - 142.80;
    } elseif ($salarioBase <= 3751.05) {
        return ($salarioBase * 0.15) - 354.80;
    } elseif ($salarioBase <= 4664.68) {
        return ($salarioBase * 0.225) - 636.13;
    } else {
        return ($salarioBase * 0.275) - 869.36;
    }
}

echo calcIRPF(4000, 3);