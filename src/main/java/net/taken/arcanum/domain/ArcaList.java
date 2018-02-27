package net.taken.arcanum.domain;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class ArcaList extends ArcaObject {

    private List<ArcaObject> value;

    public ArcaList() {
        this(new LinkedList<>());
    }

    public ArcaList(List<ArcaObject> value) {
        super(new ArcaMetaClass("List", "Object"));
        this.value = value;
    }

    public List getValue() {
        return value;
    }

    public void setValue(List<ArcaObject> value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value.stream().map(ArcaObject::toString).collect(Collectors.joining(" "));
    }
}
