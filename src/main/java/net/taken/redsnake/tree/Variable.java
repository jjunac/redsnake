package net.taken.redsnake.tree;

import com.google.common.collect.ImmutableList;
import net.taken.redsnake.interpretor.RedsEnvironment;

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
    public String execute(RedsEnvironment env) {
        return identifier;
    }
}
