package net.taken.arcanum.parser.visitors;

import net.taken.arcanum.lang.ArcaList;

import java.util.stream.Collectors;

import static net.taken.arcanum.parser.ArcanumParser.*;

public class ParamsVisitor extends ArcanumAbstractVisitor {

    public ParamsVisitor(ArcanumVisitor arcanumVisitor) {
        super(arcanumVisitor);
    }

    @Override
    public ArcaList visitParams(ParamsContext ctx) {
        return new ArcaList(ctx.expr().stream().map(this::visit).collect(Collectors.toList()));
    }

}
