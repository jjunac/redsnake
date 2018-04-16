package net.taken.redsnake.operations;

import com.google.common.annotations.VisibleForTesting;
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

    public <T extends RedsObject, U extends RedsObject, R extends RedsObject> void registerBinaryOperation(OperatorType operatorType, BinaryOperation<T, U, R> function) {
        operationTable.registerBinaryOperation(operatorType, function);
    }

    public <U extends RedsObject, V extends RedsObject, W extends RedsObject, X extends RedsObject, R extends RedsObject> Optional<BinaryOperation>
    resolveBinaryOperation(OperatorType operatorType, Type<U> leftType, Type<W> rightType) {
        Optional<BinaryOperation> directOperation = operationTable.findBinaryOperation(operatorType, leftType, rightType);
        if (directOperation.isPresent()) {
            return directOperation;
        }
        Map<Type<? extends RedsObject>, Conversion> leftConversions = conversionTable.compatibleTypes(leftType);
        List<ComplexBinaryOperation<U, V, W, W, R>> operationWithLeftConversion = conversionComposedWithBinaryOperation(operatorType, rightType, leftConversions);
        Map<Type<? extends RedsObject>, Conversion> rightConversions = conversionTable.compatibleTypes(rightType);

    }

    private <U extends RedsObject, V extends RedsObject, W extends RedsObject, X extends RedsObject, R extends RedsObject> List<ComplexBinaryOperation<U, V, W, X, R>>
    conversionComposedWithBinaryOperation(OperatorType operatorType, Type<> typeToBeConverted, Type invariantType) {
        return operationTable.compatibleTypesWithBinaryOperation(operatorType).column(invariantType).entrySet().stream()
            .map(e -> {
                Conversion leftConversion = conversionTable.compatibleTypes(typeToBeConverted).get(e.getKey());
                return leftConversion == null ? null : new ComplexBinaryOperation.ComplexBinaryOperationBuilder(e.getValue()).conversionLeft(leftConversion).build();
            })
            .filter(o -> o != null)
            .collect(Collectors.toList());
    }

}
