package net.taken.arcanum.tree.expressions.literals;

import net.taken.arcanum.lang.ArcaEnvironment;
import net.taken.arcanum.lang.ArcaInteger;

public class IntegerLiteral extends Literal {

    private final int value;

    public IntegerLiteral(String value) {
        this.value = Integer.valueOf(value);
    }

    @Override
    public ArcaInteger execute(ArcaEnvironment env) {
        return new ArcaInteger(value);
    }
}
