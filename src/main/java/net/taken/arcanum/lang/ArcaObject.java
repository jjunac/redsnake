package net.taken.arcanum.lang;

import net.taken.arcanum.reflect.ArcaMetaClass;

public class ArcaObject {

    public final ArcaMetaClass metaClass;

    public ArcaObject() {
        this.metaClass = new ArcaMetaClass("Object", null);
    }

    public ArcaObject(ArcaMetaClass metaClass) {
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

    public ArcaObject plus(ArcaObject o) {
        throw new IllegalStateException();
    }

    public ArcaObject minus(ArcaObject o) {
        throw new IllegalStateException();
    }

    public ArcaObject multiply(ArcaObject o) {
        throw new IllegalStateException();
    }

    public ArcaObject divide(ArcaObject o) {
        throw new IllegalStateException();
    }

    public ArcaObject modulo(ArcaObject o) {
        throw new IllegalStateException();
    }

    public ArcaObject power(ArcaObject o) {
        throw new IllegalStateException();
    }

    public ArcaObject uminus() {
        throw new IllegalStateException();
    }

    // =============================
    // Built-in conversions
    // =============================

    public ArcaString tos() {
        return new ArcaString(metaClass.name);
    }

    public ArcaInteger toi() {
        return new ArcaInteger(0);
    }

    public ArcaBoolean tob() {
        return ArcaBoolean.FALSE;
    }

}
