package net.taken.redsnake;

import net.taken.redsnake.lang.RedsObject;

public class Type<T extends RedsObject> {

    private final String name;

    public Type(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
