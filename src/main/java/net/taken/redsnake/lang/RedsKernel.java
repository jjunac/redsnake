package net.taken.redsnake.lang;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class RedsKernel {

    RedsEnvironment environment;

    public RedsKernel(RedsEnvironment environment) {
        this.environment = environment;
    }

    public RedsNull print(List<RedsObject> l) {
        environment.stdout.println(String.join(" ", l.stream().map(o -> o.tos().getValue()).collect(Collectors.toList())));
        environment.stdout.flush();
        return new RedsNull();
    }

        // TODO may be too angry, we should exit more smoothly
        public RedsNull exit(List<RedsObject> l) {
        if (l.size() == 0) {
            System.exit(0);
        } else {
            System.exit(l.get(0).toi().getValue());
        }
        return new RedsNull();
    }

    public Map<String, Function<List<RedsObject>, RedsObject>> getBuiltInFunctions() {
        Map<String, Function<List<RedsObject>, RedsObject>> res = new HashMap<>();
        res.put("print", this::print);
        res.put("exit", this::exit);
        return res;
    }
}
