package net.taken.redsnake.tree;

import com.google.common.collect.ImmutableList;
import net.taken.redsnake.interpretor.RedsEnvironment;
import net.taken.redsnake.interpretor.Value;
import net.taken.redsnake.tree.statements.StatementList;
import net.taken.redsnake.lang.RedsNull;
import net.taken.redsnake.lang.RedsObject;

import java.util.List;

public class Program extends Node<Integer> {

    private final StatementList statementList;

    public Program(StatementList statementList) {
        this.statementList = statementList;
    }

    @Override
    public List<Node> getChildren() {
        return ImmutableList.of(statementList);
    }

    @Override
        public Integer execute(RedsEnvironment env) {
        statementList.execute(env);
        return 0;
    }
}
