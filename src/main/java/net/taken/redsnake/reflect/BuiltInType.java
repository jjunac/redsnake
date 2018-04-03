package net.taken.redsnake.reflect;

import net.taken.redsnake.lang.RedsInteger;

public enum BuiltInType {

    INTEGER(new Type<RedsInteger>("integer")),
    STRING("string"),
    BOOLEAN("bool");

    private final Type type;

    BuiltInType(Type type) {
        this.type = type;
    }

    @Override
    public Type getType() {
        return type;
    }
}
