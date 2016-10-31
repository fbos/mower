package com.fbos.mower;

import com.fbos.mower.enums.Movement;
import com.fbos.mower.geom.FilledArea;
import com.fbos.mower.geom.Moveable;
import com.fbos.mower.geom.Position;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static com.fbos.mower.enums.Orientation.*;
import static com.fbos.mower.enums.Movement.*;

/**
 * Created by fb on 30/10/2016.
 */
public class InterpreterTest {

    private static final String BAD_FORMAT = "bad format";
    private static List<String> instructions;
    private static FilledArea area;
    private static Moveable moveable1, moveable2;
    private static List<Movement> movements1, movements2;
    private static List<Moveable> moveables;

    private Interpreter interpreter;

    @BeforeClass
    public static void doBeforeClass() {
        instructions = new ArrayList<>(5);
        instructions.add("42 657");
        instructions.add("27 111 S");
        instructions.add("GADAAG");
        instructions.add("42 657 W");
        instructions.add("AADBBA");
        area = new FilledArea(42, 657);
        moveable1 = new Mower(new Position(27, 111), SOUTH);
        moveable1.setArea(area);
        moveable2 = new Mower(new Position(42, 657), WEST);
        moveable2.setArea(area);
        moveables = Arrays.asList(moveable1, moveable2);
        movements1 = Arrays.asList(LEFT, FORWARD, RIGHT, FORWARD, FORWARD, LEFT);
        movements2 = Arrays.asList(FORWARD, FORWARD, RIGHT, BACKWARD, BACKWARD, FORWARD);
    }

    @Before
    public void doBefore() {
        interpreter = new Interpreter();
    }

    @Test
    public void testInterpret() {
        assertEquals(moveables, interpreter.interpret(instructions));
    }

    @Test
    public void testBuildArea() {
        assertEquals(area, interpreter.buildArea(instructions.get(0)));
    }

    @Test
    public void testBuildMoveable() {
        Moveable moveable = interpreter.buildMoveable(instructions.get(1), area);
        assertEquals(moveable1, moveable);

        moveable = interpreter.buildMoveable(instructions.get(3), area);
        assertEquals(moveable2, moveable);
    }

    @Test
    public void testBuildMovements() {
        List<Movement> movements = interpreter.buildMovements(instructions.get(2));
        assertEquals(InterpreterTest.movements1, movements);

        movements = interpreter.buildMovements(instructions.get(4));
        assertEquals(InterpreterTest.movements2, movements);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testBuildAreaFail() {
        interpreter.buildArea(BAD_FORMAT);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testBuildMoveableFail() {
        interpreter.buildMoveable(BAD_FORMAT, area);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testBuildMovementsFail() {
        interpreter.buildMovements(BAD_FORMAT);
    }
}