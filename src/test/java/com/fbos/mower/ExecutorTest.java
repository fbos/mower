package com.fbos.mower;

import com.fbos.mower.enums.Movement;
import com.fbos.mower.geom.FilledArea;
import com.fbos.mower.geom.Moveable;
import com.fbos.mower.geom.Position;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import static com.fbos.mower.enums.Movement.*;
import static com.fbos.mower.enums.Orientation.*;
import static org.junit.Assert.*;

/**
 * Created by fb on 30/10/2016.
 */
public class ExecutorTest {

    private static FilledArea area;
    private static Moveable moveable1, moveable2;
    private static List<Movement> movements1, movements2;
    private static List<Moveable> moveables;
    private static List<String> expectedResult;

    private Executor executor;

    @BeforeClass
    public static void doBeforeClass() {
        movements1 = Arrays.asList(LEFT, FORWARD, RIGHT, FORWARD, FORWARD, LEFT);
        movements2 = Arrays.asList(FORWARD, FORWARD, RIGHT, BACKWARD, BACKWARD);
        expectedResult = Arrays.asList("28 109 E", "40 655 N");
    }

    @Before
    public void doBefore() {
        executor = new Executor();
        area = new FilledArea(42, 657);
        moveable1 = new Mower(new Position(27, 111), SOUTH);
        moveable1.setArea(area);
        moveable2 = new Mower(new Position(42, 657), WEST);
        moveable2.setArea(area);
        moveables = Arrays.asList(moveable1, moveable2);
        moveable1.setMovements(movements1);
        moveable2.setMovements(movements2);
        moveables = Arrays.asList(moveable1, moveable2);
    }

    @Test
    public void testExecute() throws IOException {
        String instructionsFile = getClass().getResource("/instructions.txt").getFile();
        Path path = Paths.get(instructionsFile);
        List<String> result = executor.execute(path);

        String expectedResultFile = getClass().getResource("/expected_result.txt").getFile();
        List<String> expectedResult = Files.readAllLines(Paths.get(expectedResultFile));

        assertEquals(expectedResult, result);
    }

    @Test
    public void testExecuteSequential() {
        List<String> result = executor.executeSequential(moveables);
        assertEquals(expectedResult, result);
    }

    @Test
    public void testExecuteParallel() {
        List<String> result = executor.executeParallel(moveables);
        assertEquals(expectedResult, result);
    }

}