package edu.neumont.jotaylor.csc360.window;

import edu.neumont.csc415.Point;

public class Cursor{
    private Point position;
    private Point home = new Point(0,0);

    public Cursor() {
        position = home;
    }

    public Cursor(int homeX, int homeY) {
        setHome(new Point(homeX, homeY));
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
