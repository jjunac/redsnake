package net.taken.arcanum.domain;

import java.util.*;
import java.util.function.Function;

public class ArcaKernel {

    public ArcaNull print(ArcaList l) {
        System.out.println(l);
        return new ArcaNull();
    }

    public Map<ArcaString, Function<ArcaList, ArcaObject>> getBuiltInFunctions() {
        Map<ArcaString, Function<ArcaList, ArcaObject>> res = new HashMap<>();
        res.put(new ArcaString("print"), this::print);
        return res;
    }
}
