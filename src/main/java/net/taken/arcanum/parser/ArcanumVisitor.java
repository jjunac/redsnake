package net.taken.arcanum.parser;

import net.taken.arcanum.domain.*;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static net.taken.arcanum.parser.ArcanumParser.*;

public class ArcanumVisitor extends ArcanumParserBaseVisitor<ArcaObject> {

    ArcaEnvironment environment;
    Map<Class<? extends ParseTree>, ArcanumParserBaseVisitor<? extends ArcaObject>> visitors;

    public ArcanumVisitor() {
        environment = new ArcaEnvironment();
        visitors = new HashMap<>();
        registerVisitor(new ExpressionVisitor(environment), IntContext.class, BinaryExprContext.class, UnaryExprContext.class,
                AssignmentContext.class, ParenExprContext.class);
    }

    @SafeVarargs
    public final void registerVisitor(ArcanumParserBaseVisitor<? extends ArcaObject> visitor,
                                      Class<? extends ParserRuleContext>... contexts) {
        // FIXME all mock are exploding
        Arrays.stream(contexts).forEach(ctx -> visitors.put(ctx, visitor));
    }

    @Override
    public ArcaObject visit(ParseTree tree) {
        return visit(tree, tree.getClass());
    }

    ArcaObject visit(ParseTree tree, Class<? extends ParseTree> contextClass) {
        return tree.accept(visitors.get(contextClass));
    }

    @Override
    public ArcaObject visitVarDesignator(VarDesignatorContext ctx) {
        // TODO handle error
        ArcaString var = visitVar(ctx.var());
        ArcaObject res = environment.getVariable(var);
        if (res == null) {
            res = environment.resolveFunction(var).apply(new ArcaList());
        }
        return res;
    }

    @Override
    public ArcaObject visitCallWithoutParams(CallWithoutParamsContext ctx) {
        return environment.resolveFunction(visitVar(ctx.fct)).apply(new ArcaList());
    }

    @Override
    public ArcaObject visitCallWithParams(CallWithParamsContext ctx) {
        return environment.resolveFunction(visitVar(ctx.fct)).apply(visitParams(ctx.args));
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
