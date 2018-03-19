lexer grammar RedsnakeLexer;

// =============================
// Common tokens
// =============================

ENDL: '\r'?'\n';

COMMA: ',' ;

L_PAREN: '(' ;
R_PAREN: ')' ;

L_BRACK: '{' ;
R_BRACK: '}' ;

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

TRUE: 'true';
FALSE: 'false';

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
// Keywords
// =============================

IF: 'if';
ELSE: 'else';

// =============================
// Identifiers
// =============================

ID: LETTER (ID | DIGIT | '_')* ;

// =============================
// Common fragments
// =============================

fragment LETTER: 'a'..'z' | 'A'..'Z' ;
fragment DIGIT: '0'..'9' ;
fragment ESC: '\\' [btnr"'\\] ; // \b, \t, \n, \r, \", \', \\
