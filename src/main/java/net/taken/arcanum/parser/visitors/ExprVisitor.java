package net.taken.arcanum.parser.visitors;

import net.taken.arcanum.lang.ArcaInteger;
import net.taken.arcanum.lang.ArcaObject;
import net.taken.arcanum.lang.ArcaString;
import net.taken.arcanum.parser.ArcanumParser;

import static net.taken.arcanum.parser.ArcanumParser.*;

public class ExprVisitor extends ArcanumAbstractVisitor {

    public ExprVisitor(ArcanumVisitor arcanumVisitor) {
        super(arcanumVisitor);
    }

    @Override
    public ArcaInteger visitInt(IntContext ctx) {
        return new ArcaInteger(Integer.valueOf(ctx.getText()));
    }

    @Override
    public ArcaObject visitString(StringContext ctx) {
        return new ArcaString(ctx.getText());
    }

    @Override
    public ArcaObject visitBinaryExpr(BinaryExprContext ctx) {
        // FIXME: for now they only are integer
        ArcaObject l = visit(ctx.l);
        ArcaObject r = visit(ctx.r);
        switch (ctx.op.getType()) {
            case PLUS:
                return l.plus(r);
            case MINUS:
                return l.minus(r);
            case MULT:
                return l.multiply(r);
            case DIV:
                return l.divide(r);
            case MOD:
                return l.modulo(r);
            case POW:
                return l.power(r);
            default: throw new IllegalArgumentException("Unknown operator " + ctx.op);
        }
    }

    @Override
    public ArcaObject visitUnaryExpr(UnaryExprContext ctx) {
        ArcaObject e = visit(ctx.e);
        switch (ctx.op.getType()) {
            case MINUS:
                return e.uminus();
            default: throw new IllegalArgumentException("Unknown operator " + ctx.op);
        }
    }

    @Override
    public ArcaObject visitAssignment(AssignmentContext ctx) {
        ArcaObject value = visit(ctx.expr());
        environment.putVariable(arcanumVisitor.visitVar(ctx.var()), value);
        return value;
    }

    @Override
    public ArcaObject visitDesignatorExpr(DesignatorExprContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public ArcaObject visitParenExpr(ParenExprContext ctx) {
        return visit(ctx.expr());
    }

}
