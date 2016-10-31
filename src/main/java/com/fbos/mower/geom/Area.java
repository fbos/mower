package com.fbos.mower.geom;

/**
 * 2D Area defined by a minimal and a maximal positions <br/>
 *
 * Created by fb on 30/10/2016.
 */
public class Area {

    private Position positionMin;

    private Position positionMax;

    public Area(double xMax, double yMax) {
        this.positionMin = Position.POSITION_ZERO;
        this.positionMax = new Position(xMax, yMax);
    }

    public Area(double xMin, double yMin, double xMax, double yMax) {
        this.positionMin = new Position(xMin, yMin);
        this.positionMax = new Position(xMax, yMax);
    }

    /**
     * @param position
     * @return true if position is included in the area
     */
    public boolean isAuthorized(Position position) {
        return position.isSuperiorOrEqual(this.positionMin) &&
               position.isInferiorOrEqual(this.positionMax);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Area area = (Area) o;

        if (!positionMin.equals(area.positionMin)) return false;
        return positionMax.equals(area.positionMax);

    }

    @Override
    public int hashCode() {
        int result = positionMin.hashCode();
        result = 31 * result + positionMax.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return positionMin + " , " + positionMax;
    }
}
