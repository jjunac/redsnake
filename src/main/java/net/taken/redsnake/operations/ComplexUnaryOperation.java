package net.taken.redsnake.operations;

import net.taken.redsnake.interpretor.Value;
import net.taken.redsnake.lang.RedsObject;

public class ComplexUnaryOperation<U extends RedsObject, V extends RedsObject, R extends RedsObject> {

    final UnaryOperation<V, R> unaryOperation;
    final Conversion<U, V> conversion;

    public ComplexUnaryOperation(UnaryOperation<V, R> unaryOperation, Conversion<U, V> conversion) {
        this.unaryOperation = unaryOperation;
        this.conversion = conversion;
    }

    public ComplexUnaryOperation(UnaryOperation<V, R> unaryOperation) {
        this(unaryOperation, null);
    }

    public Value<R> apply(Value<U> argLeft) {
        Value<V> left = conversion == null ? (Value<V>) argLeft : conversion.apply(argLeft);
        return unaryOperation.apply(left);
    }

}
