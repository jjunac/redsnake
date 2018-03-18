package net.taken.arcanum.tree;

import com.google.common.base.Strings;
import net.taken.arcanum.lang.ArcaEnvironment;
import net.taken.arcanum.lang.ArcaInteger;
import net.taken.arcanum.lang.ArcaObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.StringWriter;

import static net.taken.arcanum.tree.TestUtils.parseProgram;
import static org.junit.jupiter.api.Assertions.*;

class ProgramTest {

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
        parseProgram("a = \"Gloater\"\n" +
            "a = a * 3\n" +
            "a = a + \"Mafurra\"\n" +
            "a = a * \"3\"\n" +
            "print a").execute(env);
        String expected = Strings.repeat("Gloater", 3);
        expected += "Mafurra";
        expected = Strings.repeat(expected, 3);
        assertEquals(expected + System.lineSeparator(), wrt.toString());
    }

    @Test
    void shouldPrintRightResultWhenPrintingMultipleParametersOfDifferentType() {
        parseProgram("print 60, \"Unjuiced\"").execute(env);
        assertEquals("60 Unjuiced" + System.lineSeparator(), wrt.toString());
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

}
