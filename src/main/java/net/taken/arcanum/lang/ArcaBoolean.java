package net.taken.arcanum.lang;

import net.taken.arcanum.reflect.ArcaMetaClass;

/**
 * Created by Nassim B on 3/9/18.
 */
public class ArcaBoolean extends ArcaObject {
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

}
