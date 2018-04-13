package net.taken.redsnake.tree.statements.expressions.literals;

import net.taken.redsnake.interpretor.RedsEnvironment;
import net.taken.redsnake.interpretor.Value;
import net.taken.redsnake.lang.RedsInteger;

public class IntegerLiteral extends Literal {

    private final int value;

    public IntegerLiteral(String value) {
        this.value = Integer.valueOf(value);
    }

    @Override
    public Value<RedsInteger> execute(RedsEnvironment env) {
        return Value.of(RedsInteger.TYPE, new RedsInteger(value));
    }
}
