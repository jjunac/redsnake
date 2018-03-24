package net.taken.redsnake.lang;

import net.taken.redsnake.reflect.ArcaMetaClass;

public class RedsBoolean extends RedsObject {
    public static final RedsBoolean FALSE = new RedsBoolean(false);
    public static final RedsBoolean TRUE = new RedsBoolean(true);
    private boolean value;

    public RedsBoolean(boolean value) {
        super(new ArcaMetaClass("Boolean", "Object"));
        this.value = value;
    }

    public RedsBoolean() {
        this(false);
    }

    public boolean getValue() {
        return this.value;
    }

    @Override
    public RedsString tos() {
        return new RedsString(Boolean.toString(value));
    }

    @Override
    public RedsInteger toi() {
        return new RedsInteger(value ? 1 : 0);
    }

    @Override
    public RedsBoolean tob() {
        return this;
    }
}
