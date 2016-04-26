package edu.neumont.jotaylor.csc360.window;

/**
 * Created by jotaylor on 4/9/2016.
 */
public interface WindowFactory {
    IWindow makeWindow();
}

class BasicWindowFactory implements WindowFactory{
    @Override
    public IWindow makeWindow() {
        return null;
    }
}

class TitleBarWindowFactory implements WindowFactory{
    @Override
    public IWindow makeWindow() {
        return null;
    }
}

class BorderWindowFactory implements WindowFactory{
    @Override
    public IWindow makeWindow() {
        return null;
    }
}

class TitleBarBorderWindowFactory implements WindowFactory{

    @Override
    public IWindow makeWindow() {
        return null;
    }
}

class BorderTitleBarBorderWindowFactory implements WindowFactory{

    @Override
    public IWindow makeWindow() {
        return null;
    }
}
