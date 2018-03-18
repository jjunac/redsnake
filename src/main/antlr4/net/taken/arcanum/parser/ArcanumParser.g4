parser grammar ArcanumParser;

options { tokenVocab = ArcanumLexer; }

program
    : statements EOF
    | EOF
    ;

statements
    // FIXME: cannot start program with an emptyline
    : statement (ENDL statement?)*
    ;

statement
    : expression
    ;

suite
    : statement
    | '{' statements
    ;

expression
    // Primary type
    : INT                                               #integerLiteral
    | TRUE                                              #booleanLiteral
    | FALSE                                             #booleanLiteral
    | STRING                                            #stringLiteral

    // Operator sort by priority
    | <assoc=right> l=expression op=POW r=expression    #arithmeticBinary
    | op='-' e=expression                               #arithmeticUnary
    | l=expression op=('*'|'/'|'%') r=expression        #arithmeticBinary
    | l=expression op=('+'|'-') r=expression            #arithmeticBinary
    | variable '=' expression                           #assignment

    // Miscellaneous
    | '(' expression ')'                                #subExpression
    | designator                                        #designatorExpression
    ;

designator
    : variable			#variableDesignator
    | call				#callDesignator
    ;

call
    : fct=variable args=parameters
    | fct=variable '(' args=parameters ')'
    | fct=variable '(' ')'
    ;

variable
    : ID
    ;

parameters
    : expression (',' expression)*
    ;
