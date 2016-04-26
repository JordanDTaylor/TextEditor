package edu.neumont.jotaylor.csc360.window;

import edu.neumont.csc415.Desktop;
import edu.neumont.csc415.DesktopColor;
import edu.neumont.csc415.IPaintable;

/**
 * Created by jotaylor on 4/8/2016.
 */
public interface IWindow extends IPaintable {
    Desktop getDesktop();
    BoundingBox getBoundingBox();
    boolean decoratorsShouldRepaint();
    void setOrigin(int x, int y);

    String getTitle();
    void setTitle(String title);

    void setForegroundColor(DesktopColor foregroundColor);
    DesktopColor getForegroundColor();
    void setBackgroundColor(DesktopColor backgroundColor);
    DesktopColor getBackgroundColor();


}


