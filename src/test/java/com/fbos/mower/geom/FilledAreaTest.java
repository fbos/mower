package com.fbos.mower.geom;

import com.fbos.mower.Mower;
import com.fbos.mower.enums.Orientation;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by fb on 30/10/2016.
 */
public class FilledAreaTest {

    private static FilledArea area;
    private static Position pos2244, pos3648, pos3649;
    private static Moveable moveable1, moveable2;

    @BeforeClass
    public static void doBeforeClass() {
        pos2244 = new Position(22, 44);
        pos3648 = new Position(36, 48);
        pos3649 = new Position(36, 49);
        area = new FilledArea(42, 77);
        moveable1 = new Mower(pos2244, Orientation.NORTH);
        moveable2 = new Mower(pos3648, Orientation.SOUTH);
    }

    @Before
    public void doBefore() {
        area.clear();
    }

    @Test
    public void testClear() {
        area.add(moveable1);
        assertEquals(moveable1, area.getContent(moveable1.getPosition()));
        area.clear();
        assertNull(area.getContent(moveable1.getPosition()));
    }

    @Test
    public void testIsAuthorized() {
        assertTrue(area.isAuthorized(moveable1.getPosition()));
        area.add(moveable1);
        assertFalse(area.isAuthorized(moveable1.getPosition()));
    }

    @Test
    public void testAdd() {
        assertTrue(area.add(moveable1));
        assertEquals(moveable1, area.getContent(moveable1.getPosition()));
        assertTrue(area.add(moveable2));
        assertEquals(moveable2, area.getContent(moveable2.getPosition()));
        assertFalse(area.add(moveable1));
        assertFalse(area.add(moveable2));
    }

    @Test
    public void testMove() {
        Position pos = moveable1.getPosition();
        area.add(moveable1);
        assertFalse(area.move(pos, moveable2));
        assertEquals(moveable1, area.getContent(pos));
        assertTrue(area.move(pos3649, moveable2));
        assertEquals(moveable2, area.getContent(pos3649));
    }

}