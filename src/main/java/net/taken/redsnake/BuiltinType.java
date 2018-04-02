package net.taken.redsnake;

public enum BuiltinType implements Type {

    INTEGER("int"),
    STRING("string"),
    BOOLEAN("bool");

    private final String type;

    BuiltinType(String type) {
        this.type = type;
    }

    @Override
    public String getType() {
        return type;
    }
}
