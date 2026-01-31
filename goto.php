<?php
var_dump("Inicio");

goto destino;

Conteudo:

var_dump("Conteudo");

return;

destino:

var_dump("Término");

goto Conteudo;