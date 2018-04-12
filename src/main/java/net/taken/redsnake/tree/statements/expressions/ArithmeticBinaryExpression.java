package net.taken.redsnake.tree.statements.expressions;

import com.google.common.collect.ImmutableList;
import net.taken.redsnake.interpretor.RedsEnvironment;
import net.taken.redsnake.interpretor.Symbol;
import net.taken.redsnake.interpretor.Value;
import net.taken.redsnake.lang.RedsObject;
import net.taken.redsnake.operations.BinaryOperation;
import net.taken.redsnake.operations.OperatorType;
import net.taken.redsnake.tree.Node;

import java.util.List;
import java.util.Optional;

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
    public Value execute(RedsEnvironment env) {
        Value vl = left.execute(env);
        Value vr = right.execute(env);
        OperatorType operatorType;
        // TODO make something prettier, may be pass the good type at the AST construction ?
        switch (type){
            case POWER:
                operatorType = OperatorType.D_STAR;
                break;
            case MULTIPLY:
                operatorType = OperatorType.STAR;
                break;
            case DIVIDE:
                operatorType = OperatorType.SLASH;
                break;
            case MODULUS:
                operatorType = OperatorType.PERCENT;
                break;
            case ADD:
                operatorType = OperatorType.PLUS;
                break;
            case SUBTRACT:
                operatorType = OperatorType.MINUS;
                break;
            default:
                throw new IllegalStateException();
        }
        Optional<BinaryOperation> operation = env.resolveBinaryOperation(operatorType, vl.getType(), vr.getType());
        if (!operation.isPresent()) {
            throw new IllegalStateException();
        }
        return operation.get().apply(vl, vr);
    }
}
