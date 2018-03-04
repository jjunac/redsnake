package net.taken.arcanum.parser;

import net.taken.arcanum.domain.ArcaEnvironment;
import net.taken.arcanum.domain.ArcaInteger;
import net.taken.arcanum.domain.ArcaObject;
import org.antlr.v4.runtime.tree.ParseTree;

import static net.taken.arcanum.parser.ArcanumParser.*;

public class ExpressionVisitor extends ArcanumAbstractVisitor {

    public ExpressionVisitor(ArcanumVisitor arcanumVisitor) {
        super(arcanumVisitor);
    }

    @Override
    public ArcaInteger visitInt(ArcanumParser.IntContext ctx) {
        return new ArcaInteger(Integer.valueOf(ctx.getText()));
    }

    @Override
    public ArcaObject visitBinaryExpr(ArcanumParser.BinaryExprContext ctx) {
        // FIXME: for now they only are integer
        int l = ((ArcaInteger)visit(ctx.l)).getValue();
        int r = ((ArcaInteger)visit(ctx.r)).getValue();
        int res;
        switch (ctx.op.getType()) {
            case PLUS:
                res = l + r;
                break;
            case MINUS:
                res = l - r;
                break;
            case MULT:
                res = l * r;
                break;
            case DIV:
                res = l / r;
                break;
            case MOD:
                res = l % r;
                break;
            case POW:
                res = (int) Math.pow(l, r);
                break;
            default: throw new IllegalArgumentException("Unknown operator " + ctx.op);
        }
        return new ArcaInteger(res);
    }

    @Override
    public ArcaObject visitUnaryExpr(UnaryExprContext ctx) {
        int e = ((ArcaInteger)visit(ctx.e)).getValue();
        switch (ctx.op.getType()) {
            case MINUS: return new ArcaInteger(-e);
            default: throw new IllegalArgumentException("Unknown operator " + ctx.op);
        }
    }

    @Override
    public ArcaObject visitAssignment(AssignmentContext ctx) {
        ArcaObject value = visit(ctx.expr());
        environment.putVariable(visitVar(ctx.var()), value);
        return value;
    }

    @Override
    public ArcaObject visitParenExpr(ParenExprContext ctx) {
        return visit(ctx.expr());
    }

}
