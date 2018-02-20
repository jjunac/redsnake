parser grammar ArcanumParser;

options { tokenVocab = ArcanumLexer; }

expression
    : expression operator expression EOF
    | NUMBER
    ;

operator
    : PLUS
    | MINUS
    ;
