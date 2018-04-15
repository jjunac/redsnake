package net.taken.redsnake.tree.statements.expressions;

import com.google.common.collect.ImmutableList;
import net.taken.redsnake.interpretor.RedsEnvironment;
import net.taken.redsnake.interpretor.Value;
import net.taken.redsnake.tree.Node;

import java.util.List;

public class SubExpression extends Expression {

    private final Expression subExpression;

    public SubExpression(Expression subExpression) {
        this.subExpression = subExpression;
    }

    @Override
    public List<Node> getChildren() {
        return ImmutableList.of(subExpression);
    }

    @Override
    public Value execute(RedsEnvironment env) {
        return subExpression.execute(env);
    }
}
