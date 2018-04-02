package net.taken.redsnake;

public class VariableSymbol extends Symbol {

    private final Type type;

    public VariableSymbol(String name, Type type) {
        super(name);
        this.type = type;
    }

}
