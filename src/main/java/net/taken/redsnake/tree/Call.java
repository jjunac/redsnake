package net.taken.redsnake.tree;

import com.google.common.collect.ImmutableList;
import net.taken.redsnake.interpretor.RedsEnvironment;
import net.taken.redsnake.interpretor.Value;
import net.taken.redsnake.tree.statements.expressions.Expression;

import java.util.List;
import java.util.stream.Collectors;

public class Call extends Node<Value> {

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
    public Value execute(RedsEnvironment env) {
        return env.resolveFunction(function.execute(env))
                .apply(parameters.stream().map(e -> e.execute(env)).collect(Collectors.toList()));
    }
}
