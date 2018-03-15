package net.taken.arcanum.parser.visitors;

import com.google.common.collect.ImmutableList;
import net.taken.arcanum.parser.ArcanumParser;
import net.taken.arcanum.parser.ArcanumParserBaseVisitor;
import net.taken.arcanum.tree.Node;
import net.taken.arcanum.tree.Program;
import net.taken.arcanum.tree.Statement;
import net.taken.arcanum.tree.expressions.Expression;
import org.antlr.v4.runtime.ParserRuleContext;

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
        return visitIfPresent(ctx.expression(), Expression.class);
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

}
