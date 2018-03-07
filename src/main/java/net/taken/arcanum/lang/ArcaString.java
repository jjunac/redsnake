package net.taken.arcanum.lang;

import net.taken.arcanum.reflect.ArcaMetaClass;

import java.util.Objects;

public class ArcaString extends ArcaObject {

    private String value;

    public ArcaString() {
        super(new ArcaMetaClass("String", "Object"));
    }

    public ArcaString(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String tos() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArcaString that = (ArcaString) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
