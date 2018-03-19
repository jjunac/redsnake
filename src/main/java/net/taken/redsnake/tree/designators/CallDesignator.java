package net.taken.redsnake.tree.designators;

import com.google.common.collect.ImmutableList;
import net.taken.redsnake.lang.RedsEnvironment;
import net.taken.redsnake.lang.RedsObject;
import net.taken.redsnake.tree.Call;
import net.taken.redsnake.tree.Node;

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
    public RedsObject execute(RedsEnvironment env) {
        return value.execute(env);
    }
}
