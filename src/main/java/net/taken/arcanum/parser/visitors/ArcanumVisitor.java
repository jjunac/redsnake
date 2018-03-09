package net.taken.arcanum.parser.visitors;

import net.taken.arcanum.lang.*;
import net.taken.arcanum.parser.ArcanumParser;
import net.taken.arcanum.parser.ArcanumParserBaseVisitor;

import static net.taken.arcanum.parser.ArcanumParser.*;

public class ArcanumVisitor extends ArcanumParserBaseVisitor<ArcaObject> {

    ArcaEnvironment environment;
    ExprVisitor exprVisitor;
    DesignatorVisitor designatorVisitor;
    CallVisitor callVisitor;
    VarVisitor varVisitor;
    ParamsVisitor paramsVisitor;

    public ArcanumVisitor() {
        this(new ArcaEnvironment());
    }

    ArcanumVisitor(ArcaEnvironment environment) {
        this.environment = environment;
        exprVisitor = new ExprVisitor(this);
        designatorVisitor = new DesignatorVisitor(this);
        callVisitor = new CallVisitor(this);
        varVisitor = new VarVisitor(this);
        paramsVisitor = new ParamsVisitor(this);
    }

    @Override
    public ArcaInteger visitInt(IntContext ctx) {
        return exprVisitor.visitInt(ctx);
    }

    @Override
    public ArcaObject visitString(StringContext ctx) {
        return exprVisitor.visitString(ctx);
    }

    @Override
    public ArcaObject visitBoolean(BooleanContext ctx) {
        System.out.println("visiting a boolean");
        return exprVisitor.visitBoolean(ctx);
    }

    @Override
    public ArcaObject visitBinaryExpr(BinaryExprContext ctx) {
        return exprVisitor.visitBinaryExpr(ctx);
    }

    @Override
    public ArcaObject visitUnaryExpr(UnaryExprContext ctx) {
        return exprVisitor.visitUnaryExpr(ctx);
    }

    @Override
    public ArcaObject visitAssignment(AssignmentContext ctx) {
        return exprVisitor.visitAssignment(ctx);
    }

    @Override
    public ArcaObject visitParenExpr(ParenExprContext ctx) {
        return exprVisitor.visitParenExpr(ctx);
    }

    @Override
    public ArcaObject visitCallDesignator(CallDesignatorContext ctx) {
        return designatorVisitor.visitCallDesignator(ctx);
    }

    @Override
    public ArcaObject visitVarDesignator(VarDesignatorContext ctx) {
        return designatorVisitor.visitVarDesignator(ctx);
    }

    @Override
    public ArcaObject visitCallWithoutParams(CallWithoutParamsContext ctx) {
        return callVisitor.visitCallWithoutParams(ctx);
    }

    @Override
    public ArcaObject visitCallWithParams(CallWithParamsContext ctx) {
        return callVisitor.visitCallWithParams(ctx);
    }

    @Override
    public ArcaString visitVar(VarContext ctx) {
        return varVisitor.visitVar(ctx);
    }

    @Override
    public ArcaList visitParams(ParamsContext ctx) {
        return paramsVisitor.visitParams(ctx);
    }
}
