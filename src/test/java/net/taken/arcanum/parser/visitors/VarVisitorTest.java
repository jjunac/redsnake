package net.taken.arcanum.parser.visitors;

import net.taken.arcanum.lang.ArcaString;
import net.taken.arcanum.parser.ArcanumParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static net.taken.arcanum.parser.visitors.TestUtils.mockContext;
import static org.junit.jupiter.api.Assertions.*;

class VarVisitorTest {

    private VarVisitor visitor;

    @BeforeEach
    void setUp() {
        visitor = new VarVisitor(new ArcanumVisitor());
    }

    @Test
    void shouldReturnIDValueWhenVisitVar() {
        ArcanumParser.VarContext varContext = mockContext(ArcanumParser.VarContext.class, "testvar");
        assertEquals(new ArcaString("testvar"), visitor.visitVar(varContext));
    }

}