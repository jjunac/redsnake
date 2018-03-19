package net.taken.redsnake.lang;

import net.taken.redsnake.reflect.ArcaMetaClass;

/**
 * Created by Nassim B on 3/9/18.
 */
public class RedsBoolean extends RedsObject {
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

}
