package net.taken.arcanum.parser.visitors;

import net.taken.arcanum.lang.ArcaInteger;
import net.taken.arcanum.lang.ArcaString;
import net.taken.arcanum.parser.ArcanumParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static net.taken.arcanum.parser.ArcanumParser.*;
import static net.taken.arcanum.tree.expressions.TestUtils.*;
import static org.junit.jupiter.api.Assertions.*;

class ExprVisitorTest {

    private ExprVisitor visitor;

    @BeforeEach
    void setUp() {
        visitor = new ExprVisitor(new ArcanumVisitor());
    }

    @Test
    void shouldReturnIntValueWhenVisitInt() {
        IntContext intContext = mockContext(IntContext.class, "42");
        assertEquals(new ArcaInteger(42), visitor.visitInt(intContext));
    }

    @Test
    void shouldReturnStringValueWhenVisitString() {
        StringContext stringContext = mockContext(StringContext.class, "VUUOWIp");
        assertEquals(new ArcaString("VUUOWIp"), visitor.visitString(stringContext));
    }

}
