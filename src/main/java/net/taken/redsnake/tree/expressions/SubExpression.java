package net.taken.redsnake.tree.expressions;

import com.google.common.collect.ImmutableList;
import net.taken.redsnake.lang.RedsEnvironment;
import net.taken.redsnake.lang.RedsObject;
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
    public RedsObject execute(RedsEnvironment env) {
        return subExpression.execute(env);
    }
}
