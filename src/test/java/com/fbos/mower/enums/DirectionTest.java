package com.fbos.mower.enums;

import org.junit.Test;

import static org.junit.Assert.*;
import static com.fbos.mower.enums.Movement.*;

/**
 * Created by fb on 30/10/2016.
 */
public class DirectionTest {

    @Test
    public void testGetFromCode() {
        assertEquals(FORWARD, getFromCode('A'));
        assertEquals(BACKWARD, getFromCode('B'));
        assertEquals(RIGHT, getFromCode('D'));
        assertEquals(LEFT, getFromCode('G'));
    }

    @Test(expected=IllegalArgumentException.class)
    public void testGetIllegalCode() {
        getFromCode('Z');
    }

}