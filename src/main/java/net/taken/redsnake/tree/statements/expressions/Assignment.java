package net.taken.redsnake.tree.statements.expressions;

import com.google.common.collect.ImmutableList;
import net.taken.redsnake.interpretor.RedsEnvironment;
import net.taken.redsnake.interpretor.Value;
import net.taken.redsnake.interpretor.VariableSymbol;
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
    public Value execute(RedsEnvironment env) {
        Value v = value.execute(env);
        VariableSymbol symbol = new VariableSymbol(name.execute(env), v);
        env.putVariable(symbol.getName(), symbol);
        return v;
    }
}
