package net.taken.arcanum.tree.expr.literals;

import net.taken.arcanum.lang.ArcaInteger;
import net.taken.arcanum.lang.ArcaObject;
import net.taken.arcanum.lang.ArcaString;

public class IntegerLiteral extends Literal {

    private final int value;

    public IntegerLiteral(String value) {
        this.value = Integer.valueOf(value);
    }

    @Override
    public ArcaInteger execute() {
        return new ArcaInteger(value);
    }
}
