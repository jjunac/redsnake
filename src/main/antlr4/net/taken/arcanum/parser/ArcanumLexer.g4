lexer grammar ArcanumLexer;


TRUE: 'true';
FALSE: 'false';

ID: LETTER (ID | DIGIT | '_')* ;

ENDL: '\r'?'\n';

COMMA: ',' ;
L_PAREN: '(' ;
R_PAREN: ')' ;

// =============================
// Common types
// =============================

INT: DIGIT+ ;
FLOAT
    : DIGIT+ '.' DIGIT*
    | '.' DIGIT+
    ;

STRING
    : '"' ( ESC | . )*? '"'     { setText(getText().substring(1, getText().length()-1)); }
    | '\'' ( ESC | . )*? '\''   { setText(getText().substring(1, getText().length()-1)); }
    ;
// =============================
// Operators
// =============================

POW: '**' ;
MULT: '*' ;
DIV: '/' ;
PLUS: '+' ;
MINUS: '-' ;
MOD: '%' ;
ASSIGN: '=' ;

WS: [ \t]+ -> skip ;

// =============================
// Common fragments
// =============================

fragment LETTER: 'a'..'z' | 'A'..'Z' ;
fragment DIGIT: '0'..'9' ;
fragment ESC: '\\' [btnr"'\\] ; // \b, \t, \n, \r, \", \', \\
