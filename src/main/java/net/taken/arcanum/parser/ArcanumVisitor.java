package net.taken.arcanum.parser;

import net.taken.arcanum.domain.ArcaInteger;
import net.taken.arcanum.domain.ArcaObject;

import java.util.HashMap;
import java.util.Map;

import static net.taken.arcanum.parser.ArcanumParser.*;

public class ArcanumVisitor extends ArcanumParserBaseVisitor<ArcaObject> {

    private Map<String, ArcaObject> variables = new HashMap<>();

    @Override
    public ArcaObject visitProgram(ProgramContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public ArcaObject visitPrint(PrintContext ctx) {
        System.out.println(variables.get(ctx.ID().getText()).toS());
        return null;
    }

    @Override
    public ArcaObject visitAssign(AssignContext ctx) {
        return variables.put(ctx.ID().getText(), visit(ctx.expression()));
    }

    @Override
    public ArcaObject visitBlank(BlankContext ctx) {
        return null;
    }

    @Override
    public ArcaObject visitBinaryExpr(BinaryExprContext ctx) {
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
    public ArcaObject visitInt(IntContext ctx) {
        return new ArcaInteger(Integer.valueOf(ctx.INT().getText()));
    }
}
