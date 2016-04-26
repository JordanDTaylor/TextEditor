package edu.neumont.jotaylor.csc360.window.decorators;

import edu.neumont.csc415.Desktop;
import edu.neumont.csc415.DesktopColor;
import edu.neumont.csc415.DesktopGraphics;
import edu.neumont.jotaylor.csc360.util.Logger;
import edu.neumont.jotaylor.csc360.window.IWindow;

public abstract class AbstractWindowDecorator implements IWindow {
    protected IWindow innerWindow;
    protected Desktop desktop;
    protected boolean repaintFlag;

    public AbstractWindowDecorator(IWindow innerWindow){
        this.innerWindow = innerWindow;
        this.desktop = innerWindow.getDesktop();
        repaintFlag = true;
    }

    @Override
    public void paint(DesktopGraphics desktopGraphics) {
        Logger.log("AbsWindowDec", "Repaint Called");
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
    public void setTitle(String title){
        innerWindow.setTitle(title);
    }
    @Override
    public void setForegroundColor(DesktopColor foregroundColor) {
        innerWindow.setForegroundColor(foregroundColor);
    }

    @Override
    public final DesktopColor getForegroundColor() {
        return innerWindow.getForegroundColor();
    }

    @Override
    public void setBackgroundColor(DesktopColor backgroundColor) {
        innerWindow.setBackgroundColor(backgroundColor);
    }

    @Override
    public final DesktopColor getBackgroundColor() {
        return innerWindow.getBackgroundColor();
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
