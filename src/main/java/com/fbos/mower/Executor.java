package com.fbos.mower;

import com.fbos.mower.geom.Moveable;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Read and interpret instructions in a file and then execute them. <br/>
 *
 * Created by fb on 30/10/2016.
 */
public class Executor {

    public enum ExecutionMode {
        SEQUENTIAL,
        PARALLEL;
    }

    private final ExecutionMode executionMode;

    /**
     * Build an executor in sequential mode by default.
     */
    public Executor() {
        this.executionMode = ExecutionMode.SEQUENTIAL;
    }

    public Executor(ExecutionMode executionMode) {
        this.executionMode = executionMode;
    }

    /**
     * Get instructions from {@link Path} and execute them.
     *
     * @param path
     * @return List of result of instruction executions
     * @throws IOException
     */
    public List<String> execute(final Path path) throws IOException {
        return execute(Files.readAllLines(path));
    }

    /**
     * Interpret literal lines to {@link Moveable} and execute them.
     * @param lines
     * @return List of result of instruction executions
     */
    private List<String> execute(final List<String> lines) {
        Interpreter interpreter = new Interpreter();
        List<Moveable> moveables = interpreter.interpret(lines);
        switch(this.executionMode) {
            case PARALLEL:
                return executeParallel(moveables);
            default:
                return executeSequential(moveables);
        }
    }

    /**
     * Move all moveables in sequential mode: each Moveable executes all
     * its movements and then hand over to the next one.
     *
     * @param moveables
     * @return List of result of instruction executions
     */
    protected List<String> executeSequential(final List<Moveable> moveables) {
        List<String> result = new ArrayList<>(moveables.size());
        moveables.forEach(moveable -> {
            moveable.init();
            moveable.move();
            result.add(moveable.toString());
        });
        return result;
    }

    /**
     * Move moveables in parallel mode: each Moveable executes only one movement,
     * then hand over to the next object, waiting to execute a movement again.
     *
     * @param moveables
     * @return
     */
    protected List<String> executeParallel(final List<Moveable> moveables) {
        List<String> result = new ArrayList<>(moveables.size());

        // init and determine max nb of movements
        int maxMovementNb = 0;
        for (Moveable moveable : moveables) {
            moveable.init();
            if (moveable.getMovements().size() > maxMovementNb) {
                maxMovementNb = moveable.getMovements().size();
            }
        }

        // execute movement one by one for each moveable
        for (int i = 0; i< maxMovementNb ; i++) {
            int index = i;
            moveables.forEach(moveable -> moveable.move(index));
        }

        moveables.forEach(moveable -> result.add(moveable.toString()));
        return result;
    }
}
