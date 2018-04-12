package net.taken.redsnake.lang;

import net.taken.redsnake.reflect.Type;

public class RedsObject {

    public static final Type<RedsObject> TYPE = new Type<>("object");

    private final Type type;

    public RedsObject(Type type) {
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    public boolean isNull() {
        return false;
    }

    // =============================
    // Operators
    // =============================

    public RedsObject plus(RedsObject o) {
        throw new IllegalStateException();
    }

    public RedsObject minus(RedsObject o) {
        throw new IllegalStateException();
    }

    public RedsObject multiply(RedsObject o) {
        throw new IllegalStateException();
    }

    public RedsObject divide(RedsObject o) {
        throw new IllegalStateException();
    }

    public RedsObject modulo(RedsObject o) {
        throw new IllegalStateException();
    }

    public RedsObject power(RedsObject o) {
        throw new IllegalStateException();
    }

    public RedsObject uminus() {
        throw new IllegalStateException();
    }

    // =============================
    // Built-in conversions
    // =============================

    public RedsString tos() {
        return new RedsString(type.getName());
    }

    public RedsInteger toi() {
        return new RedsInteger(0);
    }

    public RedsBoolean tob() {
        return RedsBoolean.FALSE;
    }

}
