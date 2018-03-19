package net.taken.arcanum.tree.statements.expressions;

import com.google.common.collect.ImmutableList;
import net.taken.arcanum.lang.ArcaEnvironment;
import net.taken.arcanum.lang.ArcaObject;
import net.taken.arcanum.tree.Node;

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
    public ArcaObject execute(ArcaEnvironment env) {
        return subExpression.execute(env);
    }
}
