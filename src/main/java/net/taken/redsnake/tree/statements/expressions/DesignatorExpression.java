package net.taken.redsnake.tree.statements.expressions;

import com.google.common.collect.ImmutableList;
import net.taken.redsnake.lang.RedsEnvironment;
import net.taken.redsnake.lang.RedsObject;
import net.taken.redsnake.tree.statements.expressions.designators.Designator;
import net.taken.redsnake.tree.Node;

import java.util.List;

public class DesignatorExpression extends Expression {

    private final Designator value;

    public DesignatorExpression(Designator value) {
        this.value = value;
    }

    @Override
    public List<Node> getChildren() {
        return ImmutableList.of(value);
    }

    @Override
    public RedsObject execute(RedsEnvironment env) {
        return value.execute(env);
    }
}
