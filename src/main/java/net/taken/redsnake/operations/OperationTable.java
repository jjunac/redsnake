package net.taken.redsnake.operations;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import net.taken.redsnake.lang.RedsObject;
import net.taken.redsnake.reflect.Type;

import java.util.Map;
import java.util.Optional;

public class OperationTable {

    @VisibleForTesting
    Table<OperatorType, Type<? extends RedsObject>, UnaryOperation> unaryOperations;

    public OperationTable() {
        this.unaryOperations = HashBasedTable.create();
    }

    public <T extends RedsObject> void registerUnaryOperation(OperatorType operatorType, Type<T> type, UnaryOperation<T, RedsObject> function) {
        unaryOperations.put(operatorType, type, function);
    }

    public <T extends RedsObject> Optional<UnaryOperation> resolveUnaryOperation(OperatorType operatorType, Type<T> type) {
        return Optional.ofNullable(unaryOperations.row(operatorType).get(type));
    }

    public Map<Type<? extends RedsObject>, UnaryOperation> typeCompatibleWithUnaryOperation(OperatorType operatorType) {
        return unaryOperations.row(operatorType);
    }

}
