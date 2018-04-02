package net.taken.redsnake.operations;

import net.taken.redsnake.BuiltinType;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

class OperationTableTest {

    OperationTable table;

    @BeforeEach
    void setUp() {
        table = new OperationTable();
    }

    void unarySetUp() {
        table.registerUnaryOperation(OperatorType.MINUS, BuiltinType.INTEGER, );

    }
}
