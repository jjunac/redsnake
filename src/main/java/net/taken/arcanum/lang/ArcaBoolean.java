package net.taken.arcanum.lang;

import net.taken.arcanum.reflect.ArcaMetaClass;

public class ArcaBoolean extends ArcaObject {
    public static final ArcaBoolean FALSE = new ArcaBoolean(false);
    public static final ArcaBoolean TRUE = new ArcaBoolean(true);

    private boolean value;

    public ArcaBoolean(boolean value) {
        super(new ArcaMetaClass("Boolean", "Object"));
        this.value = value;
    }

    public ArcaBoolean() {
        this(false);
    }

    public boolean getValue() {
        return this.value;
    }

    @Override
    public ArcaString tos() {
        return new ArcaString(Boolean.toString(value));
    }

    @Override
    public ArcaInteger toi() {
        return new ArcaInteger(value ? 1 : 0);
    }

    @Override
    public ArcaBoolean tob() {
        return this;
    }
}
