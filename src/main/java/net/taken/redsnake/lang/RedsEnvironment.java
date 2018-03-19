package net.taken.redsnake.lang;

import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class RedsEnvironment {

    RedsKernel kernel;
    PrintWriter stdout;
    Map<String, RedsObject> variables;
    Map<String, Function<List<RedsObject>, RedsObject>> functions;

    public RedsEnvironment() {
        this(new OutputStreamWriter(System.out));
    }

    public RedsEnvironment(Writer stdout) {
        kernel = new RedsKernel(this);
        this.stdout = new PrintWriter(stdout);
        variables = new HashMap<>();
        functions = new HashMap<>();
        functions.putAll(kernel.getBuiltInFunctions());
    }

    public RedsObject getVariable(String name) {
        return variables.get(name);
    }

    public void putVariable(String name, RedsObject value) {
        variables.put(name, value);
    }

    public Function<List<RedsObject>, RedsObject> resolveFunction(String name) {
        return functions.get(name);
    }

    public void putFunction(String name, Function<List<RedsObject>, RedsObject> function) {
        functions.put(name, function);
    }
}
