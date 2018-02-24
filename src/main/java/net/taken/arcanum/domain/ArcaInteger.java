package net.taken.arcanum.domain;

public class ArcaInteger extends ArcaObject {

    int value;

    public ArcaInteger() {
        super(new ArcaMetaClass("Integer", "Object"));
    }

    public ArcaInteger(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toS() {
        return Integer.toString(value);
    }
}
