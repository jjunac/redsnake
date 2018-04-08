package net.taken.redsnake.lang;

import net.taken.redsnake.interpretor.Value;
import net.taken.redsnake.reflect.Type;

public class RedsNull extends RedsObject {

    public static final Type<RedsNull> TYPE = new Type<>("null");
    public static final Value<RedsNull> VALUE = new Value<>(TYPE, new RedsNull());


    private RedsNull() {
        super(TYPE);
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
