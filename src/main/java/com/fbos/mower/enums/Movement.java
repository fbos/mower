package com.fbos.mower.enums;

/**
 * Movement to move toward. <br/>
 *
 * Created by fb on 30/10/2016.
 */
public enum Movement {

    FORWARD('A', false),
    BACKWARD('B', false),
    RIGHT('D', true),
    LEFT('G', true);

    private final char code;

    private final boolean isRotation;

    Movement(final char code, final boolean isRotation) {
        this.code = code;
        this.isRotation = isRotation;
    }

    public char getCode() {
        return this.code;
    }

    public boolean isRotation() {
        return this.isRotation;
    }

    public static Movement getFromCode(char code) {
        if (FORWARD.code == code) {
            return FORWARD;
        }
        if (BACKWARD.code == code) {
            return BACKWARD;
        }
        if (RIGHT.code == code) {
            return RIGHT;
        }
        if (LEFT.code == code) {
            return LEFT;
        }
        throw new IllegalArgumentException("Unknown code " + code);
    }
}
