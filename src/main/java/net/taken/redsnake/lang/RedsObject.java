package net.taken.redsnake.lang;

import net.taken.redsnake.reflect.ArcaMetaClass;

public class RedsObject {

    public final ArcaMetaClass metaClass;

    public RedsObject() {
        this.metaClass = new ArcaMetaClass("Object", null);
    }

    public RedsObject(ArcaMetaClass metaClass) {
        this.metaClass = metaClass;
    }

    public boolean isNull() {
        return false;
    }

    @Override
    public String toString() {
        return metaClass.name;
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

}
