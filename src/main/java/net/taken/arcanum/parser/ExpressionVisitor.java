package net.taken.arcanum.parser;

import net.taken.arcacum.parser.ArcanumParser;
import net.taken.arcacum.parser.ArcanumParserBaseVisitor;

import static net.taken.arcacum.parser.ArcanumParser.*;

public class ExpressionVisitor extends ArcanumParserBaseVisitor<Integer> {

    @Override
    public Integer visitNumber(NumberContext ctx) {
        return Integer.valueOf(ctx.NUMBER().getText());
    }

    @Override
    public Integer visitBinaryExpr(BinaryExprContext ctx) {
        int l = visit(ctx.l);
        int r = visit(ctx.r);
        switch (ctx.op.getType()) {
            case PLUS: return l + r;
            case MINUS: return l - r;
            case MULT: return l * r;
            case DIV: return l / r;
            case MOD: return l % r;
            case POW: return (int) Math.pow(l, r);
            default: throw new IllegalArgumentException("Unknown operator " + ctx.op);
        }
    }

    @Override
    public Integer visitUnaryExpr(UnaryExprContext ctx) {
        int e = visit(ctx.e);
        switch (ctx.op.getType()) {
            case MINUS: return - e;
            default: throw new IllegalArgumentException("Unknown operator " + ctx.op);
        }
    }
}
