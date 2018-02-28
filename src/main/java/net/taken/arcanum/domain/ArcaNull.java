package net.taken.arcanum.domain;

public class ArcaNull extends ArcaObject {

    @Override
    public boolean isNull() {
        return true;
    }

    @Override
    public String toString() {
        return "null";
    }
}
