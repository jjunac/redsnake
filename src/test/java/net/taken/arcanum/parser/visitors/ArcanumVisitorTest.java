package net.taken.arcanum.parser.visitors;

import com.google.common.base.Strings;
import net.taken.arcanum.lang.*;
import net.taken.arcanum.parser.ArcanumParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.StringWriter;

import static net.taken.arcanum.tree.TestUtils.*;
import static org.junit.jupiter.api.Assertions.*;



class ArcanumVisitorTest {

    private StringWriter wrt;
    private ArcaEnvironment env;

    @BeforeEach
    void setUp() {
        wrt = new StringWriter();
        env = new ArcaEnvironment(wrt);
    }

    @Test
    void shouldPrintRightResultWhenPrintCalculationWithoutParen() {
        parseProgram("print (946+-19*185**2-687)*670+895").execute(env);
        assertEquals("-435509825" + System.lineSeparator(), wrt.toString());
    }

    @Test
    void shouldPrintRightResultWhenPrintCalculationWithoutParenWithENDL() {
        parseProgram("print (946+-19*185**2-687)*670+895\n").execute(env);
        assertEquals("-435509825" + System.lineSeparator(), wrt.toString());
    }

    @Test
    void shouldPrintRightResultWhenPrintCalculationWithParen() {
        parseProgram("print((946+-19*185**2-687)*670+895)").execute(env);
        assertEquals("-435509825" + System.lineSeparator(), wrt.toString());
    }

    @Test
    void shouldPrintRightResultWhenDoingCalculationsWithVariable() {
        parseProgram("a = 42", "a = a - 40", "a = a * 2", "print a").execute(env);
        assertEquals("4" + System.lineSeparator(), wrt.toString());
    }

    @Test
    void shouldPrintRightResultWhenWorkingAroundCallsAndVariables() {
        parseProgram("a = 51\n" +
                "a = a - 42\n" +
                "print\n" +
                "print (a)*678\n").execute(env);
        assertEquals(System.lineSeparator() + "6102" + System.lineSeparator(), wrt.toString());
    }

    @Test
    void shouldPrintRightResultWhenDoingOperationOnStrings() {
        parseProgram("a = \"WRjfMs\"\n" +
            "a = a * 3\n" +
            "a = a + \"mnV\"\n" +
            "a = a * \"3\"\n" +
            "print a").execute(env);
        String expected = Strings.repeat("WRjfMs", 3);
        expected += "mnV";
        expected = Strings.repeat(expected, 3);
        assertEquals(expected + System.lineSeparator(), wrt.toString());
    }

    @Test
    void shouldPrintRightResultWhenPrintingMultipleParametersOfDifferentType() {
        parseProgram("print 60, \"iYQaKswI\"").execute(env);
        assertEquals("60 iYQaKswI" + System.lineSeparator(), wrt.toString());
    }

    @Test
    void shouldReturnSomethingWhenParsingStmt() {
        ArcaObject actual =parseProgram("598").execute(env);
        assertEquals(new ArcaInteger(598), actual);
    }

    @Test
    void shouldReturnNullWhenParsePrint() {
        ArcaObject actual = parseProgram("print 875").execute(env);
        assertTrue(actual.isNull());
    }

    @Test
    void shouldBeRightAssociativeWhenVisitBinaryExprPow() {
        ArcaObject actual = parseProgram("2**3**2").execute(env);
        assertEquals(new ArcaInteger(512), actual);
    }
}
