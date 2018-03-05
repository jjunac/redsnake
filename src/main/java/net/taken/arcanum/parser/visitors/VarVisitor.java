package net.taken.arcanum.parser.visitors;

import net.taken.arcanum.lang.ArcaString;

import static net.taken.arcanum.parser.ArcanumParser.*;

public class VarVisitor extends ArcanumAbstractVisitor {

    public VarVisitor(ArcanumVisitor arcanumVisitor) {
        super(arcanumVisitor);
    }

    @Override
    public ArcaString visitVar(VarContext ctx) {
        return new ArcaString(ctx.getText());
    }

}
