parser grammar ArcanumParser;

options { tokenVocab = ArcanumLexer; }

program
    : stmt ;

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
    : var									#varDesignator
    | call									#callDesignator
    ;

call
    : fct=var args=params					#callWithParams
    | fct=var '(' args=params ')'			#callWithParams
    | fct=var '(' ')'						#callWithoutParams
    ;

var
    : ID
    ;

params
    : expr (',' expr)*
    ;
