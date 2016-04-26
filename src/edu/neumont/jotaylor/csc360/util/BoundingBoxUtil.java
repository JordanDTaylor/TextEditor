package edu.neumont.jotaylor.csc360.util;

import edu.neumont.jotaylor.csc360.window.BoundingBox;

/**
 * Created by jotaylor on 4/9/2016.
 */
public final class BoundingBoxUtil {
    public static BoundingBox shiftLeft(BoundingBox box, int xDistance){
        return shift(box, -xDistance, 0);
    }

    public static BoundingBox shiftRight(BoundingBox box, int xDistance){
        return shift(box, xDistance, 0);
    }

    public static BoundingBox shiftUp(BoundingBox box, int yDistance){
        return shift(box, 0, -yDistance);
    }

    public static BoundingBox shiftDown(BoundingBox box, int yDistance){
        return shift(box, 0, yDistance);
    }

    public static BoundingBox shift(BoundingBox box, int xDistance, int yDistance){
        return new BoundingBox(box.getMinX() + xDistance, box.getMinY() + yDistance, box.getWidth(), box.getHeight());
    }

    public static BoundingBox expandDown(BoundingBox box, int yDistance){
        return new BoundingBox(box.getMinX(), box.getMinY(), box.getWidth(), box.getHeight() + yDistance);
    }

    public static BoundingBox expandRight(BoundingBox box, int xDistance){
        return new BoundingBox(box.getMinX(), box.getMinY(), box.getWidth() + xDistance, box.getHeight());
    }

    public static BoundingBox expandLeft(BoundingBox box, int xDistance){
        return new BoundingBox(box.getMinX() - xDistance, box.getMinY(), box.getWidth() + xDistance, box.getHeight());
    }

    public static BoundingBox expandUp(BoundingBox box, int yDistance){
        return new BoundingBox(box.getMinX(), box.getMinY() - yDistance, box.getWidth(), box.getHeight() + yDistance);
    }

    public static BoundingBox expandAll(BoundingBox box, int distance){
        return new BoundingBox(box.getMinX() - distance, box.getMinY() - distance, box.getWidth() + distance * 2, box.getHeight() + distance * 2);
    }

    public static BoundingBox shrinkUp(BoundingBox box, int yDistance){
        return new BoundingBox(box.getMinX(), box.getMinY(), box.getWidth(), box.getHeight() - yDistance);
    }

    public static BoundingBox shrinkDown(BoundingBox box, int yDistance){
        return new BoundingBox(box.getMinX(), box.getMinY() + yDistance, box.getWidth(), box.getHeight() - yDistance);
    }

    public static BoundingBox shrinkRight(BoundingBox box, int xDistance){
        return new BoundingBox(box.getMinX() + xDistance, box.getMinY(), box.getWidth() - xDistance, box.getHeight());
    }

    public static BoundingBox shrinkLeft(BoundingBox box, int xDistance){
        return new BoundingBox(box.getMinX(), box.getMinY(), box.getWidth() - xDistance, box.getHeight());
    }

    public static BoundingBox shrinkAll(BoundingBox box, int distance){
        return new BoundingBox(box.getMinX() + distance, box.getMinY() + distance, box.getWidth() - distance * 2, box.getHeight() - distance * 2);
    }

    private BoundingBoxUtil(){}
}
