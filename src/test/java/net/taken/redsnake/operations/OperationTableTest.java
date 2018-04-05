package net.taken.redsnake.operations;

import net.taken.redsnake.lang.RedsBoolean;
import net.taken.redsnake.lang.RedsInteger;
import net.taken.redsnake.lang.RedsObject;
import net.taken.redsnake.reflect.Type;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class OperationTableTest {

    static final UnaryOperation<RedsBoolean, RedsObject> booleanMinusOperation = new UnaryOperation<>(x -> new RedsBoolean(!x.getValue()));
    static final UnaryOperation<RedsInteger, RedsObject> integerMinusFunction = new UnaryOperation<>(x -> new RedsInteger(-x.getValue()));
    OperationTable table;

    @BeforeEach
    void setUp() {
        table = new OperationTable();
    }

    void unarySetUp() {
        table.unaryOperations.put(OperatorType.MINUS, RedsInteger.TYPE, integerMinusFunction);
        table.unaryOperations.put(OperatorType.MINUS, RedsBoolean.TYPE, booleanMinusOperation);
    }

    @Test
    void shouldResolveUnaryOperationWhenTypeIsMatching() {
        unarySetUp();
        Optional<UnaryOperation> actual = table.resolveUnaryOperation(OperatorType.MINUS, RedsInteger.TYPE);
        assertTrue(actual.isPresent());
        assertEquals(integerMinusFunction, actual.get());
    }

    @Test
    void shouldReturnAllTheTypeCompatibleWithAnOperation() {
        unarySetUp();
        HashMap<Type, UnaryOperation> expected = new HashMap<>();
        expected.put(RedsInteger.TYPE, integerMinusFunction);
        expected.put(RedsBoolean.TYPE, booleanMinusOperation);
        assertEquals(expected, table.typeCompatibleWithUnaryOperation(OperatorType.MINUS));
    }

    @Test
    void shouldRegisterOperation() {
        unarySetUp();
        UnaryOperation<RedsInteger, RedsObject> integerPlusFunction = new UnaryOperation<>(x -> new RedsInteger(x.getValue()));
        table.registerUnaryOperation(OperatorType.PLUS, RedsInteger.TYPE, integerPlusFunction);
        assertEquals(integerPlusFunction, table.unaryOperations.row(OperatorType.PLUS).get(RedsInteger.TYPE));
    }
}
