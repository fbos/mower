package com.fbos.mower.enums;

/**
 * Cardinal orientation. <br/>
 *
 * Created by fb on 30/10/2016.
 */
public enum Orientation {

    NORTH('N'),
    EAST('E'),
    WEST('W'),
    SOUTH('S');

    private final char code;

    Orientation(char code) {
        this.code = code;
    }

    public char getCode() {
        return this.code;
    }

    /**
     * Get the orientation after rotating by a specified movement
     *
     * @param movement to rotate toward
     * @return new Orientation
     */
    public Orientation rotate(Movement movement) {
        switch (movement) {
            case RIGHT:
                switch (this) {
                    case NORTH:
                        return EAST;
                    case EAST:
                        return SOUTH;
                    case SOUTH:
                        return WEST;
                    case WEST:
                        return NORTH;
                }
            case LEFT:
                switch (this) {
                    case NORTH:
                        return WEST;
                    case EAST:
                        return NORTH;
                    case SOUTH:
                        return EAST;
                    case WEST:
                        return SOUTH;
                }
            default:
                return this;
        }
    }

    public static Orientation getFromCode(char code) {
        if (NORTH.code == code) {
            return NORTH;
        }
        if (EAST.code == code) {
            return EAST;
        }
        if (WEST.code == code) {
            return WEST;
        }
        if (SOUTH.code == code) {
            return SOUTH;
        }
        throw new IllegalArgumentException("Unknown code " + code);
    }
}
