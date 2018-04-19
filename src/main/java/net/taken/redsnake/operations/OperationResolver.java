package net.taken.redsnake.operations;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.Sets;
import net.taken.redsnake.lang.RedsObject;
import net.taken.redsnake.reflect.Type;

import java.util.*;
import java.util.stream.Collectors;

public class OperationResolver {

    @VisibleForTesting
    OperationTable operationTable;

    @VisibleForTesting
    ConversionTable conversionTable;

    public OperationResolver() {
        operationTable = new OperationTable();
        conversionTable = new ConversionTable();
    }

    public <T extends RedsObject, R extends RedsObject> void registerConversion(Conversion<T, R> conversion) {
        conversionTable.registerConversion(conversion);
    }

    public <T extends RedsObject, R extends RedsObject> void registerUnaryOperation(OperatorType operatorType, UnaryOperation<T, R> function) {
        operationTable.registerUnaryOperation(operatorType, function);
    }

    public <U extends RedsObject, R extends RedsObject> ComplexUnaryOperation<U, R> resolveUnaryOperation(OperatorType operatorType, Type<U> type) {
        Optional<UnaryOperation> directOperation = operationTable.findUnaryOperation(operatorType, type);
        if (directOperation.isPresent()) {
            return new ComplexUnaryOperation<>(directOperation.get());
        }
        Map<Type<? extends RedsObject>, Conversion> possibleConversions = conversionTable.compatibleTypes(type);
        Map<Type<? extends RedsObject>, UnaryOperation> compatibleTypes = operationTable.compatibleTypesWithUnaryOperation(operatorType);
        Set<Type<? extends RedsObject>> commonIntermediateTypes = Sets.intersection(possibleConversions.keySet(), compatibleTypes.keySet());
        if (commonIntermediateTypes.isEmpty()) {
            throw new IllegalArgumentException("Cannot resolve operation.");
        }
        if (commonIntermediateTypes.size() > 1) {
            throw new IllegalArgumentException("Ambiguous operation, multiple conversions possible.");
        }
        Type intermediateType = commonIntermediateTypes.iterator().next();
        return new ComplexUnaryOperation<U, R>(compatibleTypes.get(intermediateType), possibleConversions.get(intermediateType));
    }

    public <T extends RedsObject, U extends RedsObject, R extends RedsObject> void registerBinaryOperation(OperatorType operatorType, BinaryOperation<T, U, R> function) {
        operationTable.registerBinaryOperation(operatorType, function);
    }

    public <U extends RedsObject, V extends RedsObject,R extends RedsObject> ComplexBinaryOperation<U, V, R>
    resolveBinaryOperation(OperatorType operatorType, Type<U> leftType, Type<V> rightType) {
        Optional<BinaryOperation> directOperation = operationTable.findBinaryOperation(operatorType, leftType, rightType);
        if (directOperation.isPresent()) {
            return new ComplexBinaryOperation.ComplexBinaryOperationBuilder<U, V, R>(directOperation.get()).build();
        }
        Map<Type<? extends RedsObject>, Conversion> leftConversions = conversionTable.compatibleTypes(leftType);
        Map<Type<? extends RedsObject>, BinaryOperation> operationWithInvariantRight =
            operationTable.compatibleLeftTypesWithBinaryOperation(operatorType, rightType);
        Set<Type<? extends RedsObject>> intermediateLeftType = Sets.intersection(leftConversions.keySet(), operationWithInvariantRight.keySet());
        if (intermediateLeftType.size() > 1) {
            throw new IllegalArgumentException("Ambiguous operation, multiple conversions possible.");
        }
        Map<Type<? extends RedsObject>, Conversion> rightConversions = conversionTable.compatibleTypes(rightType);
        Map<Type<? extends RedsObject>, BinaryOperation> operationWithInvariantLeft =
            operationTable.compatibleRightTypesWithBinaryOperation(operatorType, leftType);
        Set<Type<? extends RedsObject>> intermediateRightType = Sets.intersection(rightConversions.keySet(), operationWithInvariantLeft.keySet());
        if (intermediateRightType.size() + intermediateLeftType.size() > 1) {
            throw new IllegalArgumentException("Ambiguous operation, multiple conversions possible.");
        }
        if (intermediateRightType.size() + intermediateLeftType.size() == 0) {
            throw new IllegalArgumentException("Cannot resolve operation.");
        }
        if (intermediateLeftType.size() == 1) {
            return new ComplexBinaryOperation.ComplexBinaryOperationBuilder<U, V, R>(directOperation.get())
                .conversionLeft(leftConversions.get(intermediateLeftType.iterator().next()))
                .build();
        }
        return new ComplexBinaryOperation.ComplexBinaryOperationBuilder<U, V, R>(directOperation.get())
            .conversionRight(rightConversions.get(intermediateRightType.iterator().next()))
            .build();
    }

}
