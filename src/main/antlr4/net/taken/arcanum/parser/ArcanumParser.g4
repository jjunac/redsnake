parser grammar ArcanumParser;

options { tokenVocab = ArcanumLexer; }

program
    : (statement)* ;

statement
    : ID ENDL                       #print
    | ID '=' expression ENDL        #assign
    | ENDL                          #blank
    ;

expression
    : <assoc=right> l=expression op=POW r=expression    #binaryExpr
    | op='-' e=expression                               #unaryExpr
    | l=expression op=('*'|'/'|'%') r=expression        #binaryExpr
    | l=expression op=('+'|'-') r=expression            #binaryExpr
    | INT                                               #int
//    | FLOAT                                             #float
    ;

