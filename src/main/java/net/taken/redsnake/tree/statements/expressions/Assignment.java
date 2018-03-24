package net.taken.redsnake.tree.statements.expressions;

import com.google.common.collect.ImmutableList;
import net.taken.redsnake.lang.RedsEnvironment;
import net.taken.redsnake.lang.RedsObject;
import net.taken.redsnake.tree.Node;
import net.taken.redsnake.tree.Variable;

import java.util.List;

public class Assignment extends Expression {

    private final Variable name;
    private final Expression value;

    public Assignment(Variable name, Expression value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public List<Node> getChildren() {
        return ImmutableList.of(name, value);
    }

    @Override
    public RedsObject execute(RedsEnvironment env) {
        RedsObject v = value.execute(env);
        env.putVariable(name.execute(env), v);
        return v;
    }
}
