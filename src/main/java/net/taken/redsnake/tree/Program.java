package net.taken.redsnake.tree;

import com.google.common.collect.ImmutableList;
import net.taken.redsnake.lang.RedsEnvironment;
import net.taken.redsnake.tree.statements.StatementList;
import net.taken.redsnake.lang.RedsNull;
import net.taken.redsnake.lang.RedsObject;

import java.util.List;

public class Program extends Node<RedsObject> {

    private final StatementList statementList;

    public Program(StatementList statementList) {
        this.statementList = statementList;
    }

    @Override
    public List<Node> getChildren() {
        return ImmutableList.of(statementList);
    }

    @Override
        public RedsObject execute(RedsEnvironment env) {
        // TODO may be return the exit code of the program
        statementList.execute(env);
        return new RedsNull();
    }
}