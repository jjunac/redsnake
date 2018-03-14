package net.taken.arcanum.tree.expr.literals;

import net.taken.arcanum.lang.ArcaObject;
import net.taken.arcanum.lang.ArcaString;
import net.taken.arcanum.tree.Node;

import java.util.List;

public class StringLiteral extends Literal {

    private final String value;

    public StringLiteral(String value) {
        this.value = value;
    }

    @Override
    public ArcaString execute() {
        return new ArcaString(value);
    }
}
