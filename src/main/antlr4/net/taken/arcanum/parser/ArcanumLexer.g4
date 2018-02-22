lexer grammar ArcanumLexer;

NUMBER
    : ('0'..'9')+ ('.'('0'..'9')+)?
    ;

ENDL
    : '\r\n'
    | '\n'
    ;

MULT: '*';
DIV: '/';
PLUS: '+';
MINUS: '-';
