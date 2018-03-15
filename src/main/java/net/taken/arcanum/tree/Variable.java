package net.taken.arcanum.tree;

import com.google.common.collect.ImmutableList;
import net.taken.arcanum.lang.ArcaEnvironment;

import java.util.List;

public class Variable extends Node<String> {

    private final String identifier;

    public Variable(String identifier) {
        this.identifier = identifier;
    }

    @Override
    public List<Node> getChildren() {
        return ImmutableList.of();
    }

    @Override
    public String execute(ArcaEnvironment env) {
        return identifier;
    }
}
