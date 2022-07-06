package com.mycompany.java_4;

import java.util.Objects;

/**
 *
 * @author dieppv
 */
public final class Point {
    /**
     * coordinator x.
     */
    private int x;
    /**
     * coordinator y.
     */
    private int y;
    /**
     * distance to the target point.
     */
    private double dis;

    /**
     * constructor.
     * @param paramX
     * @param paramY
     * @param paramDis
     */
    public Point(final int paramX, final int paramY, final double paramDis) {
        this.x = paramX;
        this.y = paramY;
        this.dis = paramDis;
    }

    /**
     * get coordinator x.
     * @return x
     */
    public int getX() {
        return x;
    }

    /**
     * set coordinator x for point.
     * @param paramX
     */
    public void setX(final int paramX) {
        this.x = paramX;
    }

    /**
     * get coordinator y.
     * @return y
     */
    public int getY() {
        return y;
    }

    /**
     * set coordinator y for point.
     * @param paramY
     */
    public void setY(final int paramY) {
        this.y = paramY;
    }

    /**
     * get distance to the target point.
     * @return distance
     */
    public double getDis() {
        return dis;
    }

    /**
     * set distance to the target point.
     * @param paramDis
     */
    public void setDis(final double paramDis) {
        this.dis = paramDis;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || !(obj instanceof Point)) {
            return false;
        }

        Point point = (Point) obj;
        return point.getX() == this.x && point.getY() == this.y;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")\n";
    }
}
