package edu.neumont.jotaylor.csc360.window;

import edu.neumont.csc415.Desktop;
import edu.neumont.csc415.DesktopGraphics;

/**
 * Created by jotaylor on 4/8/2016.
 */
public abstract class AbstractWindowDecorator implements IWindow {
    protected IWindow innerWindow;
    protected int height;
    protected int width;
    protected Desktop desktop;
    protected boolean repaintFlag;

    public AbstractWindowDecorator(IWindow innerWindow){
        this.innerWindow = innerWindow;
        this.desktop = innerWindow.getDesktop();
        repaintFlag = true;
    }

    @Override
    public void paint(DesktopGraphics desktopGraphics) {
        innerWindow.paint(desktopGraphics);

        if(decoratorsShouldRepaint() || repaintFlag)
            doPaintStuff(desktopGraphics);

        repaintFlag = false;
    }

    @Override
    public String getTitle() {
        return innerWindow.getTitle();
    }

    @Override
    public Desktop getDesktop(){
        return desktop;
    }

    @Override
    public boolean decoratorsShouldRepaint() {
        return innerWindow.decoratorsShouldRepaint();
    }

    protected abstract void doPaintStuff(DesktopGraphics desktopGraphics);
}
