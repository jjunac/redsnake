package net.taken.redsnake.lang;

import com.google.common.base.Strings;
import net.taken.redsnake.reflect.ArcaMetaClass;

import java.util.Objects;

public class RedsString extends RedsObject {

    private String value;

    public RedsString() {
        super(new ArcaMetaClass("String", "Object"));
    }

    public RedsString(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public RedsString tos() {
        return this;
    }

    @Override
    public RedsInteger toi() {
        return new RedsInteger(Integer.valueOf(value));
    }

    @Override
    public RedsBoolean tob() {
        return new RedsBoolean(Boolean.getBoolean(value) || value.length() != 0);
    }

    @Override
    public RedsString plus(RedsObject o) {
        return new RedsString(value + o.tos().value);
    }

    @Override
    public RedsObject multiply(RedsObject o) {
        return new RedsString(Strings.repeat(value, o.toi().getValue()));
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ArcaString{");
        sb.append("value='").append(value).append('\'');
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RedsString that = (RedsString) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
