package net.taken.arcanum.parser.visitors;

import net.taken.arcanum.lang.*;
import net.taken.arcanum.parser.ArcanumParser;
import net.taken.arcanum.parser.ArcanumParserBaseVisitor;

import static net.taken.arcanum.parser.ArcanumParser.*;

public class ArcanumAbstractVisitor extends ArcanumParserBaseVisitor<ArcaObject> {
    ArcanumVisitor arcanumVisitor;
    ArcaEnvironment environment;

    public ArcanumAbstractVisitor(ArcanumVisitor arcanumVisitor) {
        this.arcanumVisitor = arcanumVisitor;
        this.environment = arcanumVisitor.environment;
    }

    @Override
    public ArcaObject visitProgram(ProgramContext ctx) {
        return arcanumVisitor.visitProgram(ctx);
    }

    @Override
    public ArcaObject visitNonEmptyStmt(NonEmptyStmtContext ctx) {
        return arcanumVisitor.visitNonEmptyStmt(ctx);
    }

    @Override
    public ArcaObject visitEmptyStmt(EmptyStmtContext ctx) {
        return arcanumVisitor.visitEmptyStmt(ctx);
    }

    @Override
    public ArcaInteger visitInt(IntContext ctx) {
        return arcanumVisitor.visitInt(ctx);
    }

    @Override
    public ArcaObject visitString(StringContext ctx) {
        return arcanumVisitor.visitString(ctx);
    }

    @Override
    public ArcaObject visitBoolean(BooleanContext ctx) {
        return arcanumVisitor.visitBoolean(ctx);
    }

    @Override
    public ArcaObject visitBinaryExpr(BinaryExprContext ctx) {
        return arcanumVisitor.visitBinaryExpr(ctx);
    }

    @Override
    public ArcaObject visitUnaryExpr(UnaryExprContext ctx) {
        return arcanumVisitor.visitUnaryExpr(ctx);
    }

    @Override
    public ArcaObject visitAssignment(AssignmentContext ctx) {
        return arcanumVisitor.visitAssignment(ctx);
    }

    @Override
    public ArcaObject visitDesignatorExpr(DesignatorExprContext ctx) {
        return arcanumVisitor.visitDesignatorExpr(ctx);
    }

    @Override
    public ArcaObject visitParenExpr(ParenExprContext ctx) {
        return arcanumVisitor.visitParenExpr(ctx);
    }

    @Override
    public ArcaObject visitCallDesignator(CallDesignatorContext ctx) {
        return arcanumVisitor.visitCallDesignator(ctx);
    }

    @Override
    public ArcaObject visitVarDesignator(VarDesignatorContext ctx) {
        return arcanumVisitor.visitVarDesignator(ctx);
    }

    @Override
    public ArcaObject visitCallWithoutParams(CallWithoutParamsContext ctx) {
        return arcanumVisitor.visitCallWithoutParams(ctx);
    }

    @Override
    public ArcaObject visitCallWithParams(CallWithParamsContext ctx) {
        return arcanumVisitor.visitCallWithParams(ctx);
    }

    @Override
    public ArcaString visitVar(VarContext ctx) {
        return arcanumVisitor.visitVar(ctx);
    }

    @Override
    public ArcaList visitParams(ParamsContext ctx) {
        return arcanumVisitor.visitParams(ctx);
    }
}
