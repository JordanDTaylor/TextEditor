package edu.neumont.jotaylor.csc360.util;

import edu.neumont.csc415.Point;

/**
 * Created by jotaylor on 4/8/2016.
 */
public final class Points {

    public static Point add(Point a, Point b){
        return new Point(a.getX() + b.getX(), a.getY() + b.getY());
    }

    public static Point addToPoint(Point point, int x, int y){
        return new Point(point.getX() + x, point.getY() + y);
    }
}
