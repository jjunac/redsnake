package net.taken.arcanum.tree.statements;

import com.google.common.collect.ImmutableList;
import net.taken.arcanum.lang.ArcaEnvironment;
import net.taken.arcanum.lang.ArcaNull;
import net.taken.arcanum.lang.ArcaObject;
import net.taken.arcanum.tree.Node;
import net.taken.arcanum.tree.statements.expressions.Expression;

import java.util.List;
import java.util.Optional;

public class If extends Statement {

    private final Expression condition;
    private final StatementList thenBody;
    private final Optional<StatementList> elseBody;

    If(Expression cond, StatementList thenBody, StatementList elseBody) {
        this(cond, thenBody, Optional.of(elseBody));
    }

    public If(Expression condition, StatementList thenBody, Optional<StatementList> elseBody) {
        this.condition = condition;
        this.thenBody = thenBody;
        this.elseBody = elseBody;
    }

    @Override
    public List<Node> getChildren() {
        ImmutableList.Builder<Node> res = ImmutableList.builder();
        res.add(condition, thenBody);
        elseBody.ifPresent(res::add);
        return res.build();
    }

    @Override
    public ArcaObject execute(ArcaEnvironment env) {
        if (condition.execute(env).tob().getValue()) {
            return thenBody.execute(env);
        }
        if (elseBody.isPresent()) {
            return elseBody.get().execute(env);
        }
        return new ArcaNull();
    }
}
