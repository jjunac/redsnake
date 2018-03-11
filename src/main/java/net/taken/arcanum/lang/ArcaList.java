package net.taken.arcanum.lang;

import net.taken.arcanum.reflect.ArcaMetaClass;

import java.util.*;
import java.util.stream.Collectors;

public class ArcaList extends ArcaObject {

    private LinkedList<ArcaObject> value;

    public ArcaList() {
        this(new LinkedList<>());
    }

    public ArcaList(ArcaObject... objects) {
        this(Arrays.asList(objects));
    }

    public ArcaList(List<ArcaObject> value) {
        super(new ArcaMetaClass("List", "Object"));
        this.value = new LinkedList<>(value);
    }

    public List getValue() {
        return value;
    }

    public void setValue(LinkedList<ArcaObject> value) {
        this.value = value;
    }

    public int size() {
        return value.size();
    }

    @Override
    public ArcaString tos() {
        return new ArcaString(value.stream().map(s -> s.tos().getValue()).collect(Collectors.joining(" ")));
    }

    @Override
    public ArcaInteger toi() {
        return new ArcaInteger(size());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArcaList arcaList = (ArcaList) o;
        return Objects.equals(value, arcaList.value);
    }

    @Override
    public int hashCode() {

        return Objects.hash(value);
    }

    public ArcaList push(ArcaObject o) {
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
