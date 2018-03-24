package net.taken.redsnake.tree.statements.expressions.literals;

import com.google.common.collect.ImmutableList;
import net.taken.redsnake.tree.Node;
import net.taken.redsnake.tree.statements.expressions.Expression;

import java.util.List;

public abstract class Literal extends Expression {

    @Override
    public List<Node> getChildren() {
        return ImmutableList.of();
    }
}
