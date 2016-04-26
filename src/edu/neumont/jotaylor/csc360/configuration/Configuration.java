package edu.neumont.jotaylor.csc360.configuration;

import edu.neumont.csc415.DesktopColor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Configuration{
    private static final String DEFAULT_CONFIG_PATH = "default.config";

    Map<String, String> config;
    public static final String DEFAULT_PATH = "user.config";

    Configuration(Map<String, String> map){config = map;}

//    private static Configuration read() {
//        Configuration c = null;
//        try {
//            c = read(DEFAULT_PATH);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return c;
//    }
    /**
     * @param path the path to the configuration file;
     * @return a new instance of configuration built based on the file path
     * that was passed in.
     */
    public static Configuration read(String path ) throws IOException {
        System.out.println("Building configuration");
        Map<String, String> map = getMap(path);
        Configuration configuration;

        if(map == null){
            System.out.println("user.config not loaded, trying default.config");
            map = getMap(DEFAULT_CONFIG_PATH);
        }

        configuration = new Configuration(map);

        return configuration;
    }

    protected static Map<String, String> getMap(String path) throws IOException {

        Map<String, String> config = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            config = reader.lines()
                    .map(s -> s.toUpperCase()
                        .split(":"))
                        .collect(Collectors.toMap(strings -> strings[0].trim(), strings -> strings[1].trim()));
        }
        return config;
    }

    public <T> T getValueFor(Function<String, T> function, String key){

        T t = null;
        String stringValue="";

        if(config.containsKey(key))
            stringValue = config.get(key);

        if(!stringValue.isEmpty())
            t =  function.apply(stringValue);

        return t;
    }

    public KeyboardType getKeyboardType() {
        return getValueFor(KeyboardType::valueOf, Params.KEYBOARDTYPE.toString() );
    }

    public Integer getWindowHeight() {
        return getValueFor(Integer::parseInt, Params.WINDOWHEIGHT.toString());
    }

    public Integer getWindowWidth() {
        return getValueFor(Integer::parseInt, Params.WINDOWWIDTH.toString());
    }

    public Integer getTitleHeight() {
        return getValueFor(Integer::parseInt, Params.TITLEHEIGHT.toString());
    }

    public Integer getBorderWidth() {
        return getValueFor(Integer::parseInt, Params.BORDERWIDTH.toString());
    }

    public DesktopColor getWindowBackgroundColor() {
        return getValueFor(ColorMap::get, Params.WINDOWBACKGROUNDCOLOR.toString());
    }

    public DesktopColor getWindowForegroundColor() {
        return getValueFor(ColorMap::get, Params.WINDOWFOREGROUNDCOLOR.toString());
    }

    public DesktopColor getTitleBackgroundColor() {
        return getValueFor(ColorMap::get, Params.TITLEBACKGROUNDCOLOR.toString());
    }

    public DesktopColor getTitleForegroundColor() {
        return getValueFor(ColorMap::get, Params.TITLEFOREGROUNDCOLOR.toString());
    }

    public DesktopColor getBorderColor() {
        return getValueFor(ColorMap::get, Params.BORDERCOLOR.toString());
    }

    public WindowType getWindowType() {
        return getValueFor(WindowType::valueOf, Params.WINDOWTYPE.toString());
    }

    public String getWindowTitle() {
        return getValueFor(Params.TITLE.toString());
    }

    private String getValueFor(String s) {
        return config.get(s);
    }

    @Override
    public String toString() {
        String s = "";
        for(Map.Entry<String, String> e : config.entrySet())
        {
            s += e.getKey() + ":" + e.getValue() + ", ";
        }
        return s;
    }

    enum Params{
        KEYBOARDTYPE,
        WINDOWTYPE,
        WINDOWHEIGHT,
        WINDOWWIDTH,
        TITLEHEIGHT,
        BORDERWIDTH,
        WINDOWBACKGROUNDCOLOR,
        WINDOWFOREGROUNDCOLOR,
        TITLEBACKGROUNDCOLOR,
        TITLEFOREGROUNDCOLOR,
        TITLE,
        BORDERCOLOR
    }
}
