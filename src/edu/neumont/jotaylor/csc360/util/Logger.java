package edu.neumont.jotaylor.csc360.util;

public class Logger{

    public static boolean enabled = false;
    public static void log(String type, String message){
        if(enabled){
        System.out.println(type + "\t" + message);
        }
    }
}
