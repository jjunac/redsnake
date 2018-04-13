package net.taken.redsnake.tree.statements.expressions.literals;

import net.taken.redsnake.interpretor.RedsEnvironment;
import net.taken.redsnake.interpretor.Value;
import net.taken.redsnake.lang.RedsString;

public class StringLiteral extends Literal {

    private final String value;

    public StringLiteral(String value) {
        this.value = value;
    }

    @Override
    public Value<RedsString> execute(RedsEnvironment env) {
        return Value.of(RedsString.TYPE, new RedsString(value));
    }
}
