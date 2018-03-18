package net.taken.arcanum;

import net.taken.arcanum.lang.ArcaEnvironment;
import net.taken.arcanum.lang.ArcaObject;
import net.taken.arcanum.parser.ArcanumLexer;
import net.taken.arcanum.parser.ArcanumParser;
import net.taken.arcanum.parser.visitors.ASTBuilder;
import net.taken.arcanum.tree.Program;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.apache.commons.cli.*;

import java.io.*;

public class Arcanum {

    private static final String VERSION = "0.1a";

    Options options;
    CommandLineParser cliParser;
    ASTBuilder astBuilder;
    ArcaEnvironment arcaEnvironment;

    public Arcanum(String[] args) throws ParseException {
        initOptions();
        cliParser = new DefaultParser();
        astBuilder = new ASTBuilder();
        arcaEnvironment = new ArcaEnvironment();

        CommandLine cmd = cliParser.parse(options, args);
        if (cmd.hasOption("h")) {
            printHelp();
            System.exit(0);
        }

        if (cmd.getArgList().size() == 0) {
            launchShell();
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

    private void launchShell() {
        System.out.println(String.format("Arcanum [version %s]", VERSION));
        try {
            while (true) {
                System.out.print("arca> ");
                BufferedReader is = new BufferedReader(new InputStreamReader(System.in));
                ArcaObject res = buildAST(CharStreams.fromString(is.readLine())).execute(arcaEnvironment);
                if(!res.isNull()) {
                    System.out.println("=> " + res.tos().getValue());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public Program buildAST(CharStream inputStream) {
        ArcanumLexer arcanumLexer = new ArcanumLexer(inputStream);
        TokenStream commonTokenStream = new CommonTokenStream(arcanumLexer);
        ArcanumParser parser = new ArcanumParser(commonTokenStream);
        ParseTree t = parser.program();
        return (Program) astBuilder.visit(t);
    }

    public static void main(String[] args) throws ParseException {
        new Arcanum(args);
    }

}
