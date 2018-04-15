package net.taken.redsnake.operations;

import lombok.EqualsAndHashCode;
import net.taken.redsnake.lang.RedsObject;
import net.taken.redsnake.reflect.Type;

import java.util.function.Function;

@EqualsAndHashCode(callSuper = true)
public class Conversion<U extends RedsObject, R extends RedsObject> extends UnaryOperation<U, R> {

    public Conversion(Type<U> typeArg, Type<R> typeRes, Function<U, R> operation) {
        super(typeArg, typeRes, operation);
    }

}
