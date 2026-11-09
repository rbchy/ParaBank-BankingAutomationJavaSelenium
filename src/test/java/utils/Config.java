package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/** Reads config.properties; a -D system property with the same key wins. */
public final class Config {

    private static final Properties PROPS = new Properties();

    static {
        try (InputStream in = Config.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (in != null) {
                PROPS.load(in);
            }
        } catch (IOException e) {
            throw new IllegalStateException("Cannot read config.properties", e);
        }
    }

    private Config() {
    }

    public static String get(String key) {
        String sys = System.getProperty(key);
        if (sys != null && !sys.isBlank() && !sys.startsWith("${")) {
            return sys;
        }
        return PROPS.getProperty(key);
    }

    public static String baseUrl() {
        return get("baseUrl");
    }

    public static int timeoutSeconds() {
        return Integer.parseInt(get("timeoutSeconds"));
    }

    public static boolean headless() {
        return Boolean.parseBoolean(get("headless"));
    }
}
