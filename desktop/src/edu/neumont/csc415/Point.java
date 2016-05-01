package edu.neumont.csc415;

public class Point {
    private int x;
    private int y;

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int hashCode() {
        boolean prime = true;
        byte result = 1;
        int result1 = 31 * result + this.x;
        result1 = 31 * result1 + this.y;
        return result1;
    }

    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        } else if(obj == null) {
            return false;
        } else if(this.getClass() != obj.getClass()) {
            return false;
        } else {
            Point other = (Point)obj;
            return this.x != other.x?false:this.y == other.y;
        }
    }

    public String toString() {
        return "Point(" + this.x + "," + this.y + ")";
    }
}
