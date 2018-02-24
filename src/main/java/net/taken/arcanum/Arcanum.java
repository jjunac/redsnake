package net.taken.arcanum;

import net.taken.arcanum.domain.ArcaObject;
import net.taken.arcanum.parser.ArcanumLexer;
import net.taken.arcanum.parser.ArcanumParser;
import net.taken.arcanum.parser.ArcanumVisitor;
import net.taken.arcanum.parser.ExpressionVisitor;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.IOException;

public class Arcanum {

    public static void main(String[] args) throws IOException {
        CharStream inputStream = CharStreams.fromString("a=5\na\n");
        ArcanumLexer arcanumLexer = new ArcanumLexer(inputStream);
        TokenStream commonTokenStream = new CommonTokenStream(arcanumLexer);
        ArcanumParser parser = new ArcanumParser(commonTokenStream);

        ParseTree t = parser.program();

        ArcaObject res = new ArcanumVisitor().visit(t);
    }

}
