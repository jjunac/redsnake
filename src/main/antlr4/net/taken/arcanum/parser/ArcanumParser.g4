parser grammar ArcanumParser;

options { tokenVocab = ArcanumLexer; }

program
    : statementList EOF
    | EOF
    ;

statementList
    : statement (ENDL statement)*
    ;

statement
    : s=expression      #nonEmptyStatement
    | /* empty */       #emptyStatement
    ;

expression
    // Primary type
    : INT                                               #integerLiteral
    | BTRUE                                             #booleanLiteral
    | BFALSE                                            #booleanLiteral
    | STRING                                            #stringLiteral

    // Operator sort by priority
    | <assoc=right> l=expression op=POW r=expression    #binaryExpression
    | op='-' e=expression                               #unaryExpression
    | l=expression op=('*'|'/'|'%') r=expression        #binaryExpression
    | l=expression op=('+'|'-') r=expression            #binaryExpression
    | variable '=' expression                           #assignment

    // Miscellaneous
    | '(' expression ')'                                #parenExpression
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
