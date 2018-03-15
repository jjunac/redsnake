parser grammar ArcanumParser;

options { tokenVocab = ArcanumLexer; }

program
    : statements EOF
    | EOF
    ;

statements
    : statement (ENDL statement)*
    ;

statement
    : expression
    | /* empty */
    ;

expression
    // Primary type
    : INT                                               #integerLiteral
    | BTRUE                                             #booleanLiteral
    | BFALSE                                            #booleanLiteral
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
    : fct=variable args=parameters				#callWithParameters
    | fct=variable '(' args=parameters          #callWithParameters
    | fct=variable '(' ')'					    #callWithoutParameters
    ;

variable
    : ID
    ;

parameters
    : expression (',' expression)*
    ;
