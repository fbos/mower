package com.fbos.mower.geom;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by fb on 30/10/2016.
 */
public class AreaTest {

    @Test
    public void testIsAuthorized() {
        Area area = new Area(5, 5);
        assertTrue(area.isAuthorized(Position.POSITION_ZERO));
        assertTrue(area.isAuthorized(new Position(2, 5)));
        assertTrue(area.isAuthorized(new Position(5, 5)));
        assertFalse(area.isAuthorized(new Position(5, 6)));
        assertFalse(area.isAuthorized(new Position(-1, 3)));

        area = new Area(-5, -5, 5, 5);
        assertTrue(area.isAuthorized(new Position(-4, -5)));
        assertFalse(area.isAuthorized(new Position(-6, 3)));
    }

}