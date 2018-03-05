package net.taken.arcanum.parser.visitors;

import net.taken.arcanum.lang.ArcaList;
import net.taken.arcanum.lang.ArcaObject;
import net.taken.arcanum.lang.ArcaString;

import static net.taken.arcanum.parser.ArcanumParser.*;

public class DesignatorVisitor extends ArcanumAbstractVisitor{

    public DesignatorVisitor(ArcanumVisitor arcanumVisitor) {
        super(arcanumVisitor);
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

}
