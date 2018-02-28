package net.taken.arcanum.domain;

import java.util.function.Function;

public class ArcaFunction {

    private String name;
    private Function<ArcaList, ArcaObject> function;

    public ArcaFunction(String name, Function<ArcaList, ArcaObject> function) {
        this.name = name;
        this.function = function;
    }
}
