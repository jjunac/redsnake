package net.taken.arcanum.parser.visitors;

import com.google.common.base.Strings;
import net.taken.arcanum.lang.*;
import net.taken.arcanum.parser.ArcanumParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.StringWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

import static net.taken.arcanum.parser.ArcanumParser.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static net.taken.arcanum.parser.visitors.TestUtils.*;



class ArcanumVisitorTest {

    private ArcanumVisitor visitor;
    private StringWriter wrt;

    @BeforeEach
    void setUp() {
        wrt = new StringWriter();
        visitor = new ArcanumVisitor(new ArcaEnvironment(wrt));
    }

    @Test
    void shouldPrintRightResultWhenPrintCalculationWithoutParen() {
        ArcanumParser parser = initParser("print (946+-19*185**2-687)*670+895");
        visitor.visit(parser.program());
        assertEquals("-435509825" + System.lineSeparator(), wrt.toString());
    }

    @Test
    void shouldPrintRightResultWhenPrintCalculationWithoutParenWithENDL() {
        ArcanumParser parser = initParser("print (946+-19*185**2-687)*670+895\n");
        visitor.visit(parser.program());
        assertEquals("-435509825" + System.lineSeparator(), wrt.toString());
    }

    @Test
    void shouldPrintRightResultWhenPrintCalculationWithParen() {
        ArcanumParser parser = initParser("print((946+-19*185**2-687)*670+895)");
        visitor.visit(parser.program());
        assertEquals("-435509825" + System.lineSeparator(), wrt.toString());
    }

    @Test
    void shouldPrintRightResultWhenDoingCalculationsWithVariable() {
        ArcanumParser parser = initParser("a = 42", "a = a - 40", "a = a * 2", "print a");
        ArcaObject o = visitor.visit(parser.program());
        assertEquals("4" + System.lineSeparator(), wrt.toString());
    }

    @Test
    void shouldPrintRightResultWhenWorkingAroundCallsAndVariables() {
        ArcanumParser parser = initParser("a = 51\n" +
                "a = a - 42\n" +
                "print\n" +
                "print (a)*678\n");
        visitor.visit(parser.program());
        assertEquals(System.lineSeparator() + "6102" + System.lineSeparator(), wrt.toString());
    }

    @Test
    void shouldPrintRightResultWhenDoingOperationOnStrings() {
        ArcanumParser parser = initParser("a = \"WRjfMs\"\n" +
            "a = a * 3\n" +
            "a = a + \"mnV\"\n" +
            "a = a * \"3\"\n" +
            "print a");
        visitor.visit(parser.program());
        String expected = Strings.repeat("WRjfMs", 3);
        expected += "mnV";
        expected = Strings.repeat(expected, 3);
        assertEquals(expected + System.lineSeparator(), wrt.toString());
    }

    @Test
    void shouldPrintRightResultWhenPrintingMultipleParametersOfDifferentType() {
        ArcanumParser parser = initParser("print 60, \"iYQaKswI\"");
        visitor.visit(parser.program());
        assertEquals("60 iYQaKswI" + System.lineSeparator(), wrt.toString());
    }

    @Test
    void shouldreturnSomethingWhenParsingStmt() {
        ArcanumParser parser = initParser("598");
        ArcaObject res = visitor.visit(parser.stmt());
        assertEquals(new ArcaInteger(598), res);
    }
}
