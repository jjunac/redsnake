package net.taken.arcanum.tree;

import com.google.common.collect.ImmutableList;
import net.taken.arcanum.lang.ArcaEnvironment;
import net.taken.arcanum.lang.ArcaNull;
import net.taken.arcanum.lang.ArcaObject;
import net.taken.arcanum.tree.statements.StatementList;

import java.util.List;

public class Program extends Node<ArcaObject> {

    private final StatementList statementList;

    public Program(StatementList statementList) {
        this.statementList = statementList;
    }

    @Override
    public List<Node> getChildren() {
        return ImmutableList.of(statementList);
    }

    @Override
    public ArcaObject execute(ArcaEnvironment env) {
        statementList.execute(env);
        // TODO may be return the exit code of the program
        return new ArcaNull();
    }
}
