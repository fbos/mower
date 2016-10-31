package com.fbos.mower.enums;

import org.junit.Test;

import static org.junit.Assert.*;
import static com.fbos.mower.enums.Orientation.*;
import static com.fbos.mower.enums.Movement.*;

/**
 * Created by fb on 30/10/2016.
 */
public class OrientationTest {

    @Test
    public void testGetFromCode() {
        assertEquals(NORTH, Orientation.getFromCode('N'));
        assertEquals(EAST, Orientation.getFromCode('E'));
        assertEquals(WEST, Orientation.getFromCode('W'));
        assertEquals(SOUTH, Orientation.getFromCode('S'));
    }

    @Test(expected=IllegalArgumentException.class)
    public void testGetIllegalCode() {
        Orientation.getFromCode('Z');
    }

    @Test
    public void testRotate() throws Exception {
        assertEquals(NORTH.rotate(FORWARD), NORTH);
        assertEquals(NORTH.rotate(BACKWARD), NORTH);
        assertEquals(NORTH.rotate(RIGHT), EAST);
        assertEquals(NORTH.rotate(LEFT), WEST);
        assertEquals(EAST.rotate(RIGHT), SOUTH);
        assertEquals(EAST.rotate(LEFT), NORTH);
        assertEquals(EAST.rotate(RIGHT), SOUTH);
        assertEquals(EAST.rotate(LEFT), NORTH);
        assertEquals(WEST.rotate(RIGHT), NORTH);
        assertEquals(WEST.rotate(LEFT), SOUTH);
        assertEquals(SOUTH.rotate(RIGHT), WEST);
        assertEquals(SOUTH.rotate(LEFT), EAST);
    }

}