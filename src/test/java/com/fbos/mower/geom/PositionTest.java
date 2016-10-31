package com.fbos.mower.geom;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;
import static com.fbos.mower.enums.Orientation.*;
import static com.fbos.mower.enums.Movement.*;

/**
 * Created by fb on 30/10/2016.
 */
public class PositionTest {

    private static Position pos12, pos13, pos32;

    @BeforeClass
    public static void doBeforeClass() {
        pos12 = new Position(1, 2);
        pos13 = new Position(1, 3);
        pos32 = new Position(3, 2);
    }

    @Test
    public void testIsInferiorOrEqual() {
        assertTrue(pos12.isInferiorOrEqual(pos12));
        assertTrue(pos12.isInferiorOrEqual(pos32));
        assertFalse(pos32.isInferiorOrEqual(pos12));
    }

    @Test
    public void testIsSuperiorOrEqual() {
        assertTrue(pos32.isSuperiorOrEqual(pos32));
        assertTrue(pos32.isSuperiorOrEqual(pos12));
        assertFalse(pos12.isSuperiorOrEqual(pos32));
    }

    @Test
    public void testGetNextPosition() {
        assertEquals(pos12, pos12.getNextPosition(NORTH, LEFT));
        assertEquals(pos12, pos12.getNextPosition(NORTH, RIGHT));
        assertEquals(pos13, pos12.getNextPosition(NORTH, FORWARD));
        assertEquals(new Position(2, 2), pos12.getNextPosition(EAST, FORWARD));
        assertEquals(new Position(0, 2), pos12.getNextPosition(EAST, BACKWARD));
        assertEquals(new Position(-1, 0), Position.POSITION_ZERO.getNextPosition(WEST, FORWARD));
        assertEquals(new Position(1, 0), Position.POSITION_ZERO.getNextPosition(WEST, BACKWARD));
        assertEquals(new Position(0, -1), Position.POSITION_ZERO.getNextPosition(SOUTH, FORWARD));
        assertEquals(new Position(0, 1), Position.POSITION_ZERO.getNextPosition(SOUTH, BACKWARD));
    }

}