package net.taken.redsnake.lang;

import net.taken.redsnake.reflect.Type;
import net.taken.redsnake.reflect.ArcaMetaClass;

public class RedsObject {

    public final Type type;

    public RedsObject(Type type) {
        this.type = type;
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
        return new RedsString(metaClass.name);
    }

    public RedsInteger toi() {
        return new RedsInteger(0);
    }

    public RedsBoolean tob() {
        return RedsBoolean.FALSE;
    }

}
