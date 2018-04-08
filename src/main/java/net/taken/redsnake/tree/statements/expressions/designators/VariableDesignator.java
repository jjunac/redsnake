package net.taken.redsnake.tree.statements.expressions.designators;

import com.google.common.collect.ImmutableList;
import net.taken.redsnake.interpretor.RedsEnvironment;
import net.taken.redsnake.interpretor.Value;
import net.taken.redsnake.lang.RedsObject;
import net.taken.redsnake.tree.Node;
import net.taken.redsnake.tree.Variable;

import java.util.List;

public class VariableDesignator extends Designator{

    private final Variable value;

    public VariableDesignator(Variable value) {
        this.value = value;
    }

    @Override
    public List<Node> getChildren() {
        return ImmutableList.of(value);
    }

    @Override
    public Value execute(RedsEnvironment env) {
        String identifier = value.execute(env);
        Value res = env.getVariable(identifier);
        if (res == null) {
            res = env.resolveFunction(identifier).apply(ImmutableList.of());
        }
        return res;
    }
}
