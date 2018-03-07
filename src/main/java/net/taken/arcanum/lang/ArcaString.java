package net.taken.arcanum.lang;

import com.google.common.base.Strings;
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
    public ArcaString tos() {
        return this;
    }

    @Override
    public ArcaInteger toi() {
        return new ArcaInteger(Integer.valueOf(value));
    }

    @Override
    public ArcaString plus(ArcaObject o) {
        return new ArcaString(value + o.tos().value);
    }

    @Override
    public ArcaObject multiply(ArcaObject o) {
        return new ArcaString(Strings.repeat(value, o.toi().getValue()));
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
        ArcaString that = (ArcaString) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
