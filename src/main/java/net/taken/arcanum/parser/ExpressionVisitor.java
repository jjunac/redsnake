package net.taken.arcanum.parser;

import net.taken.arcacum.parser.ArcanumParser;
import net.taken.arcacum.parser.ArcanumParserBaseVisitor;

public class ExpressionVisitor extends ArcanumParserBaseVisitor<Integer> {

    @Override
    public Integer visitNumber(ArcanumParser.NumberContext ctx) {
        return Integer.valueOf(ctx.NUMBER().getText());
    }

    @Override
    public Integer visitBinaryExpr(ArcanumParser.BinaryExprContext ctx) {
        int l = visit(ctx.left);
        int r = visit(ctx.right);
        switch (ctx.op.getType()) {
            case ArcanumParser.PLUS: return l + r;
            case ArcanumParser.MINUS: return l - r;
            case ArcanumParser.MULT: return l * r;
            case ArcanumParser.DIV: return l / r;
            default: throw new IllegalArgumentException("Unknown operator " + ctx.op);
        }
    }

}
