package net.taken.arcanum.tree.designators;

import com.google.common.collect.ImmutableList;
import net.taken.arcanum.lang.ArcaEnvironment;
import net.taken.arcanum.lang.ArcaObject;
import net.taken.arcanum.tree.Call;
import net.taken.arcanum.tree.Node;

import java.util.List;

public class CallDesignator extends Designator {

    private final Call value;

    public CallDesignator(Call value) {
        this.value = value;
    }

    @Override
    public List<Node> getChildren() {
        return ImmutableList.of(value);
    }

    @Override
    public ArcaObject execute(ArcaEnvironment env) {
        return value.execute(env);
    }
}
