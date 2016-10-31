package com.fbos.mower.geom;

import com.fbos.mower.enums.Movement;
import com.fbos.mower.enums.Orientation;

import java.util.List;

/**
 * Abstract object that can move on an area. <br/>
 *
 * Created by fb on 30/10/2016.
 */
public abstract class AbstractMoveable implements Moveable {

    private Position position;

    private Orientation orientation;

    /** the area to move on **/
    private FilledArea area;

    /** movements to execute **/
    private List<Movement> movements;

    public AbstractMoveable(final Position position, final Orientation orientation) {
        this.position = position;
        this.orientation = orientation;
    }

    @Override
    public Position getPosition() {
        return this.position;
    }

    public Orientation getOrientation() {
        return this.orientation;
    }

    public FilledArea getArea() {
        return this.area;
    }

    @Override
    public List<Movement> getMovements() {
        return this.movements;
    }

    @Override
    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public void setArea(FilledArea area) {
        this.area = area;
    }

    @Override
    public void setMovements(List<Movement> movements) {
        this.movements = movements;
    }

    @Override
    public boolean init() {
        return this.area.add(this);
    }

    @Override
    public Position move() {
        if (null != movements && !this.movements.isEmpty()) {
            movements.forEach(movement->move(movement));
        }
        return this.position;
    }

    @Override
    public Position move(int index) {
        if (null != movements && !this.movements.isEmpty() && this.movements.size() > index) {
            move(movements.get(index));
        }
        return this.position;
    }

    public Position move(Movement movement) {
        System.out.println("Move object at " + this + " toward " + movement);
        if (movement.isRotation()) {
            this.orientation = this.orientation.rotate(movement);
            return this.position;
        }

        Position nextPosition = this.position.getNextPosition(this.orientation, movement);
        if (this.area.move(nextPosition, this)) {
            System.out.println("Object is now at " + this);
        } else {
            System.out.println("Position " + nextPosition + " is not authorized on area " + area);
        }

        return position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AbstractMoveable that = (AbstractMoveable) o;

        if (!position.equals(that.position)) return false;
        if (orientation != that.orientation) return false;
        return area.equals(that.area);

    }

    @Override
    public int hashCode() {
        int result = position.hashCode();
        result = 31 * result + orientation.hashCode();
        result = 31 * result + area.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return this.position.toString() + " " + this.orientation.getCode();
    }
}
