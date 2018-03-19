package net.taken.arcanum.lang;

import java.io.*;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ArcaKernel {

    ArcaEnvironment environment;

    public ArcaKernel(ArcaEnvironment environment) {
        this.environment = environment;
    }

    public ArcaNull print(List<ArcaObject> l) {
        environment.stdout.println(String.join(" ", l.stream().map(o -> o.tos().getValue()).collect(Collectors.toList())));
        environment.stdout.flush();
        return new ArcaNull();
    }

    public ArcaNull exit(List<ArcaObject> l) {
        // TODO may be too angry, we should exit more smoothly
        if (l.size() == 0) {
            System.exit(0);
        } else {
            System.exit(l.get(0).toi().getValue());
        }
        return new ArcaNull();
    }

    public Map<String, Function<List<ArcaObject>, ArcaObject>> getBuiltInFunctions() {
        Map<String, Function<List<ArcaObject>, ArcaObject>> res = new HashMap<>();
        res.put("print", this::print);
        res.put("exit", this::exit);
        return res;
    }
}
