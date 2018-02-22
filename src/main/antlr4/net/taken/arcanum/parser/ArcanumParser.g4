parser grammar ArcanumParser;

options { tokenVocab = ArcanumLexer; }

expression
    : l=expression op=('*'|'/') r=expression        #binaryExpr
    | l=expression op=('+'|'-') r=expression        #binaryExpr
    | NUMBER                                        #number
    ;

