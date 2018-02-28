package net.taken.arcanum;

import net.taken.arcanum.domain.ArcaObject;
import net.taken.arcanum.parser.ArcanumLexer;
import net.taken.arcanum.parser.ArcanumParser;
import net.taken.arcanum.parser.ArcanumVisitor;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.apache.commons.cli.*;

import java.io.*;
import java.nio.Buffer;

public class Arcanum {

    private static final String VERSION = "0.1a";

    Options options;
    CommandLineParser cliParser;
    ArcanumVisitor arcanumVisitor;

    public Arcanum(String[] args) throws ParseException {
        initOptions();
        cliParser = new DefaultParser();
        CommandLine cmd = cliParser.parse(options, args);
        if (cmd.hasOption("h")) {
            printHelp();
            System.exit(0);
        }

        arcanumVisitor = new ArcanumVisitor();
        if (cmd.getArgList().size() == 0) {
            lauchShell();
        }
    }

    private void initOptions() {
        options = new Options();
        options.addOption("h", "help", false, "show help.");
    }

    private void printHelp() {
        HelpFormatter formater = new HelpFormatter();
        String usage = "arcanum [options] [file]";
        String header = "Arcanum is a scripting language inspired by Ruby and Python.\n\nOptions:";
        String footer = "\nPlease report issues at https://github.com/Taken0711/arcanum";
        formater.printHelp(usage, header, options, footer, false);
    }

    private void lauchShell() {
        System.out.println(String.format("Arcanum [version %s]", VERSION));
        try {
            while (true) {
                System.out.print("arca> ");
                BufferedReader is = new BufferedReader(new InputStreamReader(System.in));
                ArcaObject res = parseProgram(CharStreams.fromString(is.readLine()));
                if(!res.isNull()) {
                    System.out.println(res);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public ArcaObject parseProgram(CharStream inputStream) {
        ArcanumLexer arcanumLexer = new ArcanumLexer(inputStream);
        TokenStream commonTokenStream = new CommonTokenStream(arcanumLexer);
        ArcanumParser parser = new ArcanumParser(commonTokenStream);
        ParseTree t = parser.program();
        return arcanumVisitor.visit(t);
    }

    public static void main(String[] args) throws ParseException {
        new Arcanum(args);
    }

}
