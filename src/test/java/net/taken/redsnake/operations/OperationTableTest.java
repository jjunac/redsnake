package net.taken.redsnake.operations;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import net.taken.redsnake.lang.RedsBoolean;
import net.taken.redsnake.lang.RedsInteger;
import net.taken.redsnake.lang.RedsString;
import net.taken.redsnake.reflect.Type;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class OperationTableTest {

    static final UnaryOperation<RedsBoolean, RedsBoolean> booleanMinusOperation = new UnaryOperation<>(RedsBoolean.TYPE, RedsBoolean.TYPE, x -> new RedsBoolean(!x.getValue()));
    static final UnaryOperation<RedsInteger, RedsInteger> integerMinusOperation = new UnaryOperation<>(RedsInteger.TYPE, RedsInteger.TYPE, x -> new RedsInteger(-x.getValue()));
    static final BinaryOperation<RedsInteger, RedsInteger, RedsInteger> integersAddOperation = new BinaryOperation<>(RedsInteger.TYPE, RedsInteger.TYPE, RedsInteger.TYPE, (x, y) -> new RedsInteger(x.getValue() + y.getValue()));
    static final BinaryOperation<RedsString, RedsString, RedsString> stringsAddOperation = new BinaryOperation<>(RedsString.TYPE, RedsString.TYPE, RedsString.TYPE, (x, y) -> new RedsString(x.getValue() + y.getValue()));
    OperationTable table;

    @BeforeEach
    void setUp() {
        table = new OperationTable();
    }

    private void unarySetUp() {
        table.unaryOperations.put(OperatorType.MINUS, RedsInteger.TYPE, integerMinusOperation);
        table.unaryOperations.put(OperatorType.MINUS, RedsBoolean.TYPE, booleanMinusOperation);
    }

    private void binarySetUp() {
        table.binaryOperations.get(OperatorType.PLUS).put(RedsInteger.TYPE, RedsInteger.TYPE, integersAddOperation);
        table.binaryOperations.get(OperatorType.PLUS).put(RedsString.TYPE, RedsString.TYPE, stringsAddOperation);
    }

    @Test
    void shouldResolveUnaryOperationWhenTypeIsMatching() {
        unarySetUp();
        Optional<UnaryOperation> actual = table.resolveUnaryOperation(OperatorType.MINUS, RedsInteger.TYPE);
        assertTrue(actual.isPresent());
        assertEquals(integerMinusOperation, actual.get());
    }

    @Test
    void shouldReturnAllTheTypeCompatibleWithAUnaryOperation() {
        unarySetUp();
        HashMap<Type, UnaryOperation> expected = new HashMap<>();
        expected.put(RedsInteger.TYPE, integerMinusOperation);
        expected.put(RedsBoolean.TYPE, booleanMinusOperation);
        assertEquals(expected, table.typeCompatibleWithUnaryOperation(OperatorType.MINUS));
    }

    @Test
    void shouldRegisterUnaryOperation() {
        unarySetUp();
        UnaryOperation<RedsInteger, RedsInteger> integerPlusOperation = new UnaryOperation<>(RedsInteger.TYPE, RedsInteger.TYPE, x -> new RedsInteger(x.getValue()));
        table.registerUnaryOperation(OperatorType.PLUS, integerPlusOperation);
        assertEquals(integerPlusOperation, table.unaryOperations.row(OperatorType.PLUS).get(RedsInteger.TYPE));
    }

    @Test
    void shouldResolveBinaryOperationWhenTypeIsMatching() {
        binarySetUp();
        Optional<BinaryOperation> actual = table.resolveBinaryOperation(OperatorType.PLUS, RedsInteger.TYPE, RedsInteger.TYPE);
        assertTrue(actual.isPresent());
        assertEquals(integersAddOperation, actual.get());
    }

    @Test
    void shouldReturnAllTheTypeCompatibleWithABinaryOperation() {
        binarySetUp();
        Table<Type, Type, BinaryOperation> expected = HashBasedTable.create();
        expected.put(RedsInteger.TYPE, RedsInteger.TYPE, integersAddOperation);
        expected.put(RedsString.TYPE, RedsString.TYPE, stringsAddOperation);
        assertEquals(expected, table.typeCompatibleWithBinaryOperation(OperatorType.PLUS));
    }

    @Test
    void shouldRegisterBinaryOperation() {
        binarySetUp();
        BinaryOperation<RedsInteger, RedsInteger, RedsInteger> integerMinusOperation = new BinaryOperation<>(RedsInteger.TYPE, RedsInteger.TYPE, RedsInteger.TYPE, (x, y) -> new RedsInteger(x.getValue() - y.getValue()));
        table.registerBinaryOperation(OperatorType.MINUS, integerMinusOperation);
        assertEquals(integerMinusOperation, table.binaryOperations.get(OperatorType.MINUS).get(RedsInteger.TYPE, RedsInteger.TYPE));
    }
}
