package net.taken.redsnake.tree.statements.expressions;

import com.google.common.collect.ImmutableList;
import net.taken.redsnake.lang.RedsEnvironment;
import net.taken.redsnake.lang.RedsObject;
import net.taken.redsnake.tree.Node;

import java.util.List;

public class ArithmeticUnaryExpression extends Expression {

    public enum Type {
        MINUS("-");

        private String value;

        Type(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

    }

    private final Type type;
    private final Expression value;

    public ArithmeticUnaryExpression(Type type, Expression value) {
        this.type = type;
        this.value = value;
    }

    @Override
    public List<Node> getChildren() {
        return ImmutableList.of(value);
    }

    @Override
    public RedsObject execute(RedsEnvironment env) {
        RedsObject v = value.execute(env);
        switch (type){
            case MINUS:
                return v.uminus();
        }
        throw new IllegalStateException();
    }
}
