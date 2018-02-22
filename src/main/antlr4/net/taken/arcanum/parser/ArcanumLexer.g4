lexer grammar ArcanumLexer;

NUMBER
    : ('0'..'9')+ ('.'('0'..'9')+)?
    ;

ENDL
    : '\r\n'
    | '\n'
    ;

POW: '**';
MULT: '*';
DIV: '/';
PLUS: '+';
MINUS: '-';
MOD: '%';
