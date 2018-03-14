package net.taken.arcanum.tree.expressions.literals;

import com.google.common.collect.ImmutableList;
import net.taken.arcanum.tree.Node;
import net.taken.arcanum.tree.expressions.Expression;

import java.util.List;

public abstract class Literal extends Expression {

    @Override
    public List<? extends Node> getChildren() {
        return ImmutableList.of();
    }
}
