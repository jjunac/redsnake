package net.taken.redsnake.tree.statements.expressions.literals;

import net.taken.redsnake.interpretor.Value;
import net.taken.redsnake.lang.RedsBoolean;
import net.taken.redsnake.interpretor.RedsEnvironment;

public class BooleanLiteral extends Literal {

    private final boolean value;

    public BooleanLiteral(String value) {
        this.value = Boolean.valueOf(value);
    }

    @Override
    public Value<RedsBoolean> execute(RedsEnvironment env) {
        // TODO Boolean factory to have only 2 instances of boolean and improve performance
        return Value.of(RedsBoolean.TYPE, new RedsBoolean(value));
    }
}
