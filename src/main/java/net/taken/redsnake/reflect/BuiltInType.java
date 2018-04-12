package net.taken.redsnake.reflect;

import net.taken.redsnake.lang.RedsInteger;
import net.taken.redsnake.lang.RedsObject;

public enum BuiltInType {

    STRING(new Type<RedsInteger>("string")),
    BOOLEAN(new Type<RedsInteger>("boolean"));

    private final Type<? extends RedsObject> type;

    BuiltInType(Type<? extends RedsObject> type) {
        this.type = type;
    }

    public Type<? extends RedsObject> getType() {
        return type;
    }
}
