package net.taken.arcanum.domain;

public class ArcaObject {

    public final ArcaMetaClass metaClass;

    public ArcaObject() {
        this.metaClass = new ArcaMetaClass("Object", null);
    }

    public ArcaObject(ArcaMetaClass metaClass) {
        this.metaClass = metaClass;
    }

    public boolean isNull() {
        return false;
    }

    @Override
    public String toString() {
        return metaClass.name;
    }
}