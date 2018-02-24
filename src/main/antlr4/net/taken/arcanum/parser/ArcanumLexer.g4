lexer grammar ArcanumLexer;

INT: DIGIT+ ;
FLOAT
    : DIGIT+ '.' DIGIT*
    | '.' DIGIT+
    ;

ID: LETTER (ID | DIGIT | '_')* ;

ENDL: '\r'?'\n';

POW: '**' ;
MULT: '*' ;
DIV: '/' ;
PLUS: '+' ;
MINUS: '-' ;
MOD: '%' ;
EQ: '=' ;

WS: [ \t]+ -> skip ;

// =============================
// Common fragments
// =============================
fragment LETTER: 'a'..'z' | 'A'..'Z' ;
fragment DIGIT: '0'..'9' ;
