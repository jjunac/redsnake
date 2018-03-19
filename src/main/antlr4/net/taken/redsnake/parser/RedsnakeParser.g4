parser grammar RedsnakeParser;

options { tokenVocab = RedsnakeLexer; }

program
    : statements EOF
    | EOF
    ;

statements
    : statement? (ENDL statement?)*
    ;

statement
    : expression
    | IF cond=expression thenBody=suite (ELSE elseBody=suite)?
    ;

suite
    : ENDL* statement ENDL*
    | ENDL* '{' statements '}' ENDL*
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
