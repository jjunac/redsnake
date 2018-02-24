parser grammar ArcanumParser;

options { tokenVocab = ArcanumLexer; }

expression
    : l=expression op=POW r=expression              #binaryExpr
    | op='-' e=expression                           #unaryExpr
    | l=expression op=('*'|'/'|'%') r=expression    #binaryExpr
    | l=expression op=('+'|'-') r=expression        #binaryExpr
    | NUMBER                                        #number
    ;

