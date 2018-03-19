package net.taken.redsnake.tree;

import com.google.common.collect.ImmutableList;
import net.taken.redsnake.lang.RedsEnvironment;
import net.taken.redsnake.lang.RedsObject;
import net.taken.redsnake.tree.expressions.Expression;

import java.util.List;
import java.util.stream.Collectors;

public class Call extends Node<RedsObject> {

    private final Variable function;
    private final List<Expression> parameters;

    public Call(Variable function, List<Expression> parameters) {
        this.function = function;
        this.parameters = parameters;
    }

    @Override
    public List<Node> getChildren() {
        return ImmutableList.<Node>builder().add(function).addAll(parameters).build();
    }

    @Override
    public RedsObject execute(RedsEnvironment env) {
        return env.resolveFunction(function.execute(env))
                .apply(parameters.stream().map(e -> e.execute(env)).collect(Collectors.toList()));
    }
}
