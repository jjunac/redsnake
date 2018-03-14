package net.taken.arcanum.tree.expr;

import com.google.common.collect.ImmutableList;
import net.taken.arcanum.lang.ArcaObject;
import net.taken.arcanum.tree.Node;

import java.util.List;

public class BinaryExpr extends Expr {

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
    private final Expr left;
    private final Expr right;

    public BinaryExpr(Type type, Expr left, Expr right) {
        this.type = type;
        this.left = left;
        this.right = right;
    }

    @Override
    public List<? extends Node> getChildren() {
        return ImmutableList.of(left, right);
    }

    @Override
    public ArcaObject execute() {
        ArcaObject l = left.execute();
        ArcaObject r = left.execute();
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
