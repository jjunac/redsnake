package net.taken.redsnake.operations;

import net.taken.redsnake.interpretor.Value;
import net.taken.redsnake.lang.RedsObject;

public class ComplexUnaryOperation<U extends RedsObject, R extends RedsObject> {

    private final UnaryOperation<? extends RedsObject, R> unaryOperation;
    private final Conversion<U, ? extends RedsObject> conversion;

    public ComplexUnaryOperation(UnaryOperation<? extends RedsObject, R> unaryOperation, Conversion<U, ? extends RedsObject> conversion) {
        this.unaryOperation = unaryOperation;
        this.conversion = conversion;
    }

    public ComplexUnaryOperation(UnaryOperation<U, R> unaryOperation) {
        this(unaryOperation, null);
    }

    public Value<R> apply(Value<U> argLeft) {
        Value left = conversion == null ? argLeft : conversion.apply(argLeft);
        return unaryOperation.apply(left);
    }

}
