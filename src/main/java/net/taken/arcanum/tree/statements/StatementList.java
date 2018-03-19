package net.taken.arcanum.tree.statements;

import com.google.common.collect.ImmutableList;
import net.taken.arcanum.lang.ArcaEnvironment;
import net.taken.arcanum.lang.ArcaNull;
import net.taken.arcanum.lang.ArcaObject;
import net.taken.arcanum.tree.Node;

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
    public ArcaObject execute(ArcaEnvironment env) {
        statements.forEach(s -> s.execute(env));
        return new ArcaNull();
    }
}
