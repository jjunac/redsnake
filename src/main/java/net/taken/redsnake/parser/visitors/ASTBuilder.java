package net.taken.redsnake.parser.visitors;

import com.google.common.collect.ImmutableList;
import net.taken.redsnake.parser.RedsnakeLexer;
import net.taken.redsnake.parser.RedsnakeParser;
import net.taken.redsnake.parser.RedsnakeParserBaseVisitor;
import net.taken.redsnake.tree.*;
import net.taken.redsnake.tree.designators.CallDesignator;
import net.taken.redsnake.tree.designators.Designator;
import net.taken.redsnake.tree.designators.VariableDesignator;
import net.taken.redsnake.tree.expressions.*;
import net.taken.redsnake.tree.expressions.literals.BooleanLiteral;
import net.taken.redsnake.tree.expressions.literals.IntegerLiteral;
import net.taken.redsnake.tree.expressions.literals.StringLiteral;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

public class ASTBuilder extends RedsnakeParserBaseVisitor<Node> {

    @Override
    public Node visitProgram(RedsnakeParser.ProgramContext ctx) {
        List<Statement> statements = ImmutableList.of();
        if (ctx.statements() != null) {
            statements = visit(ctx.statements().statement(), Statement.class);
        }
        return new Program(statements);
    }

    @Override
    public Node visitStatement(RedsnakeParser.StatementContext ctx) {
        return visit(ctx.expression());
    }

    @Override
    public Node visitIntegerLiteral(RedsnakeParser.IntegerLiteralContext ctx) {
        return new IntegerLiteral(ctx.getText());
    }

    @Override
    public Node visitBooleanLiteral(RedsnakeParser.BooleanLiteralContext ctx) {
        return new BooleanLiteral(ctx.getText());
    }

    @Override
    public Node visitStringLiteral(RedsnakeParser.StringLiteralContext ctx) {
        return new StringLiteral(ctx.getText());
    }

    @Override
    public Node visitArithmeticBinary(RedsnakeParser.ArithmeticBinaryContext ctx) {
        return new ArithmeticBinaryExpression(getArithmeticBinaryOperator(ctx.op), (Expression) visit(ctx.l), (Expression) visit(ctx.r));
    }

    @Override
    public Node visitArithmeticUnary(RedsnakeParser.ArithmeticUnaryContext ctx) {
        return new ArithmeticUnaryExpression(getArithmeticUnaryOperator(ctx.op), (Expression) visit(ctx.e));
    }

    @Override
    public Node visitAssignment(RedsnakeParser.AssignmentContext ctx) {
        return new Assignment((Variable) visit(ctx.variable()), (Expression) visit(ctx.expression()));
    }

    @Override
    public Node visitSubExpression(RedsnakeParser.SubExpressionContext ctx) {
        return new SubExpression((Expression) visit(ctx.expression()));
    }

    @Override
    public Node visitDesignatorExpression(RedsnakeParser.DesignatorExpressionContext ctx) {
        return new DesignatorExpression((Designator) visit(ctx.designator()));
    }

    @Override
    public Node visitVariableDesignator(RedsnakeParser.VariableDesignatorContext ctx) {
        return new VariableDesignator((Variable) visit(ctx.variable()));
    }

    @Override
    public Node visitCallDesignator(RedsnakeParser.CallDesignatorContext ctx) {
        return new CallDesignator((Call) visit(ctx.call()));
    }

    @Override
    public Node visitCall(RedsnakeParser.CallContext ctx) {
        List<Expression> parameters = ImmutableList.of();
        if (ctx.parameters() != null) {
            parameters = visit(ctx.parameters().expression(), Expression.class);
        }
        return new Call((Variable) visit(ctx.fct), parameters);
    }

    @Override
    public Node visitVariable(RedsnakeParser.VariableContext ctx) {
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
            case RedsnakeLexer.POW:
                return ArithmeticBinaryExpression.Type.POWER;
            case RedsnakeLexer.MULT:
                return ArithmeticBinaryExpression.Type.MULTIPLY;
            case RedsnakeLexer.DIV:
                return ArithmeticBinaryExpression.Type.DIVIDE;
            case RedsnakeLexer.MOD:
                return ArithmeticBinaryExpression.Type.MODULUS;
            case RedsnakeLexer.PLUS:
                return ArithmeticBinaryExpression.Type.ADD;
            case RedsnakeLexer.MINUS:
                return ArithmeticBinaryExpression.Type.SUBTRACT;
        }
        throw new IllegalArgumentException("Unsopported operator: " + operator.getText());
    }

    private static ArithmeticUnaryExpression.Type getArithmeticUnaryOperator(Token operator) {
        switch (operator.getType()) {
            case RedsnakeLexer.MINUS:
                return ArithmeticUnaryExpression.Type.MINUS;
        }
        throw new IllegalArgumentException("Unsopported operator: " + operator.getText());
    }

}
