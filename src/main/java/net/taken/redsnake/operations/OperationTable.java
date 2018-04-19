package net.taken.redsnake.operations;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import net.taken.redsnake.lang.RedsObject;
import net.taken.redsnake.reflect.Type;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;
import java.util.Optional;

public class OperationTable {

    @VisibleForTesting
    Table<OperatorType, Type<? extends RedsObject>, UnaryOperation> unaryOperations;
    @VisibleForTesting
    Map<OperatorType, Table<Type<? extends RedsObject>, Type<? extends RedsObject>, BinaryOperation>> binaryOperations;

    public OperationTable() {
        this.unaryOperations = HashBasedTable.create();
        this.binaryOperations = new EnumMap<>(OperatorType.class);
        Arrays.stream(OperatorType.values()).forEach(t -> binaryOperations.put(t, HashBasedTable.create()));
    }

    public <T extends RedsObject, R extends RedsObject> void registerUnaryOperation(OperatorType operatorType, UnaryOperation<T, R> function) {
        unaryOperations.put(operatorType, function.getTypeArg(), function);
    }

    public <T extends RedsObject> Optional<UnaryOperation> findUnaryOperation(OperatorType operatorType, Type<T> type) {
        return Optional.ofNullable(unaryOperations.get(operatorType, type));
    }

    public Map<Type<? extends RedsObject>, UnaryOperation> compatibleTypesWithUnaryOperation(OperatorType operatorType) {
        return unaryOperations.row(operatorType);
    }

    public <T extends RedsObject, U extends RedsObject, R extends RedsObject> void registerBinaryOperation(OperatorType operatorType, BinaryOperation<T, U, R> function) {
        binaryOperations.get(operatorType).put(function.getTypeArg1(), function.getTypeArg2(), function);
    }

    public <T extends RedsObject, U extends RedsObject> Optional<BinaryOperation> findBinaryOperation(OperatorType operatorType, Type<T> type1, Type<U> type2) {
        return Optional.ofNullable(binaryOperations.get(operatorType).get(type1, type2));
    }

    public Map<Type<? extends RedsObject>, BinaryOperation> compatibleRightTypesWithBinaryOperation(OperatorType operatorType, Type<? extends RedsObject> leftType) {
        return binaryOperations.get(operatorType).row(leftType);
    }

    public Map<Type<? extends RedsObject>, BinaryOperation> compatibleLeftTypesWithBinaryOperation(OperatorType operatorType, Type<? extends RedsObject> rightType) {
        return binaryOperations.get(operatorType).column(rightType);
    }

}
