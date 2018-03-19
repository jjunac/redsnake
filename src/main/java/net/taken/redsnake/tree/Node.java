package net.taken.redsnake.tree;

import net.taken.redsnake.lang.RedsEnvironment;

import java.util.List;

public abstract class Node<T> {

    // TODO save location for error trace
    public Node() {
    }

    public abstract List<Node> getChildren();

    public abstract T execute(RedsEnvironment env);

}
