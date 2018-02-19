package net.taken.arcanum.parser;

import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

public class Parser {

    ANTLRFileStream input = new ANTLRFileStream(fileName); // a character stream
    ArcanumLexer lex = new ArcanumLexer(input); // transforms characters into tokens
    CommonTokenStream tokens = new CommonTokenStream(lex); // a token stream
    ArcanumParser parser = new ArcanumParser(tokens); // transforms tokens into parse trees
    ParseTree t = parser.expression(); // creates the parse tree from the called rule

}
