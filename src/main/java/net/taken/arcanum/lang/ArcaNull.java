package net.taken.arcanum.lang;

import net.taken.arcanum.reflect.ArcaMetaClass;

public class ArcaNull extends ArcaObject {

    public ArcaNull() {
        super(new ArcaMetaClass("Null", "Object"));
    }

    @Override
    public boolean isNull() {
        return true;
    }

    @Override
    public ArcaString tos() {
        return new ArcaString("null");
    }

    @Override
    public ArcaInteger toi() {
        return new ArcaInteger(0);
    }

    @Override
    public ArcaBoolean tob() {
        return ArcaBoolean.FALSE;
    }
}
