package net.taken.arcanum.lang;

public class ArcaNull extends ArcaObject {

    @Override
    public boolean isNull() {
        return true;
    }

    @Override
    public String tos() {
        return "null";
    }
}
