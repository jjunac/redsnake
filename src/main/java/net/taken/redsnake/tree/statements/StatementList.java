package net.taken.redsnake.tree.statements;

import com.google.common.collect.ImmutableList;
import net.taken.redsnake.lang.RedsEnvironment;
import net.taken.redsnake.lang.RedsNull;
import net.taken.redsnake.lang.RedsObject;
import net.taken.redsnake.tree.Node;

import java.util.List;

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
    public RedsObject execute(RedsEnvironment env) {
        statements.forEach(s -> s.execute(env));
        return new RedsNull();
    }
}
