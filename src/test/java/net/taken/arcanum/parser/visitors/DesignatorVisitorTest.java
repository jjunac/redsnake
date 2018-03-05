package net.taken.arcanum.parser.visitors;

import net.taken.arcanum.lang.ArcaInteger;
import net.taken.arcanum.lang.ArcaString;
import net.taken.arcanum.parser.ArcanumParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static net.taken.arcanum.parser.visitors.TestUtils.initParser;
import static net.taken.arcanum.parser.visitors.TestUtils.mockArcaFunction;
import static net.taken.arcanum.parser.visitors.TestUtils.mockContext;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DesignatorVisitorTest {

    private DesignatorVisitor visitor;

    @BeforeEach
    void setUp() {
        visitor = new DesignatorVisitor(new ArcanumVisitor());
    }

    @Test
    void shouldReturnVariableValueWhenVisitVarDesignator() {
        ArcanumParser.VarDesignatorContext ctx = mock(ArcanumParser.VarDesignatorContext.class);
        ArcanumParser.VarContext testvar = mockContext(ArcanumParser.VarContext.class, "testvar");
        when(ctx.var()).thenReturn(testvar);
        visitor.environment.putVariable(new ArcaString("testvar"), new ArcaInteger(223));
        assertEquals(new ArcaInteger(223), visitor.visitVarDesignator(ctx));
    }

    @Test
    void shouldCallFunctionWhenVisitVarDesignatorAndVarDoesntExist() {
        ArcanumParser.VarDesignatorContext ctx = mock(ArcanumParser.VarDesignatorContext.class);
        ArcanumParser.VarContext testvar = mockContext(ArcanumParser.VarContext.class, "testFunction");
        when(ctx.var()).thenReturn(testvar);
        visitor.environment.putFunction(new ArcaString("testFunction"), mockArcaFunction(new ArcaInteger(22)));
        assertEquals(new ArcaInteger(22), visitor.visitVarDesignator(ctx));
    }

    @Test
    void shouldCallFunctionWhenVisitVarDesignatorButVarDoesntExist() {
        ArcanumParser parser = initParser("-(2)**2");
        ArcaInteger actual = (ArcaInteger) visitor.visit(parser.expr());
        assertEquals(new ArcaInteger(-4), actual);
    }

}