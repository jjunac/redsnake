package net.taken.arcanum.lang;

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
