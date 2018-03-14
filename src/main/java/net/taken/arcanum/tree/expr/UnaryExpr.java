package net.taken.arcanum.tree.expr;

import com.google.common.collect.ImmutableList;
import net.taken.arcanum.lang.ArcaObject;
import net.taken.arcanum.tree.Node;

import java.util.List;

public class UnaryExpr extends Expr {

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
    private final Expr value;

    public UnaryExpr(Type type, Expr value) {
        this.type = type;
        this.value = value;
    }

    @Override
    public List<? extends Node> getChildren() {
        return ImmutableList.of(value);
    }

    @Override
    public ArcaObject execute() {
        ArcaObject v = value.execute();
        switch (type){
            case MINUS:
                return v.uminus();
        }
        throw new IllegalStateException();
    }
}
