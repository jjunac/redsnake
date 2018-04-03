package net.taken.redsnake.operations;

import net.taken.redsnake.BuiltInType;
import org.junit.jupiter.api.BeforeEach;

class OperationTableTest {

    OperationTable table;

    @BeforeEach
    void setUp() {
        table = new OperationTable();
    }

    void unarySetUp() {
        table.registerUnaryOperation(OperatorType.MINUS, BuiltInType.INTEGER, );

    }
}
