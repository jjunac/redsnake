package net.taken.redsnake.tree.statements.expressions.literals;

import net.taken.redsnake.lang.RedsBoolean;
import net.taken.redsnake.lang.RedsEnvironment;

public class BooleanLiteral extends Literal {

    private final boolean value;

    public BooleanLiteral(String value) {
        this.value = Boolean.valueOf(value);
    }

    @Override
    public RedsBoolean execute(RedsEnvironment env) {
        return new RedsBoolean(value);
    }
}
