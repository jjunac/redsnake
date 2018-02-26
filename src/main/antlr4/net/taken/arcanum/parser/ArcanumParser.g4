parser grammar ArcanumParser;

options { tokenVocab = ArcanumLexer; }

program
    : (stmt)* ;

stmt
    : expr (ENDL expr)*
    | ENDL
    ;

expr
    // Primary type
    : INT                                   #int

    // Operator sort by priority
    | <assoc=right> l=expr op=POW r=expr    #binaryExpr
    | op='-' e=expr                         #unaryExpr
    | l=expr op=('*'|'/'|'%') r=expr        #binaryExpr
    | l=expr op=('+'|'-') r=expr            #binaryExpr
    | var '=' expr                          #assignment

    // Miscellaneous
    | '(' expr ')'                          #parenExpr
    | designator                            #designatorExpr
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
