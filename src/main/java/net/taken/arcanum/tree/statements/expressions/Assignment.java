package net.taken.arcanum.tree.statements.expressions;

import com.google.common.collect.ImmutableList;
import net.taken.arcanum.lang.ArcaEnvironment;
import net.taken.arcanum.lang.ArcaObject;
import net.taken.arcanum.tree.Node;
import net.taken.arcanum.tree.Variable;

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
    public ArcaObject execute(ArcaEnvironment env) {
        ArcaObject v = value.execute(env);
        env.putVariable(name.execute(env), v);
        return v;
    }
}
