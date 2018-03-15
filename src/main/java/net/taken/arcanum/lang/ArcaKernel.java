package net.taken.arcanum.lang;

import java.io.*;
import java.util.*;
import java.util.function.Function;

public class ArcaKernel {

    ArcaEnvironment environment;

    public ArcaKernel(ArcaEnvironment environment) {
        this.environment = environment;
    }

    public ArcaNull print(List<ArcaObject> l) {
        // FIXME fix print
        //environment.stdout.println(l.tos().getValue());
        environment.stdout.flush();
        return new ArcaNull();
    }

    public ArcaNull exit(List<ArcaObject> l) {
        if (l.size() == 0) {
            System.exit(0);
        } else {
            // FIXME fix exit
            //System.exit(((ArcaInteger)l.getValue().get(0)).getValue());
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
