package net.taken.redsnake.operations;

import net.taken.redsnake.interpretor.Value;
import net.taken.redsnake.lang.RedsObject;

public class ComplexBinaryOperation<U extends RedsObject, V extends RedsObject, W extends RedsObject, X extends RedsObject, R extends RedsObject> {

    final BinaryOperation<V, X, R> binaryOperation;
    final Conversion<U, V> conversionLeft;
    final Conversion<W, X> conversionRight;

    private ComplexBinaryOperation(Conversion<U, V> conversionLeft, Conversion<W, X> conversionRight, BinaryOperation<V, X, R> binaryOperation) {
        this.conversionLeft = conversionLeft;
        this.conversionRight = conversionRight;
        this.binaryOperation = binaryOperation;
    }

    public static class ComplexBinaryOperationBuilder {
        private final BinaryOperation<V, X, R> binaryOperation;
        private Conversion<U, V> conversionLeft;
        private Conversion<W, X> conversionRight;

        public ComplexBinaryOperationBuilder(BinaryOperation<V, X, R> binaryOperation) {
            this.binaryOperation = binaryOperation;
        }

        public ComplexBinaryOperationBuilder conversionLeft(Conversion<U, V> conversionLeft) {
            this.conversionLeft = conversionLeft;
            return this;
        }

        public ComplexBinaryOperationBuilder conversionRight(Conversion<W, X> conversionRight) {
            this.conversionRight = conversionRight;
            return this;
        }

        public ComplexBinaryOperation<U, V, W, X, R> build() {
            return new ComplexBinaryOperation<>(conversionLeft, conversionRight, binaryOperation);
        }
    }

    public Value<R> apply(Value<U> argLeft, Value<W> argRight) {
        Value<V> left = conversionLeft == null ? (Value<V>) argLeft : conversionLeft.apply(argLeft);
        Value<X> right = conversionLeft == null ? (Value<X>) argRight : conversionRight.apply(argRight);
        return binaryOperation.apply(left, right);
    }
}
