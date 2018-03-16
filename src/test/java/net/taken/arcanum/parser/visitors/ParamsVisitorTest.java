package net.taken.arcanum.parser.visitors;

import net.taken.arcanum.lang.ArcaInteger;
import net.taken.arcanum.lang.ArcaList;
import net.taken.arcanum.lang.ArcaString;
import net.taken.arcanum.parser.ArcanumParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static net.taken.arcanum.tree.expressions.TestUtils.mockNode;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ParamsVisitorTest {

    private ParamsVisitor visitor;

    @BeforeEach
    void setUp() {
        visitor = new ParamsVisitor(new ArcanumVisitor());
    }

    @Test
    void shouldReturnListWhenVisitParams() {
        ArcanumParser.ParamsContext ctx = mock(ArcanumParser.ParamsContext.class);
        List<ArcanumParser.ExprContext> expr = new LinkedList<>();
        expr.add(mockNode(ArcanumParser.ExprContext.class, new ArcaInteger(24)));
        expr.add(mockNode(ArcanumParser.ExprContext.class, new ArcaString("yHBpQI")));
        expr.add(mockNode(ArcanumParser.ExprContext.class, new ArcaList(new ArcaInteger(881))));
        when(ctx.expr()).thenReturn(expr);
        assertEquals(new ArcaList(new ArcaInteger(24), new ArcaString("yHBpQI"), new ArcaList(new ArcaInteger(881))),
                visitor.visitParams(ctx));
    }

}
