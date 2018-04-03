package net.taken.redsnake.operations;

import net.taken.redsnake.lang.RedsObject;
import net.taken.redsnake.reflect.Type;

import java.util.function.Function;

public class Operation {

    private Operation(Function<T, R>) {
    }

    public static <T extends RedsObject, R extends RedsObject> void createUnary(Type<T> type, Function<T, R>) {

    }
}
