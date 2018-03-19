package net.taken.redsnake.lang;

import net.taken.redsnake.reflect.ArcaMetaClass;

import java.util.Objects;

public class RedsInteger extends RedsObject {

    private int value;

    public RedsInteger() {
        super(new ArcaMetaClass("Integer", "Object"));
    }

    public RedsInteger(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public RedsString tos() {
        return new RedsString(Integer.toString(value));
    }

    @Override
    public RedsInteger toi() {
        return this;
    }

    @Override
    public RedsBoolean tob() {
        return new RedsBoolean(value != 0);
    }

    @Override
    public RedsInteger plus(RedsObject o) {
        return new RedsInteger(value + o.toi().value);
    }

    @Override
    public RedsInteger minus(RedsObject o) {
        return new RedsInteger(value - o.toi().value);
    }

    @Override
    public RedsInteger multiply(RedsObject o) {
        return new RedsInteger(value * o.toi().value);
    }

    @Override
    public RedsInteger divide(RedsObject o) {
        return new RedsInteger(value / o.toi().value);
    }

    @Override
    public RedsInteger modulo(RedsObject o) {
        return new RedsInteger(value % o.toi().value);
    }

    @Override
    public RedsInteger power(RedsObject o) {
        return new RedsInteger((int) Math.pow(value , o.toi().value));
    }

    @Override
    public RedsInteger uminus() {
        return new RedsInteger(- value);
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
        RedsInteger that = (RedsInteger) o;
        return value == that.value;
    }

    @Override
    public int hashCode() {

        return Objects.hash(value);
    }
}
