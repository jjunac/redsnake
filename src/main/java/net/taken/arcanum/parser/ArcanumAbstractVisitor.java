package net.taken.arcanum.parser;

import net.taken.arcanum.domain.ArcaEnvironment;
import net.taken.arcanum.domain.ArcaObject;

public class ArcanumAbstractVisitor extends ArcanumParserBaseVisitor<ArcaObject> {
    ArcanumVisitor arcanumVisitor;
    ArcaEnvironment environment;

    public ArcanumAbstractVisitor(ArcanumVisitor arcanumVisitor) {
        this.arcanumVisitor = arcanumVisitor;
        this.environment = arcanumVisitor.environment;
    }

}
