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

    public <U extends RedsObject, V extends RedsObject, R extends RedsObject> ComplexUnaryOperation<U, V, R> resolveUnaryOperation(OperatorType operatorType, Type<U> type) {
        Optional<UnaryOperation> directOperation = operationTable.findUnaryOperation(operatorType, type);
        if (directOperation.isPresent()) {
            return Optional.of(new ComplexUnaryOperation<>(directOperation.get()));
        }
        Map<Type<? extends RedsObject>, Conversion> possibleConversions = conversionTable.compatibleTypes(type);
        Map<Type<? extends RedsObject>, UnaryOperation> compatibleTypes = operationTable.compatibleTypesWithUnaryOperation(operatorType);
        Sets.SetView<Type<? extends RedsObject>> commonIntermediateTypes = Sets.intersection(possibleConversions.keySet(), compatibleTypes.keySet());
        if (commonIntermediateTypes.isEmpty()) {
            throw new IllegalArgumentException(String.format("Cannot resolve operation %s %s", operatorType.toString(), type.getName()));
        }
        if (commonIntermediateTypes.size() > 1) {
            throw new IllegalArgumentException(String.format("Ambiguous operation %s %s, multiple conversions possible.", operatorType.toString(), type.getName()));
        }
        Type<V> intermediateType = (Type<V>) commonIntermediateTypes.iterator().next();
        return new ComplexUnaryOperation<U, V, R>(compatibleTypes.get(intermediateType), possibleConversions.get(intermediateType));
    }

    public <T extends RedsObject, U extends RedsObject, R extends RedsObject> void registerBinaryOperation(OperatorType operatorType, BinaryOperation<T, U, R> function) {
        operationTable.registerBinaryOperation(operatorType, function);
    }

    public <U extends RedsObject, V extends RedsObject, W extends RedsObject, X extends RedsObject, R extends RedsObject> Optional<BinaryOperation>
    resolveBinaryOperation(OperatorType operatorType, Type<U> leftType, Type<W> rightType) {
        // FIXME
        Optional<BinaryOperation> directOperation = operationTable.findBinaryOperation(operatorType, leftType, rightType);
        if (directOperation.isPresent()) {
            return directOperation;
        }
        Map<Type<? extends RedsObject>, Conversion> leftConversions = conversionTable.compatibleTypes(leftType);
        List<ComplexBinaryOperation<U, V, W, W, R>> operationWithLeftConversion = conversionComposedWithBinaryOperation(operatorType, rightType, leftConversions);
        Map<Type<? extends RedsObject>, Conversion> rightConversions = conversionTable.compatibleTypes(rightType);

    }

    private <U extends RedsObject, V extends RedsObject, W extends RedsObject, X extends RedsObject, R extends RedsObject> List<ComplexBinaryOperation<U, V, W, X, R>>
    conversionComposedWithBinaryOperation(OperatorType operatorType, Type typeToBeConverted, Map<Type, BinaryOperation> compatibleTypeMap) {
        Map<Type<? extends RedsObject>, Conversion> possibleConversionMap = conversionTable.compatibleTypes(typeToBeConverted);
        return compatibleTypeMap.entrySet().stream()
            .map(e -> {
                Conversion<U, V> leftConversion = (Conversion<U, V>) possibleConversionMap.get(e.getKey());
                return leftConversion == null ? null : new ComplexBinaryOperation.ComplexBinaryOperationBuilder(e.getValue()).conversionLeft(leftConversion).build();
            })
            .filter(o -> o != null)
            .collect(Collectors.toList());
    }

}
