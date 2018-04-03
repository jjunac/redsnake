package net.taken.redsnake;

import net.taken.redsnake.reflect.Type;

public class VariableSymbol extends Symbol {

    private final Type type;

    public VariableSymbol(String name, Type type) {
        super(name);
        this.type = type;
    }

}
