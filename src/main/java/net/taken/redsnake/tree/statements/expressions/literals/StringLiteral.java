package net.taken.redsnake.tree.statements.expressions.literals;

import net.taken.redsnake.lang.RedsEnvironment;
import net.taken.redsnake.lang.RedsString;

public class StringLiteral extends Literal {

    private final String value;

    public StringLiteral(String value) {
        this.value = value;
    }

    @Override
    public RedsString execute(RedsEnvironment env) {
        return new RedsString(value);
    }
}
