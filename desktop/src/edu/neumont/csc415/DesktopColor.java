package edu.neumont.csc415;

import java.awt.Color;

public class DesktopColor {
    public static DesktopColor BLACK;
    public static DesktopColor WHITE;
    public static DesktopColor BLUE;
    public static DesktopColor CYAN;
    public static DesktopColor DARK_GRAY;
    public static DesktopColor GRAY;
    public static DesktopColor GREEN;
    public static DesktopColor LIGHT_GRAY;
    public static DesktopColor MAGENTA;
    public static DesktopColor ORANGE;
    public static DesktopColor PINK;
    public static DesktopColor RED;
    public static DesktopColor YELLOW;
    private Color color;

    static {
        BLACK = new DesktopColor(Color.BLACK);
        WHITE = new DesktopColor(Color.WHITE);
        BLUE = new DesktopColor(Color.BLUE);
        CYAN = new DesktopColor(Color.CYAN);
        DARK_GRAY = new DesktopColor(Color.DARK_GRAY);
        GRAY = new DesktopColor(Color.GRAY);
        GREEN = new DesktopColor(Color.GREEN);
        LIGHT_GRAY = new DesktopColor(Color.LIGHT_GRAY);
        MAGENTA = new DesktopColor(Color.MAGENTA);
        ORANGE = new DesktopColor(Color.ORANGE);
        PINK = new DesktopColor(Color.PINK);
        RED = new DesktopColor(Color.RED);
        YELLOW = new DesktopColor(Color.YELLOW);
    }

    private DesktopColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return this.color;
    }
}
