package net.taken.arcanum.tree.expressions;

import com.google.common.collect.ImmutableList;
import net.taken.arcanum.lang.ArcaEnvironment;
import net.taken.arcanum.lang.ArcaObject;
import net.taken.arcanum.tree.Node;

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
    public ArcaObject execute(ArcaEnvironment env) {
        ArcaObject v = value.execute();
        switch (type){
            case MINUS:
                return v.uminus();
        }
        throw new IllegalStateException();
    }
}
