package net.taken.redsnake.operations;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import net.taken.redsnake.lang.RedsBoolean;
import net.taken.redsnake.lang.RedsInteger;
import net.taken.redsnake.lang.RedsObject;
import net.taken.redsnake.lang.RedsString;
import net.taken.redsnake.reflect.Type;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class OperationTableTest {

    static final UnaryOperation<RedsBoolean, RedsObject> booleanMinusOperation = new UnaryOperation<>(x -> new RedsBoolean(!x.getValue()));
    static final UnaryOperation<RedsInteger, RedsObject> integerMinusOperation = new UnaryOperation<>(x -> new RedsInteger(-x.getValue()));
    static final BinaryOperation<RedsInteger, RedsInteger, RedsInteger> integersAddOperation = new BinaryOperation<>((x, y) -> new RedsInteger(x.getValue() + y.getValue()));
    static final BinaryOperation<RedsString, RedsString, RedsString> stringsAddOperation = new BinaryOperation<>((x, y) -> new RedsString(x.getValue() + y.getValue()));
    OperationTable table;

    @BeforeEach
    void setUp() {
        table = new OperationTable();
    }

    void unarySetUp() {
        table.unaryOperations.put(OperatorType.MINUS, RedsInteger.TYPE, integerMinusOperation);
        table.unaryOperations.put(OperatorType.MINUS, RedsBoolean.TYPE, booleanMinusOperation);
    }

    void binarySetUp() {
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
        UnaryOperation<RedsInteger, RedsObject> integerPlusOperation = new UnaryOperation<>(x -> new RedsInteger(x.getValue()));
        table.registerUnaryOperation(OperatorType.PLUS, RedsInteger.TYPE, integerPlusOperation);
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
        BinaryOperation<RedsInteger, RedsInteger, RedsObject> integerMinusOperation = new BinaryOperation<>((x, y) -> new RedsInteger(x.getValue() - y.getValue()));
        table.registerBinaryOperation(OperatorType.MINUS, RedsInteger.TYPE, RedsInteger.TYPE, integerMinusOperation);
        assertEquals(integerMinusOperation, table.binaryOperations.get(OperatorType.MINUS).get(RedsInteger.TYPE, RedsInteger.TYPE));
    }
}
