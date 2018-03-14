package net.taken.arcanum.tree.expressions.literals;

import net.taken.arcanum.lang.ArcaBoolean;

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
