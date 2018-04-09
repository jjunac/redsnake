package net.taken.redsnake;

import net.taken.redsnake.interpretor.RedsEnvironment;
import net.taken.redsnake.lang.RedsObject;
import net.taken.redsnake.parser.RedsnakeLexer;
import net.taken.redsnake.parser.RedsnakeParser;
import net.taken.redsnake.parser.visitors.ASTBuilder;
import net.taken.redsnake.tree.statements.Statement;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.apache.commons.cli.*;

import java.io.*;

public class Redsnake {

    private static final String VERSION = "0.1a";

    Options options;
    CommandLineParser cliParser;
    ASTBuilder astBuilder;
    RedsEnvironment redsEnvironment;

    public Redsnake(String[] args) throws ParseException {
        initOptions();
        cliParser = new DefaultParser();
        astBuilder = new ASTBuilder();
        redsEnvironment = new RedsEnvironment();

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
        String usage = "redsnake [options] [file]";
        String header = "Redsnake is a scripting language inspired by Ruby and Python.\n\nOptions:";
        String footer = "\nPlease report issues at https://github.com/Taken0711/redsnake";
        formater.printHelp(usage, header, options, footer, false);
    }

    private void launchShell() {
        System.out.println(String.format("Redsnake [version %s]", VERSION));
        try {
            while (true) {
                System.out.print("reds> ");
                BufferedReader is = new BufferedReader(new InputStreamReader(System.in));
                RedsObject res = buildAST(CharStreams.fromString(is.readLine())).execute(redsEnvironment).getValue();
                // TODO get the type and do a test on it, cleaner
                if(!res.isNull()) {
                    System.out.println("=> " + res.tos().getValue());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public Statement buildAST(CharStream inputStream) {
        RedsnakeLexer redsnakeLexer = new RedsnakeLexer(inputStream);
        TokenStream commonTokenStream = new CommonTokenStream(redsnakeLexer);
        RedsnakeParser parser = new RedsnakeParser(commonTokenStream);
        ParseTree t = parser.statement();
        return (Statement) astBuilder.visit(t);
    }

    public static void main(String[] args) throws ParseException {
        new Redsnake(args);
    }

}
