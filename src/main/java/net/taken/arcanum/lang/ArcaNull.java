package net.taken.arcanum.lang;

public class ArcaNull extends ArcaObject {

    @Override
    public boolean isNull() {
        return true;
    }

    @Override
    public ArcaString tos() {
        return new ArcaString("null");
    }

    @Override
    public ArcaInteger toi() {
        return new ArcaInteger(0);
    }
}
