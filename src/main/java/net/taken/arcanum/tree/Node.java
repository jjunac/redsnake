package net.taken.arcanum.tree;

import net.taken.arcanum.lang.ArcaObject;

import java.util.List;

public abstract class Node {

    // TODO save location for error trace
    public Node() {
    }

    public abstract List<? extends Node> getChildren();

    public abstract ArcaObject execute();

}
