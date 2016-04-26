package edu.neumont.jotaylor.csc360.util;

import edu.neumont.csc415.Point;

public class Cursor{
    private Point position;
    private Point home;

    public Cursor (int x, int y){
        setHome(new Point(x, y));
        position = home;
    }

    public Point getHome() {
        return home;
    }

    public void setHome(Point home) {
        this.home = home;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public void home(){
        position = home;
    }

    public void lineFeed(int lineHeight){
        position = new Point(home.getX(), position.getY() + lineHeight);
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(int x, int y) {
        position = new Point(x,y);
    }
}
