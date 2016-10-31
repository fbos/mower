package com.fbos.mower.geom;

import com.fbos.mower.enums.Movement;

import java.util.List;

/**
 * Object that can move on a area. <br/>
 *
 * Created by fb on 30/10/2016.
 */
public interface Moveable {

    /**
     * Set the area to move on
     *
     * @param area
     */
    void setArea(FilledArea area);

    /**
     * Set the position on area
     *
     * @param position
     */
    void setPosition(Position position);

    /**
     * Set the movements to execute
     *
     * @param movements
     */
    void setMovements(List<Movement> movements);

    /**
     * @return the position on area
     */
    Position getPosition();

    /**
     * @return the movements to execute
     */
    List<Movement> getMovements();

    /**
     * Add itself to the area.
     *
     * @return true if operation was authorized
     */
    boolean init();

    /**
     * Move on the area toward all the specified movements.
     *
     * @return the new Position resulting all movements
     */
    Position move();

    /**
     * Move on the area toward the specified movement at index.
     *
     * @return the new Position resulting all movements
     */
    Position move(int index);

    /**
     * Move on the area toward a specified movement.
     *
     * @param movement to move toward
     * @return the new Position resulting of the movement
     */
    Position move(Movement movement);
}
