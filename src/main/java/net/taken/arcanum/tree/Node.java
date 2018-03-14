package net.taken.arcanum.tree;

import net.taken.arcanum.lang.ArcaObject;

import java.util.List;

public abstract class Node<T> {

    // TODO save location for error trace
    public Node() {
    }

    public abstract List<? extends Node> getChildren();

    public abstract T execute();

}
