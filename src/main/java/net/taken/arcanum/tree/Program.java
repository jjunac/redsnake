package net.taken.arcanum.tree;

import com.google.common.collect.ImmutableList;
import net.taken.arcanum.lang.ArcaEnvironment;
import net.taken.arcanum.lang.ArcaNull;
import net.taken.arcanum.lang.ArcaObject;

import java.util.List;

public class Program extends Node<ArcaObject> {

    private final List<Statement> statements;

    public Program(List<Statement> statements) {
        this.statements = statements;
    }

    @Override
    public List<Node> getChildren() {
        return ImmutableList.copyOf(statements);
    }

    @Override
    public ArcaObject execute(ArcaEnvironment env) {
        statements.forEach(s -> s.execute(env));
        return new ArcaNull();
    }
}
