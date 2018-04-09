package net.taken.redsnake.interpretor;

import lombok.Getter;
import net.taken.redsnake.lang.RedsObject;
import net.taken.redsnake.reflect.Type;

public class VariableSymbol<T extends RedsObject> extends Value<T> implements Symbol {

    @Getter
    private final String name;

    public VariableSymbol(String name, Value<T> v) {
        this(name, v.getType(), v.getValue());
    }

    public VariableSymbol(String name, Type<T> type, T value) {
        super(type, value);
        this.name = name;
    }

}
