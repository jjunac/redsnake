package net.taken.arcanum.tree.statements.expressions.literals;

import com.google.common.collect.ImmutableList;
import net.taken.arcanum.tree.Node;
import net.taken.arcanum.tree.statements.expressions.Expression;

import java.util.List;

public abstract class Literal extends Expression {

    @Override
    public List<Node> getChildren() {
        return ImmutableList.of();
    }
}
