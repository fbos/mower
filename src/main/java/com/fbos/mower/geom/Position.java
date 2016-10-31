package com.fbos.mower.geom;

import com.fbos.mower.enums.Movement;
import com.fbos.mower.enums.Orientation;

/**
 * 2D position. <br/>
 *
 * Created by fb on 30/10/2016.
 */
public class Position {

    protected static final Position POSITION_ZERO = new Position(0,0);

    private final double x;

    private final double y;

    public Position(final double x, final double y) {
        this.x = x;
        this.y = y;
    }

    protected double getX() {
        return x;
    }

    protected double getY() {
        return y;
    }

    protected boolean isInferiorOrEqual(Position position) {
        return this.x <= position.x && this.y <= position.y;
    }

    protected boolean isSuperiorOrEqual(Position position) {
        return this.x >= position.x && this.y >= position.y;
    }

    protected Position getNextPosition(final Orientation orientation, final Movement movement) {
        double xx = this.x, yy = this.y;
        switch (movement) {
            case FORWARD:
                switch(orientation) {
                    case NORTH:
                        yy++;
                        break;
                    case EAST:
                        xx++;
                        break;
                    case WEST:
                        xx--;
                        break;
                    case SOUTH:
                        yy--;
                        break;
                }
                break;
            case BACKWARD:
                switch(orientation) {
                    case NORTH:
                        yy--;
                        break;
                    case EAST:
                        xx--;
                        break;
                    case WEST:
                        xx++;
                        break;
                    case SOUTH:
                        yy++;
                        break;
                }
                break;
            default:
                return this;
        }
        return new Position(xx, yy);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Position position = (Position) o;

        if (x != position.x) return false;
        return y == position.y;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(x);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(y);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        // don't show decimals
        return new Double(x).intValue() + " " + new Double(y).intValue();
    }

}
