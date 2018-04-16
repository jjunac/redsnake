package net.taken.redsnake.operations;

import com.google.common.annotations.VisibleForTesting;
import net.taken.redsnake.lang.RedsObject;

import java.util.Optional;

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

    public <T extends RedsObject, R extends RedsObject> Optional<>

    public <T extends RedsObject, U extends RedsObject, R extends RedsObject> void registerBinaryOperation(OperatorType operatorType, BinaryOperation<T, U, R> function) {
        operationTable.registerBinaryOperation(operatorType, function);
    }

}
