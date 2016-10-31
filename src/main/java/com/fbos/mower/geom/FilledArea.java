package com.fbos.mower.geom;

import java.util.concurrent.ConcurrentHashMap;

/**
 * An area that not authorizes an object to be on same position that an other object. <br/>
 * Thead-safe. <br/>
 * Not used yet. </br>
 *
 * Created by fb on 30/10/2016.
 */
public class FilledArea extends Area {

    private ConcurrentHashMap<Position, Object> contents;

    public FilledArea(double xMax, double yMax) {
        super(xMax, yMax);
        contents = new ConcurrentHashMap();
    }

    public FilledArea(double xMin, double yMin, double xMax, double yMax) {
        super(xMin, yMin, xMax, yMax);
        contents = new ConcurrentHashMap();
    }

    public Object getContent(Position position) {
        return contents.get(position);
    }

    public void clear() {
        contents.clear();
    }

    @Override
    public boolean isAuthorized(Position position) {
        return super.isAuthorized(position) && !contents.containsKey(position);
    }

    /**
     * Add a new {@link Moveable} to the area.
     * @param moveable
     * @return true if add was authorized
     */
    protected boolean add(Moveable moveable) {
        return add(moveable.getPosition(), moveable);
    }

    /**
     * Add a new object to the area.
     * @param position
     * @param object
     * @return true if add was authorized
     */
    protected synchronized boolean add(final Position position, final Object object) {
        if (isAuthorized(position)) {
            contents.put(position, object);
            return true;
        }
        return false;
    }

    /**
     * Move a Moveable object on the area.
     *
     * @param newPosition new position to set.
     * @param moveable with old position
     * @return true if move was authorized
     */
    protected synchronized boolean move(final Position newPosition, final Moveable moveable) {
        if (isAuthorized(newPosition)) {
            // remove object from its former position
            contents.remove(moveable.getPosition());
            moveable.setPosition(newPosition);
            // put object fon its new position
            contents.put(newPosition, moveable);
            return true;
        }
        return false;
    }



}
