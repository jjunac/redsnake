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

    public <T extends RedsObject> void registerUnaryOperation(OperatorType operatorType, Type<T> type, UnaryOperation<T, RedsObject> function) {
        unaryOperations.put(operatorType, type, function);
    }

    public <T extends RedsObject> Optional<UnaryOperation> resolveUnaryOperation(OperatorType operatorType, Type<T> type) {
        return Optional.ofNullable(unaryOperations.get(operatorType, type));
    }

    public Map<Type<? extends RedsObject>, UnaryOperation> typeCompatibleWithUnaryOperation(OperatorType operatorType) {
        return unaryOperations.row(operatorType);
    }

    public <T extends RedsObject, U extends RedsObject> void registerBinaryOperation(OperatorType operatorType, Type<T> type1, Type<U> type2, BinaryOperation<T, U, RedsObject> function) {
        binaryOperations.get(operatorType).put(type1, type2, function);
    }

    public <T extends RedsObject, U extends RedsObject> Optional<BinaryOperation> resolveBinaryOperation(OperatorType operatorType, Type<T> type1, Type<U> type2) {
        return Optional.ofNullable(binaryOperations.get(operatorType).get(type1, type2));
    }

    public Table<Type<? extends RedsObject>, Type<? extends RedsObject>, BinaryOperation> typeCompatibleWithBinaryOperation(OperatorType operatorType) {
        return binaryOperations.get(operatorType);
    }

}
