package net.taken.arcanum.tree.designators;

import com.google.common.collect.ImmutableList;
import net.taken.arcanum.lang.ArcaEnvironment;
import net.taken.arcanum.lang.ArcaObject;
import net.taken.arcanum.tree.Node;
import net.taken.arcanum.tree.Variable;

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
    public ArcaObject execute(ArcaEnvironment env) {
        String identifier = value.execute(env);
        ArcaObject res = env.getVariable(identifier);
        if (res == null) {
            res = env.resolveFunction(identifier).apply(ImmutableList.of());
        }
        return res;
    }
}
