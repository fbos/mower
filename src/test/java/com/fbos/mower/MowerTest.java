package com.fbos.mower;

import com.fbos.mower.geom.Area;
import com.fbos.mower.geom.FilledArea;
import com.fbos.mower.geom.Position;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;
import static com.fbos.mower.enums.Orientation.*;
import static com.fbos.mower.enums.Movement.*;

/**
 * Created by fb on 30/10/2016.
 */
public class MowerTest {

    private static FilledArea area;
    private static Mower mower;
    private static Position pos12, pos02;

    @BeforeClass
    public static void doBeforeClass() {
        area = new FilledArea(42, 657);
        pos12 = new Position(1, 2);
        pos02 = new Position(0, 2);
    }

    @Before
    public void doBefore() {
        area.clear();
        mower = new Mower(pos12, NORTH);
        mower.setArea(area);
    }

    @Test
    public void testInit() {
        assertTrue(mower.init());
        assertFalse(mower.init());
    }

    @Test
    public void testMove() {
        mower.init();

        // must rotate to WEST
        mower.move(LEFT);
        assertEquals(pos12, mower.getPosition());
        assertEquals(WEST, mower.getOrientation());

        // must move forward to WEST
        mower.move(FORWARD);
        assertEquals(pos02, mower.getPosition());

        // must be blocked
        mower.move(FORWARD);
        assertEquals(pos02, mower.getPosition());

        // enlarge the area
        area = new FilledArea(-5, -5, 5, 5);
        mower.setArea(area);
        mower.init();

        // must move forward to WEST
        mower.move(FORWARD);
        assertEquals(new Position(-1, 2), mower.getPosition());
    }

}