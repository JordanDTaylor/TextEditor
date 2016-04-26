package edu.neumont.jotaylor.csc360.window;

import edu.neumont.csc415.Point;

/**
 * Code based on javafx.geometry. BoundingBox, but uses desktop.Point, and only handles
 * 2 Dimensions.
 */
public class BoundingBox {
    private int hash = 0;

    /**
     * The x coordinate of the upper-left corner of this {@code BoundingBox}.
     *
     * @defaultValue 0.0
     */
    public final double getMinX() { return minX; }
    private double minX;

    /**
     * The y coordinate of the upper-left corner of this {@code BoundingBox}.
     *
     * @defaultValue 0.0
     */
    public final double getMinY() { return minY; }
    private double minY;
    /**
     * The width of this {@code BoundingBox}.
     *
     * @defaultValue 0.0
     */
    public final double getWidth() { return width; }
    private double width;
    /**
     * The height of this {@code BoundingBox}.
     *
     * @defaultValue 0.0
     */
    public final double getHeight() { return height; }
    private double height;
    /**
     * The x coordinate of the lower-right corner of this {@code BoundingBox}.
     *
     * @defaultValue {@code minX + width}
     */
    public final double getMaxX() { return maxX; }
    private double maxX;
    /**
     * The y coordinate of the lower-right corner of this {@code BoundingBox}.
     *
     * @defaultValue {@code minY + height}
     */
    public final double getMaxY() { return maxY; }
    private double maxY;

    /**
     * Indicates whether any of the dimensions(width or height) of this bounds
     * is less than zero.
     * @return true if any of the dimensions(width or height) of this bounds
     * is less than zero.
     */
    public boolean isEmpty() {
        return getMaxX() < getMinX() || getMaxY() < getMinY();
    }

    /**
     * Tests if the specified point is inside the boundary of {@code BoundingBox}.
     *
     * @param p the specified point to be tested
     * @return true if the specified point is inside the boundary of this
     * {@code BoundingBox}; false otherwise.
     */
    public boolean contains(Point p){
        if(p==null)return false;
        return contains(p.getX(), p.getY());
    }

    /**
     * Tests if the specified {@code (x, y)} coordinates are inside the boundary
     * of {@code BoundingBox}.
     *
     * @param x the specified x coordinate to be tested
     * @param y the specified y coordinate to be tested
     * @return true if the specified {@code (x, y)} coordinates are inside the
     * boundary of this {@code BoundingBox}; false otherwise.
     */
    public boolean contains(double x, double y){
        if (isEmpty()) return false;
        return x >= getMinX() && x <= getMaxX() && y >= getMinY() && y <= getMaxY();
    }

    /**
     * Tests if the interior of this {@code BoundingBox} entirely contains the
     * specified Bounds, {@code b}.
     *
     * @param b The specified Bounds
     * @return true if the specified Bounds, {@code b}, is inside the
     * boundary of this {@code BoundingBox}; false otherwise.
     */
    public boolean contains(BoundingBox b){
        if ((b == null) || b.isEmpty()) return false;
        return contains(b.getMinX(), b.getMinY(), b.getWidth(), b.getHeight());
    }

    /**
     * Tests if the interior of this {@code BoundingBox} entirely contains the
     * specified rectangular area.
     *
     * @param x the x coordinate of the upper-left corner of the specified
     * rectangular area
     * @param y the y coordinate of the upper-left corner of the specified
     * rectangular area
     * @param w the width of the specified rectangular area
     * @param h the height of the specified rectangular area
     * @return true if the interior of this {@code BoundingBox} entirely contains
     * the specified rectangular area; false otherwise.
     */
    public boolean contains(double x, double y, double w, double h){
        return contains(x, y) && contains(x + w, y + h);
    }

    /**
     * Tests if the interior of this {@code BoundingBox} intersects the interior
     * of a specified Bounds, {@code b}.
     *
     * @param b The specified Bounds
     * @return true if the interior of this {@code BoundingBox} and the interior
     * of the specified Bounds, {@code b}, intersect.
     */
    public boolean intersects(BoundingBox b){
        if(b==null)return false;
        return intersects(b.getMinX(), b.getMinY(), b.getWidth(), b.getHeight());
    }

    /**
     * Tests if the interior of this {@code BoundingBox} intersects the interior
     * of a specified rectangular area.
     *
     * @param x the x coordinate of the upper-left corner of the specified
     * rectangular area
     * @param y the y coordinate of the upper-left corner of the specified
     * rectangular area
     * @param w the width of the specified rectangular area
     * @param h the height of the specified rectangular area
     * @return true if the interior of this {@code BoundingBox} and the interior
     * of the rectangular area intersect.
     */
    public boolean intersects(double x, double y, double w, double h){
        if (isEmpty() || w < 0 || h < 0 ) return false;
        return (x + w >= getMinX() &&
                        y + h >= getMinY() &&
                        x <= getMaxX() &&
                        y <= getMaxY());
    }

    /**
     * Creates a new instance of {@code BoundingBox} class.
     * @param minX the X coordinate of the upper-left corner
     * @param minY the Y coordinate of the upper-left corner
     * @param width the width of the {@code BoundingBox}
     * @param height the height of the {@code BoundingBox}
     */
    protected BoundingBox(double minX, double minY, double width, double height) {
        this.minX = minX;
        this.minY = minY;
        this.width = width;
        this.height = height;
        this.maxX = minX + width;
        this.maxY = minY + height;
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * @param obj the reference object with which to compare
     * @return true if this object is the same as the obj argument; false otherwise
     */
    @Override public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj instanceof BoundingBox) {
            BoundingBox other = (BoundingBox) obj;
            return getMinX() == other.getMinX()
                           && getMinY() == other.getMinY()
                           && getWidth() == other.getWidth()
                           && getHeight() == other.getHeight();
        } else return false;
    }



    /**
     * Returns a hash code value for the object.
     * @return a hash code value for the object.
     */
    @Override public int hashCode() {
        if (hash == 0) {
            long bits = 7L;
            bits = 31L * bits + Double.doubleToLongBits(getMinX());
            bits = 31L * bits + Double.doubleToLongBits(getMinY());
            bits = 31L * bits + Double.doubleToLongBits(getWidth());
            bits = 31L * bits + Double.doubleToLongBits(getHeight());
            hash = (int) (bits ^ (bits >> 32));
        }
        return hash;
    }

    /**
     * Returns a string representation of this {@code BoundingBox}.
     * This method is intended to be used only for informational purposes.
     * The content and format of the returned string might getMary between
     * implementations.
     * The returned string might be empty but cannot be {@code null}.
     */
    @Override public String toString() {
        return "BoundingBox ["
                       + "minX:" + getMinX()
                       + ", minY:" + getMinY()
                       + ", width:" + getWidth()
                       + ", height:" + getHeight()
                       + ", maxX:" + getMaxX()
                       + ", maxY:" + getMaxY()
                       + "]";
    }
}
