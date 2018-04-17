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
        Type intermediateType = computeIntermediateType(operatorType, possibleConversions.keySet(), compatibleTypes.keySet());
        return new ComplexUnaryOperation<U, R>(compatibleTypes.get(intermediateType), possibleConversions.get(intermediateType));
    }

    public <T extends RedsObject, U extends RedsObject, R extends RedsObject> void registerBinaryOperation(OperatorType operatorType, BinaryOperation<T, U, R> function) {
        operationTable.registerBinaryOperation(operatorType, function);
    }

    public <U extends RedsObject, V extends RedsObject,R extends RedsObject> BinaryOperation<U, V, R>
    resolveBinaryOperation(OperatorType operatorType, Type<U> leftType, Type<V> rightType) {
        Optional<BinaryOperation> directOperation = operationTable.findBinaryOperation(operatorType, leftType, rightType);
        if (directOperation.isPresent()) {
            return directOperation.get();
        }
        Map<Type<? extends RedsObject>, Conversion> leftConversions = conversionTable.compatibleTypes(leftType);
        Map<Type<? extends RedsObject>, BinaryOperation<? extends RedsObject, V, R>> operationWithInvarientRight = operationTable.

    }

    private Type computeIntermediateType(OperatorType operatorType, Set<Type<? extends RedsObject>> possibleConversions, Set<Type<? extends RedsObject>> compatibleTypes) {
        Sets.SetView<Type<? extends RedsObject>> commonIntermediateTypes = Sets.intersection(possibleConversions, compatibleTypes);
        if (commonIntermediateTypes.isEmpty()) {
            throw new IllegalArgumentException("Cannot resolve operation");
        }
        if (commonIntermediateTypes.size() > 1) {
            throw new IllegalArgumentException("Ambiguous operation, multiple conversions possible.");
        }
        return commonIntermediateTypes.iterator().next();
    }

}
