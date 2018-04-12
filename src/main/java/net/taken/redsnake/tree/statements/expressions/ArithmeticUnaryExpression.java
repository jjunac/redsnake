package net.taken.redsnake.tree.statements.expressions;

import com.google.common.collect.ImmutableList;
import net.taken.redsnake.interpretor.RedsEnvironment;
import net.taken.redsnake.interpretor.Value;
import net.taken.redsnake.lang.RedsObject;
import net.taken.redsnake.operations.BinaryOperation;
import net.taken.redsnake.operations.OperatorType;
import net.taken.redsnake.operations.UnaryOperation;
import net.taken.redsnake.tree.Node;

import java.util.List;
import java.util.Optional;

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
    public Value execute(RedsEnvironment env) {
        Value v = value.execute(env);
        OperatorType operatorType;
        // TODO same as binary expression
        switch (type){
            case MINUS:
                operatorType = OperatorType.MINUS;
                break;
            default:
                throw new IllegalStateException();
        }
        Optional<UnaryOperation> operation = env.resolveUnaryOperation(operatorType, v.getType());
        if (!operation.isPresent()) {
            throw new IllegalStateException();
        }
        return operation.get().apply(v);
    }
}
