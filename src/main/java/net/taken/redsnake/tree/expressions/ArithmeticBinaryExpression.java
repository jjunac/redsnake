package net.taken.redsnake.tree.expressions;

import com.google.common.collect.ImmutableList;
import net.taken.redsnake.lang.RedsEnvironment;
import net.taken.redsnake.lang.RedsObject;
import net.taken.redsnake.tree.Node;

import java.util.List;

public class ArithmeticBinaryExpression extends Expression {

    public enum Type {
        POWER("**"),
        MULTIPLY("*"),
        DIVIDE("/"),
        MODULUS("%"),
        ADD("+"),
        SUBTRACT("-");

        private String value;

        Type(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

    }

    private final Type type;
    private final Expression left;
    private final Expression right;

    public ArithmeticBinaryExpression(Type type, Expression left, Expression right) {
        this.type = type;
        this.left = left;
        this.right = right;
    }

    @Override
    public List<Node> getChildren() {
        return ImmutableList.of(left, right);
    }

    @Override
    public RedsObject execute(RedsEnvironment env) {
        RedsObject l = left.execute(env);
        RedsObject r = right.execute(env);
        switch (type){
            case POWER:
                return l.power(r);
            case MULTIPLY:
                return l.multiply(r);
            case DIVIDE:
                return l.divide(r);
            case MODULUS:
                return l.modulo(r);
            case ADD:
                return l.plus(r);
            case SUBTRACT:
                return l.minus(r);
        }
        throw new IllegalStateException();
    }
}
