package net.taken.arcanum.domain;

import net.taken.arcanum.lang.ArcaKernel;
import net.taken.arcanum.lang.ArcaList;
import net.taken.arcanum.lang.ArcaObject;
import net.taken.arcanum.lang.ArcaString;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class ArcaEnvironment {

    ArcaKernel kernel;
    Map<ArcaString, ArcaObject> variables;
    Map<ArcaString, Function<ArcaList, ArcaObject>> functions;

    public ArcaEnvironment() {
        kernel = new ArcaKernel();
        variables = new HashMap<>();
        functions = new HashMap<>();
        functions.putAll(kernel.getBuiltInFunctions());
    }

    public ArcaObject getVariable(ArcaString name) {
        return variables.get(name);
    }

    public void putVariable(ArcaString name, ArcaObject value) {
        variables.put(name, value);
    }

    public Function<ArcaList, ArcaObject> resolveFunction(ArcaString name) {
        return functions.get(name);
    }

    public void putFunction(ArcaString name, Function<ArcaList, ArcaObject> function) {
        functions.put(name, function);
    }
}
