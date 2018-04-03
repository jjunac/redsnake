package net.taken.redsnake.operations;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import net.taken.redsnake.reflect.Type;

import java.util.Map;
import java.util.Optional;

public class OperationTable {

    Table<OperatorType, Type, Operation> unaryOperations;

    public OperationTable() {
        this.unaryOperations = HashBasedTable.create();
    }

    public void registerUnaryOperation(OperatorType operatorType, Type type, Operation operation) {
        unaryOperations.put(operatorType, type, operation);
    }

    public void registerBinaryOperation(Operation operation, OperatorType operatorType, boolean isCommutative, Type type1,Type type2) {

    }

    public Optional<Operation> resolveUnaryOperation(OperatorType operatorType, Type type) {
        return Optional.ofNullable(unaryOperations.row(operatorType).get(type));
    }

    public Map<Type, Operation> typeCompatibleWithUnaryOperation(OperatorType operatorType) {
        return unaryOperations.row(operatorType);
    }

}
