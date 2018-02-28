package net.taken.arcanum.parser;

import net.taken.arcanum.domain.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static net.taken.arcanum.parser.ArcanumParser.*;

public class ArcanumVisitor extends ArcanumParserBaseVisitor<ArcaObject> {

    ArcaKernel kernel;
    Map<ArcaString, ArcaObject> variables;
    Map<ArcaString, Function<ArcaList, ArcaObject>> functions;

    public ArcanumVisitor() {
        kernel = new ArcaKernel();
        variables = new HashMap<>();
        functions = new HashMap<>();
        functions.putAll(kernel.getBuiltInFunctions());
    }

    @Override
    public ArcaInteger visitInt(IntContext ctx) {
        return new ArcaInteger(Integer.valueOf(ctx.getText()));
    }

    @Override
    public ArcaObject visitBinaryExpr(BinaryExprContext ctx) {
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
        variables.put(visitVar(ctx.var()), value);
        return value;
    }

    @Override
    public ArcaObject visitParenExpr(ParenExprContext ctx) {
        return visit(ctx.expr());
    }

    @Override
    public ArcaObject visitVarDesignator(VarDesignatorContext ctx) {
        // TODO handle error
        ArcaString var = visitVar(ctx.var());
        ArcaObject res = variables.get(var);
        if (res == null) {
            res = functions.get(var).apply(new ArcaList());
        }
        return res;
    }

    @Override
    public ArcaObject visitCallWithoutParams(CallWithoutParamsContext ctx) {
        return functions.get(visitVar(ctx.fct)).apply(new ArcaList());
    }

    @Override
    public ArcaObject visitCallWithParams(CallWithParamsContext ctx) {
        return functions.get(visitVar(ctx.fct)).apply(visitParams(ctx.args));
    }

    @Override
    public ArcaString visitVar(VarContext ctx) {
        return new ArcaString(ctx.getText());
    }

    @Override
    public ArcaList visitParams(ParamsContext ctx) {
        return new ArcaList(ctx.expr().stream().map(this::visit).collect(Collectors.toList()));
    }
}
