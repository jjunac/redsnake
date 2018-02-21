package net.taken.arcanum;

import net.taken.arcacum.parser.ArcanumLexer;
import net.taken.arcacum.parser.ArcanumParser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

public class Arcanum {

    public static void main(String[] args) {
        CharStream inputStream = CharStreams.fromString("1+1");
        ArcanumLexer markupLexer = new ArcanumLexer(inputStream);
        CommonTokenStream commonTokenStream = new CommonTokenStream(markupLexer);
        ArcanumParser parser = new ArcanumParser(commonTokenStream);

        ParseTree t = parser.expression();

        System.out.println(t.getText());
    }

}
