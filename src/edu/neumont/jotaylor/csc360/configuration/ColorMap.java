package edu.neumont.jotaylor.csc360.configuration;

import edu.neumont.csc415.DesktopColor;

import java.util.HashMap;

/**
 * Created by jorda on 4/25/2016.
 */
public class ColorMap {
    private static HashMap<String, DesktopColor> colors = new HashMap<>();
    static {
        colors.put("BLACK", DesktopColor.BLACK);
        colors.put("WHITE", DesktopColor.WHITE);
        colors.put("BLUE", DesktopColor.BLUE);
        colors.put("CYAN", DesktopColor.CYAN);
        colors.put("DARK_GRAY", DesktopColor.DARK_GRAY);
        colors.put("DARKGRAY", DesktopColor.DARK_GRAY);
        colors.put("GRAY", DesktopColor.GRAY);
        colors.put("GREEN", DesktopColor.GREEN);
        colors.put("LIGHT_GRAY", DesktopColor.LIGHT_GRAY);
        colors.put("LIGHTGRAY", DesktopColor.LIGHT_GRAY);
        colors.put("MAGENTA", DesktopColor.MAGENTA);
        colors.put("ORANGE", DesktopColor.ORANGE);
        colors.put("PINK", DesktopColor.PINK);
        colors.put("RED", DesktopColor.RED);
        colors.put("YELLOW", DesktopColor.YELLOW);
    }

    public static DesktopColor get(String color){
        return colors.get(color.toUpperCase());
    }
}
