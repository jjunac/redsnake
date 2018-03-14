package net.taken.arcanum.tree.expr.literals;

import net.taken.arcanum.lang.ArcaBoolean;
import net.taken.arcanum.lang.ArcaString;

public class BooleanLiteral extends Literal {

    private final boolean value;

    public BooleanLiteral(String value) {
        this.value = Boolean.valueOf(value);
    }

    @Override
    public ArcaBoolean execute() {
        return new ArcaBoolean(value);
    }
}
