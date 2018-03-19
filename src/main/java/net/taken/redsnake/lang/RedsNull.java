package net.taken.redsnake.lang;

import net.taken.redsnake.reflect.ArcaMetaClass;

public class RedsNull extends RedsObject {

    public RedsNull() {
        super(new ArcaMetaClass("Null", "Object"));
    }

    @Override
    public boolean isNull() {
        return true;
    }

    @Override
    public RedsString tos() {
        return new RedsString("null");
    }

    @Override
    public RedsInteger toi() {
        return new RedsInteger(0);
    }

    @Override
    public RedsBoolean tob() {
        return RedsBoolean.FALSE;
    }
}
