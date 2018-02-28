package net.taken.arcanum.domain;

import java.util.*;
import java.util.function.Function;

public class ArcaKernel {

    public ArcaNull print(ArcaList l) {
        System.out.println(l);
        return new ArcaNull();
    }

    public ArcaNull exit(ArcaList l) {
        if (l.size() == 0) {
            System.exit(0);
        } else {
            // TODO fix this with to_i method
            System.exit(((ArcaInteger)l.getValue().get(0)).getValue());
        }
        return new ArcaNull();
    }

    public Map<ArcaString, Function<ArcaList, ArcaObject>> getBuiltInFunctions() {
        Map<ArcaString, Function<ArcaList, ArcaObject>> res = new HashMap<>();
        res.put(new ArcaString("print"), this::print);
        res.put(new ArcaString("exit"), this::exit);
        return res;
    }
}
