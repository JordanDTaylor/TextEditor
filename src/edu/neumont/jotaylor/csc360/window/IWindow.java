package edu.neumont.jotaylor.csc360.window;

import edu.neumont.csc415.Desktop;
import edu.neumont.csc415.IPaintable;
import edu.neumont.csc415.Point;

/**
 * Created by jotaylor on 4/8/2016.
 */
public interface IWindow extends IPaintable {
    String getTitle();
    Point getTopLeft();
    Point getBottomRight();
    int getHeight();
    int getWidth();
    Desktop getDesktop();
    boolean decoratorsShouldRepaint();
}


