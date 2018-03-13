package net.taken.arcanum.tree.expr;

import net.taken.arcanum.lang.ArcaObject;
import net.taken.arcanum.tree.Node;

import java.util.List;

public class BinaryExpr extends Expr {

    public enum Type {
        ADD("+");

        private String value;

        Type(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    Expr leftOperand;
    Expr rightOperand;

    @Override
    public List<? extends Node> getChildren() {
        return null;
    }

    @Override
    public ArcaObject execute() {
        return null;
    }
}
