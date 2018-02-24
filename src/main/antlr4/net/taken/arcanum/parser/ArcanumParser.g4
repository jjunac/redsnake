parser grammar ArcanumParser;

options { tokenVocab = ArcanumLexer; }

program
    : (stmt)* ;

stmt
    : expr ENDL
    | ENDL
    ;

expr
    : INT                                   #int

    | <assoc=right> l=expr op=POW r=expr    #binaryExpr
    | l=expr op=('*'|'/'|'%') r=expr        #binaryExpr
    | l=expr op=('+'|'-') r=expr            #binaryExpr

    | op='-' e=expr                         #unaryExpr

    | designator                            #designatorExpr

    | var '=' expr                          #assignment

    ;

designator
    : var
    | call
    ;

call
    : var '(' params ')'
    | var params
    ;

var
    : ID
    ;

params
    : (expr (',' expr)*)?
    ;
