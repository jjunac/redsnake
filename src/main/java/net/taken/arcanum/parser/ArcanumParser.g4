parser grammar Arcanum;

expression
    : expression operator expression
    | NUMBER
    ;

operator
    : PLUS
    | MINUS
    ;
