<?php

/*
 * Tips escalares
 * Booleno(bool)
 * Inteiro(int)
 * Float(float) * Também conhecido por double
 * String(string)
 * 
 * Tipos Compostos
 * Array(array)
 * Object(object)
 * Callable(callable)
 * Iterable(iterable)
 * 
 * Tipos Especiais
 * Resource(resourse)
 * Null(NULL)
 * 
 * Tipos Pseudos
 * Mixed(mixed)
 * Void(void)
 */

$sobrenome = "Ferreira";

$nome1 = "Victor Canadas $sobrenome";
$nome2 = 'Victor Canadas $sobrenome';

$idade = 50.2546;

$vip = false;

$aereas = ["Gol", "Latam", "Azul"];

class Cliente {
    public function __construct(public string $nome){
        echo $this->nome;
    }
}

$victor = new Cliente("Victor Canadas");

$linhasAereas = function(): iterable{
    return ["Gol", "Latam", "Azul"];
};

function executar(mixed $parametro): void {

}

$nulo = NULL;

$arquivo = fopen("./variaveis.php", "a+");
/*
echo $nome1;
echo "<br/>";
echo $nome2;
echo "<br/>";
*/
echo "<br/>";
echo gettype($arquivo);
echo "<br/>";
echo get_debug_type($arquivo);  
echo "<br/>";
//var_dump(is_iterable($linhasAereas()));
//var_dump(is_callable($linhasAereas));



