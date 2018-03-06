package net.taken.arcanum.parser.visitors;

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
        assertEquals("-435509825", wrt.toString());
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
        assertEquals("-435509825" + System.lineSeparator(), wrt.toString());
    }
}