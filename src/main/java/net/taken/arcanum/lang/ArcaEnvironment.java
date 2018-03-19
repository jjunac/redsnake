package net.taken.arcanum.lang;

import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class ArcaEnvironment {

    ArcaKernel kernel;
    PrintWriter stdout;
    Map<String, ArcaObject> variables;
    Map<String, Function<List<ArcaObject>, ArcaObject>> functions;

    public ArcaEnvironment() {
        this(new OutputStreamWriter(System.out));
    }

    public ArcaEnvironment(Writer stdout) {
        kernel = new ArcaKernel(this);
        this.stdout = new PrintWriter(stdout);
        variables = new HashMap<>();
        functions = new HashMap<>();
        functions.putAll(kernel.getBuiltInFunctions());
    }

    public ArcaObject getVariable(String name) {
        return variables.get(name);
    }

    public void putVariable(String name, ArcaObject value) {
        variables.put(name, value);
    }

    public Function<List<ArcaObject>, ArcaObject> resolveFunction(String name) {
        return functions.get(name);
    }

    public void putFunction(String name, Function<List<ArcaObject>, ArcaObject> function) {
        functions.put(name, function);
    }
}
