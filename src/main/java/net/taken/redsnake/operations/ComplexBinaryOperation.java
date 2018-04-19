package net.taken.redsnake.operations;

import net.taken.redsnake.interpretor.Value;
import net.taken.redsnake.lang.RedsObject;

public class ComplexBinaryOperation<U extends RedsObject, V extends RedsObject, R extends RedsObject> {

    // TODO I think we can mke inheritance with BinaryOperation

    private final BinaryOperation<? extends RedsObject, ? extends RedsObject, R> binaryOperation;
    private final Conversion<U, ? extends RedsObject> conversionLeft;
    private final Conversion<V, ? extends RedsObject> conversionRight;

    private ComplexBinaryOperation(Conversion<U, ? extends RedsObject> conversionLeft, Conversion<V, ? extends RedsObject> conversionRight, BinaryOperation<? extends RedsObject, ? extends RedsObject, R> binaryOperation) {
        this.conversionLeft = conversionLeft;
        this.conversionRight = conversionRight;
        this.binaryOperation = binaryOperation;
    }

    public static class ComplexBinaryOperationBuilder<U extends RedsObject, V extends RedsObject, R extends RedsObject> {
        private final BinaryOperation<? extends RedsObject, ? extends RedsObject, R> binaryOperation;
        private Conversion<U, ? extends RedsObject> conversionLeft;
        private Conversion<V, ? extends RedsObject> conversionRight;

        public ComplexBinaryOperationBuilder(BinaryOperation<? extends RedsObject, ? extends RedsObject, R> binaryOperation) {
            this.binaryOperation = binaryOperation;
        }

        public ComplexBinaryOperationBuilder conversionLeft(Conversion<U, ? extends RedsObject> conversionLeft) {
            this.conversionLeft = conversionLeft;
            return this;
        }

        public ComplexBinaryOperationBuilder conversionRight(Conversion<V, ? extends RedsObject> conversionRight) {
            this.conversionRight = conversionRight;
            return this;
        }

        public ComplexBinaryOperation<U, V, R> build() {
            return new ComplexBinaryOperation<>(conversionLeft, conversionRight, binaryOperation);
        }
    }

    public Value<R> apply(Value<U> argLeft, Value<V> argRight) {
        Value left = conversionLeft == null ? argLeft : conversionLeft.apply(argLeft);
        Value right = conversionLeft == null ? argRight : conversionRight.apply(argRight);
        return binaryOperation.apply(left, right);
    }
}
