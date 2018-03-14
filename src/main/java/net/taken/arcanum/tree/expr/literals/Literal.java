package net.taken.arcanum.tree.expr.literals;

import com.google.common.collect.ImmutableList;
import net.taken.arcanum.tree.Node;
import net.taken.arcanum.tree.expr.Expr;

import java.util.List;

public abstract class Literal extends Expr {

    @Override
    public List<? extends Node> getChildren() {
        return ImmutableList.of();
    }
}
