package net.taken.arcanum.parser.visitors;

import com.google.common.collect.ImmutableList;
import net.taken.arcanum.parser.ArcanumParser;
import net.taken.arcanum.parser.ArcanumLexer;
import net.taken.arcanum.parser.ArcanumParserBaseVisitor;
import net.taken.arcanum.tree.*;
import net.taken.arcanum.tree.statements.Statement;
import net.taken.arcanum.tree.statements.expressions.designators.CallDesignator;
import net.taken.arcanum.tree.statements.expressions.designators.Designator;
import net.taken.arcanum.tree.statements.expressions.designators.VariableDesignator;
import net.taken.arcanum.tree.statements.expressions.*;
import net.taken.arcanum.tree.statements.expressions.literals.BooleanLiteral;
import net.taken.arcanum.tree.statements.expressions.literals.IntegerLiteral;
import net.taken.arcanum.tree.statements.expressions.literals.StringLiteral;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

public class ASTBuilder extends ArcanumParserBaseVisitor<Node> {

    @Override
    public Node visitProgram(ArcanumParser.ProgramContext ctx) {
        List<Statement> statements = ImmutableList.of();
        if (ctx.statements() != null) {
            statements = visit(ctx.statements().statement(), Statement.class);
        }
        return new Program(statements);
    }

    @Override
    public Node visitStatement(ArcanumParser.StatementContext ctx) {
        return visit(ctx.expression());
    }

    @Override
    public Node visitIntegerLiteral(ArcanumParser.IntegerLiteralContext ctx) {
        return new IntegerLiteral(ctx.getText());
    }

    @Override
    public Node visitBooleanLiteral(ArcanumParser.BooleanLiteralContext ctx) {
        return new BooleanLiteral(ctx.getText());
    }

    @Override
    public Node visitStringLiteral(ArcanumParser.StringLiteralContext ctx) {
        return new StringLiteral(ctx.getText());
    }

    @Override
    public Node visitArithmeticBinary(ArcanumParser.ArithmeticBinaryContext ctx) {
        return new ArithmeticBinaryExpression(getArithmeticBinaryOperator(ctx.op), (Expression) visit(ctx.l), (Expression) visit(ctx.r));
    }

    @Override
    public Node visitArithmeticUnary(ArcanumParser.ArithmeticUnaryContext ctx) {
        return new ArithmeticUnaryExpression(getArithmeticUnaryOperator(ctx.op), (Expression) visit(ctx.e));
    }

    @Override
    public Node visitAssignment(ArcanumParser.AssignmentContext ctx) {
        return new Assignment((Variable) visit(ctx.variable()), (Expression) visit(ctx.expression()));
    }

    @Override
    public Node visitSubExpression(ArcanumParser.SubExpressionContext ctx) {
        return new SubExpression((Expression) visit(ctx.expression()));
    }

    @Override
    public Node visitDesignatorExpression(ArcanumParser.DesignatorExpressionContext ctx) {
        return new DesignatorExpression((Designator) visit(ctx.designator()));
    }

    @Override
    public Node visitVariableDesignator(ArcanumParser.VariableDesignatorContext ctx) {
        return new VariableDesignator((Variable) visit(ctx.variable()));
    }

    @Override
    public Node visitCallDesignator(ArcanumParser.CallDesignatorContext ctx) {
        return new CallDesignator((Call) visit(ctx.call()));
    }

    @Override
    public Node visitCall(ArcanumParser.CallContext ctx) {
        List<Expression> parameters = ImmutableList.of();
        if (ctx.parameters() != null) {
            parameters = visit(ctx.parameters().expression(), Expression.class);
        }
        return new Call((Variable) visit(ctx.fct), parameters);
    }

    @Override
    public Node visitVariable(ArcanumParser.VariableContext ctx) {
        return new Variable(ctx.getText());
    }

    private <T> Optional<T> visitIfPresent(ParserRuleContext context, Class<T> clazz) {
        return Optional.ofNullable(context)
            .map(this::visit)
            .map(clazz::cast);
    }

    private <T> List<T> visit(List<? extends ParserRuleContext> contexts, Class<T> clazz) {
        return contexts.stream()
            .map(this::visit)
            .map(clazz::cast)
            .collect(toList());
    }

    private static ArithmeticBinaryExpression.Type getArithmeticBinaryOperator(Token operator) {
        switch (operator.getType()) {
            case ArcanumLexer.POW:
                return ArithmeticBinaryExpression.Type.POWER;
            case ArcanumLexer.MULT:
                return ArithmeticBinaryExpression.Type.MULTIPLY;
            case ArcanumLexer.DIV:
                return ArithmeticBinaryExpression.Type.DIVIDE;
            case ArcanumLexer.MOD:
                return ArithmeticBinaryExpression.Type.MODULUS;
            case ArcanumLexer.PLUS:
                return ArithmeticBinaryExpression.Type.ADD;
            case ArcanumLexer.MINUS:
                return ArithmeticBinaryExpression.Type.SUBTRACT;
        }
        throw new IllegalArgumentException("Unsopported operator: " + operator.getText());
    }

    private static ArithmeticUnaryExpression.Type getArithmeticUnaryOperator(Token operator) {
        switch (operator.getType()) {
            case ArcanumLexer.MINUS:
                return ArithmeticUnaryExpression.Type.MINUS;
        }
        throw new IllegalArgumentException("Unsopported operator: " + operator.getText());
    }

}
