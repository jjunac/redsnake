package net.taken.arcanum.parser;

import net.taken.arcacum.parser.ArcanumParser;
import net.taken.arcacum.parser.ArcanumParserBaseVisitor;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.runtime.tree.TerminalNode;

public class ExpressionVisitor extends ArcanumParserBaseVisitor {
    public Object visitExpression(ArcanumParser.ExpressionContext ctx) {
        return null;
    }

    public Object visitOperator(ArcanumParser.OperatorContext ctx) {
        return null;
    }

    public Object visit(ParseTree parseTree) {
        return null;
    }

    public Object visitChildren(RuleNode ruleNode) {
        return null;
    }

    public Object visitTerminal(TerminalNode terminalNode) {
        return null;
    }

    public Object visitErrorNode(ErrorNode errorNode) {
        return null;
    }
}
