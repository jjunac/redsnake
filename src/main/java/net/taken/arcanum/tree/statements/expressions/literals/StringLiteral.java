package net.taken.arcanum.tree.statements.expressions.literals;

import net.taken.arcanum.lang.ArcaEnvironment;
import net.taken.arcanum.lang.ArcaString;

public class StringLiteral extends Literal {

    private final String value;

    public StringLiteral(String value) {
        this.value = value;
    }

    @Override
    public ArcaString execute(ArcaEnvironment env) {
        return new ArcaString(value);
    }
}
