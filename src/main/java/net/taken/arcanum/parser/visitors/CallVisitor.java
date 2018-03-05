package net.taken.arcanum.parser.visitors;

import net.taken.arcanum.lang.ArcaList;
import net.taken.arcanum.lang.ArcaObject;

import static net.taken.arcanum.parser.ArcanumParser.*;

public class CallVisitor extends ArcanumAbstractVisitor {

    public CallVisitor(ArcanumVisitor arcanumVisitor) {
        super(arcanumVisitor);
    }

    @Override
    public ArcaObject visitCallWithoutParams(CallWithoutParamsContext ctx) {
        return environment.resolveFunction(visitVar(ctx.fct)).apply(new ArcaList());
    }

    @Override
    public ArcaObject visitCallWithParams(CallWithParamsContext ctx) {
        return environment.resolveFunction(visitVar(ctx.fct)).apply(visitParams(ctx.args));
    }

}
