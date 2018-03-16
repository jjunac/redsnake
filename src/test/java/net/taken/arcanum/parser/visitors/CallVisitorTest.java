package net.taken.arcanum.parser.visitors;

import net.taken.arcanum.lang.ArcaInteger;
import net.taken.arcanum.lang.ArcaList;
import net.taken.arcanum.lang.ArcaObject;
import net.taken.arcanum.lang.ArcaString;
import net.taken.arcanum.parser.ArcanumParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.function.Function;

import static net.taken.arcanum.tree.expressions.TestUtils.mockArcaFunction;
import static net.taken.arcanum.tree.expressions.TestUtils.mockContext;
import static net.taken.arcanum.tree.expressions.TestUtils.mockNode;
import static org.mockito.Mockito.mock;

class CallVisitorTest {

    private CallVisitor visitor;

    @BeforeEach
    void setUp() {
        visitor = new CallVisitor(new ArcanumVisitor());
    }

    @Test
    void shouldCallFunctionWhenVisitCallWithParams() {
        Function<ArcaList, ArcaObject> fct = mockArcaFunction(new ArcaInteger(42));
        visitor.environment.putFunction(new ArcaString("unitTestFunction"), fct);
        ArcanumParser.CallWithParamsContext ctx = mock(ArcanumParser.CallWithParamsContext.class);
        ctx.fct = mockContext(ArcanumParser.VarContext.class, "unitTestFunction");
        ctx.args = mockNode(ArcanumParser.ParamsContext.class, new ArcaList());
        assertEquals(new ArcaInteger(42), visitor.visitCallWithParams(ctx));
    }

    @Test
    void shouldCallFunctionWhenVisitCallWithoutParams() {
        Function<ArcaList, ArcaObject> fct = mockArcaFunction(new ArcaInteger(107));
        visitor.environment.putFunction(new ArcaString("unitTestFunction"), fct);
        ArcanumParser.CallWithoutParamsContext ctx = mock(ArcanumParser.CallWithoutParamsContext.class);
        ctx.fct = mockContext(ArcanumParser.VarContext.class, "unitTestFunction");
        assertEquals(new ArcaInteger(107), visitor.visitCallWithoutParams(ctx));
    }

}
