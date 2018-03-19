package net.taken.redsnake.tree.designators;

import com.google.common.collect.ImmutableList;
import net.taken.redsnake.lang.RedsEnvironment;
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
    public RedsObject execute(RedsEnvironment env) {
        String identifier = value.execute(env);
        RedsObject res = env.getVariable(identifier);
        if (res == null) {
            res = env.resolveFunction(identifier).apply(ImmutableList.of());
        }
        return res;
    }
}
