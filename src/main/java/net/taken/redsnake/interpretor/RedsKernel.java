package net.taken.redsnake.interpretor;

import net.taken.redsnake.lang.RedsNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class RedsKernel {

    RedsEnvironment environment;

    public RedsKernel(RedsEnvironment environment) {
        this.environment = environment;
    }

    public Value<RedsNull> print(List<Value> l) {
        environment.stdout.println(String.join(" ", l.stream().map(o -> o.getValue().tos().getValue()).collect(Collectors.toList())));
        environment.stdout.flush();
        return RedsNull.VALUE;
    }

        // TODO may be too angry, we should exit more smoothly
        public Value<RedsNull> exit(List<Value> l) {
        if (l.size() == 0) {
            System.exit(0);
        } else {
            System.exit(l.get(0).getValue().toi().getValue());
        }
        return RedsNull.VALUE;
    }

    public Map<String, Function<List<Value>, Value>> getBuiltInFunctions() {
        Map<String, Function<List<Value>, Value>> res = new HashMap<>();
        res.put("print", this::print);
        res.put("exit", this::exit);
        return res;
    }
}
