package net.taken.redsnake.tree.statements.expressions;

import com.google.common.collect.ImmutableList;
import net.taken.redsnake.interpretor.RedsEnvironment;
import net.taken.redsnake.interpretor.Symbol;
import net.taken.redsnake.interpretor.Value;
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
    public Value execute(RedsEnvironment env) {
        return value.execute(env);
    }
}
