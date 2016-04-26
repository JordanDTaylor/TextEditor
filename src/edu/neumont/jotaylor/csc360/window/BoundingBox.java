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
    public final int getMinX() { return minX; }
    private int minX;

    /**
     * The y coordinate of the upper-left corner of this {@code BoundingBox}.
     *
     * @defaultValue 0.0
     */
    public final int getMinY() { return minY; }
    private int minY;
    /**
     * The width of this {@code BoundingBox}.
     *
     * @defaultValue 0.0
     */
    public final int getWidth() { return width; }
    private int width;
    /**
     * The height of this {@code BoundingBox}.
     *
     * @defaultValue 0.0
     */
    public final int getHeight() { return height; }
    private int height;
    /**
     * The x coordinate of the lower-right corner of this {@code BoundingBox}.
     *
     * @defaultValue {@code minX + width}
     */
    public final int getMaxX() { return maxX; }
    private int maxX;
    /**
     * The y coordinate of the lower-right corner of this {@code BoundingBox}.
     *
     * @defaultValue {@code minY + height}
     */
    public final int getMaxY() { return maxY; }
    private int maxY;

    public final Point getTopLeft() {
        return new Point(getMinX(),  getMinY());
    }

    public final Point getTopRight() {
        return new Point(getMaxX(), getMinY());
    }

    public final Point getBottomRight() {
        return new Point(getMaxX(), getMaxY());
    }

    public final Point getBottomLeft() {
        return new Point(getMinX(), getMaxY());
    }

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
    public boolean contains(Point p) {
        return p != null && contains(p.getX(), p.getY());
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
    public boolean contains(int x, int y) {
        return !isEmpty()
                && x >= getMinX()
                && x <= getMaxX()
                && y >= getMinY()
                && y <= getMaxY();
    }

    /**
     * Tests if the interior of this {@code BoundingBox} entirely contains the
     * specified Bounds, {@code b}.
     *
     * @param b The specified Bounds
     * @return true if the specified Bounds, {@code b}, is inside the
     * boundary of this {@code BoundingBox}; false otherwise.
     */
    public boolean contains(BoundingBox b) {
        return !((b == null) || b.isEmpty()) &&
                contains(b.getMinX(), b.getMinY(), b.getWidth(), b.getHeight());
    }

    /**
     * Tests if the interior of this {@code BoundingBox} entirely contains the
     * specified rectangular area.
     *
     * @param x the x coordinate of the upper-left corner of the specified
     * rectangular area
     * @param y the y coordinate of the upper-left corner of the specified
     * rectangular area
     * @param width the width of the specified rectangular area
     * @param height the height of the specified rectangular area
     * @return true if the interior of this {@code BoundingBox} entirely contains
     * the specified rectangular area; false otherwise.
     */
    public boolean contains(int x, int y, int width, int height){
        return contains(x, y) && contains(x + width, y + height);
    }

    /**
     * Tests if the interior of this {@code BoundingBox} intersects the interior
     * of a specified Bounds, {@code bBox}.
     *
     * @param bBox The specified Bounds
     * @return true if the interior of this {@code BoundingBox} and the interior
     * of the specified Bounds, {@code bBox}, intersect.
     */
    public boolean intersects(BoundingBox bBox) {
        return bBox != null && intersects(bBox.getMinX(), bBox.getMinY(), bBox.getWidth(), bBox.getHeight());
    }

    /**
     * Tests if the interior of this {@code BoundingBox} intersects the interior
     * of a specified rectangular area.
     *
     * @param x the x coordinate of the upper-left corner of the specified
     * rectangular area
     * @param y the y coordinate of the upper-left corner of the specified
     * rectangular area
     * @param width the width of the specified rectangular area
     * @param height the height of the specified rectangular area
     * @return true if the interior of this {@code BoundingBox} and the interior
     * of the rectangular area intersect.
     */
    public boolean intersects(int x, int y, int width, int height) {
        return !(isEmpty() || width < 0 || height < 0) &&
                (
                    x + width >= getMinX() &&
                    y + height >= getMinY() &&
                    x <= getMaxX() && y <= getMaxY()
                );
    }

    /**
     * Creates a new instance of {@code BoundingBox} class.
     * @param minX the X coordinate of the upper-left corner
     * @param minY the Y coordinate of the upper-left corner
     * @param width the width of the {@code BoundingBox}
     * @param height the height of the {@code BoundingBox}
     */
    public BoundingBox(int minX, int minY, int width, int height) {
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
            bits = 31L * bits + getMinX();
            bits = 31L * bits + getMinY();
            bits = 31L * bits + getWidth();
            bits = 31L * bits + getHeight();
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
