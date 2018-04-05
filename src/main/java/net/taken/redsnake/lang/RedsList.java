package net.taken.redsnake.lang;

import net.taken.redsnake.reflect.Type;

import java.util.*;
import java.util.stream.Collectors;

public class RedsList extends RedsObject {

    public static final Type<RedsList> TYPE = new Type<>("list");


    private LinkedList<RedsObject> value;

    public RedsList() {
        this(new LinkedList<>());
    }

    public RedsList(RedsObject... objects) {
        this(Arrays.asList(objects));
    }

    public RedsList(List<RedsObject> value) {
        super(TYPE);
        this.value = new LinkedList<>(value);
    }

    public List<RedsObject> getValue() {
        return value;
    }

    public void setValue(LinkedList<RedsObject> value) {
        this.value = value;
    }

    public int size() {
        return value.size();
    }

    @Override
    public RedsString tos() {
        return new RedsString(value.stream().map(s -> s.tos().getValue()).collect(Collectors.joining(" ")));
    }

    @Override
    public RedsInteger toi() {
        return new RedsInteger(size());
    }

    @Override
    public RedsBoolean tob() {
    return new RedsBoolean(value.size() != 0);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RedsList redsList = (RedsList) o;
        return Objects.equals(value, redsList.value);
    }

    @Override
    public int hashCode() {

        return Objects.hash(value);
    }

    public RedsList push(RedsObject o) {
        value.push(o);
        return this;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ArcaList{");
        sb.append("value=").append(value);
        sb.append('}');
        return sb.toString();
    }
}
