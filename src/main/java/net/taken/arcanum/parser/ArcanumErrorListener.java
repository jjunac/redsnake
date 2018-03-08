package net.taken.arcanum.parser;

import com.google.common.base.Strings;
import org.antlr.v4.runtime.*;


public class ArcanumErrorListener extends ConsoleErrorListener {

    @Override
    public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
        String errorLine = ((CommonTokenStream) recognizer.getInputStream()).getTokenSource().getInputStream().toString().split("\\r?\\n")[line - 1];
        StringBuilder stringBuilder = new StringBuilder(String.format("SyntaxError: %s", msg));
        stringBuilder.append(System.lineSeparator());
        stringBuilder.append(errorLine);
        stringBuilder.append(System.lineSeparator());
        stringBuilder.append(Strings.repeat(" ", charPositionInLine));
        Token offendingToken = (Token) offendingSymbol;
        int start = offendingToken.getStartIndex();
        int stop = offendingToken.getStopIndex();
        if (start >= 0 && stop >= 0){
            stringBuilder.append(Strings.repeat("^", stop - start));
        }
        stringBuilder.append(System.lineSeparator());
        stringBuilder.append(String.format("\tat file.arca:%d", line));
        System.err.println(stringBuilder.toString());
    }
}
