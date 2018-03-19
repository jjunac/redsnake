package net.taken.arcanum.tree.statements.expressions;

import com.google.common.collect.ImmutableList;
import net.taken.arcanum.lang.ArcaEnvironment;
import net.taken.arcanum.lang.ArcaObject;
import net.taken.arcanum.tree.statements.expressions.designators.Designator;
import net.taken.arcanum.tree.Node;

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
    public ArcaObject execute(ArcaEnvironment env) {
        return value.execute(env);
    }
}
