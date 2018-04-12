package net.taken.redsnake.tree.statements;

import com.google.common.collect.ImmutableList;
import net.taken.redsnake.interpretor.RedsEnvironment;
import net.taken.redsnake.interpretor.Value;
import net.taken.redsnake.lang.RedsNull;
import net.taken.redsnake.lang.RedsObject;
import net.taken.redsnake.tree.Node;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class StatementList extends Statement {

    private final List<Statement> statements;

    public StatementList(List<Statement> statements) {
        this.statements = statements;
    }

    @Override
    public List<Node> getChildren() {
        return ImmutableList.copyOf(statements);
    }

    @Override
    public Value execute(RedsEnvironment env) {
        // TODO optimize this
        LinkedList<Value> values = statements.stream().map(s -> s.execute(env)).collect(Collectors.toCollection(LinkedList::new));
        return values.getLast();
    }
}
