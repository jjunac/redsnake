package net.taken.arcanum.parser;

import com.sun.istack.internal.NotNull;
import net.taken.arcanum.domain.ArcaInteger;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static net.taken.arcanum.parser.ArcanumParser.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ArcanumVisitorTest {

    private ArcanumVisitor visitor;

    @BeforeEach
    void setUp() {
        visitor = new ArcanumVisitor();
    }

    private ArcanumParser initParser(@NotNull String s) {
        CharStream inputStream = CharStreams.fromString(s);
        ArcanumLexer arcanumLexer = new ArcanumLexer(inputStream);
        TokenStream commonTokenStream = new CommonTokenStream(arcanumLexer);
        return new ArcanumParser(commonTokenStream);
    }

    @Test
    void shouldReturnIntValueWhenVisitInt() {
        IntContext intContext = mock(IntContext.class);
        when(intContext.getText()).thenReturn("42");
        assertEquals(new ArcaInteger(42), visitor.visitInt(intContext));
    }

}