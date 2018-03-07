package net.taken.arcanum.lang;

import net.taken.arcanum.reflect.ArcaMetaClass;

import java.util.Objects;

public class ArcaInteger extends ArcaObject {

    private int value;

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
    public ArcaString tos() {
        return new ArcaString(Integer.toString(value));
    }

    @Override
    public ArcaInteger toi() {
        return this;
    }

    @Override
    public ArcaInteger plus(ArcaObject o) {
        return new ArcaInteger(value + o.toi().value);
    }

    @Override
    public ArcaInteger minus(ArcaObject o) {
        return new ArcaInteger(value - o.toi().value);
    }

    @Override
    public ArcaInteger multiply(ArcaObject o) {
        return new ArcaInteger(value * o.toi().value);
    }

    @Override
    public ArcaInteger divide(ArcaObject o) {
        return new ArcaInteger(value / o.toi().value);
    }

    @Override
    public ArcaInteger modulo(ArcaObject o) {
        return new ArcaInteger(value % o.toi().value);
    }

    @Override
    public ArcaInteger power(ArcaObject o) {
        return new ArcaInteger((int) Math.pow(value , o.toi().value));
    }

    @Override
    public ArcaInteger uminus() {
        return new ArcaInteger(- value);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ArcaInteger{");
        sb.append("value=").append(value);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArcaInteger that = (ArcaInteger) o;
        return value == that.value;
    }

    @Override
    public int hashCode() {

        return Objects.hash(value);
    }
}
