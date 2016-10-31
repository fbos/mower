package com.fbos.mower;

import com.fbos.mower.enums.Orientation;
import com.fbos.mower.geom.AbstractMoveable;
import com.fbos.mower.geom.Position;

/**
 * Mower aka "Tondeuse", {@link com.fbos.mower.geom.Moveable} object which can cut grass. <br/>
 *
 * Created by fb on 30/10/2016.
 */
public class Mower extends AbstractMoveable {

    public Mower(Position position, Orientation orientation) {
        super(position, orientation);
    }

    public void cutGrass() {
        throw new UnsupportedOperationException();
    }

}
